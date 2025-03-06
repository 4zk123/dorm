package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolApplyDto {
    private Integer id;
    private String livingRegion;
    private LocalDateTime createTime;
    private Integer status;
}
