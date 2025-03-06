package com.cduestc.dorm.Controller;

import com.cduestc.dorm.Service.RegisterService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping
    public result search(@RequestBody RegisterPage registerPage){
        RegisterPaging registerPaging = registerService.search(registerPage);
        return result.success(registerPaging);
    }
    @GetMapping("/unit")
    public result getUnit(){
        List<Unit> units = registerService.getUnit();
        return result.success(units);
    }
    @GetMapping("/matter")
    public result getMatter(String unit){
        List<Matter> matters = registerService.getMatter(unit);
        return result.success(matters);
    }
    @PostMapping("/insert/register")
    public result insertRegister(@RequestBody Register register){
        registerService.insertRegister(register);
        return result.success();
    }
    @DeleteMapping("/{id}")
    public result deleteRegisterById(@PathVariable Integer id){
        registerService.deleteRegisterById(id);
        return result.success();
    }
    @PutMapping("/update")
    public result updateRegisterById(@RequestBody Register register){
        registerService.updateRegisterById(register);
        return result.success();
    }
    @PostMapping("/del/batch")
    public result delBatch(@RequestBody List<Integer> ids){
        registerService.delBatch(ids);
        return result.success();
    }
}
