package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sclass {
    private Integer id;
    private String sclass;
    private Integer subjectId;
    private Integer collegeId;
}
