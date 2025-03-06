package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackApplyPage {
    private String college;
    private Integer type;
    private String studentId;
    private String studentName;
    private Integer status;
    private Integer pageSize;
    private Integer pageNum;
}
