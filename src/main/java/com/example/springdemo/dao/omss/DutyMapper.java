package com.example.springdemo.dao.omss;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface DutyMapper {
    List<Map> selectDutyUser(@Param("userName") String userName);
}
