package com.example.springdemo.dao.shparm;

import com.example.springdemo.pojo.shparm.CountryItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LocationMapper {
    List<CountryItem> selectCountryList(@Param("countryName") String countryName);
}
