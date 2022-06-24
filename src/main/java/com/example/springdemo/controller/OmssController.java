package com.example.springdemo.controller;

import com.example.springdemo.service.impl.OmssService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
