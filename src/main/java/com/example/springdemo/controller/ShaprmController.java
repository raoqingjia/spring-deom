package com.example.springdemo.controller;

import com.example.springdemo.pojo.shparm.ShparmNation;
import com.example.springdemo.service.impl.ShaprmService;
import com.example.springdemo.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shaprm")
public class ShaprmController {

    @Autowired
    public ShaprmService shaprmService;

    // http://localhost:8081/shaprm/selectCountry?countryName=&pageNum=1&pageSize=10
    @GetMapping("/selectCountry")
    public BaseResult selectCountry(@RequestParam String countryName , @RequestParam String pageSize ,@RequestParam String pageNum){
        return shaprmService.selectCountry(countryName,pageNum,pageSize);
    }

    // http://localhost:8081/shaprm/selectByPrimaryKey?id=1
    @GetMapping("/selectByPrimaryKey")
    public ShparmNation selectByPrimaryKey(@RequestParam Integer id){
        return shaprmService.selectByPrimaryKey(id);
    }

}
