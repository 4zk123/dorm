<template>
    <div>
     <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select> 
  <div>
  <el-button type="primary" style="width: 100px;line-height: 0px;" @click="search()" icon="el-icon-search" >查询楼栋</el-button>
  <el-button type="primary" @click="BuildingAdd" style="width: 100px;line-height: 0px;" icon="el-icon-circle-plus-outline"  v-if="per<1">新增楼栋</el-button>
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
      <el-button type="primary" style="width: 100px;line-height: 0px;" icon="el-icon-bottom-right" @click="expor" v-if="per<1">导出</el-button>
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
      <el-table-column prop="buildingType" label="楼栋类型">
        <template v-slot="scope">
            <span v-if="scope.row.buildingType == 0">楼道</span>
            <span v-if="scope.row.buildingType == 1">大寝</span>
        </template>
      </el-table-column>
      <el-table-column prop="bunkType" label="寝室类型">
        
        <template v-slot="scope">
            <span v-if="scope.row.bunkType == 2">双人寝</span>
            <span v-if="scope.row.bunkType == 4">四人寝</span>
            <span v-if="scope.row.bunkType == 6">六人寝</span>
        </template>
      </el-table-column>
      <el-table-column prop="num" label="寝室数量">
      </el-table-column>
      <el-table-column label="操作" >
        <template v-slot="scope"  v-if="per<1">
         

          <el-popconfirm
              confirm-button-text='确定'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="delById(scope.row.building)"
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

  


    <el-dialog title="新增楼栋" :visible.sync="InsertdialogBuilding" width="30%" >
      <el-form label-width="100px">
        
  <el-form-item label="楼栋">
          <el-input v-model="form.building" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="楼栋类型">
          <el-radio-group v-model="buildingType">
    <el-radio :label=0>楼道</el-radio>
    <el-radio :label=1>大寝</el-radio>
  </el-radio-group>
        </el-form-item>
        <el-form-item label="楼层数量">
          <el-input v-model="form.floor" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="房间数量" v-if="buildingType == 0">
          <el-input v-model="form.roomNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="大寝数量" v-if="buildingType == 1">
          <el-input v-model="form.roomNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="小寝数量" v-if="buildingType == 1">
          <el-input v-model="form.roomsNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="寝室类型">
          <el-radio-group v-model="bunkType">
              <el-radio :label=2>2人寝</el-radio>
              <el-radio :label=4>4人寝</el-radio>
              <el-radio :label=6>6人寝</el-radio>
            </el-radio-group>

          
        </el-form-item>
      </el-form>
     
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="saveBuilding()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>
    
    <!-- <el-dialog title="编辑楼栋" :visible.sync="UpdatedialogBuilding" width="30%" >
      <el-form label-width="100px">
        
  <el-form-item label="楼栋">
          <el-input v-model="form.building" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="楼栋类型">
          <el-radio-group v-model="buildingType">
    <el-radio :label=0>楼道</el-radio>
    <el-radio :label=1>大寝</el-radio>
  </el-radio-group>
        </el-form-item>

        


        <el-form-item label="楼层数量">
          <el-input v-model="form.floor" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="房间数量" v-if="buildingType == 0">
          <el-input v-model="form.roomNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="大寝数量" v-if="buildingType == 1">
          <el-input v-model="form.roomNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="小寝数量" v-if="buildingType == 1">
          <el-input v-model="form.roomsNum" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="寝室类型">
          <el-radio-group v-model="bunkType">
              <el-radio :label=2>2人寝</el-radio>
              <el-radio :label=4>4人寝</el-radio>
              <el-radio :label=6>6人寝</el-radio>
            </el-radio-group>
        </el-form-item>
      </el-form>
     
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="updateBuilding()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog> -->
</div>
</template>
<script>
import request from '@/utils/request'
export default {
    name: 'DormBuilding',
    data(){
        return{
          delBuildings:'',
          buildingType:'',
          bunkType:'',
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
                pageNum: '',
                pageSize: '',
                buildingType:0,
                roomNum:'',
                roomsNum:'',
            },
            buildings:[{
                id:"",
                building:""
            }],
            rooms:[{
                id:"",
                room:""
            }],
            per:'',
        }
    },
    created(){
        this.reset()
        this.search()
        this.getBuilding()
        this.testPermission()
    },
    methods:{
      testPermission(){
        this.per = sessionStorage.getItem("permission")
      },
        async getBuilding(){
        const res = await request.get("/dorm/building")
        console.log(res)
        this.buildings = res.data
      },
        async search(){
        let form = this.form 
        form.pageNum = this.pageNum
        form.pageSize = this.pageSize
        let res;
          console.log("building")
          res = await request.post("/dorm/count/building",this.form)
          console.log(res)
          this.tableData = res.data.building
          this.total = res.data.total
      },
      async delById(building){
        await request.delete("/dorm/building/"+building)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.search()
      },
      reset(){
        this.InsertdialogBuilding = false,
        this.form = {
                building: '',
                room: '',
                bunk: '',
                pageNum: '',
                pageSize: ''
            },
            this.searchBuilding = "",
            this.InserBuilding = ""
            this.search()
            this.buildingType = ''
            this.bunkType = ''
            },
      async saveBuilding(){
        this.form.buildingType = this.buildingType
        this.form.bunkType = this.bunkType
        console.log(this.form)
        await request.post("/dorm/insert/building",this.form)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.InsertdialogBuilding = false,
        this.search()
      },
      BuildingAdd(){
        this.InsertdialogBuilding = true
      },
      cancel(){
        this.InsertdialogBuilding = false
        this.buildingType = ''
            this.bunkType = ''
      },
      async delBatch(){
          console.log(this.delBuildings)
          let ids = this.delBuildings
          await request.post("/dorm/building/del/batch",ids)
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
            ids.push(val[i].building)
          }
          this.delBuildings = ids;

      },
      async expor(){
        window.open("http://localhost:80/api/dorm/export")
      }
    },
}
</script>
<style></style>
