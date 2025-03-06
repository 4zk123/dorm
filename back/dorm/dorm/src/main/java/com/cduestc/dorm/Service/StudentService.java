package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.Student;
import com.cduestc.dorm.entity.StudentPage;
import com.cduestc.dorm.entity.StudentPaging;

import java.util.List;

public interface StudentService {
    StudentPaging select(StudentPage studentPage);

    Boolean insert(Student student);

    Boolean delete(String id);

    Boolean delBatch(List<String> ids);

    Boolean updateById(Student student);

    Student getStudentById(String id);

    List<Student> export(StudentPage studentPage);
}
