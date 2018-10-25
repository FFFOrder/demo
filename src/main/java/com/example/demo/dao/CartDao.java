package com.example.demo.dao;

import com.example.demo.domain.CartDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface CartDao {
    //查询购物车
    List<CartDO> list(Map<String, Object> map);
    //添加购物车
    int add(CartDO cartDO);
    //删除购物车
    int delete(String id);
    //增加商品数量
    int addNumber(CartDO cartDO);
    //根据id查询购物车信息
    CartDO getById(String id);
    //删除用户购物车的全部商品
    int delAll(String username);
}
