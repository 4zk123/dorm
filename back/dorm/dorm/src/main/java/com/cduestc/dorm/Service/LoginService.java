package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.MLoginTest;
import com.cduestc.dorm.entity.SLoginTest;

public interface LoginService {
    SLoginTest STestAccount(String id, String pwd);

    MLoginTest MTestAccount(String id, String pwd);
}
