package com.example.springdemo.controller;

import com.example.springdemo.bo.CountryItem;
import com.example.springdemo.service.impl.ShaprmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shaprm")
public class ShaprmController {

    @Autowired
    public ShaprmService shaprmService;

    // http://localhost:8081/shaprm/selectCountry
    @GetMapping("/selectCountry")
    public List<CountryItem> selectCountry(){

        return shaprmService.selectCountry();
    }
}
