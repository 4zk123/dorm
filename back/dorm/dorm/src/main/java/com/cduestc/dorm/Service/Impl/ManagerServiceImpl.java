package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.ManagerMapper;
import com.cduestc.dorm.Service.ManagerSerivce;
import com.cduestc.dorm.entity.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerSerivce {
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public List<Manager> getManagerListName() {
        List<Manager> managers = managerMapper.getManagerListName();
        return managers;
    }
}
