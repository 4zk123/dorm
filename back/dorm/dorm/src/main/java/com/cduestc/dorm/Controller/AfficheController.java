package com.cduestc.dorm.Controller;

import com.cduestc.dorm.Mapper.AfficheMapper;
import com.cduestc.dorm.Service.AfficheService;
import com.cduestc.dorm.entity.Affiche;
import com.cduestc.dorm.entity.AffichePage;
import com.cduestc.dorm.entity.AffichePaging;
import com.cduestc.dorm.entity.result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affiche")
public class AfficheController {
    @Autowired
    private AfficheService afficheService;
    @Autowired
    private AfficheMapper afficheMapper;
    @PostMapping("/search")
    public result search(@RequestBody AffichePage affichePage){
        AffichePaging affichePaging = afficheService.search(affichePage);
        return result.success(affichePaging);
    }
    @PostMapping("/insert")
    public result insertAffiche(@RequestBody Affiche affiche){
        afficheService.insertAffiche(affiche);
        return result.success();
    }
//    @GetMapping
//    public result searchToStudent(String studentId){
//        Affiche affiche = afficheService.searchToStudent(studentId);
//        return result.success(affiche);
//    }
    @DeleteMapping("/{id}")
    public result delById(@PathVariable Integer id){
        afficheService.delById(id);
        return result.success();
    }
    @PutMapping("/update")
    public result updateById(@RequestBody Affiche affiche){
        afficheService.updateById(affiche);
        return result.success();
    }
    @PostMapping("/del/batch")
    public result delBatch(@RequestBody List<Integer> ids){
        afficheService.delBatch(ids);
        return result.success();
    }
    @GetMapping("/get")
    @Cacheable(cacheNames = "afficheList")
    public result getListTitle(){
        List<Affiche> affiches = afficheService.getListTitle();
        return result.success(affiches);
    }
    @GetMapping("/search/{id}")
    public result searchAffiche(@PathVariable Integer id){
        Affiche affiche = afficheService.searchAffiche(id);
        return result.success(affiche);
    }
    @GetMapping("/get/first")
    @Cacheable(cacheNames = "afficheFirst")
    public result searchAfficheFirst(){
        Integer id = afficheMapper.searchAfficheFirst();
        Affiche affiche = afficheService.searchAfficheFirst(id);
        return result.success(affiche);
    }
}
