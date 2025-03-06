package com.cduestc.dorm.entity;

import com.cduestc.dorm.utils.ExcelExport;
import com.cduestc.dorm.utils.ExcelImport;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @ExcelExport(value = "学号")
    private String id;
    @ExcelExport(value = "姓名")
    private String name;
    @ExcelExport(value = "密码")
    private String pwd;
    @ExcelExport(value = "学院")
    private String college;
    @ExcelExport(value = "专业")
    private String subject;
    @ExcelExport(value = "年级")
    private String grade;
    @ExcelExport(value = "班级")
    private String sclass;
    @ExcelExport(value = "性别")
    private String gender;
    @ExcelExport(value = "联系方式")
    private String phone;
    @ExcelExport(value = "入住时间")
    private LocalDateTime stayTime;
    @ExcelExport(value = "离宿时间")
    private LocalDateTime outTime;
    @ExcelExport(value = "楼栋")
    private String building;
    @ExcelExport(value = "寝室")
    private String room;
    @ExcelExport(value = "床位")
    private Integer bunk;
    @ExcelExport(value = "寝室ID")
    private Integer dormId;
}
