package com.example.demo.service.impl;

import com.example.demo.dao.ManagerDao;
import com.example.demo.domain.ManagerDO;
import com.example.demo.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerServiceImpl implements ManagerService {
    @Autowired
    ManagerDao managerDao;

    @Override
    public ManagerDO getByName(String username){
        return managerDao.getByName(username);
    }
}
