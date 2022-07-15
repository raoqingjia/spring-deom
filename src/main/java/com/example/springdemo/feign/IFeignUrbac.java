package com.example.springdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("ABS-SVC-URBAC")
public interface IFeignUrbac {
    @RequestMapping(value="/api/v2/perm/searchSystemEnum",consumes="application/json",method = RequestMethod.POST)
    public String searchSystemEnum(@RequestParam("pageCode") String pageCode);

    @RequestMapping(value = "/api/v2/perm/getStaffByRoleName", method = RequestMethod.GET)
    public String getStaffByRoleName(@RequestParam("companyNumber") String companyNumber,
                                     @RequestParam("roleName") String roleName);
}
