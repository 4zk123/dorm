package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.ChangeApply;
import com.cduestc.dorm.entity.ChangeApplyPage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChangeApplyMapper {
    void upChangeApply(ChangeApply changeApply);
    @Insert("insert into change_apply(student_name,student_id,college,reason,type,building,room,bunk,ex_building,ex_room,ex_bunk,ex_id,ex_name,status) "+
            "values(#{studentName},#{studentId},#{college},#{reason},#{type},#{building},#{room},#{bunk},#{exBuilding},#{exRoom},#{exBunk},#{exId},#{exName},#{status})")
    void insertChangeApply(ChangeApply changeApply);
    @Delete("delete from change_apply where id = #{id}")

    void delChangeApply(Integer id);

    void delBatchChangeApply(@Param("ids") List<Integer> ids);

    List<ChangeApply> getListChangeApply(ChangeApplyPage changeApplyPage);

    Integer getTotalChangeApply(ChangeApplyPage changeApplyPage);
}
