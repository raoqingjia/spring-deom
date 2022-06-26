package com.example.springdemo.query;

import com.example.springdemo.pojo.mogo.User;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RedisUserParams {
    public RedisUserParams(String key, User value, String time) {
        this.key = key;
        this.value = value;
        this.time = time;
    }
    String key;
    User value;
    String time;
}
