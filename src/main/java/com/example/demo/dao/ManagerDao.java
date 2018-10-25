package com.example.demo.dao;

import com.example.demo.domain.ManagerDO;

public interface ManagerDao {
    //更加username查询管理员信息
    ManagerDO getByName(String username);
}
