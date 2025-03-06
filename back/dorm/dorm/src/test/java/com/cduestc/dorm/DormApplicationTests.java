package com.cduestc.dorm;

import cn.hutool.core.date.DateTime;
import com.cduestc.dorm.Controller.*;
import com.cduestc.dorm.Service.DormService;
import com.cduestc.dorm.Service.RegisterService;
import com.cduestc.dorm.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class DormApplicationTests {
    @Autowired
    private PreController preController;
//    private ApplyController applyController;
//    private RegisterService registerService;
//    private DormService dormService;
//    private RegisterController registerController;

//    private AfficheController afficheController;
//    private DormController dormController;
    @Test
    void affiche(){

//        Register register = new Register();
//        register.setId(3);
//        register.setBuilding("30");
//        register.setPhone("123");
//        register.setMatter("维修热水器");
//        register.setUnit("网络管理委员会");
//        registerService.deleteRegisterById(3);
//        System.out.println(registerController.getMatter("寝室维修单位"));
//        AffichePage affichePage = new AffichePage();
//        affichePage.setId(1);
//        affichePage.setStatus(0);
//        affichePage.setWriteName("123");
//        affichePage.setPageNum(1);
//        affichePage.setPageSize(5);
//        System.out.println(affichePage);
//        afficheController.search(affichePage);
    }
//    @Test
//    void insertBunk(){
//        Bunk bunk=new Bunk();
//        bunk.setBuildingId(3);
//        bunk.setRoomId(32);
//        dormService.insertBunk(bunk);
//    }
    @Test
    void testSchoolApply(){
        SchoolApply schoolApply = new SchoolApply();
//        schoolApply.setStudentId("1");
        schoolApply.setId(1);
//        schoolApply.setStudentName("zhangsan");
        schoolApply.setCollege("计算机学院");
//        schoolApply.setLivingRegion("XXXXXXX");
//        schoolApply.setLivingPlace("XXXX");
        schoolApply.setStatus(2);
//        schoolApply.setOutTime(LocalDateTime.now());
//        applyController.insertSchoolApply(schoolApply);
//        ListSchoolApplyPage listSchoolApplyPage = new ListSchoolApplyPage();
//        listSchoolApplyPage.setCollege("计算机学院");
//        listSchoolApplyPage.setStudentId("1");
//        System.out.println(applyController.getListSchoolApply(listSchoolApplyPage));
//        applyController.updateSchoolApplyById(schoolApply);
    }
//    @Test
//    void testBuildingInsert(){
//        BuildingInsert buildingInsert = new BuildingInsert();
//        buildingInsert.setBuilding("13");
//        buildingInsert.setBuildingType(1);
//        buildingInsert.setFloor(6);
//        buildingInsert.setRoomNum(2);
//        buildingInsert.setRoomsNum(3);
//        buildingInsert.setBunkType(4);
//        dormService.inserBuilding(buildingInsert);
//    }
//    @Test
//    void testBuilding(){
//        BuildingPage buildingPage = new BuildingPage();
//        buildingPage.setPageNum(1);
//        buildingPage.setPageSize(5);
//        System.out.println(dormService.countBuildingNum(buildingPage));
//    }
//    @Test
//    void testDeleteBuilding(){
//        String building = "28-5";
//        dormService.deleteBuilding(building);
//    }
//    @Test
//    void testDeleteRoom(){
//        List<DelBatchRoom> a= new ArrayList<>();
//        DelBatchRoom delBatchRoom1 = new DelBatchRoom("107","18");
//        DelBatchRoom delBatchRoom2 = new DelBatchRoom("108","18");
//        a.add(delBatchRoom1);
//        a.add(delBatchRoom2);
//        dormService.delBatchRoom(a);
//
////        String b=a.get(0);
////        System.out.println(b);
////        dormService.delBatchRoom();
//    }
//    @Test
//    void testCountBunk(){
//        BunkPage bunkPage = new BunkPage();
//        bunkPage.setBuilding("18");
//        bunkPage.setRoom("302");
//        bunkPage.setPageNum(1);
//        bunkPage.setPageSize(5);
//
//        System.out.println(dormService.countBunkNum(bunkPage));
//    }
    @Autowired
    private ApplyController applyController;
    @Test
    void testBackApply(){
//        BackApplyPage backApplyPage = new BackApplyPage();
//        backApplyPage.setPageNum(1);
//        backApplyPage.setPageSize(5);
//    backApplyPage.setStudentId("1");
//    backApplyPage.setCollege("计算机学院");
//        System.out.println(applyController.getListBackApply(backApplyPage));
        BackApply backApply = new BackApply();
        backApply.setCollege("计算机学院");
        backApply.setType(1);
        backApply.setOutTime(LocalDateTime.now());
        backApply.setBackTime(LocalDateTime.now());
        backApply.setStayPlace("asdvfnjovdaso");
        backApply.setStayStreet("sdqasddfs");
        backApply.setEmeName("131233");
        backApply.setEmePhone("fdew213");
        backApply.setStudentId("2");
        backApply.setStudentName("李四");
        applyController.insertBackApply(backApply);
    }
    @Test
    void testTakeDorm(){
        PreDorms preDorms = new PreDorms();

        List<String> room1 = new ArrayList<>();
        room1.add("101");
        room1.add("102");
        preDorms.setBuilding("18");
        preDorms.setDorms(room1);
        preDorms.setGender(1);
        preDorms.setCollege("计算机学院");
        preDorms.setStartTime(LocalDateTime.now());
        preDorms.setEndTime(LocalDateTime.now());
        System.out.println(preController.take(preDorms));

//        System.out.println(preController.testToStu("计算机学院"));
    }


    @Test
    void testSaveRoom(){
        SaveRoomIn saveRoomIn = new SaveRoomIn("2","1","6019");
        System.out.println(preController.saveRoom(saveRoomIn));
    }
}
