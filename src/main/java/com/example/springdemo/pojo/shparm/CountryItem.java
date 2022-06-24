package com.example.springdemo.pojo.shparm;

import lombok.Data;

@Data
public class CountryItem {
    private String countryId;
    private String countryName;
    private String countryNumber;
    private String cityId;
    private String cityName;
    private String cityNumber;
    private String createTime;
}
