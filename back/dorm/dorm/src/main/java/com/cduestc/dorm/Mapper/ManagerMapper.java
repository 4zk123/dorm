package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ManagerMapper {
    @Select("select id,name from manager")
    List<Manager> getManagerListName();
}
