package com.example.springdemo.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.springdemo.dao.User;
import com.example.springdemo.utils.BaseResult;
import com.example.springdemo.utils.RedisUtil;
import com.example.springdemo.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import reactor.core.Fuseable;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    Logger log = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    @Qualifier("StringRedisTemplateSentinel")
    private StringRedisTemplate stringRedisTemplateSentinel;

    public BaseResult setData(String key, String value, String time) {
        BaseResult result = new BaseResult();
        log.info(redisUtil.getInstance().toString());
        try {
            if (StringUtils.isNotBlank(time)) {
                long t = Long.parseLong(time);
                System.out.println(t);
                redisUtil.setForTimeMS(key, value, t);
            } else {
                redisUtil.set(key, value);
            }
            result.setSuccess("数据更新成功");
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    public BaseResult setStringRedisData(String key, String value, String time) {
        BaseResult result = new BaseResult();
        try {
            if (StringUtils.isNotBlank(time)) {
                long t = Long.parseLong(time);
                System.out.println(t);
                stringRedisTemplateSentinel.opsForValue().set(key, value, t , TimeUnit.SECONDS);  // 设置过期时间
            } else {
                stringRedisTemplateSentinel.opsForValue().set(key, value);
            }
            result.setSuccess("数据更新成功");
        } catch (Exception e) {
            result.setError(e.getMessage());
        }

        return result;
    }

    public BaseResult getData(String key) {
        BaseResult result = new BaseResult();
        try {
            String value = redisUtil.get(key);
            result.setSuccess(value);
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    public BaseResult getStringRedisData(String key) {
        BaseResult result = new BaseResult();
        try {
            String value = stringRedisTemplateSentinel.opsForValue().get(key);
            result.setSuccess(value);
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    public BaseResult setJsonString(String key, String value, String time) {
        BaseResult result = new BaseResult();
        try {
            if (StringUtils.isNotBlank(time)) {
                long t = Long.parseLong(time);
                redisUtil.setForTimeMS(key, value, t);
            } else {
                redisUtil.set(key,  value);
            }
            result.setSuccess("数据更新成功");
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    public BaseResult getJsonString(String key) {
        BaseResult result = new BaseResult();
        try {
            String value = stringRedisTemplateSentinel.opsForValue().get(key);
            log.info("getJsonString value 结果=>{}", value);
            String parse = (String) JSON.parse(value);
            result.setSuccess(JSONObject.parseObject(parse, User.class));
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

}
