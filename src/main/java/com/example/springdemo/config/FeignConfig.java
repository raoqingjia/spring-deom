package com.example.springdemo.config;

import feign.Request;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {


    /**
     * 配置请求重试
     *
     */
    @Bean
    public Retryer feignRetryer() {
        // period=100 发起当前请求的时间间隔，单位毫秒
        // maxPeriod=1000 发起当前请求的最大时间间隔，单位毫秒
        // maxAttempts=5 最多请求次数，包括第一次
        return new Retryer.Default(200, 2000, 10);
    }


    /**
     * 设置请求超时时间
     *默认
     * public Options() {
     * this(10 * 1000, 60 * 1000);
     * }
     *
     */
    @Bean
    Request.Options feignOptions() {

        return new Request.Options(60 * 1000, 60 * 1000);
    }



    /**
     * 打印请求日志
     * @return
     */
    @Bean
    public feign.Logger.Level multipartLoggerLevel() {
        return feign.Logger.Level.FULL;
    }

}
