package com.cduestc.dorm.Mapper;

import com.cduestc.dorm.entity.Affiche;
import com.cduestc.dorm.entity.AffichePage;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AfficheMapper {
    List<Affiche> search(AffichePage affichePage);

    int total(AffichePage affichePage);
    @Insert("insert into affiche(title,text,status,write_name,write_time) " +
            "value(#{title},#{text},#{status},#{writeName},#{writeTime})")
    void insertAffiche(Affiche affiche);
    @Delete("delete from affiche where id = #{id}")
    void delById(Integer id);

    void updateById(Affiche affiche);
    @Select("select max(id) from affiche where status = 1")
    Integer searchAfficheFirst();
    @Select("select * from affiche where id = #{afficheId}")
    Affiche searchAffiche(Integer afficheId);
    @Select("select * from affiche where status = 1 order by write_time desc limit 0,5 ")
    List<Affiche> getListTitle();

    void delBatch(@Param("ids") List<Integer> ids);
}
