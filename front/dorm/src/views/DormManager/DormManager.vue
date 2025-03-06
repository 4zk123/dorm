<template>
<div id="app">
    <el-container>
  <el-aside width="200px" style="height: 870px;">
    <DormAside></DormAside>
  </el-aside>
  <el-container>
    <el-header>
      <DormHeader></DormHeader>
    </el-header>
    <el-main>
        <router-view></router-view>
  </el-main>
  </el-container>
</el-container>
  </div>
</template>
  <script>
import DormHeader from '@/components/Header.vue'
import DormAside from '@/components/Aside.vue'
// import StudentMain from '@/components/Main.vue'
// import DormMain from '@/components/DormMain.vue'
// import request from '@/utils/request'
export default {
  name: 'App',
  components: {
    DormHeader,
    DormAside,
    // StudentMain,
    // DormMain
  },
  comments:{
    DormHeader,
    DormAside,
  },
  data() {
    return{
      
    }
  },
  methods: {
    

  }
}
</script>
<style>
#app {
  height: 100%;
  min-height: 100vh;
}
.el-header, .el-footer {
    background-color: #B3C0D1;
    color: #333;
    text-align: center;
    line-height: 60px;
  }
  .el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
    line-height: 200px;
    padding: 0px;
    height: 870px;
  }
  .el-main{
    display: block;
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 100%;
    padding: 0px;
  }
  
</style>