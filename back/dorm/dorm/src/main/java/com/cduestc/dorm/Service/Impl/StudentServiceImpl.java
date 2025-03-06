package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.DormMapper;
import com.cduestc.dorm.Mapper.StudentMapper;
import com.cduestc.dorm.Service.StudentService;
import com.cduestc.dorm.entity.Student;
import com.cduestc.dorm.entity.StudentPage;
import com.cduestc.dorm.entity.StudentPaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private DormMapper dormMapper;
    @Override
    public StudentPaging select(StudentPage studentPage) {
        Integer pagesize = studentPage.getPageSize();

        Integer pagenum = (studentPage.getPageNum() - 1) * pagesize;

        studentPage.setPageNum(pagenum);
        studentPage.setPageSize(pagesize);
        List<Student> students = studentMapper.select(studentPage);
        Integer total = studentMapper.count(studentPage);
        StudentPaging studentPaging = new StudentPaging();
        studentPaging.setStudents(students);
        studentPaging.setTotal(total);
        return studentPaging;
    }

    @Override
    public Boolean insert(Student student) {
        dormMapper.insertStudentIdToBunk(student);
        Integer dormId = dormMapper.selectByStudentId(student.getId());
        student.setStayTime(LocalDateTime.now());
        student.setDormId(dormId);
        student.setPwd("123456");
        Boolean bool = studentMapper.insert(student);
        return bool;
    }

    @Override
    public Boolean delete(String id) {
        Integer bunkId = dormMapper.getBunkId(id);
        dormMapper.upBunkStuId(bunkId);
        Boolean bool = studentMapper.delete(id);
        return bool;
    }

    @Override
    public Boolean delBatch(List<String> ids) {
        Boolean bool = studentMapper.delBatch(ids);
        for (int i = 0;i<ids.size();i++){
            dormMapper.upStuIdFBunk(ids.get(i));
        }
        return bool;
    }

    @Override
    public Boolean updateById(Student student) {
        Boolean bool = studentMapper.updateById(student);
        return bool;
    }

    @Override
    public Student getStudentById(String id) {
        Student student = studentMapper.getStudentById(id);
        return student;
    }

    @Override
    public List<Student> export(StudentPage studentPage) {
        List<Student> students= studentMapper.export(studentPage);
        return students;
    }
}
