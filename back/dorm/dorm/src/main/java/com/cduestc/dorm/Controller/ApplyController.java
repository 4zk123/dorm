package com.cduestc.dorm.Controller;

import com.cduestc.dorm.Service.BackApplyService;
import com.cduestc.dorm.Service.ChangeApplyService;
import com.cduestc.dorm.Service.OtherApplyService;
import com.cduestc.dorm.Service.SchoolApplyService;
import com.cduestc.dorm.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/apply")
public class ApplyController {
    @Autowired
    private SchoolApplyService schoolApplyService;
    @Autowired
    private BackApplyService backApplyService;
    @Autowired
    private OtherApplyService otherApplyService;
    @Autowired
    private ChangeApplyService changeApplyService;
    @PostMapping("/school/living")
    public result insertSchoolApply(@RequestBody SchoolApply schoolApply){
        schoolApplyService.insertSchoolApply(schoolApply);
        return result.success();
    }
    @GetMapping("/school/living/id")
    public result getListSchoolApplyById(String studentId){
        List<SchoolApplyDto> schoolApplyDtos = schoolApplyService.getListSchoolApplyById(studentId);
        return result.success(schoolApplyDtos);
    }
    @PostMapping("/school/living/list")
    public result getListSchoolApply(@RequestBody ListSchoolApplyPage page){
        ListSchoolApplyPaging schoolApplies = schoolApplyService.getListSchoolApply(page);
        return result.success(schoolApplies);
    }
    @DeleteMapping("/school/living/{id}")
    public result delSchoolApply(@PathVariable Integer id){
        schoolApplyService.delSchoolApply(id);
        return result.success();
    }
    @PutMapping("/school/living")
    public result updateSchoolApplyById(@RequestBody SchoolApply schoolApply){
        schoolApplyService.updateSchoolApplyById(schoolApply);
        return result.success();
    }
    @PostMapping("/school/living/batch")
    public result delBatchSchoolApply(@RequestBody List<Integer> ids){
        schoolApplyService.delBatchSchoolApply(ids);
        return result.success();
    }

    //返校离校申请
    //管理员功能：搜索返校离校申请接口
    @PostMapping("/back/getList")
    public result getListBackApply(@RequestBody BackApplyPage backApply){
        BackApplyPaging backApplyPaging = backApplyService.getBackApplyPage(backApply);
        return result.success(backApplyPaging);
    }
    //学生功能：新增返校离校申请
    @PostMapping("/back/insert")
    public result insertBackApply(@RequestBody BackApply backApply){
        backApplyService.insertBackApply(backApply);
        return result.success();
    }
    //管理员&学生功能：更新返校离校申请
    @PutMapping("/back/up")
    public result updateBackApply(@RequestBody BackApply backApply){
        backApplyService.updateBackApply(backApply);
        return result.success();
    }
    @DeleteMapping("/back/{id}")
    public result delBackApplyById(@PathVariable Integer id){
        backApplyService.delBackApplyById(id);
        return  result.success();
    }
    @PostMapping("/back/del/batch")
    public result delBatchBackApply(@RequestBody List<Integer> ids){
        backApplyService.delBatchBackApply(ids);
        return result.success();
    }

    //其他申请
    //管理员：搜索其他申请
    @PostMapping("/other/getList")
    public result getListOtherApply(@RequestBody OtherApplyPage otherApplyPage){
        OtherApplyPaging otherApplyPaging = otherApplyService.getListOtherApply(otherApplyPage);
        return result.success(otherApplyPaging);
    }
    //新增其他申请
    @PostMapping("/other/insert")
    public result insertOtherApply(@RequestBody OtherApply otherApply){
        otherApplyService.insertOtherApply(otherApply);
        return result.success();
    }
    //修改其他申请
    @PutMapping("/other/update")
    public result updateOtherApply(@RequestBody OtherApply otherApply){
        otherApplyService.updateOtherApply(otherApply);
        return result.success();
    }
    //删除其他申请
    @DeleteMapping("/other/{id}")
    public result delOtherApply(@PathVariable Integer id){
        otherApplyService.delOtherApply(id);
        return result.success();
    }
    //批量删除其他申请
    @PostMapping("/other/del/batch")
    public result delBatchOtherApply(@RequestBody List<Integer> ids){
        otherApplyService.delBatchOtherApply(ids);
        return result.success();
    }

    //寝室申请
    //新增
    @PostMapping("/change/insert")
    public result insertChangeApply(@RequestBody ChangeApply changeApply){
        System.out.println(changeApply);
        changeApplyService.insertChangeApply(changeApply);
        return result.success();
    }
    //根据id删除
    @DeleteMapping("/change/{id}")
    public result delChangeApply(@PathVariable Integer id){
        changeApplyService.delChangeApply(id);
        return result.success();
    }
    //更新
    @PutMapping("/change/up")
    public result upChangeApply(@RequestBody ChangeApply changeApply){
        changeApplyService.upChangeApply(changeApply);
        return result.success();
    }
    //批量删除
    @PostMapping("/change/del/batch")
    public result delBatchChangeApply(@RequestBody List<Integer> ids){
        changeApplyService.delBatchChangeApply(ids);
        return result.success();
    }
    //查看
    @PostMapping("/change")
    public result getChangeApply(@RequestBody ChangeApplyPage changeApplyPage){
        ChangeApplyPaging changeApplyPaging = changeApplyService.getChangeApply(changeApplyPage);
        return result.success(changeApplyPaging);
    }
}
