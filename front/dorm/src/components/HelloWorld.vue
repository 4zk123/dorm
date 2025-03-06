<template>
  <div class="hello">
    <el-container>
      <el-aside width="200px">
      <el-menu
      default-active="2"
      class="el-menu-vertical-demo"
      @open="handleOpen"
      @close="handleClose"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b"
      height="870px"
      :router='true'>
      <el-menu-item index="/DormManager">
        <i class="el-icon-s-custom"></i>
        <span slot="title">学生入住信息</span>
      </el-menu-item>
      
      <el-menu-item index="2">
        <i class="el-icon-s-home"></i>
        <span slot="title">寝室房间管理</span>
      </el-menu-item>


      <el-menu-item index="3" :disabled=disabled>
        <i class="el-icon-document"></i>
        <span slot="title">学生申请审批</span>
      </el-menu-item>
      <el-menu-item index="4">
        <i class="el-icon-s-check"></i>
        <span slot="title">人员登记</span>
      </el-menu-item>
      <el-menu-item index="5">
        <i class="el-icon-s-comment"></i>
        <span slot="title">公告信息</span>
      </el-menu-item>
    </el-menu>
    </el-aside>

  <el-container>
    <el-header>
    <div style="width: 100%; height: 100%; display: flex;">
      <img src="../assets/logo.png" alt="logo" style="width: 60px; height: 60px; float: left;">
      <div style="font-size: 30px; margin-left: 500px;">HEllo</div>
      <img :src=imgurl :alt=sname style="border-radius: 50%;margin-left: 530px;">
      <i class="el-icon-switch-button" style="font-size: 30px; margin-top: 20px; margin-left: 20px;"></i>
    </div>
  </el-header>
    <el-main>
      <!-- <div>
      <div style="padding: 10px 0;margin-top: 20px;margin-left: 20px;">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入ID："></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入名称："></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-position" placeholder="请输入地址："></el-input>
      <el-button style="margin-left: 5px" type="primary" @click="load">搜索</el-button>
      <el-button style="margin-left: 5px" type="warning" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0">
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
      <el-button type="primary"  @click="test">导入<i class="el-icon-bottom-left"></i></el-button>
      <el-button type="primary">导出<i class="el-icon-bottom-right"></i></el-button>
    </div>
    <el-table :data="tableData" border :header-cell-class-name="headerBackgroundColor" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="80">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="140">
      </el-table-column>
      <el-table-column prop="nicakname" label="昵称" width="120">
      </el-table-column>
      <el-table-column prop="email" label="邮箱">
      </el-table-column>
      <el-table-column prop="phone" label="电话">
      </el-table-column>
      <el-table-column prop="address" label="地址">
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="success" @click="handleEdit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>

          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm  = del(scope.row.id)
          >
            <el-button type="danger" slot="reference" style="margin-left: 5px">删除 <i class="el-icon-remove"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total>
    </el-pagination>
      </div> -->
      <DormMain></DormMain>
    </el-main>
  </el-container>
</el-container>
  </div>
</template>

<script>
import DormMain from '@/components/Main.vue'
import request from '@/utils/request'
export default {
  name: 'HelloWorld',
  components:{
    DormMain
  },
  props: {
    imgurl: String
  },
  data(){
    return{
      disabled: false,
      headerBackgroundColor:'',
      pageNum:0,
      pageSize: 10,
      total:0,
      tableData: [],
      sname: ''
    }
  },
  created() {

  },
  methods: {
    async test(){
      console.log("123123")
        const res = await request.get("/test")
        console.log(res)
      },
    handleCurrentChange(){},
    handleSizeChange(){},
    handleSelectionChange(){},
    delBatch() {},
    handleAdd() {},
    handleOpen(x){
      console.log(x)
    },
    handleClose(x){
      console.log(x)
    },
    load(){
      console.log("load")
    },
    reset(){
      console.log("reset")
    }
    },

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
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
