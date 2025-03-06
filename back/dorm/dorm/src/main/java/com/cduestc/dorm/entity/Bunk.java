package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bunk {
    private Integer id;
    private Integer bunk;
    private String room;
    private String building;
    private Integer num;
}
