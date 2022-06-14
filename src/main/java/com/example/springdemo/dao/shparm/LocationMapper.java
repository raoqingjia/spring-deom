package com.example.springdemo.dao.shparm;

import com.example.springdemo.bo.CountryItem;

import java.util.List;

public interface LocationMapper {
    List<CountryItem> selectCountryList();
}
