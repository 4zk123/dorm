package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListSchoolApplyPage {
    private String studentId;
    private String studentName;
    private String college;
    private LocalDateTime createTime;
    private Integer status;
    private Integer pageSize;
    private Integer pageNum;
}
