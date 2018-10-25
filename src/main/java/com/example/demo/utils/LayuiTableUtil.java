package com.example.demo.utils;

import java.util.List;

/**
 * layui.table的数据类型
 */
public class LayuiTableUtil {
    public int code;    //标志
    public String msg;  //信息
    public int count;   //数据库的总条数
    public List<?> data;    //表格内容

    public LayuiTableUtil(List<?> data, int count){
        this.code = 0;
        this.msg = "";
        this.count = count;
        this.data = data;
    }
}
