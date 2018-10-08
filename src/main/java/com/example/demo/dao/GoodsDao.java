package com.example.demo.dao;

import com.example.demo.domain.GoodsDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsDao {
    List<GoodsDO> getGoods(Map<String, Object> map);
}
