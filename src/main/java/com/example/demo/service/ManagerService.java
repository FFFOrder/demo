package com.example.demo.service;

import com.example.demo.domain.ManagerDO;

public interface ManagerService {
    //查询管理员信息
    ManagerDO getByName(String username);
}
