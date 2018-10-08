package com.example.demo.service.impl;

import com.example.demo.dao.GoodsDao;
import com.example.demo.domain.GoodsDO;
import com.example.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    GoodsDao goodsDao;

    @Override
    public List<GoodsDO> getGoods(Map<String, Object> map){
        return goodsDao.getGoods(map);
    }
}
