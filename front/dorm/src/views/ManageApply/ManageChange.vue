<template>
    <div>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入ID：" v-model="form.studentId"></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入姓名："  v-model="form.studentName"></el-input>
        <el-select v-model="form.college" value-key="id" clearable placeholder="请选择学院">
    <el-option
      v-for="item in colleges"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
  <el-select v-model="form.type" value-key="id" clearable placeholder="请选择类型" >
    <el-option
      v-for="item in types"
      :key="item.id"
      :label="item.type"
      :value="item.id">
    </el-option>
  </el-select>
    <div style="margin: 20px;">
        
      <el-button  type="primary" @click="search">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
      <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm  = "delBatch"
      >
        <el-button type="danger" slot="reference" style="margin-left: 8px;margin-right: 8px;">批量删除<i class="el-icon-remove"></i></el-button>
      </el-popconfirm>
    </div>
    <el-table :data="tableData" border @selection-change="handleSelectionChange" >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="60">
      </el-table-column>
      <el-table-column prop="studentName" label="学生姓名" width="80">
      </el-table-column>
      <el-table-column prop="studentId" label="学生学号" >
      </el-table-column>
      <el-table-column prop="type" label="申请类型" width="80">
        <template v-slot="scope">
            <span v-if="scope.row.type ==0">互换寝室</span>
            <span v-if="scope.row.type ==1">调入空寝室</span>
        </template>
      </el-table-column>
      <el-table-column prop="exName" label="互换学生" width="80">
      </el-table-column>
      <el-table-column prop="exId" label="互换学生学号" >
      </el-table-column>
      <el-table-column prop="status" label="状态" >
        <template v-slot="scope">
            <span v-if="scope.row.status ==0">正在审核</span>
            <span v-if="scope.row.status ==1">审核通过</span>
            <span v-if="scope.row.status ==2">审核驳回</span>
        </template>
      </el-table-column>
      <el-table-column prop="college" label="学院" >
      </el-table-column>
      <el-table-column prop="building" label="寝室" width="60">
        <template v-slot="scope">
        <span>{{scope.row.building}}-{{scope.row.room}}</span></template>
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
          <el-button type="success" @click="handleEdit(scope.row)" style="width: 80px;">编辑 <i class="el-icon-edit"></i></el-button>

          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delById(scope.row.id)"
          >
            <el-button type="danger" slot="reference" style="margin-left: 5px; width: 80px;">删除 <i class="el-icon-remove"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageNum"
        :page-sizes="[5, 10]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total>
    </el-pagination>

    <el-dialog title="修改申请" :visible.sync="Updatedialog" width="45%" >
      <el-form label-width="100px">
        <el-form-item label="楼栋">
            <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom()">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>  
      </el-form-item> 
      <el-form-item label="学生姓名" prop="studentName">
      <el-input v-model="form.studentName" placeholder="请输入学生姓名"></el-input>
    </el-form-item>
    <el-form-item label="学号" prop="studentId">
      <el-input v-model="form.studentId" placeholder="请输入学号"></el-input>
    </el-form-item>
    <el-form-item label="申请原因" prop="reason">
      <el-input v-model="form.reason" placeholder="请输入原因"></el-input>
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
        
  <el-form-item label="状态" prop="status">
        
        <el-radio-group v-model="form.status">
        <el-radio :label="0">正在审核</el-radio>
        <el-radio :label="1">审核通过</el-radio>
        <el-radio :label="2">审核驳回</el-radio>
      </el-radio-group>
      
          </el-form-item>
    
  <el-form-item label="类型" prop="type">
        
    <el-radio-group v-model="form.type">
    <el-radio :label="0">互换寝室</el-radio>
    <el-radio :label="1">调入空寝室</el-radio>
  </el-radio-group>
  
      </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="Add(form)">添加</el-button>
      <el-button @click="click">取消</el-button>
    </el-form-item>
      </el-form>
    </el-dialog>
    
    </div>
</template>
<script>
import axios from 'axios'


import request from '@/utils/request'

export default {
    name:'ManageChange',
    data(){
      
        return{
            types:[
                    {
                        id:0,
                        type:'互换寝室'
                    },
                    {
                        id:1,
                        type:'调入空寝室'
                    },
                ],
          colleges:[],
          buildings:[],
            dorms:[
              
            ],
            getDorms:[
            {label:"101",
              key:1
            },
              {label:"102",
                key:2
              },
              {
                label:"103",
                key:3
              },
            ],
            Updatedialog:false,
            startTime:'',
            endTime:'',
            form:{
                dorms:[],
                college:'',
                startTime:'',
                endTime:'',
                gender:'',
                building:'',
            },
            pageNum:1,
            pageSize:5,
            total: 0,
            ids:[],
            tableData:[],
           
        }
    },
    created(){
      this.reset()
        this.getBuilding()
        this.getCollege()
        this.search()
    },
    methods: {
        
        reset(){
            this.form ={
               
            },
            this.getDorms = []
        },
        async delBatch(){
            console.log(this.ids)
            await request.post("/apply/change/del/batch",this.ids)
            this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.reset()
            this.search()
        },
        handleSelectionChange(val){
            console.log(val)
            let ids = []
            for(let i = 0;i<val.length;i++){
                ids.push(val[i].id)
            }
            this.ids = ids
        },
        click(){
          this.Updatedialog = false
          this.reset()
        },
        handleEdit(val){
            console.log(val)
            this.Updatedialog = true
            this.form = val
        },
        async delById(id){
            console.log(id)
            await request.delete("/apply/change/"+id)
            this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.reset()
            this.search()
        },
        handleSizeChange(size){
            console.log(size)
            this.pageSize=size
            this.search()
        },
        handleCurrentChange(page){
            console.log(page)
            this.pageNum = page
            this.search()
        },
       async update(){
        
       },
        async search(){
            let form = this.form
            form.pageNum = this.pageNum
            form.pageSize = this.pageSize
            console.log(form)
            const res = await request.post("/apply/change",form)
            console.log(res.data)
            this.tableData = res.data.changeApplies
            this.total = res.data.total
        },
        async getBuilding(){
        const res = await axios.get("http://localhost:80/api/dorm/building")
        console.log(res.data)
        this.buildings = res.data.data
      },
      async getCollege(){
      const res = await axios.get('http://localhost:80/api/apart/college')
      console.log(res.data)
      this.colleges = res.data.data
    },

    },
}
</script>
<style>
.el-transfer-panel__item.el-checkbox .el-checkbox__label {
    margin-left: 20px;
}
</style>