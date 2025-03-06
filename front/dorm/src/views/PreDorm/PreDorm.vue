<template>
   
    <div>
        <div style="font-size: 40px;margin: 20px;font-family: 'STHupo';">
            预选寝室
        </div>
            <div v-show="show">
                <el-collapse accordion>
  <el-collapse-item  v-for="(item,index) in building" :key="index" @click.native="getRoom(item)">
    <template slot="title">{{item}}</template>
    <div style="display: grid; grid-template-columns: 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr 1fr;">
        <el-button v-for="(i,inx) in room" :key="inx" @click="chooseRoom=i" style="margin-top: 5px;">{{i}}</el-button>
    </div>
  </el-collapse-item>
  
</el-collapse>
            </div>
            <el-button type="primary" @click="submit"  v-show="show">提交</el-button>
        </div>
    </template>
    <script>
import axios from 'axios'




    // import axios from '@/utils/request';

    export default {
        name: 'PreDorms',
        data(){
            return{
                show:false,
                bui:'',
                chooseRoom:'',
              building:["1","18"],
              room:["101","102"],
              bgc:"aliceblue"
            }
        },
        created () {
           this.testCollege()
           this.getBuilding()
        },
        methods:{
           async testCollege(){
                let college = sessionStorage.getItem('college').toString()
                const res = await axios.get("http://localhost:80/api/pre/test/stu?college="+college)
                console.log(res.data)
                if(res.data.data==true){
                    this.show = true
                    this.$message({
          showClose: true,
          message: '正在预选寝室',
          type: 'success'
        });
                }
                else{
                    this.$message({
          showClose: true,
          message: '预选寝室时间错误',
          type: 'error'
        });
                }
           },
           choose(){
            console.log(this.chooseRoom)
           },
           async submit(){
            let form={}
            form.studentId = sessionStorage.getItem('studentId')
            form.building = this.bui
            form.room = this.chooseRoom
            console.log(form)
            if(form.room != null || form.room!=''){
                const res = await axios.post(" http://localhost:80/api/pre/save/room",form)
                console.log(res.data)
                if(res.data.data >0){
                    this.$message({
          showClose: true,
          message: '预选成功',
          type: 'success'
        });
        this.show=false
                }
                else{
                    this.$message({
          showClose: true,
          message: '该寝室人数已满',
          type: 'error'
        });
                }
                

            }
            else{
                this.$message({
          showClose: true,
          message: '数据未输入完全',
          type: 'error'
        });
            }
            
           },
           async getBuilding(){
            let college = sessionStorage.getItem('college').toString()
            const res = await axios.get(" http://localhost:80/api/pre/get/college?college="+college)
            console.log(res.data)
            if(res.data != null){
                this.building = res.data.data
            }else{
                this.$message({
          showClose: true,
          message: '不存在该学院预选寝室',
          type: 'error'
        });
            }
           },
           async getRoom(bui){
            this.bui = bui
            let college = sessionStorage.getItem('college').toString()
            const res = await axios.get(" http://localhost:80/api/pre/get/building?college="+college+"&building="+bui)
            console.log(res.data)
            this.room = res.data.data
           },

        },
    }
    </script>
    <style>

    </style>