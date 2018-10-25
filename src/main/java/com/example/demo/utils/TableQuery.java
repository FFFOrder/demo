package com.example.demo.utils;

import java.util.HashMap;
import java.util.Map;

public class TableQuery extends HashMap {
    public TableQuery(Map<String, Object> map){
        this.putAll(map);
        int limit = Integer.parseInt(map.get("limit").toString());
        int offset = (Integer.parseInt(map.get("page").toString()) - 1) * limit;
        this.put("offset", offset);
        this.put("limit", limit);
    }
}
