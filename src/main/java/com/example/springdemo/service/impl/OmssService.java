package com.example.springdemo.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.example.springdemo.dao.omss.DutyMapper;
import com.example.springdemo.dao.omss.SystemOperationLogMapper;
import com.example.springdemo.pojo.omss.SystemOperationLog;
import com.example.springdemo.utils.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@Service
public class OmssService {

    @Autowired
    public DutyMapper dutyMapper;

    @Autowired
    public SystemOperationLogMapper SystemOperationLogMapper;

    public PageInfo selectDutyUser(String userName ,String pageNum , String pageSize ){

        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Map> dutyUser = dutyMapper.selectDutyUser(userName);
        // 分页工具分页
        PageInfo pageInfo = new PageInfo(dutyUser);

        return pageInfo;
    }

    public BaseResult systemOperationLogDownload(HttpServletResponse response){
     List<SystemOperationLog>  logList = SystemOperationLogMapper.selectSystemOperationLogAll();
        BaseResult result = new BaseResult();
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("测试"+System.currentTimeMillis(), "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), SystemOperationLog.class)  //  指定写用哪个class去写
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())// 表格宽度自适应
                    .sheet("模板")  // excel中sheet页名称
                    .doWrite(logList);
            result.setSuccess("下载成功");
        }catch (Exception e){
            result.setError("下载失败");
        }
        return  result;
    }
//
    public String  systemOperationLogUpload(List<SystemOperationLog> list) throws IOException {
        SystemOperationLogMapper.systemOperationLogUpload(list);
        return "上传成功";
    }
}
