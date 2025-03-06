package com.cduestc.dorm.Controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cduestc.dorm.Service.StudentService;
import com.cduestc.dorm.entity.Student;
import com.cduestc.dorm.entity.StudentPage;
import com.cduestc.dorm.entity.StudentPaging;
import com.cduestc.dorm.entity.result;
import com.cduestc.dorm.utils.ExcelUtils;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @PostMapping("/get")
    public result Select(@RequestBody StudentPage studentPage){
        System.out.println("select2:"+studentPage);
        System.out.println(""+studentPage);
        StudentPaging students = studentService.select(studentPage);
        if(students != null){
            return result.success(students);
        }else {
            return result.error("未查询到数据！");
        }
    }
    @PostMapping
    public result Insert(@RequestBody Student student){
        System.out.println(student);
        Boolean bool = studentService.insert(student);
        if(bool){
            return result.success("插入学生数据成功");
        }else {
            return result.error("插入学生数据失败");
        }
    }
    @DeleteMapping("/{id}")
    public result delete(@PathVariable String id){
        Boolean bool = studentService.delete(id);
        if (bool){
            return result.success("删除数据成功");
        }
        else {
            return result.error("删除数据失败");
        }

    }
    @PostMapping("/del/batch")
    public result delBatch(@RequestBody List<String> ids){
        Boolean bool = studentService.delBatch(ids);
        if (bool){
            return result.success("删除数据成功");
        }
        else {
            return result.error("删除数据失败");
        }
    }
    @PutMapping("/update")
    public result updateById(@RequestBody Student student){
        Boolean bool = studentService.updateById(student);
        return result.success(bool);
    }
    @GetMapping("/get/{id}")
    public result getStudentById(@PathVariable String id){
        Student student = studentService.getStudentById(id);
        return result.success(student);
    }

    @GetMapping("/export")
    public void export(HttpServletResponse response) throws IOException {
        StudentPage studentPage = new StudentPage();
        List<Student> students = studentService.export(studentPage);
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("学生入住信息", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            // 这里需要设置不关闭流
            EasyExcel.write(response.getOutputStream(), Student.class).autoCloseStream(Boolean.FALSE).sheet("模板")
                    .doWrite(students);
        } catch (Exception e) {
            // 重置response
            response.reset();
            response.setContentType("application/json");
            response.setCharacterEncoding("utf-8");
            Map<String, String> map = new HashMap<String, String>();
            map.put("status", "failure");
            map.put("message", "下载文件失败" + e.getMessage());
            response.getWriter().println(JSON.toJSONString(map));
        }
    }
//    @PostMapping("/export)

}
