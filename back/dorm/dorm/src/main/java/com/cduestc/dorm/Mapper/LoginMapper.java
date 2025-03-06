package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.MLoginTest;
import com.cduestc.dorm.entity.SLoginTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select ID,name,college from student where ID = #{ID} and pwd = #{pwd}")
    SLoginTest STestAccount(@Param("ID") String ID,@Param("pwd") String pwd);
    @Select("select ID,name,permission from manager where ID = #{ID} and pwd = #{pwd} ")
    MLoginTest MTestAccount(@Param("ID") String ID,@Param("pwd") String pwd);
}
