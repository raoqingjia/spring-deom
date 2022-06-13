package com.example.springdemo.dao.shaprm;

import com.example.springdemo.bo.CountryItem;
import org.springframework.stereotype.Component;

import java.util.List;

public interface LocationMapper {
    List<CountryItem> selectCountryList();
}
