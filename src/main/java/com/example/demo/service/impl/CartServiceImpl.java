package com.example.demo.service.impl;

import com.example.demo.dao.CartDao;
import com.example.demo.domain.CartDO;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartDao cartDao;

    @Override
    public List<CartDO> list(Map<String, Object> map){
        return cartDao.list(map);
    }
    @Override
    public int add(CartDO cartDO){
        return cartDao.add(cartDO);
    }
    @Override
    public int delete(String  id) {
        cartDao.delete(id);
        return 0;
    }
}
