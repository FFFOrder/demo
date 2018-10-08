package com.example.demo.dao;

import com.example.demo.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    String getPassWord(String userName);
    int add(UserDO userDO);
}
