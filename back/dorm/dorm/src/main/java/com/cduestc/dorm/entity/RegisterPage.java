package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterPage {
    private Integer id;
    private String name;
    private String phone;
    private String building;
    private String room;
    private String matter;
    private String unit;
    private LocalDateTime arriveTime;
    private LocalDateTime leaveTime;
    private Integer pageSize;
    private Integer pageNum;
}
