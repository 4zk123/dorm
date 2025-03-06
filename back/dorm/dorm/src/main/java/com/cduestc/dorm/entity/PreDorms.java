package com.cduestc.dorm.entity;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreDorms {
    private List<String> dorms;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer gender;
    private String college;
    private String building;
}
