package com.example.demo.service.impl;

import com.example.demo.dao.GoodsDao;
import com.example.demo.domain.GoodsDO;
import com.example.demo.service.GoodsService;
import com.example.demo.utils.TableQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsDO> list(Map<String, Object> map){
        return goodsDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return goodsDao.count(map);
    }

    @Override
    public int save(GoodsDO goodsDO){
        return goodsDao.save(goodsDO);
    }

    @Override
    public int add(GoodsDO goodsDO){
        return goodsDao.add(goodsDO);
    }

    @Override
    public void addNumber(String id, int num){
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("number", num);
        goodsDao.addNumber(map);
    }

    @Override
    public int sold(GoodsDO goodsDO) {
        return goodsDao.sold(goodsDO);
    }
}
