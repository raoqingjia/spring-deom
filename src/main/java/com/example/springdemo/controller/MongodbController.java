package com.example.springdemo.controller;
import com.example.springdemo.dao.User;
import com.example.springdemo.service.impl.MongodbService;
import com.example.springdemo.utils.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping(path = "/mongodb")
@ResponseBody
public class MongodbController {

    @Autowired
    private MongodbService mongodbService;

    @RequestMapping(path = "/insert", method = RequestMethod.GET)
    public void insert(HttpServletRequest request, @RequestParam String type) {  //  @RequestParam定义的入参，如果前端没传，接口报400
        if ("1".equals(type)) {
            mongodbService.insert();
        }
        if ("2".equals(type)) {
            mongodbService.insertMore();
        }
    }

    @RequestMapping(path = "/remove", method = RequestMethod.GET)
    public void remove(@RequestParam String type, @RequestParam String id) {  //  @RequestParam定义的入参，如果前端没传，接口报400
        if ("1".equals(type)) {
            mongodbService.remove(id);
        }
        if ("2".equals(type)) {
            String[] ids = id.split(",");
            mongodbService.removeMore(ids);
        }
    }

//   http://localhost:8081/mongodb/find?type=1&id=&name=
    @RequestMapping(path = "/find", method = RequestMethod.GET)
    public BaseResult find(@RequestParam String type, @RequestParam String id, @RequestParam String name) {  //  @RequestParam定义的入参，如果前端没传，接口报400
        BaseResult result = new BaseResult();
        List<User> data = new ArrayList<>();
        if ("1".equals(type)) {
            String[] ids = id.split(",");
            data = mongodbService.findById(ids);
            result.setBizData(data);
        }
        if ("2".equals(type)) {
            data = mongodbService.findAll();
            result.setBizData(data);
        }
        if ("3".equals(type)) {
            data = mongodbService.findByName(name);
            result.setBizData(data);
        }
        result.setSuccess(data);
        System.out.println(result.toString());
        return result;
    }

    @RequestMapping(path = "/update", method = RequestMethod.GET)
    public BaseResult update(@RequestParam String id) {  //  @RequestParam定义的入参，如果前端没传，接口报400
        String[] ids = id.split(",");
        BaseResult result = mongodbService.update(ids);
        System.out.println(result.toString());
        return result;
    }

}
