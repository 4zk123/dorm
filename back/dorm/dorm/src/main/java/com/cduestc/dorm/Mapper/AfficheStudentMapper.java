package com.cduestc.dorm.Mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AfficheStudentMapper {
    @Delete("delete from affiche_student where affiche_id = #{afficheId}")
    void delByAfficheId(Integer afficheId);
    @Select("select status from affiche_student where student_id = #{studentId} and affiche_Id = #{afficheId}")
    Integer searchStatus(Integer afficheId,String studentId);
//    @Delete("delete from affiche where id in\n" +
//            "    <foreach collection=\"ids\" open=\"(\" close=\")\" separator=\",\" item=\"id\">\n" +
//            "            #{id}\n" +
//            "    </foreach>")
//    void delBatch(List<Integer> ids);
}
