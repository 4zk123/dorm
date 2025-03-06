package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DormMapper {
    @Insert("insert into bunk(building,room,bunk,student_id) values(#{building},#{room},#{bunk},#{id})")
    Boolean inserStudentAndDorm(Student student);
    @Select("select id from bunk where student_id = #{id}")
    Integer selectByStudentId(String id);
    @Select("select * from bunk where room = #{room}")
    List<Bunk> getBunk(String room);
    @Select("select * from room where building = #{building}")
    List<Room> getRoom(String building);
    @Select("select * from building")
    List<Building> getBuilding();

    List<Dorm> search(DormPage dorm);

    int count(DormPage dorm);
    @Insert("insert into building(building,building_type,bunk_type) values(#{building},#{buildingType},#{bunkType})")
    void inserBuilding(BuildingInsert buildingInsert);
    @Insert("insert into room(room,building,num) values(#{room},#{building},#{num})")
    void insertRoom(Room room);
    @Insert("insert into bunk(bunk,room,building) values(#{bunk},#{room},#{building})")
    void insertBunk(Bunk bunk);
//    @Select("select count(*) from building where id = #{id}")
//    Integer CountBuildingNum(Integer id);
    @Select("select count(*) from room where building = #{building}")
    Integer CountRoomNumByBuilding(String building);

//    @Select("select count(*) from bunk")
//    Integer CountBunkNum(Integer id);

    @Select("select * from room where id = #{id}")
    Room getRoomRoomById(Integer id);
    int countBuildingNum(BuildingPage buildingPage);
    List<Building> getListOfBuildingId(BuildingPage buildingPage);

//    List<RoomNum> getListOfRoomId();
//    @Select("select id from bunk")
//    List<Integer> getListOfBunkId();
    @Delete("delete from bunk where id = #{id}")
    void deleteBunkById(Integer id);
    @Delete("delete from building where building = #{building}")
    void deleteBuilding(String building);
    @Delete("delete from bunk where building = #{building}")
    void deleteBunkByBuilding(String building);
    @Delete("delete from bunk where room = #{room} and building = #{building}")
    void deleteBunkByRoom(@Param("room") String room,@Param("building") String building);
    @Delete("delete from room where building = #{building}")
    void deleteRoomByBuilding(String building);
    @Delete("delete from room where building = #{building} and room = #{room}")
    void deleteRoomByBuildingAndRoom(@Param("room") String room,@Param("building") String building);
    @Delete("delete from room where id = #{id}")
    void deleteRoomById(Integer id);
    List<Room> getListOfRoom(RoomPage roomPage);
    @Select("select count(*) from dorm where room = #{room} and building = #{building}")
    int countLivingNum(Room room);


    int countRoomNum(RoomPage roomPage);
    @Select("select id from room where building = #{buidling} and room = #{room}")
    Integer getRoomId(@Param("building") String building,@Param("room") String room);
    @Select("select id from building where building = #{buidling}")
    Integer getBuildingId(@Param("building") String building);

    void delBatchBuilding(@Param("delBuildings") List<String> delBuildings);

    List<Bunk> countBunkNum(BunkPage bunkPage);

    Integer countBunkTotal(BunkPage bunkPage);

    void delBatchBunk(@Param("ids") List<Integer> ids);
    @Select("select distinct room from bunk where building = #{building} and student_id is NULL \n")
    List<String> getEmptyRoom(String building);
    @Update("update bunk set student_id = #{id} where building = #{building} and room = #{room} and bunk = #{bunk}")
    int insertStudentIdToBunk(Student student);
//    void delBatchRoom(@Param("delRooms") List<String> delRooms,@Param("building") String building);
    @Update("update bunk set student_id =null where id = #{id}")
    void upBunkStuId(@Param("id") Integer id);
    @Select("select id from bunk where student_id = #{id}")
    Integer getBunkId(@Param("id") String id);
    @Update("update bunk set student_id = null where student_id = #{id}")
    void upStuIdFBunk(@Param("id") String id);


}
