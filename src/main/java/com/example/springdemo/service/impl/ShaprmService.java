package com.example.springdemo.service.impl;

import com.example.springdemo.pojo.shparm.CountryItem;
import com.example.springdemo.dao.shparm.LocationMapper;
import com.example.springdemo.dao.shparm.ShparmNationMapper;
import com.example.springdemo.pojo.shparm.ShparmNation;
import com.example.springdemo.utils.BaseResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShaprmService {

    @Autowired
    public LocationMapper locationMapper;

    @Autowired
    public ShparmNationMapper shparmNationMapper;



    public BaseResult selectCountry(String countryName ,String pageNum , String pageSize ){
        BaseResult result = new BaseResult();
        try {
            PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
            List<CountryItem> countryList = locationMapper.selectCountryList(countryName);
            // 分页工具分页
            PageInfo pageInfo = new PageInfo(countryList);
            result.setSuccess(pageInfo);
        } catch (Exception e) {
            result.setError(e.getMessage());
        }
        return result;
    }

    public ShparmNation selectByPrimaryKey(Integer id){
        return shparmNationMapper.selectByPrimaryKey(id);
    }

}
