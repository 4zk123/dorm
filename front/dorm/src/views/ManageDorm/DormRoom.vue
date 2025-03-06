<template>
    <div>
        <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom()">
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
  <div>
  <el-button type="primary" style="width: 100px;line-height: 0px;" @click="search()" icon="el-icon-search">查询寝室</el-button>
  <el-button type="primary" @click="RoomAdd" style="width: 100px;line-height: 0px;" icon="el-icon-circle-plus-outline"  v-if="per<1">新增寝室</el-button>
      <el-button style="margin-left: 5px;width: 100px;line-height: 0px;" type="warning" @click="reset" icon="el-icon-refresh">重置</el-button>
      <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm  = "delBatch"
      >
        <el-button type="danger" slot="reference" style="margin-left: 8px;margin-right: 8px;width: 100px;line-height: 0px;" icon="el-icon-remove-outline"  v-if="per<1">批量删除</el-button>
      </el-popconfirm>
  </div>

  <el-table :data="tableData" border @selection-change="handleSelectionChange">
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
      <!-- <el-table-column prop="livingNum" label="入住数量">
      </el-table-column> -->
      <el-table-column prop="num" label="可住数量">
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope"  v-if="per<1">
         

          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delRoomById(scope.row.id)"
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
    <el-dialog title="新增寝室" :visible.sync="InsertdialogRoom" width="30%" >
      <el-form label-width="100px">
        <el-form-item label="楼栋">
  <el-select v-model="form.building" clearable placeholder="请选择楼栋">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>
</el-form-item>
  <el-form-item label="寝室">
          <el-input v-model="form.room" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="寝室类型">
          <el-radio-group v-model="num">
              <el-radio :label=2>2人寝</el-radio>
              <el-radio :label=4>4人寝</el-radio>
              <el-radio :label=6>6人寝</el-radio>
            </el-radio-group>

          
        </el-form-item>
        
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="saveRoom()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>
    </div>


</template>
<script>
import request from '@/utils/request'
export default {
    name: 'DormRoom',
    data(){
        return{
          delRooms:'',
          num:'',
            InsertdialogRoom: false,
            tableData:[],
            searchBuilding:"",
            searchRoom:"",
            InserBuilding:"",
            InserRoom:"",
            pageNum:1,
            pageSize:5,
            total:0,
            per:'',
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
                num:'',
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
        }
    },
    created(){
      this.reset()
        this.search()
        this.getBuilding()
        this.testPermission()
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
      async search(){
        let form = this.form 
        form.pageNum = this.pageNum
        form.pageSize = this.pageSize
        let res;
          res = await request.post("/dorm/count/room",form)
          console.log(res)
          this.tableData = res.data.room
          this.total = res.data.total 
      },
      async saveRoom(){
        this.form.num = this.num
        console.log(this.form)
        await request.post("/dorm/insert/room",this.form)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.InsertdialogRoom = false,
        this.search()
      },
      async delRoomById(id){
        await request.delete("/dorm/room/"+id)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.search()
      },
      RoomAdd(){
        this.InsertdialogRoom = true
      },
    cancel(){
        this.InsertdialogRoom = false
      },
      reset(){
        this.InsertdialogRoom = false,
        this.form = {
                building: '',
                room: '',
                num:'',
                pageNum: '',
                pageSize: ''
            },
            this.searchRoom = "",
            this.InserRoom = ""
            this.search()
      },
      async delBatch(){
          console.log(this.delRooms)
          let delbatch = this.delRooms
          

          await request.post("/dorm/room/del/batch",delbatch)
          this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
          this.reset()
            this.search()
          
        },
        testPermission(){
        this.per = sessionStorage.getItem("permission")
      },
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
        let ids = [];
        
          for(let i=0;i<val.length;i++){
            let room = {room:'',building:''} 
            room.building = val[i].building
            room.room = val[i].room
            ids.push(
              room
            )
            console.log(ids)
          }
          this.delRooms = ids;
      },
      
    }
}
</script>
<style></style>
