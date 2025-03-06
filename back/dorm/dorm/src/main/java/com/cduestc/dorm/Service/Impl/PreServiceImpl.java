package com.cduestc.dorm.Service.Impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.cduestc.dorm.Mapper.PreMapper;
import com.cduestc.dorm.Service.PreService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PreServiceImpl implements PreService {
    @Autowired
    private PreMapper preMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public List take(PreDorms dorms) {
        List<String> room = dorms.getDorms();
        LocalDateTime startTime = dorms.getStartTime().plusHours(8);
        LocalDateTime endTime = dorms.getEndTime().plusHours(8);
        System.out.println("start:"+startTime);
        System.out.println("end:"+endTime);
        String college = dorms.getCollege();
        Integer gender = dorms.getGender();
        String building = dorms.getBuilding();;
        List<Dorm> dormTak = new ArrayList<>();
        for (int i = 0;i<room.size();i++){
            if (room.get(i) != null){
                Dorm dorm =new Dorm();
                dorm.setRoom(room.get(i));
                dorm.setCollege(college);
                dorm.setBuilding(building);
                dorm.setStartTime(startTime);
                dorm.setEndTime(endTime);
                dorm.setGender(gender);
                dormTak.add(dorm);
            }
        }
        System.out.println(dormTak);
        preMapper.take(dormTak);
        return dormTak;
    }

    @Override
    public boolean testToStu(String college) {

        TestTimeStu testTimeStu  = preMapper.getToStu(college);
        if (testTimeStu != null){
            LocalDateTime start =  testTimeStu.getStartTime();
            LocalDateTime end = testTimeStu.getEndTime();
            LocalDateTime now = LocalDateTime.now();
            if (now.isBefore(start)){
                System.out.println("早了");
                return false;
            }
            else if (now.isAfter(end)){
                System.out.println("晚了");
                return false;
            }
            return true;
        }
        return false;

    }

    @Override
    public List<String> getToCollege(String college) {
        List<String> building = preMapper.getToCollege(college);
        return building;
    }

    @Override
    public List<String> getByBuilding(String college, String building) {
        List<String> rooms = preMapper.getByBuilding(college,building);
        return rooms;
    }

    @Override
    public Boolean testRoomIn(TestRoomIn testRoomIn) {
        String roomIn = "roomIn::college_"+testRoomIn.getCollege()+"building"+testRoomIn.getBuilding();
        Object test = redisTemplate.opsForValue().get(roomIn);
        if (test != null){
            Integer testRoom = Integer.parseInt(test.toString());
            if (testRoom < 4){
                redisTemplate.opsForValue().set(roomIn,testRoom + 1);
                return true;
            }
            else
                return false;
        }
        return false;
    }

    @Override
    public int saveRoom(SaveRoomIn saveRoomIn) {
        Integer bunkId = preMapper.getBunkId(saveRoomIn);
        if (bunkId != null){
            Integer bunk = Integer.parseInt(bunkId.toString());
            int save = preMapper.saveRoom(bunk,saveRoomIn.getStudentId());
            int stu = preMapper.saveStudent(bunk,saveRoomIn.getStudentId());
            System.out.println("save:"+save);
            return save;
        }
        else {
            return 0;
        }

    }

    @Override
    public PrePaging getListPre(PrePage prePage) {
        Integer pageNum = prePage.getPageNum();
        Integer pageSize = prePage.getPageSize();
        pageNum = (pageNum - 1) * pageSize;
        prePage.setPageNum(pageNum);
        Integer total = preMapper.total(prePage);
        List<PreDorms> dorms = preMapper.getListPre(prePage);
        PrePaging paging = new PrePaging(dorms,total);
        return paging;
    }

    @Override
    public void del(String college, String building) {
        preMapper.del(college,building);
    }
}
