<template>
<div>
    <div style="display: flex; justify-content: space-between;">
    <div class="affiche">
        <h2>  
            {{ affiche.title }}
        </h2>
        <p>            {{affiche.text}}
        </p>
        <div style="display: flex;justify-content: space-between;">
        <p style="margin: 5px;">发布人：{{affiche.writeName}}</p>
        <p style="margin: 5px;">发布时间：{{affiche.writeTime}}</p>
    </div>
    </div>
    <div class="personal">
        <h2>个人信息</h2>
        <span>学号：{{personal.id}}</span>
        <span>姓名：{{personal.name}}</span>
        <span>学院：{{personal.college}}</span>
        <span>专业：{{personal.subject}}</span>
        <span>年级：{{personal.grade}}</span>
        <span style="margin-bottom: 10px;">班级：{{personal.sclass}}</span>
        <span style="margin-bottom: 10px;">寝室：{{personal.building}}-{{personal.room}}-{{personal.bunk}}</span>
    </div>
</div>
<div style="margin: 20px;">
    <el-button type="primary" @click="getListAffiche()">查看往期公告</el-button>
    <!-- <el-button type="primary">查看返校离校申请</el-button>
    <el-button type="primary">查看校外居住申请</el-button>
    <el-button type="primary">查看换寝申请</el-button>
    <el-button type="primary">查看其他申请</el-button> -->
</div>
<div style=" margin:50px;">
    <span style="font-size: 60px; font-family: 'STHupo';">欢迎{{personal.name}}来到寝室管理系统!</span>
</div>


<div  style="width: 400px;">
    <el-dialog title="往期公告" :visible.sync="afficheShow">
        <el-collapse accordion>
  <el-collapse-item v-for="(item,index) in afficheList" :key="index">
    <template slot="title">{{item.title}}</template>
    <div>{{ item.text }}</div>
    <div>编辑者：{{item.writeName}}</div>
    <div>编辑时间：{{item.writeTime}}</div>
  </el-collapse-item>
</el-collapse>
</el-dialog>

    
</div>


    </div>
</template>
<script>
import axios from '@/utils/request';
export default {
    name: 'StudentIndex',
    data(){
        return{
            afficheShow:false,
            affiche:{
                title: '',
                text:'',
                writeName:'',
                writeTime: ''
            },
            afficheList:[{}],
            personal:{
                id: '',
                name:'',
                gender:'',
                age:'',
                college:'',
                subject:'',
                grade:'',
                class:'',
                building:'',
                buildings:'',

            },
            schoolApplies:[
               
            ],
            backApplies:[],
            elseApplies:[],

            
        }
    },
    created(){
        this.getPersonl()
        this.getAfficheFirst()
    },
    methods:{
        async getPersonl(){
            // let studentName = sessionStorage.getItem("student")
            let studentId = sessionStorage.getItem("studentId")
            const res = await axios.get("student/get/"+studentId)
            console.log(res.data)
            this.personal = res.data
        },
        
        async getAffiche(id){
           
            const res = await axios.get("affiche/search/"+id)
            console.log(res.data)
        },
        async getAfficheFirst(){
            const res = await axios.get("affiche/get/first")
            console.log(res.data)
            this.affiche = res.data
        },
        async getListAffiche(){
            this.afficheShow = true
            const res = await axios.get("affiche/get")
            console.log(res.data)
            this.afficheList = res.data
        },
        async getListSchoolApplies(){

        }
    },
}
</script>
<style>
.affiche{
    width: 600px;
    height: 350px;
    border: 1px solid gray;
    background-color: rgb(254, 252, 252);
    border-radius: 5%;
    margin-left: 80px;
    margin-top: 20px;
    display: grid;
}
.personal{
    width: 400px;
    height: 350px;
    border: 5px solid gray;
    background-color: rgb(254, 252, 252);
    border-radius: 5%;
    margin-right: 80px;
    margin-top: 20px;
    display: grid;
    flex-wrap: wrap;
    align-content: space-between;
}
.apply{
    width: 300px;
    height: 350px;
    border: 5px solid gray;
    background-color: rgb(214, 214, 214);
    border-radius: 5%;
    margin: 20px;
    display: grid;
    flex-wrap: wrap;
    
}
.personal>span{
    text-align: left;
    margin-left: 20px;
    border-bottom: 1px solid black;
    /* background-color: aqua; */
}
</style>