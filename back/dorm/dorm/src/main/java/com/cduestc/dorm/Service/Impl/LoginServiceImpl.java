package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.LoginMapper;
import com.cduestc.dorm.Service.LoginService;
import com.cduestc.dorm.entity.MLoginTest;
import com.cduestc.dorm.entity.SLoginTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    @Override
    public SLoginTest STestAccount(String ID, String pwd) {
        SLoginTest sLoginTest =  loginMapper.STestAccount(ID,pwd);
        return sLoginTest;
    }

    @Override
    public MLoginTest MTestAccount(String ID, String pwd) {
        MLoginTest mLoginTest = loginMapper.MTestAccount(ID,pwd);
//        if (mLoginTest.isNullorEmpty()){
            return mLoginTest;
//        }
//        else {
//            return ;
//        }

    }
}
