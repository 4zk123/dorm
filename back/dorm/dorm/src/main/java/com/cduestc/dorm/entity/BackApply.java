package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackApply {
    private Integer id;
    private String college;
    private Integer type;
    private String building;
    private LocalDateTime outTime;
    private LocalDateTime backTime;
    private String stayPlace;
    private String stayStreet;
    private String emeName;
    private String emePhone;
    private String studentId;
    private String studentName;
    private Integer status;
}
