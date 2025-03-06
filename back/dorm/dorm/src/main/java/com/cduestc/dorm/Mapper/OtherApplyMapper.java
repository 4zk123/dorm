package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.OtherApply;
import com.cduestc.dorm.entity.OtherApplyPage;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OtherApplyMapper {
    List<OtherApply> getListOtherApply(OtherApplyPage otherApplyPage);

    int getListOtherApplyTotal(OtherApplyPage otherApplyPage);
    @Insert("insert into other_apply(student_id,student_name,building,room,apply,status) "+
            "values (#{studentId},#{studentName},#{building},#{room},#{apply},#{status})")
    void insertOtherApply(OtherApply otherApply);

    void updateOtherApply(OtherApply otherApply);
    @Delete("delete from other_apply where id = #{id}")
    void delOtherApply(Integer id);

    void delBatchOtherApply(@Param("ids") List<Integer> ids);
}
