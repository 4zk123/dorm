<template>
    <div>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入学生学号：" v-model="form.studentId"></el-input>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入学生姓名：" v-model="form.studentName"></el-input>
  <el-select v-model="form.type" value-key="id" clearable placeholder="请选择状态">
    <el-option
      v-for="item in status"
      :key="item.id"
      :label="item.name"
      :value="item.val">
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
      <el-table-column prop="id" label="id" width="60px">
      </el-table-column>
      <el-table-column prop="studentId" label="学生学号" width="160px">
      </el-table-column>
      <el-table-column prop="studentName" label="学生姓名" width="100px">
      </el-table-column>
      <el-table-column prop="status" label="状况">
        <template v-slot="scope">
            <span v-if="scope.row.status == 0">正在审核</span>
            <span v-if="scope.row.status == 1">审核通过</span>
            <span v-if="scope.row.status == 2">未通过</span>
        </template>
      </el-table-column>
      <el-table-column prop="building" label="栋" width="40">
      </el-table-column>
      <el-table-column prop="room" label="寝室" width="80">
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
    
  <el-form-item label="状态" prop="status">
        
    <el-radio-group v-model="form.status">
    <el-radio :label="0">正在审核</el-radio>
    <el-radio :label="1">审核通过</el-radio>
    <el-radio :label="2">未通过</el-radio>
  </el-radio-group>
  
      </el-form-item>
      
      
         
          <el-form-item label="栋">
            <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom()">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select> 
        
      </el-form-item> 
      <el-form-item label="寝室">
      <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" >
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
</el-form-item> 
    <el-form-item>
        <el-input
  type="textarea"
  placeholder="请输入内容"
  v-model="form.apply"
  maxlength="100"
  show-word-limit
>
</el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="updateApply(form)">修改</el-button>
      <el-button @click="resetApply()">取消</el-button>
    </el-form-item>
      </el-form>
    </el-dialog>

    </div>
</template>
<script>
import request from '@/utils/request'
export default {
    name:'OtherApply',
    data(){
        return{
            form:{
                id: '',
                studentName:'',
                studentId:'',
                room:'',
                status:'',
                building:'',
                apply:'',
            },
            pageNum:1,
            pageSize:5,
            total: 0,
            ids:[],
            tableData:[],
            buildings:[],
            rooms:[],
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
            types:[{
                id:0,
                name:'留校',
                val:0
            },
            {
                id:1,
                name:'离校',
                val:1
            }
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
            this.getBuilding()
        },
        async getBuilding(){
        const res = await request.get("/dorm/building")
        console.log(res)
        this.buildings = res.data
      },
      async getRoom(){
        let building = this.form.building
        console.log(building)
        const res = await request.get("/dorm/room?building="+building)
        console.log(res)
        this.rooms = res.data
      },
        async search(){
            let form = this.form
            form.pageNum = this.pageNum
            form.pageSize = this.pageSize
            const res = await request.post("/apply/other/getList",form)
            console.log(res.data)
            this.tableData = res.data.otherApplies
            this.total = res.data.total
        },
        reset(){
            this.form ={
                id: '',
                studentName:'',
                studentId:'',
                room:'',
                status:'',
                building:'',
                apply:'',
            }
        },
        async delBatch(){
            console.log(this.ids)
            await request.post("/apply/other/del/batch",this.ids)
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
            await request.delete("/apply/other/"+id)
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
            form.apply = form.apply.toString()
            await request.put("/apply/other/update",form)
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