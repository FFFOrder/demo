package com.example.demo.service.impl;

import com.example.demo.dao.UserDao;
import com.example.demo.domain.UserDO;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public String getPassWord(String userName){
        return userDao.getPassWord(userName);
    }
    @Override
    public int add(UserDO userDO){
        return userDao.add(userDO);
    }
    public String getUser(){
        return "zzm";
    }
}
