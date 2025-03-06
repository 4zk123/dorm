package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.BackApply;
import com.cduestc.dorm.entity.BackApplyPage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BackApplyMapper {
    List<BackApply> getListBackApply(BackApplyPage backApply);

    Integer getBackApplyTotal(BackApplyPage backApply);
    @Insert("insert into back_apply(college,type,out_time,back_time,stay_place,stay_street," +
            "eme_name,eme_phone,student_id,student_name,status,building) "+
            "values(#{college},#{type},#{outTime},#{backTime},#{stayPlace},#{stayStreet}," +
            "#{emeName},#{emePhone},#{studentId},#{studentName},#{status},#{building})")
            void insertBackApply(BackApply backApply);

    void updateBackApply(BackApply backApply);
    @Delete("delete from back_apply where id = #{id}")
    void delBackApplyById(Integer id);

    void delBatchBackApply(@Param("ids") List<Integer> ids);
}
