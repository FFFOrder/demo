package com.example.demo.service;

import com.example.demo.domain.CartDO;

import java.util.List;
import java.util.Map;

public interface CartService {
    List<CartDO> list(Map<String, Object> map);
    int add(CartDO cartDO);
    int delete(String id);
}
