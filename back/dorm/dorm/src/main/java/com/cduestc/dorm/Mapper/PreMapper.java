package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PreMapper {
    int take(List<Dorm> dorm);
    @Select("select start_time,end_time,college from dorm where college = #{college} order by id desc limit 1")
    TestTimeStu getToStu(String college);
    @Select("select distinct building from dorm where college = #{coollege}")
    List<String> getToCollege(String college);
    @Select("select distinct room from dorm where college = #{college} and building = #{building}")
    List<String> getByBuilding(@Param("college") String college,@Param("building") String building);
    @Select("select id from bunk where building= #{building} and room = #{room} and student_id is null order by id desc limit 1 ")
    Integer getBunkId(SaveRoomIn saveRoomIn);
    @Update("update bunk set student_id = #{studentId} where id = #{bunkId}")
    int saveRoom(@Param("bunkId") Integer bunkId,@Param("studentId") String studentId);

    Integer total(PrePage prePage);

    List<PreDorms> getListPre(PrePage prePage);


    @Delete("delete from dorm where college=#{college} and building = #{building}")
    void del(@Param("college") String college,@Param("building") String building);
    @Update("update student set dorm_id = #{bunk} where id = #{studentId}")
    int saveStudent(@Param("bunk") Integer bunk,@Param("studentId") String studentId);
}
