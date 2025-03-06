package com.cduestc.dorm.Controller;

import com.cduestc.dorm.Service.ApartmentService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/apart")
public class ApartmentController {
    @Autowired
    private ApartmentService apartmentService;
    @GetMapping("/grade")
    public result GetGrade(){
        List<Grade> grades = apartmentService.getGrade();
        return result.success(grades);
    }
    @GetMapping("/sclass")
    public result GetSclass(Integer subjectId,Integer gradeId){
        List<Sclass> sclasses = apartmentService.getSclass(subjectId,gradeId);
        return result.success(sclasses);
    }
    @GetMapping("/subject")
    public result GetSubject(Integer collegeId){
        List<Subject> subjects = apartmentService.getSubject(collegeId);
        return result.success(subjects);
    }
    @GetMapping("/college")
    public result GetCollege(){
        List<College> colleges = apartmentService.getCollege();
        return result.success(colleges);
    }
}
