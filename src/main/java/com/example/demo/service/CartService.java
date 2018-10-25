package com.example.demo.service;

import com.example.demo.domain.CartDO;

import java.util.List;
import java.util.Map;

public interface CartService {
    //查询购物车
    List<CartDO> list(Map<String, Object> map);
    //添加购物车
    int add(CartDO cartDO);
    //删除购物车
    int delete(String id);
    //添加物品数量
    int addNumber(CartDO cartDO);
    //通过id查询购物车中的商品
    CartDO getById(String id);
    //删除购物车中所有条目
    int delAll(String username);
    //删除购物车条目
    int del(String id);
}
