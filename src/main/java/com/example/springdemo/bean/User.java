package com.example.springdemo.bean;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    public User(String id, String name, String data, String gender, String address) {
        this.id = id;
        this.name = name;
        this.data = data;
        this.gender = gender;
        this.address = address;
    }
    private String id;
    private String name;
    private String data;
    private String gender;
    private String address;
}
