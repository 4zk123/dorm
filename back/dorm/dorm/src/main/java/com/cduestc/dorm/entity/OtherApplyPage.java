package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherApplyPage {
    private Integer status;
    private String building;
    private String studentId;
    private String studentName;
    private Integer pageSize;
    private Integer pageNum;
}
