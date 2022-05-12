package com.example.springdemo.controller;
import com.example.springdemo.service.impl.MqProducerService;
import com.example.springdemo.utils.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/activemq")
public class MqProducerController {
    @Autowired
    public MqProducerService mqProducerService;

    private Logger logger = LoggerFactory.getLogger(MqProducerController.class);

    // http://localhost:8081/activemq/sendProducerMsg?destinationName=bboss.test.sendProducerMsg&data=66666
    @RequestMapping(path = "/sendProducerMsg", method = RequestMethod.GET)
    public BaseResult sendProducerMsg(@RequestParam String  destinationName, @RequestParam  Object data){

        logger.info("/sendProducerMsg入参destinationName=>{},data=>{}",destinationName,data);
        return  mqProducerService.sendProducerMsg(destinationName, data);
    }
}
