package com.example.springdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.feign.IFeignUrbac;
import com.example.springdemo.feign.IFeignProduct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    Logger log = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    private IFeignProduct productServiceF;
    @Autowired
    private IFeignUrbac feignUrbac;
    //      String details = feignUrbac.getStaffByRoleName("000","4");

    public JSONObject queryOfferList() {
        String details = productServiceF.offerDetails("50013");
        log.info("details=>{}",JSONObject.parseObject(details));
        return JSONObject.parseObject(details);
    }



}
