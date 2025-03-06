package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomPage {
    private String building;
    private String room;
    private Integer pageNum;
    private Integer pageSize;
}
