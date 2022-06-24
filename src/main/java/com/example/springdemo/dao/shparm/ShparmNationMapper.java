package com.example.springdemo.dao.shparm;

import com.example.springdemo.pojo.shparm.ShparmNation;

public interface ShparmNationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShparmNation record);

    int insertSelective(ShparmNation record);

    ShparmNation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShparmNation record);

    int updateByPrimaryKey(ShparmNation record);
}