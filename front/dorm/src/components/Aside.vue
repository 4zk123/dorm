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
      <el-menu-item index="/DormManager/studentMain">
        <i class="el-icon-s-custom"></i>
        <span slot="title">学生入住信息</span>
      </el-menu-item>
      
      <!-- <el-menu-item index="/DormManager/dormMain">
        <i class="el-icon-s-home"></i>
        <span slot="title">寝室房间管理</span>
      </el-menu-item> -->

      <el-submenu>
      <template slot="title">
          <i class="el-icon-s-home"></i>
          <span>寝室房间管理</span>
        </template>
    <el-menu-item index="/DormManager/Building" >
      <i class="el-icon-s-home"></i>
      <span slot="title">楼栋管理</span>
    </el-menu-item>
    <el-menu-item index="/DormManager/Room" >
      <i class="el-icon-s-home"></i>
      <span slot="title">房间管理</span>
    </el-menu-item>
    <el-menu-item index="/DormManager/Bunk" >
      <i class="el-icon-s-home"></i>
      <span slot="title">床位管理</span>
    </el-menu-item>
    <el-menu-item index="/DormManager/Pre"  v-if="per<1">
      <i class="el-icon-s-home"></i>
      <span slot="title">寝室预选</span>
    </el-menu-item>
    </el-submenu>

      <el-submenu v-if="per<1">
      <template slot="title">
          <i class="el-icon-document"></i>
          <span>学生申请</span>
        </template>
    <el-menu-item index="/ManageApply/BackApply" >
      <i class="el-icon-document"></i>
      <span slot="title">返校离校</span>
    </el-menu-item>
    <el-menu-item index="/ManageApply/LivingApply" >
      <i class="el-icon-document"></i>
      <span slot="title">校外居住</span>
    </el-menu-item>
    <el-menu-item index="/ManageApply/ManageChange" >
      <i class="el-icon-document"></i>
      <span slot="title">换寝申请</span>
    </el-menu-item>
    <el-menu-item index="/ManageApply/OtherApply" >
      <i class="el-icon-document"></i>
      <span slot="title">其他申请</span>
    </el-menu-item>
    </el-submenu>
      <!-- <el-menu-item index="/DormManager/dormApproval" :disabled=disabled>
        <i class="el-icon-document"></i>
        <span slot="title">学生申请审批</span>
      </el-menu-item> -->
      <el-menu-item index="/DormManager/dormRegister">
        <i class="el-icon-s-check"></i>
        <span slot="title">人员登记</span>
      </el-menu-item>
      <el-menu-item index="/DormManager/dormAffiche" v-if="per<1">
        <i class="el-icon-s-comment"></i>
        <span slot="title">公告信息</span>
      </el-menu-item>
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
          per:0,
        }
        
    },
    created(){
        this.testPermission()
    },
    methods:{
      handleOpen(x){
        console.log(x)
      },
      handleClose(x){
        console.log(x)
      },
      testPermission(){
        this.per = sessionStorage.getItem('permission')
        console.log(this.per)
      },
    }
}
</script>
<style>

</style>