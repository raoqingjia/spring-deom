package com.example.springdemo.service.impl;

import com.example.springdemo.dao.omss.DutyMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OmssService {

    @Autowired
    public DutyMapper dutyMapper;

    public PageInfo selectDutyUser(String userName ,String pageNum , String pageSize ){

        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        List<Map> dutyUser = dutyMapper.selectDutyUser(userName);
        // 分页工具分页
        PageInfo pageInfo = new PageInfo(dutyUser);

        return pageInfo;
    }

}
