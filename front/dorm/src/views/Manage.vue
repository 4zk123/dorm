<template>
    <div style="display: block; padding: 0px;">
    <el-container>
  <el-aside width="200px" style="height: 870px;">Aside</el-aside>
  <el-container>
    <el-header>Header</el-header>
    <el-main>

        <div>
      <div style="padding: 10px 0;margin-top: 20px;margin-left: 20px;">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入ID："></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入名称："></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入地址："></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0;display: block;">
      <el-button type="primary" @click="handleAdd">新增<i class="el-icon-circle-plus"></i></el-button>
      <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm  = "delBatch"
      >
        <el-button type="danger" slot="reference" style="margin-left: 8px;margin-right: 8px">批量删除<i class="el-icon-remove"></i></el-button>
      </el-popconfirm>
      <el-button type="primary">导入<i class="el-icon-bottom-left"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-bottom-right"></i></el-button>
    </div>
      </div>

    </el-main>
  </el-container>
</el-container>
</div>
</template>

<script>
export default{
    name: "ManageView",
    components: {

    }
}
</script>

<style scoped>
  /* .el-header, .el-footer {
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
  }
  
  .el-main {
    display: block;
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 100%;
    padding: 0px;
    
  } */

</style>