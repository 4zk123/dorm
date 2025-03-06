<template>
    <el-menu
    default-active="2"
    class="el-menu-vertical-demo"
    @open="handleOpen"
    @close="handleClose"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b"
    height="870px"
    router>
    <el-menu-item index="/student/index">
      <i class="el-icon-s-check"></i>
      <span slot="title">首页</span>
    </el-menu-item>
    
    <el-submenu>
      <template slot="title">
          <i class="el-icon-s-home"></i>
          <span>寝室房间管理</span>
        </template>
        <el-menu-item index="/student/predorms" >
      <i class="el-icon-s-home"></i>
      <span slot="title">寝室选择</span>
    </el-menu-item>
        <el-menu-item index="/student/changeApply" >
          <i class="el-icon-s-home"></i>
          <span slot="title">更换寝室</span>
        </el-menu-item>
    </el-submenu>
  
    <el-submenu>
      <template slot="title">
          <i class="el-icon-document"></i>
          <span>学生申请</span>
        </template>
    <el-menu-item index="/student/StudentBack" >
      <i class="el-icon-document"></i>
      <span slot="title">返校离校</span>
    </el-menu-item>
    <el-menu-item index="/student/SchoolApply" >
      <i class="el-icon-document"></i>
      <span slot="title">校外居住</span>
    </el-menu-item>
    <el-menu-item index="/student/StudentOther" >
      <i class="el-icon-document"></i>
      <span slot="title">其他申请</span>
    </el-menu-item>
    </el-submenu>
    
  </el-menu>
</template>
<script>
// import StudentMain from '@/components/Main.vue'
// import DormMain from '@/components/DormMain.vue'


export default {
  name: 'DormAside',
  components:{
    // StudentMain,
    // DormMain
  },
  data(){
      return{
        disabled: false,
      }
      
  },
  methods:{
    handleOpen(x){
      console.log(x)
    },
    handleClose(x){
      console.log(x)
    },
  }
}
</script>
<style>

</style>