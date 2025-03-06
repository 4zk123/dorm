package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.College;
import com.cduestc.dorm.entity.Grade;
import com.cduestc.dorm.entity.Sclass;
import com.cduestc.dorm.entity.Subject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApartmentMapper {
    @Select("select * from grade")
    List<Grade> getGrade();
    @Select("select * from sclass where subject_id = #{subjectId} and grade_id = #{gradeId}")
    List<Sclass> getSclass(@Param("subjectId") Integer subjectId,@Param("gradeId") Integer gradeId);
    @Select("select * from subject where college_id = #{collegeId}")
    List<Subject> getSubject(Integer collegeId);
    @Select("select * from college")
    List<College> getCollege();

    @Select("select name from college where id = #{id}")
    String selectColegeById(Integer id);
    @Select("select id from college where name = #{name}")
    Integer selectCollegeByName(String name);
    @Select("select name from subject where id = #{id}")
    String selectSubjectById(Integer id);
    @Select("select id from subject where name = #{name}")
    Integer selectSubjectByName(String name);
    @Select("select name from grade where id = #{id}")
    String selectGradeById(Integer id);
    @Select("select id from grade where grade = #{grade}")
    Integer selectGradeByName(String grade);
    @Select("select name from sclass where id = #{id}")
    Integer selectSclassById(Integer id);
    @Select("select id from sclass where sclass = #{sclass}")
    Integer selectSclassByName(String sclass);
}
