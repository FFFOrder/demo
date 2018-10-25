package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.UserDO;

import java.util.Map;

public interface UserService {
    //查询用户密码
    String getPassWord(String userName);
    //添加用户
    int add(UserDO userDO);
    //查询用户信息
    UserDO get(Map<String, Object> map);
    //更新用户信息
    int update(UserDO userDO);
}
