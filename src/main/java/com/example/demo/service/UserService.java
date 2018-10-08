package com.example.demo.service;

import com.example.demo.domain.UserDO;

public interface UserService {
    String getPassWord(String userName);
    int add(UserDO userDO);
}
