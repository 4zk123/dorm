package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.Matter;
import com.cduestc.dorm.entity.Register;
import com.cduestc.dorm.entity.RegisterPage;
import com.cduestc.dorm.entity.Unit;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RegisterMapper {
    List<Register> search(RegisterPage registerPage);

    int count(RegisterPage registerPage);
    @Select("select * from matter where unit = #{unit}")
    List<Matter> getMatter(String unit);
    @Select("select * from unit")
    List<Unit> getUnit();
    @Insert("insert into register(name,phone,building,room,unit,matter,arrive_time) " +
            "value(#{name},#{phone},#{building},#{room},#{unit},#{matter},#{arriveTime})")
    void insertRegister(Register register);
    @Delete("delete from register where id = #{id}")
    void deleteRegisterById(Integer id);

    void updateRegisterById(Register register);

    void delBatch(@Param("ids") List<Integer> ids);
}
