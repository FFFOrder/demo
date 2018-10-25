package com.example.demo.utils;

/**
 * 时间格式转换
 */
public class TimeFormat {
    /**
     * 去除字符串结尾的'.0'
     * @param time
     * @return
     */
    public static String timeFormat(String time){
        if(time != null && time.equals("") != true)
        {
            return time.substring(0, time.indexOf("."));
        } else {
            return "";
        }
    }
}
