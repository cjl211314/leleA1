package com.lele.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    //日期转字符串
    public static String date2String(Date date,String string){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(string);//yyyy-MM-dd
        String forman = simpleDateFormat.format(date);
        return forman;
    }
    //字符串转日期
    public static Date string2Date(String str,String trda){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(trda);
        try {
            Date date = simpleDateFormat.parse(str);
            return date;
        } catch (ParseException e) {
            throw new RuntimeException("日期格式转换异常"+e);
        }

    }
}
