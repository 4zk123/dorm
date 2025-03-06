package com.cduestc.dorm.Service.Impl;

import com.cduestc.dorm.Mapper.AfficheMapper;
import com.cduestc.dorm.Mapper.AfficheStudentMapper;
import com.cduestc.dorm.Service.AfficheService;
import com.cduestc.dorm.entity.Affiche;
import com.cduestc.dorm.entity.AffichePage;
import com.cduestc.dorm.entity.AffichePaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AfficheServiceImpl implements AfficheService {
    @Autowired
    private AfficheMapper afficheMapper;
    @Autowired
    private AfficheStudentMapper afficheStudentMapper;
    @Override
    public AffichePaging search(AffichePage affichePage) {
        Integer pageNum = (affichePage.getPageNum() - 1) * affichePage.getPageSize();
        affichePage.setPageNum(pageNum);
        List<Affiche> affiches = afficheMapper.search(affichePage);
        int total = afficheMapper.total(affichePage);
        AffichePaging affichePaging = new AffichePaging();
        affichePaging.setAffiches(affiches);
        affichePaging.setTotal(total);
        return affichePaging;
    }

    @Override
    public void insertAffiche(Affiche affiche) {
        affiche.setWriteTime(LocalDateTime.now());
        afficheMapper.insertAffiche(affiche);
    }

    @Override
    public void delById(Integer id) {
        afficheMapper.delById(id);
        afficheStudentMapper.delByAfficheId(id);
    }

    @Override
    public void updateById(Affiche affiche) {
        afficheMapper.updateById(affiche);
    }

//    @Override
//    public Affiche searchToStudent(String studentId) {
//        Affiche affiche = new Affiche();
//        int afficheId = afficheMapper.searchId();
//        int status = afficheStudentMapper.searchStatus(afficheId,studentId);
//        if(status == 1){
//            affiche = afficheMapper.searchAffiche(afficheId);
//            return affiche;
//        }
//        return affiche;
//    }


    @Override
    public void delBatch(List<Integer> ids) {
        afficheMapper.delBatch(ids);
    }

    @Override
    public List<Affiche> getListTitle() {
        List<Affiche> affiches = afficheMapper.getListTitle();
        return affiches;
    }

    @Override
    public Affiche searchAffiche(Integer id) {
        Affiche affiche = afficheMapper.searchAffiche(id);
        return affiche;
    }

    @Override
    public Affiche searchAfficheFirst(Integer id) {
        Affiche affiche = afficheMapper.searchAffiche(id);
        return affiche;
    }


}
