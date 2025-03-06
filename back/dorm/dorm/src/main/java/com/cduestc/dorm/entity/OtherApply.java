package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtherApply {
    private Integer id;
    private Integer status;
    private String studentId;
    private String studentName;
    private String building;
    private String room;
    private String apply;
}
