package com.cduestc.dorm.Service;

import com.cduestc.dorm.entity.Affiche;
import com.cduestc.dorm.entity.AffichePage;
import com.cduestc.dorm.entity.AffichePaging;

import java.util.List;

public interface AfficheService {
    AffichePaging search(AffichePage affichePage);

    void insertAffiche(Affiche affiche);

    void delById(Integer id);

    void updateById(Affiche affiche);

//    Affiche searchToStudent(String studentId);

    void delBatch(List<Integer> ids);


    List<Affiche> getListTitle();

    Affiche searchAffiche(Integer id);

    Affiche searchAfficheFirst(Integer id);
}
