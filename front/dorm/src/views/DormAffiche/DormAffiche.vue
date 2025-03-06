<template>
    <div>
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入ID：" v-model="form.id"></el-input>
        <el-select v-model="form.writeName" value-key="id" clearable placeholder="请选择管理员">
    <el-option
      v-for="item in managers"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 


    <div>
      <el-button  type="primary" @click="search">搜索</el-button>
      <el-button type="warning" @click="reset">重置</el-button>
    </div>
      <div style="margin: 10px 0">
      <el-button type="primary" @click="afficheAdd">新增<i class="el-icon-circle-plus"></i></el-button>
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

    <el-table :data="tableData" border @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="id">
      </el-table-column>
      <el-table-column prop="title" label="标题">
      </el-table-column>
      <el-table-column label="内容">
        <template v-slot="scope">
      <el-button type="success" style="margin-left: 5px; width: 80px;" @click="searchAfficheById(scope.row)">内容<i class="el-icon-remove"></i></el-button>
    </template>
      </el-table-column>
      <el-table-column prop="status" label="状况">
      </el-table-column>
      <el-table-column prop="writeName" label="作者">
      </el-table-column>
      <el-table-column prop="writeTime" label="时间">
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

    <el-dialog title="新增公告" :visible.sync="InsertdialogAffiche" width="30%" >
      <el-form label-width="100px">
        
        <el-form-item label="标题">
          <el-input v-model="form.title" type="text" maxlength="15" show-word-limit autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
            <el-input type="textarea" placeholder="请输入内容" v-model="form.text" maxlength="100" show-word-limit></el-input>
        </el-form-item>
        <el-form-item label="状况">
          <el-radio v-model="form.status" label=1 style="color: black; ">发布</el-radio>
          <el-radio v-model="form.status" label=0 style="color: black;">留稿</el-radio>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="saveAffiche()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog title="编辑公告" :visible.sync="UpdatedialogAffiche" width="30%" >
      <el-form label-width="100px">
        
  <el-form-item label="标题">
          <el-input v-model="form.title" type="text" maxlength="15" show-word-limit autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="内容">
            <el-input type="textarea" placeholder="请输入内容" v-model="form.text" maxlength="100" show-word-limit></el-input>
        </el-form-item>
 
        <el-form-item label="状况">
          <el-radio v-model="form.status" label="1" style="color: black; ">发布</el-radio>
          <el-radio v-model="form.status" label="0" style="color: black;">留稿</el-radio>
        </el-form-item>
        <el-select v-model="form.writeName" value-key="id" clearable placeholder="请选择管理员">
    <el-option
      v-for="item in managers"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select> 
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="updateAffiche()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>

</div>
</template>
<script>
import request from '@/utils/request'
export default {
    name: 'DormAffiche',
    data() {
        return {
            MessageTitle:'这是一段内容',
            MessageText: '标题名称',
            form:{},
            managers:[{
                id: 0,
                name: ""
            }],
            timePicker: "",
            tableData:[],
            pageNum:1,
            pageSize:5,
            total:0,
            InsertdialogAffiche: false,
            UpdatedialogAffiche: false,
            delIds:[],
        }
    },
    created(){
        this.load()
    },
    methods: {
        load(){
            this.getManager()
            this.search()
        },
        open(){
            this.$alert(this.MessageText, this.MessageTitle, {
          confirmButtonText: '确定',
          showClose: false
        });
        },
        async getManager(){
          const res= await request.get('/manager/name')
          console.log(res.data)
          this.managers = res.data
        },
        async search(){
          console.log(this.form)
            this.form.pageNum = this.pageNum
            this.form.pageSize = this.pageSize
            const res = await request.post("/affiche/search",this.form)
            console.log(res)
            this.tableData = res.data.affiches
            this.total = res.data.total
        },
        reset(){
          this.MessageText = ""
            this.MessageTitle =  ""
            this.InsertdialogAffiche = false
            this.UpdatedialogAffiche = false
            this.form = {}
        },
        afficheAdd(){
            this.MessageText = ""
            this.MessageTitle =  ""
            this.form = {}
            this.InsertdialogAffiche = true
        },
        async delBatch(){
          console.log(this.delIds)
          let ids = this.delIds
          await request.post("/affiche/del/batch",ids)
          this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
          this.reset()
            this.load()
          
        },
        handleSelectionChange(val){
          console.log(val)
          let ids = [];
          for(let i=0;i<val.length;i++){
            ids.push(val[i].id)
          }
          this.delIds = ids;
        },
        searchAfficheById(val){
            console.log(val)
            this.MessageText = val.text
            this.MessageTitle = val.title 
            this.open()
        },
        handleEdit(val){
            this.UpdatedialogAffiche = true
            this.form.text = val.text
            this.form.title =  val.title
            this.form.id = val.id
        },
        async delById(id){
            await request.delete("/affiche/"+id)
            this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.reset()
            this.load()
        },
        handleSizeChange(val){
          this.pageSize = val
          this.search()
        },
        handleCurrentChange(val){
          this.pageNum = val
          this.search()
        },
        async saveAffiche(){
          let form = this.form
          form.writeName = sessionStorage.getItem('manager')
          console.log(form)
          await request.post('/affiche/insert',form)
          this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.reset()
            this.load()
        },
        async updateAffiche(){
          let form = this.form
          form.writeName = sessionStorage.getItem("manager")
          await request.put("/affiche/update",form)
          this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
            this.reset()
            this.load() 
        },
        cancel(){
            this.MessageText = ""
            this.MessageTitle =  ""
            this.InsertdialogAffiche = false
            this.UpdatedialogAffiche = false
        }
      }
    
  }
</script>
<style scoped>
.el-message-box {
    position: absolute;
    z-index: 10;
    left: 700px;
    top:200px;
}
</style>