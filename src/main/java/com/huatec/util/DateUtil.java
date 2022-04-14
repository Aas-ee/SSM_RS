package com.huatec.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    public static Date getDate() throws ParseException {
        //获取当前系统时间
        Date date = new Date();
        //设置特定的事件格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将时间转为指定格式并返回
        return sdf.parse(sdf.format(date));
    }
}