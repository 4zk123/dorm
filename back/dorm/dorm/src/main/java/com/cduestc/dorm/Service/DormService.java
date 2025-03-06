package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.*;

import java.util.List;

public interface DormService {

    List<Building> getBuilding();

    List<Room> getRoom(String building);

    List<Bunk> getBunk(String room);

    DormPaging search(DormPage dorm);

    void inserBuilding(BuildingInsert buildingInsert);

    void insertRoom(Room room);

    void insertBunk(Bunk bunk);

    BuildingNum countBuildingNum(BuildingPage buildingPage);

    void deleteBunkById(Integer id);

    void deleteBuilding(String building);

    RoomNum countRoomNum(RoomPage roomPage);

    void deleteRoomById(Integer id);

    void delBatchBuilding(List<String> delBuildings);

    void delBatchRoom(List<DelBatchRoom> delBatchRoom);

    BunkPaging countBunkNum(BunkPage bunkPage);

    void delBatchBunk(List<Integer> ids);

    List<String> getEmptyRoom(String building);
}
