package com.cduestc.dorm.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dorm {
    private Integer id;
    private String building;
    private String room;
    private Integer bunk;
    private String college;
    private Integer gender;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String name;
}
