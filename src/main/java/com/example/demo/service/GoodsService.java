package com.example.demo.service;

import com.example.demo.domain.GoodsDO;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<GoodsDO> getGoods(Map<String, Object> map);
}
