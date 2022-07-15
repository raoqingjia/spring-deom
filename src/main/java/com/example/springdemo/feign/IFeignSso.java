package com.example.springdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//@FeignClient(name = "abs-svc-sso")
@FeignClient(name = "abs-svc-sso")
public interface IFeignSso {

    @RequestMapping(value="/api/v2/auth/sendSMS",consumes="application/json",method = RequestMethod.POST)
    public String sendSms(@RequestBody String smsBody);
}
