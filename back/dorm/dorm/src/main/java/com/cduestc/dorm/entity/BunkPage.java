package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BunkPage {
    private Integer bunk;
    private String room;
    private String building;
    private Integer pageNum;
    private Integer pageSize;
}
