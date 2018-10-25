package com.example.demo.utils;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class PageUtil implements Serializable {
    public int page;        //当前页
    public int limit;       //页面容量
    public int pageCount;      //总页数
    public int count;          //总条数
    public List<?> list;

    public PageUtil(List<?> list, int count, int page, int limit){
        this.list = list;
        this.count = count;
        this.page = page;
        this.limit = limit;
        this.pageCount = count / limit + 1;
    }
}
