package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.Student;
import com.cduestc.dorm.entity.StudentPage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface StudentMapper {

    Integer count(StudentPage studentPage);

    List<Student> select(StudentPage studentPage);
    @Insert("insert into student(id,name,pwd,college,subject,grade,sclass,gender,phone,stay_time,out_time,dorm_id)"+
    "values(#{id},#{name},#{pwd},#{college},#{subject},#{grade},#{sclass},#{gender},#{phone},#{stayTime},#{outTime},#{dormId})")
    Boolean insert(Student student);
    @Delete("delete from student where id = #{id}")
    Boolean delete(String id);

    Boolean delBatch(@Param("ids") List<String> ids);

    Boolean updateById(Student student);
    Student getStudentById(String id);

    List<Student> export(StudentPage studentPage);
}
