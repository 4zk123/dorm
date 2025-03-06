package com.cduestc.dorm.Controller;

import com.cduestc.dorm.Service.PreService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/pre")
public class PreController {
    @Autowired
    private PreService preService;
    @PostMapping("/take")
    public result take(@RequestBody PreDorms dorms){
        System.out.println(dorms);
        List<Dorm> dorm = preService.take(dorms);
        System.out.println("===========");
        System.out.println(dorms);
        return result.success(dorm);
    }
    @GetMapping("/test/stu")
    public result testToStu(String college){
        boolean test = preService.testToStu(college);
        return result.success(test);
    }
    @GetMapping("/get/college")
    public result getToCollege(String college){
        List<String> buildings = preService.getToCollege(college);
        return result.success(buildings);
    }
    @GetMapping("/get/building")
    public result getByBuilding(String college,String building){
        List<String> rooms = preService.getByBuilding(college,building);
        return result.success(rooms);
    }
    @PostMapping("/test/room")
    public result testRoomIn(@RequestBody TestRoomIn testRoomIn){
        Boolean bool = preService.testRoomIn(testRoomIn);
        return result.success(bool);
    }
    @PostMapping("/save/room")
    public result saveRoom(@RequestBody SaveRoomIn saveRoomIn){
        int save = preService.saveRoom(saveRoomIn);
        return result.success(save);
    }
    @PostMapping()
    public result getListPre(@RequestBody PrePage prePage){
        PrePaging paging = preService.getListPre(prePage);
        return result.success(paging);
    }
    @GetMapping("/del")
    public result del(String college,String building){
        preService.del(college,building);
        return result.success();
    }
}
