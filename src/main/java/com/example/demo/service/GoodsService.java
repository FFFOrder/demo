package com.example.demo.service;

import com.example.demo.domain.GoodsDO;
import com.example.demo.utils.TableQuery;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    //查询商品
    List<GoodsDO> list(Map<String, Object> map);
    //统计条数
    int count(Map<String, Object> map);
    //更新商品信息
    int save(GoodsDO goodsDO);
    //添加商品
    int add(GoodsDO goodsDO);
    //修改商品库存
    void addNumber(String id, int number);
    //修改销量
    int sold(GoodsDO goodsDO);
}
