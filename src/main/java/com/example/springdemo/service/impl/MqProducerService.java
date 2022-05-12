package com.example.springdemo.service.impl;

import com.example.springdemo.utils.BaseResult;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Destination;

@Service
public class MqProducerService {

    @Resource(name = "jmsTemplate")
    public JmsTemplate jmsTemplate;


    public BaseResult sendProducerMsg(String  destinationName, Object data){
        BaseResult result = new BaseResult();
        try {
            Destination destination = new ActiveMQQueue(destinationName); // bboss.test.sendProducerMsg
            jmsTemplate.convertAndSend(destination,data);
            result.setError("成功");
        }catch (Exception e){
            System.out.println(e.getMessage());
            result.setError("失败");
        }
        return result;
    }
}
