package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.*;

import java.util.List;

public interface RegisterService {
    RegisterPaging search(RegisterPage registerPage);

    List<Unit> getUnit();

    List<Matter> getMatter(String unit);

    void insertRegister(Register register);

    void deleteRegisterById(Integer id);

    void updateRegisterById(Register register);

    void delBatch(List<Integer> ids);
}
