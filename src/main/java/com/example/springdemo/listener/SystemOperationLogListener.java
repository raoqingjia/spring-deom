package com.example.springdemo.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.example.springdemo.pojo.omss.SystemOperationLog;
import com.example.springdemo.service.impl.OmssService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class SystemOperationLogListener extends AnalysisEventListener<SystemOperationLog> {

    // 每隔500条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
    private static final int BATCH_COUNT = 500;
    List <SystemOperationLog> list = new ArrayList<>();

    // 由于 SystemOperationLogListener 不能交给Spring管理 所以我们只能手动传入 omssService
    public OmssService omssService;

    public SystemOperationLogListener(){ }
    public SystemOperationLogListener(OmssService omssService){
       this.omssService = omssService;
    }

    @Override
    public void invoke(SystemOperationLog systemOperationLog, AnalysisContext context) {
        log.info("解析到一条数据:{}", JSON.toJSONString(systemOperationLog));
        SystemOperationLog logItem = new SystemOperationLog();
        logItem.setCreateBy(systemOperationLog.getCreateBy());
        logItem.setCreateDate(systemOperationLog.getCreateDate());
        logItem.setLogType(systemOperationLog.getLogType());
        logItem.setOperationInfo(systemOperationLog.getOperationInfo());
        logItem.setOperationResult(systemOperationLog.getOperationState());
        logItem.setOperationState(systemOperationLog.getOperationState());
        logItem.setOperationType(systemOperationLog.getOperationType());
        logItem.setRemark(systemOperationLog.getRemark());
        logItem.setState(systemOperationLog.getState());
        logItem.setUpdateBy(systemOperationLog.getUpdateBy());
        logItem.setUpdateDate(systemOperationLog.getUpdateDate());
        list.add(logItem);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            try {
                saveData();
            } catch (IOException e) {
                log.error("保存失败{}",e.getMessage());
            }
            // 存储完成清理 list
            list.clear();
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        try {
            saveData();
        } catch (IOException e) {
            log.error("保存失败{}",e.getMessage());
        }
        log.info("所有数据解析完成！");
    }
    // 加上存储数据库
    private void saveData() throws IOException {
        log.info("{}条数据，开始存储数据库！", list.size());
        list.remove(0);
        omssService.systemOperationLogUpload(list);
        log.info("存储数据库成功！");
    }
}
