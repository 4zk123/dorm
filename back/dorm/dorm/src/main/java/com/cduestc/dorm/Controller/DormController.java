package com.cduestc.dorm.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.cduestc.dorm.Service.DormService;
import com.cduestc.dorm.entity.*;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dorm")
public class DormController {
    @Autowired
    private DormService dormService;
    @GetMapping("/building")
    public result getBuilding(){
        List<Building> buildings = dormService.getBuilding();
        return result.success(buildings);
    }
    @GetMapping("/room")
    public result getRoom(String building){
        List<Room> rooms = dormService.getRoom(building);
        return result.success(rooms);
    }
    @GetMapping("/bunk")
    public result getBunk(String room,String Bunk){
        List<Bunk> bunks = dormService.getBunk(room);
        return result.success(bunks);
    }
    @PostMapping("/search")
    public result search(@RequestBody DormPage dorm){
        DormPaging dorms = dormService.search(dorm);
        return result.success(dorms);
    }
    @PostMapping("/insert/building")
    public result inserBuilding(@RequestBody BuildingInsert buildingInsert){
        dormService.inserBuilding(buildingInsert);
        return result.success();
    }
    @PostMapping("/insert/room")
    public result insertRoom(@RequestBody Room room){
        dormService.insertRoom(room);
        return result.success();
    }
    @PostMapping("/insert/bunk")
    public result insertBunk(@RequestBody Bunk bunk){
        dormService.insertBunk(bunk);
        return result.success();
    }
    @PostMapping("/count/building")
    public result countBuildingNum(@RequestBody BuildingPage buildingPage){
        BuildingNum buildingNums = dormService.countBuildingNum(buildingPage);
        return result.success(buildingNums);
    }
    @PostMapping("/count/bunk")
    public result countBunkNum(@RequestBody BunkPage bunkPage){
        BunkPaging bunk = dormService.countBunkNum(bunkPage);
        return result.success(bunk);
    }
    @PostMapping("/count/room")
    public result countRoomNum(@RequestBody RoomPage roomPage){
        RoomNum roomNum = dormService.countRoomNum(roomPage);
        return result.success(roomNum);
    }
    @DeleteMapping("/building/{building}")
    public result deleteBuilding(@PathVariable String building){
       dormService.deleteBuilding(building);
       return result.success();
    }
    @DeleteMapping("/bunk/{id}")
    public result deleteBunkById(@PathVariable Integer id){
        dormService.deleteBunkById(id);
        return result.success();
    }
    @DeleteMapping("/room/{id}")
    public result deleteRoomById(@PathVariable Integer id){
        dormService.deleteRoomById(id);
        return result.success();
    }
    @PostMapping("/building/del/batch")
    public result delBatchBuilding(@RequestBody List<String> delBuildings){
        dormService.delBatchBuilding(delBuildings);
        return result.success();
    }
    @PostMapping("/room/del/batch")
    public result delBatchRoom(@RequestBody List<DelBatchRoom> delBatchRoom){
        System.out.println(delBatchRoom);
        dormService.delBatchRoom(delBatchRoom);
        return result.success();
    }
    @PostMapping("/bunk/del/batch")
    public result delBatchBunk(@RequestBody List<Integer> ids){
        dormService.delBatchBunk(ids);
        return result.success();
    }
    @GetMapping("/empty/room")
    public result getEmptyRoom(String building){
        List<String> rooms = dormService.getEmptyRoom(building);
        return result.success(rooms);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        List<Building> buildings = dormService.getBuilding();

        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("楼栋信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), Building.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(buildings);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }








}
