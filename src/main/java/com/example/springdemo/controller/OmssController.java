package com.example.springdemo.controller;

import com.alibaba.excel.EasyExcel;
import com.example.springdemo.listener.SystemOperationLogListener;
import com.example.springdemo.pojo.omss.SystemOperationLog;
import com.example.springdemo.service.impl.OmssService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/omss")
public class OmssController {

    @Autowired
    public OmssService omssService;

    // http://localhost:8081/omss/selectDutyUser?userName=&pageSize=10&pageNum=1
    @GetMapping("/selectDutyUser")
    public PageInfo selectDutyUser(@RequestParam String userName , @RequestParam String pageNum ,@RequestParam String pageSize ){
        return omssService.selectDutyUser(userName,pageNum,pageSize);
    }

    // http://localhost:8081/omss/systemOperationLogDownload
    @GetMapping("/systemOperationLogDownload")
    public void systemOperationLogDownload(HttpServletResponse response){
        omssService.systemOperationLogDownload(response);
        // 这里如果返回具体的类，后台会报错 No converter for XXX with preset Content-Type ‘application/vnd.ms-excel；charset=utf-8‘,所以什么都不返回
    }

    // http://localhost:8081/omss/systemOperationLogUpload       systemOperationLogUpload multipartFile =>
    @PostMapping("/systemOperationLogUpload")
    public String systemOperationLogUpload(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        log.info("systemOperationLogUpload multipartFile =>",multipartFile);
        EasyExcel.read(multipartFile.getInputStream(), SystemOperationLog.class, new SystemOperationLogListener(omssService)).sheet().doRead();
        return "上传成功";
    }
}
