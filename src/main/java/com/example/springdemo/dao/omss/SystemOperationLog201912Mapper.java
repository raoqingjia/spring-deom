package com.example.springdemo.dao.omss;

import com.example.springdemo.pojo.omss.SystemOperationLog201912;

import java.util.List;

public interface SystemOperationLog201912Mapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemOperationLog201912 record);

    int insertSelective(SystemOperationLog201912 record);

    SystemOperationLog201912 selectByPrimaryKey(Long id);

    List<SystemOperationLog201912> selectSystemOperationLogAll();

    int updateByPrimaryKeySelective(SystemOperationLog201912 record);

    int updateByPrimaryKey(SystemOperationLog201912 record);
}