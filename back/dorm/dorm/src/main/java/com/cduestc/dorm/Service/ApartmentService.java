package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.College;
import com.cduestc.dorm.entity.Grade;
import com.cduestc.dorm.entity.Sclass;
import com.cduestc.dorm.entity.Subject;

import java.util.List;

public interface ApartmentService {
    List<Grade> getGrade();

    List<Sclass> getSclass(Integer subjectId,Integer gradeId);

    List<Subject> getSubject(Integer collegeId);

    List<College> getCollege();
}
