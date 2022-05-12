package com.example.springdemo.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {
    // 获取当前时间日期
    public static String getCurrentDate(String type){
        String format = "";
        if("1".equals(type)){
             format = "yyyy-MM-dd HH:mm:ss";
        }
        if("2".equals(type)){
            format = "yyyyMMddHHmmss";
        }
        DateFormat df = new SimpleDateFormat(format);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());
    }

}
