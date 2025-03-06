package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DormPage {
    private Integer id;
    private String building;
    private String room;
    private Integer bunk;
    private String studentId;
    private String name;
    private Integer pageNum;
    private Integer pageSize;
}
