package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.DormMapper;
import com.cduestc.dorm.Service.DormService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DormServiceImpl implements DormService {
    @Autowired
    private DormMapper dormMapper;
    private String middleNum = "0";
    private String middle = "-";
    @Override
    public List<Building> getBuilding() {
        List<Building> buildings = dormMapper.getBuilding();
        return buildings;
    }

    @Override
    public List<Room> getRoom(String building) {
        List<Room> rooms = dormMapper.getRoom(building);
        return rooms;
    }

    @Override
    public List<Bunk> getBunk(String room) {
        List<Bunk> bunks = dormMapper.getBunk(room);
        return bunks;
    }

    @Override
    public DormPaging search(DormPage dorm) {
        int PageNum = dorm.getPageNum() - 1;
        dorm.setPageNum(PageNum);
        int total = dormMapper.count(dorm);
        List<Dorm> dorms = dormMapper.search(dorm);
        DormPaging dormPaging = new DormPaging();
        dormPaging.setDorms(dorms);
        dormPaging.setTotal(total);
        return dormPaging;
    }

    @Override
    public void inserBuilding(BuildingInsert buildingInsert) {
        String building = buildingInsert.getBuilding();
        Integer type = buildingInsert.getBuildingType();
        Integer floor = buildingInsert.getFloor();
        Integer room = buildingInsert.getRoomNum();
        Integer rooms = buildingInsert.getRoomsNum();
        Integer bunk = buildingInsert.getBunkType();
        dormMapper.inserBuilding(buildingInsert);
        if (type == 0){
            for (int i = 1;i<=floor;i++){
                for (int j = 1;j<=room;j++){
                    Room roomInsert = new Room();
                    roomInsert.setBuilding(building);
                    roomInsert.setNum(bunk);
                    String roomName;
                    if (j>9){
                        roomName = Integer.toString(i) + j;
                    }
                    else {
                        roomName = i + middleNum + j;
                    }
                    roomInsert.setRoom(roomName);
                    dormMapper.insertRoom(roomInsert);
                    for (int k = 1;k<=bunk;k++){
                        Bunk bunkInsert = new Bunk();
                        bunkInsert.setBuilding(building);
                        bunkInsert.setRoom(roomName);
                        bunkInsert.setBunk(k);
                        dormMapper.insertBunk(bunkInsert);
                    }
                }
            }
        }
        else if (type == 1){
            room = floor * room;
                for (int j=1;j<=room;j++){
                    for (int i = 1;i<=rooms;i++){
                        String roomName = j + middle + i;
                        Room roomInsert = new Room();
                        roomInsert.setBuilding(building);
                        roomInsert.setRoom(roomName);
                        roomInsert.setNum(bunk);
                        dormMapper.insertRoom(roomInsert);
                        for (int k=1;k<=bunk;k++){
                            Bunk bunkInsert = new Bunk();
                            bunkInsert.setBuilding(building);
                            bunkInsert.setRoom(roomName);
                            bunkInsert.setBunk(k);
                            dormMapper.insertBunk(bunkInsert);
                        }
                    }
                }
        }

    }

    @Override
    public void insertRoom(Room room) {
        dormMapper.insertRoom(room);
        Bunk bunk = new Bunk();
        bunk.setRoom(room.getRoom());
        bunk.setBuilding(room.getBuilding());
        bunk.setNum(room.getNum());
        this.insertBunk(bunk);
    }

    @Override
    public void insertBunk(Bunk bunk) {
        if(bunk.getBunk() != null){
            dormMapper.insertBunk(bunk);
        }
        else if(bunk.getNum() != null){
        if(bunk.getNum() == 2){
            for(int i = 1;i<=2;i++){
                bunk.setBunk(i);
                dormMapper.insertBunk(bunk);
            }
        }
        else if(bunk.getNum() == 4){
            for(int i = 1;i<=4;i++){
                bunk.setBunk(i);
                dormMapper.insertBunk(bunk);
            }
        }
        else if(bunk.getNum() == 6){
            for(int i = 1;i<=6;i++){
                bunk.setBunk(i);
                dormMapper.insertBunk(bunk);
            }
        }
        }
    }

    @Override
    public BuildingNum countBuildingNum(BuildingPage buildingPage) {
        Integer pageNum = buildingPage.getPageNum();
        Integer pageSize = buildingPage.getPageSize();;
        pageNum = (pageNum - 1) * pageSize;
        buildingPage.setPageNum(pageNum);
        List<Building> buildingIds = dormMapper.getListOfBuildingId(buildingPage);
        int i;
        for(i=0;i<buildingIds.size();i++){
            String building =  buildingIds.get(i).getBuilding();
            Integer count = dormMapper.CountRoomNumByBuilding(building);
            buildingIds.get(i).setNum(count);
        }
        BuildingNum buildingNum=new BuildingNum();
        buildingNum.setBuilding(buildingIds);
        buildingNum.setTotal(dormMapper.countBuildingNum(buildingPage));
        return buildingNum;
    }
    @Override
    public void deleteBunkById(Integer id) {
        dormMapper.deleteBunkById(id);
    }

    @Override
    public void deleteBuilding(String building) {
        dormMapper.deleteBunkByBuilding(building);
        dormMapper.deleteRoomByBuilding(building);
        dormMapper.deleteBuilding(building);
    }

    @Override
    public RoomNum countRoomNum(RoomPage roomPage) {
        int pageNum = (roomPage.getPageNum()-1) * roomPage.getPageSize();
        roomPage.setPageNum(pageNum);
        List<Room> rooms = dormMapper.getListOfRoom(roomPage);
        for(int i = 0;i < rooms.size();i++){
            Room room = rooms.get(i);
            int livingroom = dormMapper.countLivingNum(room);
            room.setLivingNum(livingroom);
            rooms.set(i,room);
        }
        RoomNum roomNum = new RoomNum();
        roomNum.setRoom(rooms);
        roomNum.setTotal(dormMapper.countRoomNum(roomPage));
        return roomNum;
    }

    @Override
    public void deleteRoomById(Integer id) {
        Room room = dormMapper.getRoomRoomById(id);

        dormMapper.deleteBunkByRoom(room.getRoom(),room.getBuilding());
        dormMapper.deleteRoomById(id);
    }

    @Override
    public void delBatchBuilding(List<String> delBuildings) {
        for (int i = 0;i<delBuildings.size();i++){
            dormMapper.deleteBunkByBuilding(delBuildings.get(i));
            dormMapper.deleteRoomByBuilding(delBuildings.get(i));
        }
        dormMapper.delBatchBuilding(delBuildings);
    }

    @Override
    public void delBatchRoom(List<DelBatchRoom> delBatchRoom) {
//        List<String> delRooms = delBatchRoom.getDelRooms();
//        String building = delBatchRoom.getBuilding();
//        for (int i = 0;i<delRooms.size();i++){
//            dormMapper.deleteBunkByRoom(delRooms.get(i),building);
//        }
//        dormMapper.delBatchRoom(delRooms,building);
        for (int i = 0;i<delBatchRoom.size();i++){
            dormMapper.deleteBunkByRoom(delBatchRoom.get(i).getRoom(),delBatchRoom.get(i).getBuilding());
            dormMapper.deleteRoomByBuildingAndRoom(delBatchRoom.get(i).getRoom(),delBatchRoom.get(i).getBuilding());
        }
    }

    @Override
    public BunkPaging countBunkNum(BunkPage bunkPage) {
        int pageNum = (bunkPage.getPageNum()-1) * bunkPage.getPageSize();
        bunkPage.setPageNum(pageNum);
        List<Bunk> bunks = dormMapper.countBunkNum(bunkPage);
        Integer total = dormMapper.countBunkTotal(bunkPage);
        BunkPaging bunkPaging = new BunkPaging(bunks,total);
        return bunkPaging;
    }

    @Override
    public void delBatchBunk(List<Integer> ids) {
        dormMapper.delBatchBunk(ids);
    }

    @Override
    public List<String> getEmptyRoom(String building) {
        List<String> rooms = dormMapper.getEmptyRoom(building);
        return rooms;
    }
}
