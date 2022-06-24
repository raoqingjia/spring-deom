package com.example.springdemo.controller;

import com.example.springdemo.service.impl.OmssService;
import com.example.springdemo.utils.BaseResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
        // 这里如果返回具体的类，后台会报错
//        No converter for XXX with preset Content-Type ‘application/vnd.ms-excel；charset=utf-8‘
        //  https://blog.csdn.net/qq_42651201/article/details/120710224
    }
}
