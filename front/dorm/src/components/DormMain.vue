<template>
    <div class="app">
      <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select> 
    <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室">
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <!-- <el-select v-model="form.bunk" value-key="id" clearable placeholder="请选择床位" @change="log">
    <el-option
      v-for="item in bunks"
      :key="item.id"
      :label="item.bunk"
      :value="item.bunk">
    </el-option>
  </el-select> -->
  <div>
    <el-button type="primary" style="margin-left: 5px;width: 100px;line-height: 0px;" round @click="classification = 'building',search()">查询楼栋</el-button>
      <el-button type="primary" style="margin-left: 5px;width: 100px;line-height: 0px;" round @click="classification = 'room',search()">查询寝室</el-button>
      <el-button type="primary" style="margin-left: 5px;width: 100px;line-height: 0px;" round @click="classification = 'bunk',search()">查询床位</el-button>
      <el-button style="margin-left: 5px;width: 100px;line-height: 0px;" type="primary" @click="search">搜索</el-button>
      <el-button style="margin-left: 5px;width: 100px;line-height: 0px;" type="warning" @click="reset">重置</el-button>
      </div>
      <div style="margin: 10px 0">
        <el-button type="primary" @click="BuildingAdd" style="width: 100px;line-height: 0px;">新增楼栋<i class="el-icon-circle-plus"></i></el-button>
        <el-button type="primary" @click="RoomAdd" style="width: 100px;line-height: 0px;">新增寝室<i class="el-icon-circle-plus"></i></el-button>
        <el-button type="primary" @click="BunkAdd" style="width: 100px;line-height: 0px;">新增床位<i class="el-icon-circle-plus"></i></el-button>
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
      <el-button type="primary" style="width: 100px;line-height: 0px;" @click="expor()">导出<i class="el-icon-bottom-right"></i></el-button>
    </div>
      <!-- <BuildingTable  :table-data="tableData" v-model="pageNum" :page-size="pageSize" :total="total" @log="log"></BuildingTable> -->
        <el-table :data="tableData" border @selection-change="handleSelectionChange" v-if="classification == 'bunk'">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="id">
      </el-table-column>
      <el-table-column prop="name" label="姓名">
      </el-table-column>
      <el-table-column prop="building" label="栋">
      </el-table-column>
      <el-table-column prop="room" label="寝室">
      </el-table-column>
      <el-table-column prop="bunk" label="床位" >
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

    <el-table :data="tableData" border @selection-change="handleSelectionChange" v-if="classification == 'building'">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="id">
      </el-table-column>
      <el-table-column prop="building" label="栋">
      </el-table-column>
      <el-table-column prop="num" label="寝室数量">
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

    <el-table :data="tableData" border @selection-change="handleSelectionChange" v-if="classification == 'room'">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="id">
      </el-table-column>
      <el-table-column prop="building" label="栋">
      </el-table-column>
      <el-table-column prop="room" label="寝室">
      </el-table-column>
      <el-table-column prop="livingNum" label="入住数量">
      </el-table-column>
      <el-table-column prop="num" label="可住数量">
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

          <!-- 新增表格 -->
          <el-dialog title="新增寝室" :visible.sync="InsertdialogRoom" width="30%" >
      <el-form label-width="100px">
        
  <el-select v-model="form.building" clearable placeholder="请选择楼栋">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>
  <el-form-item label="寝室">
          <el-input v-model="form.room" autocomplete="off"></el-input>
        </el-form-item>

        <el-select v-model="form.num"  clearable placeholder="请选择入住人数">
    <el-option
      v-for="item in LivingNum"
      :key="item.id"
      :label="item.num"
      :value="item.num">
    </el-option>
  </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="saveRoom()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>

    <el-dialog title="新增楼栋" :visible.sync="InsertdialogBuilding" width="30%" >
      <el-form label-width="100px">
        
  <el-form-item label="楼栋">
          <el-input v-model="form.building" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="saveBuilding()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>
    </div>
</template>



<script>
import request from '@/utils/request'
// import BuildingTable from './BuildingTable.vue'
export default {
    name:'dormMain',
    // components: {
    //   BuildingTable
    // },
    data(){
    return {
      InsertdialogRoom: false,
      InsertdialogBuilding: false,
      tableData:[],
      searchBuilding:"",
      searchRoom:"",
      InserBuilding:"",
      InserRoom:"",
      pageNum:1,
      pageSize:5,
      total:0,
      classification: "bunk",
      LivingNum:[{
        id:1,
        num:2
      },
      {
        id:2,
        num:4
      },
      {
        id:3,
        num:6
      }
    ],
            form:{
                building: '',
                room: '',
                bunk: '',
                pageNum: '',
                pageSize: ''
            },
            buildings:[{
                id:"",
                building:""
            }],
            rooms:[{
                id:"",
                room:""
            }],
            // bunks:[{
            //     id:"",
            //     bunk:""
            // }],
        }
    },
    created(){
      this.getBuilding()
      this.search()
    },
    methods:{
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
      // async getBunk(val){
      //   this.form.room = val.room
      //   const res = await request.get("/dorm/bunk?roomId="+val.id)
      //   console.log(res)
      //   this.bunks = res.data
      // },
      async search(){
        let form = this.form 
        form.pageNum = this.pageNum
        form.pageSize = this.pageSize
        let res;
        if(this.classification == 'building'){
          console.log("building")
          res = await request.get("/dorm/count/building?pageSize="+this.pageSize+"&pageNum="+this.pageNum)
          console.log(res)
          this.tableData = res.data.building
          this.total = res.data.total
      }
      else if(this.classification == 'bunk'){
        console.log("bunk")
          res = await request.post("/dorm/search",form)
          console.log(res)
          this.tableData = res.data.dorms
          this.total = res.data.total
      }
      else if(this.classification == 'room'){
          console.log("room")
          res = await request.post("/dorm/count/room",form)
          console.log(res)
          this.tableData = res.data.room
          this.total = res.data.total
      }
        
        
      },
      BunkAdd(){},
      reset(){
        this.cancel()
          this.search()
      },
      BuildingAdd(){
        this.InsertdialogBuilding = true
      },
      RoomAdd(){
        this.InsertdialogRoom = true
      },
      async saveBuilding(){
        console.log(this.form)
      },
      async saveRoom(){
        console.log(this.form)
      },
      cancel(){
        this.InsertdialogBuilding = false,
        this.InsertdialogRoom = false
        this.form = {
                building: '',
                room: '',
                bunk: '',
                pageNum: '',
                pageSize: ''
            },
            this.searchBuilding = "",
            this.searchRoom = "",
            this.InserBuilding = "",
            this.InserRoom = ""
      },
      delBatch(){},
      handleSizeChange(size){
        console.log(size)
        this.pageSize = size
        this.search()
      },
      handleCurrentChange(page){
        console.log(page)
        this.pageNum=page
        this.search()
      },
      handleSelectionChange(val){
        console.log(val)
      },
      handleEdit(row){
        console.log(row)
      },
      async expor(){
        window.open("http://localhost:80/api/dorm/export")
      }
    }
}
</script>
<style>

</style>