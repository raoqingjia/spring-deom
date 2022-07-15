package com.example.springdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.pojo.shparm.ShparmNation;
import com.example.springdemo.service.impl.ProductService;
import com.example.springdemo.service.impl.ShaprmService;
import com.example.springdemo.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    public ProductService productService;

    // http://localhost:8182/product/queryOfferList?companyNum=100
    @GetMapping("/queryOfferList")
    public JSONObject queryOfferList(){
        return productService.queryOfferList();
    }


}
