package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrePage {
    private Integer dorms;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer gender;
    private String college;
    private String building;
    private Integer pageSize;
    private Integer pageNum;
}
