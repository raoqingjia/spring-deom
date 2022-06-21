package com.example.springdemo.service.impl;

import com.example.springdemo.dao.omss.DutyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OmssService {

    @Autowired
    public DutyMapper dutyMapper;

    public List<Map> selectDutyUser(String userName){

        return dutyMapper.selectDutyUser(userName);
    }

}
