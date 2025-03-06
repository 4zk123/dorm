package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentPage {
    private String id;
    private String name;
    private String college;
    private String subject;
    private String grade;
    private String sclass;
    private String building;
    private String room;
    private Integer bunk;
    private Integer pageNum;
    private Integer pageSize;

}
