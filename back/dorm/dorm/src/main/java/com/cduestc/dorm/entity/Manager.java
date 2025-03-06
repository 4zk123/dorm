package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private String ID;
    private String name;
    private String pwd;
    private Integer permission;
    private String phone;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
