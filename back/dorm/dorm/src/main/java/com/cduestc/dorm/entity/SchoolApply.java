package com.cduestc.dorm.entity;

import com.sun.org.apache.bcel.internal.classfile.LocalVariable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolApply {
    private Integer id;
    private String studentId;
    private String studentName;
    private String college;
    private LocalDateTime outTime;
    private String livingRegion;
    private String LivingPlace;
    private String emergencyConcat;
    private String emergencyConcatPhone;
    private LocalDateTime createTime;
    private Integer status;
}
