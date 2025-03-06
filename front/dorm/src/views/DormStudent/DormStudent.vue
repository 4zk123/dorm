<template>
<div id="app">
    <el-container>
  <el-aside width="200px" style="height: 870px;">
    <StudentAside></StudentAside>
  </el-aside>
  <el-container>
    <el-header>
      <StudentHeader></StudentHeader>
    </el-header>
    <el-main>
        <router-view></router-view>
  </el-main>
  </el-container>
</el-container>
  </div>
</template>
<script>
import StudentAside from '@/components/StudentAside.vue'
import StudentHeader from '@/components/StudentHeader.vue'
export default {
    name: 'StudentManage',
    components:{
        StudentAside,
        StudentHeader,
        
    },
}
</script>
<style scoped>

</style>