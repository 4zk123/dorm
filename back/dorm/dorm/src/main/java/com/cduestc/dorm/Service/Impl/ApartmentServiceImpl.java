package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.ApartmentMapper;
import com.cduestc.dorm.Service.ApartmentService;
import com.cduestc.dorm.entity.College;
import com.cduestc.dorm.entity.Grade;
import com.cduestc.dorm.entity.Sclass;
import com.cduestc.dorm.entity.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {
    @Autowired
    private ApartmentMapper apartmentMapper;

    @Override
    public List<Grade> getGrade() {
        List<Grade> grades = apartmentMapper.getGrade();
        return grades;
    }

    @Override
    public List<Sclass> getSclass(Integer subjectId,Integer gradeId) {
        List<Sclass> sclasses = apartmentMapper.getSclass(subjectId,gradeId);
        return sclasses;
    }

    @Override
    public List<Subject> getSubject(Integer collegeId) {
        List<Subject> subjects = apartmentMapper.getSubject(collegeId);
        return subjects;
    }

    @Override
    public List<College> getCollege() {
        List<College> colleges = apartmentMapper.getCollege();
        return colleges;
    }
}
