package com.example.demo.dao;

import com.example.demo.domain.CartDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
@Mapper
public interface CartDao {
    List<CartDO> list(Map<String, Object> map);
    int add(CartDO cartDO);
    int delete(String id);
}
