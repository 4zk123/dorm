package com.cduestc.dorm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class result {
    private Integer code;
    private String msg;
    private Object data;
    public static result success(){
        return new result(1,"success",null);
    }
    public static result success(Object data){
        return new result(1,"success",data);
    }
    public static result error(String msg){
        return new result(0,msg,null);
    }
}
