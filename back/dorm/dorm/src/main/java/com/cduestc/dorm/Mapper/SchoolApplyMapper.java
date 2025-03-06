package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.ListSchoolApplyPage;
import com.cduestc.dorm.entity.SchoolApply;
import com.cduestc.dorm.entity.SchoolApplyDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface SchoolApplyMapper {
    @Insert("insert into school_apply(student_id,student_name,college,out_time,living_region,living_place,emergency_concat,emergency_concat_phone,create_time,status) "+
            "values(#{studentId},#{studentName},#{college},#{outTime},#{livingRegion},#{livingPlace},#{emergencyConcat},#{emergencyConcatPhone},#{createTime},#{status})")
    void insertSchoolApply(SchoolApply schoolApply);
    @Select("select id,living_region,create_time,status from school_apply where student_id = #{studentId}")
    List<SchoolApplyDto> getListSchoolApplyById(String studentId);

    List<SchoolApply> getListSchoolApply(ListSchoolApplyPage page);

    int countSchoolApply(ListSchoolApplyPage page);
    @Delete("delete from school_apply where id = #{id}")
    void delSchoolApply(Integer id);

    void updateSchoolApplyById(SchoolApply schoolApply);

    void delBatchSchoolApply(@Param("ids") List<Integer> ids);
}
