<template>
    <div>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入学生学号：" v-model="form.studentId"></el-input>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入学生姓名：" v-model="form.studentName"></el-input>
        <el-select v-model="form.college" value-key="id" clearable placeholder="请选择学院">
    <el-option
      v-for="item in college"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
  <el-select v-model="form.status" value-key="id" clearable placeholder="请选择申请状态">
    <el-option
      v-for="item in status"
      :key="item.id"
      :label="item.name"
      :value="item.val">
    </el-option>
  </el-select> 
  <el-date-picker
      v-model="form.createTime"
      type="datetime"
      placeholder="选择日期时间">
    </el-date-picker>
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
      <el-table-column prop="id" label="id" width="60px">
      </el-table-column>
      <el-table-column prop="studentId" label="学生学号" width="160px">
      </el-table-column>
      <el-table-column prop="studentName" label="学生姓名" width="100px">
      </el-table-column>
      <el-table-column prop="college" label="学院" >
      </el-table-column>
      <el-table-column prop="status" label="状况">
        <template v-slot="scope">
            <span v-if="scope.row.status == 0">正在审核</span>
            <span v-if="scope.row.status == 1">审核通过</span>
            <span v-if="scope.row.status == 2">未通过</span>
        </template>
      </el-table-column>
      <el-table-column prop="livingRegion" label="校外居住区域">
      </el-table-column>
      <el-table-column prop="createTime" label="申请时间">
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


    <el-dialog title="编辑申请" :visible.sync="UpdatedialogApply" width="30%" >
      <el-form label-width="100px">
        
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
  <el-form-item label="状态" prop="status">
        
    <el-radio-group v-model="form.status">
    <el-radio :label="0">正在审核</el-radio>
    <el-radio :label="1">审核通过</el-radio>
    <el-radio :label="2">未通过</el-radio>
  </el-radio-group>
      </el-form-item>
    <el-form-item label="居住区域" >
        
      <el-input v-model="form.livingRegion" placeholder="居住区域"></el-input>
    </el-form-item>
    <el-form-item label="居住街道" prop="livingPlace">
      <el-input v-model="form.livingPlace" placeholder="请输入校外居住地址"></el-input>
    </el-form-item>
    <el-form-item label="出校时间" required>
      <el-col :span="11">
        <el-form-item prop="outTime">
          <el-date-picker type="date" placeholder="请输入出校时间" v-model="form.outTime" style="width: 100%;"></el-date-picker>
        </el-form-item>
      </el-col>
    </el-form-item>
    <el-form-item label="紧急联系人" prop="emergencyContact">
      <el-input v-model="form.emergencyConcat" placeholder="请输入紧急联系人"></el-input>
    </el-form-item>
    <el-form-item label="紧急联系方式" prop="emergencyContactPhone">
      <el-input v-model="form.emergencyConcatPhone" placeholder="请输入紧急联系方式"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="updateApply(form)">申请</el-button>
      <el-button @click="resetApply()">取消</el-button>
    </el-form-item>
      </el-form>
    </el-dialog>

    </div>
</template>
<script>
// import {pcaTextArr} from "element-china-area-data"
import request from '@/utils/request'
export default {
    name:'LivingApply',
    data(){
        return{
            
            form:{
                id: '',
                studentName:'',
                studentId:'',
                college:'',
                outTime:'',
                livingRegion:'',
                livingPlace:'',
                emergencyConcat:'',
                emergencyConcatPhone:''
            },
            college:[],
            pageNum:1,
            pageSize:5,
            total: 0,
            ids:[],
            tableData:[],
            updateSchoolApplyDialog: '',
            
            status:[
                {
                    id: 0,
                    name: "正在审核",
                    val: 0
                },
                {
                    id: 1,
                    name: "审核通过",
                    val: 1
                },
                {
                    id: 2,
                    name: "未通过",
                    val: 2
                },
            ],
            UpdatedialogApply: false,
        }
    },
    created(){
        this.load()
    },
    methods: {
        load(){
            this.search()
            this.reset()
            this.getCollege()
        },
        async getCollege(){
        const res = await request.get('/apart/college')
        console.log(res)
        this.college = res.data
    },
        async search(){
            let form = this.form
            form.pageNum = this.pageNum
            form.pageSize = this.pageSize
            const res = await request.post("/apply/school/living/list",form)
            console.log(res.data)
            this.tableData = res.data.schoolApplies
            this.total = res.data.total
        },
        reset(){
            this.form ={
                id: '',
                studentName:'',
                studentId:'',
                college:'',
                outTime:'',
                livingRegion:'',
                livingPlace:'',
                emergencyConcat:'',
                emergencyConcatPhone:''
            }
        },
        async delBatch(){
            console.log(this.ids)
            await request.post("/apply/school/living/batch",this.ids)
            this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.load()
        },
        handleSelectionChange(val){
            console.log(val)
            let ids = []
            for(let i = 0;i<val.length;i++){
                ids.push(val[i].id)
            }
            this.ids = ids
        },
        handleEdit(val){
            console.log(val)
            this.UpdatedialogApply = true 
            this.form = val
        },
        async delById(id){
            console.log(id)
            await request.delete("/apply/school/living/"+id)
            this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.load()
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
        resetApply(){
            this.UpdatedialogApply = false
            this.reset()
        },
        async updateApply(form){
            console.log(form)
            await request.put("/apply/school/living",form)
            this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.resetApply()
            this.search()
        },
    },
}
</script>
<style></style>