package com.example.springdemo.dao.omss;

import com.example.springdemo.pojo.omss.SystemOperationLog;

import java.util.List;

public interface SystemOperationLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemOperationLog record);

    int insertSelective(SystemOperationLog record);

    SystemOperationLog selectByPrimaryKey(Long id);

    List<SystemOperationLog> selectSystemOperationLogAll();

    int systemOperationLogUpload(List<SystemOperationLog> record);


}