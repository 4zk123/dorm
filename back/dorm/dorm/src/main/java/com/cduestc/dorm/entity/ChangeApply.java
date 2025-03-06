package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeApply {
    private Integer id;
    private String studentName;
    private String studentId;
    private String college;
    private String reason;
    private Integer type;
    private String building;
    private String room;
    private Integer bunk;
    private String exBuilding;
    private String exRoom;
    private Integer exBunk;
    private String exId;
    private String exName;
    private Integer status;
}
