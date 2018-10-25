package com.example.demo.dao;

import com.example.demo.domain.GoodsDO;
import com.example.demo.utils.TableQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsDao {
    //查询商品
    List<GoodsDO> list(Map<String, Object> map);
    //统计条数
    int count(Map<String, Object> map);
    //更新商品信息
    int save(GoodsDO goodsDO);
    //添加商品
    int add(GoodsDO goodsDO);
    //修改库存
    void addNumber(Map<String, Object> map);
    //添加销量
    int sold(GoodsDO goodsDO);
}
