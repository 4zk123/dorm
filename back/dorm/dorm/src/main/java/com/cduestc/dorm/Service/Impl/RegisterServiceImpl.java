package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.RegisterMapper;
import com.cduestc.dorm.Service.RegisterService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private RegisterMapper registerMapper;

    @Override
    public RegisterPaging search(RegisterPage registerPage) {
        int pageNum = (registerPage.getPageNum() - 1) * registerPage.getPageSize();
        registerPage.setPageNum(pageNum);
        List<Register> registers = registerMapper.search(registerPage);
        int total = registerMapper.count(registerPage);
        RegisterPaging registerPaging = new RegisterPaging();
        registerPaging.setRegisters(registers);
        registerPaging.setTotal(total);
        return registerPaging;
    }

    @Override
    public List<Unit> getUnit() {
        List<Unit> units= registerMapper.getUnit();
        return units;
    }

    @Override
    public List<Matter> getMatter(String unit) {
        List<Matter> matters = registerMapper.getMatter(unit);
        return matters;
    }

    @Override
    public void insertRegister(Register register) {
        register.setArriveTime(LocalDateTime.now());
        registerMapper.insertRegister(register);
    }

    @Override
    public void deleteRegisterById(Integer id) {
        registerMapper.deleteRegisterById(id);
    }

    @Override
    public void updateRegisterById(Register register) {
        register.setLeaveTime(LocalDateTime.now());
        registerMapper.updateRegisterById(register);
    }

    @Override
    public void delBatch(List<Integer> ids) {
        registerMapper.delBatch(ids);
    }
}
