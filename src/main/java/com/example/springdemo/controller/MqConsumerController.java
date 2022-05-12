package com.example.springdemo.controller;


import com.example.springdemo.service.impl.MongodbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqConsumerController {

    @Autowired
    private MongodbService mongodbService;
//  目前activemq已经下线了
//    @JmsListener(destination = "bboss.test.sendProducerMsg", containerFactory = "containerFactory")
    public void handler(String msg) {
        System.out.println("Consumer msg = " + msg);
        mongodbService.insert(msg);
    }

}
