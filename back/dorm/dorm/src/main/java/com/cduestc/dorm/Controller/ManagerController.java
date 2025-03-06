package com.cduestc.dorm.Controller;

import com.cduestc.dorm.Service.ManagerSerivce;
import com.cduestc.dorm.entity.Manager;
import com.cduestc.dorm.entity.result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/manager")
public class ManagerController {
    @Autowired
    private ManagerSerivce managerSerivce;
    @GetMapping("/name")
    public result getManagerListName(){
        List<Manager> managers = managerSerivce.getManagerListName();
        return result.success(managers);
    }
}
