package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Affiche {
    private Integer id;
    private String title;
    private String text;
    private Integer status;
    private String writeName;
    private LocalDateTime writeTime;
}
