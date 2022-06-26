package com.example.springdemo.controller;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.service.impl.RedisService;
import com.example.springdemo.utils.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/redis")
public class RedisController {

    private Logger logger = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisService redisService;

    // http://localhost:8081/redis/setDate?key=bboss.redis.test&value=66666&time=1800
    @RequestMapping(path = "/setDate", method = RequestMethod.GET)
    public BaseResult setDate(@RequestParam String  key, @RequestParam  String value,String time){
        logger.info("/redis/setDate 入参 key=>{},value=>{},time=>{}",key,value,time);
        BaseResult result = redisService.setData(key,value,time);
        logger.info("/redis/setDate 结果=>{}", JSON.toJSONString(result));
        return result;
    }


    // http://localhost:8081/redis/getDate?key=bboss.redis.test
    @RequestMapping(path = "/getDate", method = RequestMethod.GET)
    public BaseResult getDate(@RequestParam String  key){
        logger.info("/redis/getDate 入参 key=>{}",key);
        BaseResult result =  redisService.getData(key);
        logger.info("/redis/getDate 结果=>{}", JSON.toJSONString(result));
        return result;
    }


    // http://localhost:8081/redis/setStringRedisData?key=bboss.redis.string.test&value=66666&time=1800
    @RequestMapping(path = "/setStringRedisData", method = RequestMethod.GET)
    public BaseResult setStringRedisData(@RequestParam String  key, @RequestParam  String value, String time){
        logger.info("/redis/setStringRedisData 入参 key=>{},value=>{},time=>{}",key,value,time);
        BaseResult result = redisService.setStringRedisData(key,value,time);
        logger.info("/redis/setStringRedisData 结果=>{}", JSON.toJSONString(result));
        return result;
    }

    // http://localhost:8081/redis/getStringRedisData?key=bboss.redis.string.test
    @RequestMapping(path = "/getStringRedisData", method = RequestMethod.GET)
    public BaseResult getStringRedisData(@RequestParam String  key){
        logger.info("/redis/getStringRedisData 入参 key=>{}",key);
        BaseResult result =  redisService.getStringRedisData(key);
        logger.info("/redis/getStringRedisData 结果=>{}", JSON.toJSONString(result));
        return result;
    }


//http://localhost:8081/redis/setJsonString
//{
//    "key":"bboss.redis.JsonString",
//        "value": {
//            "id":"101",
//            "name":"JsonString",
//            "data":"bboss.redis.JsonString",
//            "address":"beijing",
//            "gender": "man"
//       },
//      "time":""
//   }
//  https://www.codenong.com/cs106123676/
    @RequestMapping(path = "/setJsonString", method = RequestMethod.POST)
    public BaseResult setJsonString( @RequestBody String params){
        logger.info("/redis/setJsonString 入参 params=>{}",params);
        JSONObject jsonObject = JSONObject.parseObject(params);
        logger.info("/redis/setJsonString 入参 jsonObject=>{}",jsonObject);
        BaseResult result =  redisService.setJsonString(jsonObject.getString("key"),jsonObject.getString("value"),jsonObject.getString("time"));
        logger.info("/redis/setJsonString 结果=>{}", JSON.toJSONString(result));
        return result;
    }

    // http://localhost:8081/redis/getJsonString?key=bboss.redis.JsonString
    @RequestMapping(path = "/getJsonString", method = RequestMethod.GET)
    public BaseResult getJsonString(@RequestParam String  key){
        logger.info("/redis/getJsonString 入参 key=>{}",key);
        BaseResult result =  redisService.getJsonString(key);
        logger.info("/redis/getJsonString 结果=>{}", JSON.toJSONString(result));
        return result;
    }

}
