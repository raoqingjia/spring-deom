package com.example.springdemo.feign;

import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

@FeignClient(name = "ABS-SVC-PRODUCT",url = "10.248.50.225:8990")
public interface IFeignProduct {
    @RequestMapping(value="/api/v1/pcOffer/queryOfferList",method = RequestMethod.POST,consumes = "application/json;charset=UTF-8")
    public JSONObject queryOfferList(HttpServletResponse response);

    @RequestMapping(value="/api/v1/pcOffer/offerDetails",method = RequestMethod.GET)
    public String offerDetails(@RequestParam("offerNum") String offerNum);
}
