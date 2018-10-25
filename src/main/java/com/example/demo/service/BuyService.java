package com.example.demo.service;

import com.example.demo.domain.BuyDO;

import java.util.List;
import java.util.Map;

public interface BuyService {
    //添加购物记录
    int add(BuyDO buyDO);
    //查询购物记录
    List<BuyDO> list(Map<String, Object> map);
}
