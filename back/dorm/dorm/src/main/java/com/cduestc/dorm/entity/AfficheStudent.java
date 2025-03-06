package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AfficheStudent {
    private Integer id;
    private Integer afficheId;
    private String studentId;
    private Integer status;
}
