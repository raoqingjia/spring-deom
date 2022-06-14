package com.example.springdemo.service.impl;

import com.example.springdemo.bo.CountryItem;
import com.example.springdemo.dao.shparm.LocationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShaprmService {

    @Autowired
    public LocationMapper locationMapper;

    public List<CountryItem> selectCountry(){

        return locationMapper.selectCountryList();
    }

}
