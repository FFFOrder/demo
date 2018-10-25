package com.example.demo.dao;

import com.example.demo.domain.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface UserDao {
    //根据username查询用户密码
    String getPassWord(String userName);
    //添加用户
    int add(UserDO userDO);
    //查询用户
    UserDO get(Map<String, Object> map);
    //更新用户信息
    int update(UserDO userDO);
}
