<template>
<el-form :model="form" :rules="rules" ref="form" label-width="100px">
    <el-form-item label="学生姓名" prop="studentName">
      <el-input v-model="form.studentName" placeholder="请输入学生姓名"></el-input>
    </el-form-item>
    <el-form-item label="学号" prop="studentId">
      <el-input v-model="form.studentId" placeholder="请输入学号"></el-input>
    </el-form-item>
    <el-form-item label="学院" prop="college">
    <el-select v-model="form.college" value-key="id" clearable placeholder="请选择学院">
    <el-option
      v-for="item in college"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select>
  </el-form-item>
    <el-form-item label="居住区域" prop="livingRegion">
        <el-cascader
        size="large"
        :options="pcaTextArr"
        v-model="form.livingRegion">
      </el-cascader>
    </el-form-item>
    <el-form-item label="居住街道" prop="livingPlace">
      <el-input v-model="form.livingPlace" placeholder="请输入校外居住地址"></el-input>
    </el-form-item>
    <el-form-item label="出校时间" required>
      <el-col :span="11">
        <el-form-item prop="outTime">
          <el-date-picker type="date" placeholder="请输入出校时间" v-model="form.outTime" style="width: 600px;left: 370px"></el-date-picker>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item label="紧急联系人" prop="emergencyContact">
      <el-input v-model="form.emergencyContact" placeholder="请输入紧急联系人"></el-input>
    </el-form-item>
    <el-form-item label="紧急联系方式" prop="emergencyContactPhone">
      <el-input v-model="form.emergencyContactPhone" placeholder="请输入紧急联系方式"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="submitApply(form)">申请</el-button>
      <el-button @click="resetApply()">重置</el-button>
    </el-form-item>
  </el-form>
  </template>
  <script>
  import {
  pcaTextArr,
} from "element-china-area-data";
import request from '@/utils/request'
    export default {
        name: 'SchoolApply',
      data() {
        return {
            pcaTextArr,
            // OutLivingRegion:[],
            formData:{},
            form:{
                studentName:'',
                studentId:'',
                college:'',
                outTime:'',
                livingRegion:'',
                livingPlace:'',
                emergencyContact:'',
                emergencyContactPhone:''
            },
            college:[],
          rules: {
            studentName: [
              { required: true, message: '请输入学生姓名', trigger: 'blur' },
              { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
            ],
            studentId: [
              { required: true, message: '请输入学号', trigger: 'blur' }
            ],
            college: [
              { required: true, message: '请选择学院', trigger: 'change' }
            ],
            livingRegion: [
              { required: true, message: '请选择活动区域',  trigger: 'change' }
            ],
            livingPlace: [
              { required: true, message: '请输入校外居住地址', trigger: 'blur' }
            ],
            
          }
        };
      },
      created() {
        this.getCollege()
      },
      methods: {
        async getCollege(){
        const res = await request.get('/apart/college')
        console.log(res)
        this.college = res.data
    },
        async submitApply(form) {
            form.livingRegion = form.livingRegion.toString() 
            console.log(form)
            await request.post("/apply/school/living",form)
            this.$message({
          showClose: true,
          message: '申请成功',
          type: 'success'
        });
           this.resetApply()
        },
        resetApply() {
          this.form = {
                studentName:'',
                studentId:'',
                college:'',
                outTime:'',
                livingRegion:'',
                livingPlace:'',
                emergencyContact:'',
                emergencyContactPhone:''
            }
        }
      }
    }
  </script>
<style scoped>
.el-input{
    width:600px;
    left:0
}
</style>