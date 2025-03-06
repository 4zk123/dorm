package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuildingInsert {
    private String building;
    private Integer buildingType;
    private Integer floor;
    private Integer roomNum;
    private Integer roomsNum;
    private Integer BunkType;
}
