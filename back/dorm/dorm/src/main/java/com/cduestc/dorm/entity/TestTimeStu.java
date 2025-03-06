package com.cduestc.dorm.entity;

import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestTimeStu {
    private String college;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
