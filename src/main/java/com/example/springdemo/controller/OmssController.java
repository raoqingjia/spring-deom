package com.example.springdemo.controller;

import com.example.springdemo.service.impl.OmssService;
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

    // http://localhost:8081/omss/selectDutyUser?userName=
    @GetMapping("/selectDutyUser")
    public List<Map> selectDutyUser(@RequestParam String userName){
        return omssService.selectDutyUser(userName);
    }
}
