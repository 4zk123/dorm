package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AffichePage {
    private Integer id;
    private Integer status;
    private String writeName;
    private Integer pageNum;
    private Integer pageSize;
}
