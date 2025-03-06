<template>
    <div class="app">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入ID：" v-model="form.id"></el-input>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入姓名：" v-model="form.name"></el-input>
        <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select> 
    <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" >
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <el-select v-model="form.unit" value-key="id" clearable placeholder="请选择单位" @change="getMatter">
    <el-option
      v-for="item in units"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
    <el-select v-model="form.matter" value-key="id" clearable placeholder="请选择事项" >
    <el-option
      v-for="item in matters"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select>

  <div>
      <el-button style="margin-left: 5px;width: 100px;line-height: 0px;" type="primary" @click="search">搜索</el-button>
      <el-button style="margin-left: 5px;width: 100px;line-height: 0px;" type="warning" @click="reset">重置</el-button>
      </div>

      <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" style="width: 100px;line-height: 0px;">新增<i class="el-icon-circle-plus"></i></el-button>
      <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm  = "delBatch"
      >
        <el-button type="danger" slot="reference" style="margin-left: 8px;margin-right: 8px;width: 100px;line-height: 0px;">批量删除<i class="el-icon-remove"></i></el-button>
      </el-popconfirm>
    </div>

  <el-table :data="tableData"  @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="id" width="80px">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="80px">
      </el-table-column>
      <el-table-column prop="building" label="栋" width="80px">
      </el-table-column>
      <el-table-column prop="room" label="寝室" width="80px">
      </el-table-column>
      <el-table-column prop="unit" label="单位">
      </el-table-column>
      <el-table-column prop="matter" label="事项">
      </el-table-column>
      <el-table-column prop="arriveTime" label="到达时间" >
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

    <el-dialog title="新增人员登记" :visible.sync="RegisterDialogInsert" width="30%" >
      <el-form label-width="100px">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入姓名：" v-model="form.name"></el-input>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入联系方式：" v-model="form.phone"></el-input>
        <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select> 
    <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" >
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <el-select v-model="form.unit" value-key="id" clearable placeholder="请选择单位" @change="getMatter">
    <el-option
      v-for="item in units"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
    <el-select v-model="form.matter" value-key="id" clearable placeholder="请选择事项" >
    <el-option
      v-for="item in matters"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="SaveRegister()">确 定</el-button>
        <el-button type="danger" @click="reset">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog title="更新人员登记" :visible.sync="UpdateRegister" width="30%" >
      <el-form label-width="100px">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入姓名：" v-model="form.name"></el-input>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入联系方式：" v-model="form.phone"></el-input>
        <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select> 
    <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" >
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <el-select v-model="form.unit" value-key="id" clearable placeholder="请选择单位" @change="getMatter">
    <el-option
      v-for="item in units"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
    <el-select v-model="form.matter" value-key="id" clearable placeholder="请选择事项" >
    <el-option
      v-for="item in matters"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="updateRegister()">确 定</el-button>
        <el-button type="danger" @click="reset">取 消</el-button>
      </div>
    </el-dialog>
    </div>
</template>
<script>
import request from '@/utils/request'
export default {
    name: 'dormRegister',
    data() {
        return {
            total:0,
            tableData:[],
            rooms:[],
            buildings:[],
            units:[],
            matters:[],
            pageNum:1,
            pageSize:5,
            RegisterDialogInsert:false,
            UpdateRegister: false,
            form:{
                id: "",
                name : "",
                phone: "",
                building: '',
                room: '',
                unit:"",
                matter:"",
                arriveTime:"",
                leaveTime:"",
                pageNum: '',
                pageSize: ''
            }
        }
    },
    created(){
        this.getBuilding()
        this.getUnit()
        this.search()
    },
    methods: {
        async getBuilding(){
        const res = await request.get("/dorm/building")
        console.log(res)
        this.buildings = res.data
      },
      async getRoom(){
        let building = this.form.building
        const res = await request.get("/dorm/room?building="+building)
        console.log(res)
        this.rooms = res.data
      },
      async search(){
      let form = this.form
        form.pageNum = this.pageNum
        form.pageSize = this.pageSize
       console.log(form)
       const res = await request.post('/register',form)
       this.tableData = res.data.registers
      this.total = res.data.total
    },
    async getUnit(){
        const res = await request.get("/register/unit")
        console.log(res)
        this.units = res.data
      },
      async getMatter(){
        let unit = this.form.unit.toString()
        console.log(unit)
        const res = await request.get("/register/matter?unit="+unit)
        console.log(res)
        this.matters = res.data
      },
      async SaveRegister(){
        console.log(this.form)
        await request.post("/register/insert/register",this.form)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.reset()
    },
    async updateRegister(){
        console.log(this.form)
        await request.put("/register/update",this.form)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.reset()
    },
    handleSelectionChange(val){
      console.log(val)
        let ids = [];
        for(var i = 0;i<val.length;i++){
          ids.push(val[i].id)
          console.log(ids)
        }
        this.multipleSelection = ids
    },
    handleEdit(row){
        console.log(row)
        this.UpdateRegister = true
        this.form = row
    },
    handleSizeChange(val){
        this.pageSize = val
        this.search()
    },
    handleCurrentChange(val){
        this.pageNum = val
        this.search()
    },
    reset(){
        this.form = {
                id: "",
                name : "",
                phone: "",
                building: '',
                room: '',
                unit:"",
                matter:"",
                arriveTime:"",
                leaveTime:"",
                pageNum: '',
                pageSize: ''
            }
            this.RegisterDialogInsert = false
            this.UpdateRegister = false
            this.search()
    },
    async delById(id){
        console.log(id)
        await request.delete("/register/"+id)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.search()
    },
    async delBatch(){
      let ids = this.multipleSelection
      await request.post("/register/del/batch",ids)
      this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
      this.search()
    },
    handleAdd(){
        this.RegisterDialogInsert = true
    },
    
    }
}
</script>
<style></style>