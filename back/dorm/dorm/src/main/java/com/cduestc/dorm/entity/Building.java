package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Building {
    private Integer id;
    private String building;
    private Integer num;
    private Integer floor;
    private Integer roomNum;
    private Integer roomsNum;
    private Integer buildingType;
    private Integer bunkType;
}
