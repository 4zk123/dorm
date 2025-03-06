package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeApplyPage {
    private String studentName;
    private String studentId;
    private String college;
    private Integer type;
    private Integer status;
    private Integer pageNum;
    private Integer pageSize;
}
