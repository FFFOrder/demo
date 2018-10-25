package com.example.demo.service.impl;

import com.example.demo.dao.BuyDao;
import com.example.demo.domain.BuyDO;
import com.example.demo.service.BuyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BuyServiceImpl implements BuyService {
    @Autowired
    BuyDao buyDao;
    @Override
    public int add(BuyDO buyDO){
        return buyDao.add(buyDO);
    }
    @Override
    public List<BuyDO> list(Map<String, Object> map){
        return buyDao.list(map);
    }
}
