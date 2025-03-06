<template>
    <div>
        <el-select v-model="form.college" value-key="id" clearable placeholder="请选择学院">
    <el-option
      v-for="item in colleges"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
  <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>  
    <div style="margin: 20px;">
        <el-button type="primary" @click="handleAdd" style="width: 100px;line-height: 0px;">新增<i class="el-icon-circle-plus"></i></el-button>
      <el-button  type="primary" @click="search">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
    <el-table :data="tableData" border @selection-change="handleSelectionChange" >
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="college" label="学院" >
      </el-table-column>
      <el-table-column prop="building" label="楼栋" >
      </el-table-column>
      <el-table-column prop="startTime" label="开始时间" >
      </el-table-column>
      <el-table-column prop="endTime" label="结束时间" >
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope">
        

          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delById(scope.row.college,scope.row.building)"
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

    <el-dialog title="新增预选寝室" :visible.sync="Updatedialog" width="45%" >
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
      <el-form-item label="学院">
        <el-select v-model="form.college" value-key="id" clearable placeholder="请选择学院">
    <el-option
      v-for="item in colleges"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
</el-form-item>
      <el-form-item>
        <el-transfer v-model="dorms" :data="getDorms"></el-transfer>
      </el-form-item>
        
    <el-form-item label="预选时间" prop="startTime">
      <div class="block">
    <el-date-picker
      v-model="form.startTime"
      type="datetime"
      placeholder="选择日期时间"
      default-time="12:00:00">
    </el-date-picker>
  </div>
    </el-form-item>
    <el-form-item label="截至时间" prop="endTime">
      <div class="block">
    <el-date-picker
      v-model="form.endTime"
      type="datetime"
      placeholder="选择日期时间"
      default-time="12:00:00">
    </el-date-picker>
  </div>
    </el-form-item>
    
  <el-form-item label="寝室类型" prop="gender">
        
    <el-radio-group v-model="form.gender">
    <el-radio :label="0">男生寝室</el-radio>
    <el-radio :label="1">女生寝室</el-radio>
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


// import request from '@/utils/request'

export default {
    name:'ManagePre',
    data(){
      
        return{
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
          getRooms:[],
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
        async search(){
          let form = this.form
          form.pageSize = this.pageSize
          form.pageNum = this.pageNum
          const res = await axios.post("http://localhost:80/api/pre",form)
          console.log(res.data)
          this.tableData = res.data.data.preDormsList
          this.total = res.data.data.total
          
        },
        reset(){
            this.form ={
               
            },
            this.getDorms = []
            this.dorms = []
            this.Updatedialog=false
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
            
        },
        async delById(college,building){
            console.log(college+''+building)
           const res =await axios.get("http://localhost:80/api/pre/del?college="+college+"&building="+building)
           console.log(res.data)
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
        async Add(){
          let dorm = this.dorms
          let dorms=[]
          let getDorms = this.getRooms
          console.log(dorm)
          console.log(getDorms)
          for(let i=0;i<dorm.length;i++){
            let inx = dorm[i]-1
            console.log("inx:"+inx+"i"+i)
            let val = getDorms[inx]
            console.log("val:"+val)
            dorms.push(val)
          }
          console.log(dorms)
          this.form.dorms = dorms
          console.log(this.form)
          const res = await axios.post("http://localhost:80/api/pre/take",this.form)
          console.log(res.data)
          this.form.dorms = []
          
          this.Updatedialog=false
          this.reset()
           this.search()
        },
        click(){
          this.Updatedialog=false
          this.form.dorms = []
          this.Updatedialog=false
          this.reset()
           this.search()
        },
        async getRoom(){
          this.getDorms = []
          const res = await axios.get("http://localhost:80/api/dorm/empty/room?building="+this.form.building)
          console.log(res.data)
          let room = res.data.data
          this.getRooms=room
          
          let getroom=[];
          for(let i = 0;i<=room.length;i++){
            getroom.push({
              label:room[i],
              key:i+1
            })
          }
          this.getDorms = getroom
          console.log(getroom)
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
    handleAdd(){
      this.Updatedialog=true
    }
    },
}
</script>
<style>
.el-transfer-panel__item.el-checkbox .el-checkbox__label {
    margin-left: 20px;
}
</style>