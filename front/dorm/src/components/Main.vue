<template>
  <!-- main -->
    <div class="app">
      <!-- 条件查询 -->
      <div style="padding: 10px 0;margin-top: 20px;margin-left: 20px;">
        <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入ID：" v-model="form.id"></el-input>
      <el-input style="width: 200px" suffix-icon="el-icon-message" placeholder="请输入姓名："  v-model="form.name"></el-input>
      <el-select v-model="SelectCollege" value-key="id" clearable placeholder="请选择学院" @change="getSubject">
    <el-option
      v-for="item in college"
      :key="item.id"
      :label="item.name"
      :value="item">
    </el-option>
  </el-select>
    <el-select v-model="InsertSubject" value-key="id" clearable placeholder="请选择专业" @change="getGrade">
    <el-option
      v-for="item in subject"
      :key="item.id"
      :label="item.name"
      :value="item">
    </el-option>
  </el-select>
  <el-select v-model="InsertGrade" value-key="id" clearable placeholder="请选择年级" @change="getSclass">
    <el-option
      v-for="item in grade"
      :key="item.id"
      :label="item.grade"
      :value="item">
    </el-option>
  </el-select>
  <el-select v-model="form.sclass" clearable placeholder="请选择班级">
    <el-option
      v-for="item in classes"
      :key="item.id"
      :label="item.sclass"
      :value="item.sclass">
    </el-option>
  </el-select>
  <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>
  <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" @change="getBunk">
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <el-select v-model="form.bunk" value-key="id" clearable placeholder="请选择床位">
    <el-option
      v-for="item in bunks"
      :key="item.id"
      :label="item.bunk"
      :value="item.bunk">
    </el-option>
  </el-select>
  <div>
      <el-button  type="primary" @click="load">搜索</el-button>
      <el-button type="warning" @click="clear">重置</el-button>
      </div>
    </div>
      <div style="margin: 10px 0">
      <el-button type="primary" @click="handleAdd" v-if="per<1">新增<i class="el-icon-circle-plus"></i></el-button>
      <el-popconfirm
          confirm-button-text='确定'
          cancel-button-text='取消'
          icon="el-icon-info"
          icon-color="red"
          title="您确定删除吗？"
          @confirm  = "delBatch"
      >
        <el-button type="danger" slot="reference" style="margin-left: 8px;margin-right: 8px;" v-if="per<1">批量删除<i class="el-icon-remove"></i></el-button>
      </el-popconfirm>



  <el-button type="primary" @click="expor" v-if="per<1">导出<i class="el-icon-bottom-right"></i></el-button>

      
    </div>
    <!-- 表单数据 -->
    <el-table :data="tableData" border :header-cell-class-name="headerBackgroundColor" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="id" label="ID" width="120">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="gender" label="性别" width="60">
      </el-table-column>
      <el-table-column prop="phone" label="联系方式" width="140">
      </el-table-column>
      <el-table-column prop="building" label="栋" width="60">
      </el-table-column>
      <el-table-column prop="room" label="寝室" width="60">
      </el-table-column>
      <el-table-column prop="bunk" label="床位" width="60">
      </el-table-column>
      <el-table-column prop="college" label="学院">
      </el-table-column>
      <el-table-column prop="subject" label="专业">
      </el-table-column>
      <el-table-column prop="grade" label="年级" width="80">
      </el-table-column>
      <el-table-column prop="sclass" label="班级" width="60">
      </el-table-column>
      <el-table-column label="操作">
        <template v-slot="scope" v-if="per<1">
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
    <!-- 分页 -->
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
    <el-dialog title="新增数据" :visible.sync="dialogFormVisible" width="30%" >
      <el-form label-width="100px">
        <el-form-item label="学号">
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.gender" label="男" style="color: black; ">男性</el-radio>
          <el-radio v-model="form.gender" label="女" style="color: black;">女性</el-radio>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
  <el-select v-model="InsertCollege" value-key="id" clearable placeholder="请选择学院" @change="getSubject">
    <el-option
      v-for="item in college"
      :key="item.id"
      :label="item.name"
      :value="item">
    </el-option>
  </el-select>
    <el-select v-model="InsertSubject" value-key="id" clearable placeholder="请选择专业" @change="getGrade">
    <el-option
      v-for="item in subject"
      :key="item.id"
      :label="item.name"
      :value="item">
    </el-option>
  </el-select>
  <el-select v-model="InsertGrade" value-key="id" clearable placeholder="请选择年级" @change="getSclass">
    <el-option
      v-for="item in grade"
      :key="item.id"
      :label="item.grade"
      :value="item">
    </el-option>
  </el-select>
  <el-select v-model="form.sclass" clearable placeholder="请选择班级">
    <el-option
      v-for="item in classes"
      :key="item.id"
      :label="item.sclass"
      :value="item.sclass">
    </el-option>
  </el-select>
  <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>
  <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" @change="getBunk">
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <el-select v-model="form.bunk" value-key="id" clearable placeholder="请选择床位">
    <el-option
      v-for="item in bunks"
      :key="item.id"
      :label="item.bunk"
      :value="item.bunk">
    </el-option>
  </el-select>


      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="save()">确 定</el-button>
        <el-button type="danger" @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>


    <el-dialog title="编辑数据" :visible.sync="updateDialogShow" width="30%" >
      <el-form label-width="100px">
        <el-form-item label="学号">
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="名称">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio v-model="form.gender" label="男" style="color: black; ">男性</el-radio>
          <el-radio v-model="form.gender" label="女" style="color: black;">女性</el-radio>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input v-model="form.phone" autocomplete="off"></el-input>
        </el-form-item>
  <el-select v-model="InsertCollege" value-key="id" clearable placeholder="请选择学院" @change="getSubject">
    <el-option
      v-for="item in college"
      :key="item.id"
      :label="item.name"
      :value="item">
    </el-option>
  </el-select>
    <el-select v-model="InsertSubject" value-key="id" clearable placeholder="请选择专业" @change="getGrade">
    <el-option
      v-for="item in subject"
      :key="item.id"
      :label="item.name"
      :value="item">
    </el-option>
  </el-select>
  <el-select v-model="InsertGrade" value-key="id" clearable placeholder="请选择年级" @change="getSclass">
    <el-option
      v-for="item in grade"
      :key="item.id"
      :label="item.grade"
      :value="item">
    </el-option>
  </el-select>
  <el-select v-model="form.sclass" clearable placeholder="请选择班级">
    <el-option
      v-for="item in classes"
      :key="item.id"
      :label="item.sclass"
      :value="item.sclass">
    </el-option>
  </el-select>
  <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋" @change="getRoom">
    <el-option
      v-for="item in buildings"
      :key="item.id"
      :label="item.building"
      :value="item.building">
    </el-option>
  </el-select>
  <el-select v-model="form.room" value-key="id" clearable placeholder="请选择寝室" @change="getBunk">
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
  <el-select v-model="form.bunk" value-key="id" clearable placeholder="请选择床位">
    <el-option
      v-for="item in bunks"
      :key="item.id"
      :label="item.bunk"
      :value="item.bunk">
    </el-option>
  </el-select>
      </el-form>
      <div slot="footer" class="dialog-footer" style="display: flex;">
        <el-button type="primary" @click="update()">确 定</el-button>
        <el-button type="danger" @click="cancelUpdate()">取 消</el-button>
      </div>
    </el-dialog>
      </div>
</template>
<script>
import request from '@/utils/request'
export default {
    name: 'StudentMain',
    data() {
        return{
          permission: 0,
          value:'',
      headerBackgroundColor:'',
      pageNum:1,
      pageSize: 5,
      total:0,
      tableData: [],
      dialogFormVisible: false,
      updateDialogShow: false,
      college: [{
        id: 1,
        name: ""
      }],
      subject: [{
        id: 1,
        name: ""
      }],
      grade: [{
        id: 1,
        grade: ""
      }],
      classes: [{
        id: 1,
        sclass: ""
      }],
      buildings:[{
        id:1,
        building: ""
      }],
      rooms:[{
        id:1,
        room:""
      }],
      bunks:[{
        id:1,
        bunk:""
      }],
      SelectCollege: "",SelectSubject: "",SelectGrade: "",SelectClass: "",InputID: "",InputName: "",
      InsertCollege:[],InsertSubject:[],InsertGrade:[],InserBuilding:[],InserRoom:[],
      form: {
        id: "",
        name: "",
        gender: "",
        phone: "",
        college: "",
        subject: "",
        grade: "",
        sclass: "",
        building: '',
        room: '',
        bunk: '',
        pageNum: '',
        pageSize: ''
      },
      per:'',
      multipleSelection: [],
      SubjectId: ''
        }
    },
    created() {
       this.load()
       this.testPermission()
    },
    methods: {
      async handleEdit(row){
        console.log(row)
        this.updateDialogShow = true;
        this.form = row
      },
      async delById(id){
        console.log("学生ID："+id)
        const res = await request.delete("/student/"+id)
        console.log(res)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.load()
      },
      async save(){
        console.log(this.form)
        const res = await request.post("/student",this.form)
        console.log(res)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.cancel()
        this.load()
      },
      async test(){
        const res = await request.get("/manager/test")
        console.log(res)
      },
      clear(){
        this.form = {
        id: "",
        name: "",
        gender: "",
        phone: "",
        college: "",
        subject: "",
        grade: "",
        sclass: "",
        building: '',
        room: '',
        bunk: '',
        pageNum: '',
        pageSize: ''
      },
      this.updateDialogShow = false
      },
      load(){
      this.select()
      this.getCollege();
      this.getBuilding()
    },
    async select(){
      let form = this.form
        form.pageNum = this.pageNum
        form.pageSize = this.pageSize
       console.log(form)
       const res = await request.post('/student/get',form)
       this.tableData = res.data.students
      this.total = res.data.total
    },
    testPermission(){
        this.per = sessionStorage.getItem("permission")
      },
//学院专业年级班级楼栋寝室床位数据接口获取
    async getCollege(){
      const res = await request.get('/apart/college')
      console.log(res)
      this.college = res.data
    },
    async getSubject(value){
      this.form.college = value.name
      let collegeId = value.id
      const res = await request.get('/apart/subject?collegeId='+collegeId)
      console.log(res)
      this.subject = res.data
    },
    async getGrade(value){
      this.form.subject = value.name
      this.SubjectId = value.id
      const res = await request.get('/apart/grade')
      console.log(res)
      this.grade = res.data
    },
    async getSclass(value){
      this.form.grade = value.grade
      let subjectId = this.SubjectId
      let gradeId = value.id
      const res = await request.get('/apart/sclass?gradeId='+gradeId+'&subjectId='+subjectId)
      console.log(res)
      this.classes = res.data
      console.log(this.form)
    },  
    async getBuilding(){
      const res = await request.get('/dorm/building')
      console.log(res)
      this.buildings = res.data
    },
    async getRoom(){
      console.log(this.form)
      const res = await request.get('/dorm/room?building='+this.form.building)
      console.log(res)
      this.rooms = res.data
    },
    async getBunk(){
      const res = await request.get('/dorm/bunk?room='+this.form.room)
      console.log(res)
      this.bunks = res.data
    },

    handleCurrentChange(page){
        console.log(page)
        this.pageNum = page
        this.select()
      },
      handleSizeChange(pageSize){
        console.log(pageSize)
        this.pageSize = pageSize
        this.select()
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
      async delBatch() {
        console.log("delbatch:"+this.multipleSelection)
        const res = await request.post('/student/del/batch',this.multipleSelection)
        console.log(res)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.load()
      },
      handleAdd() {
        this.dialogFormVisible = true;
        this.clear()
      },
      reset(){
        this.SelectCollege = "",
        this.SelectSubject = "",
        this.SelectGrade = "",
        this.SelectClass = "",
        this.InputID = "",
        this.InputName = ""
      },
      cancel(){
        this.dialogFormVisible = false
        this.form = {
        id: "",
        name: "",
        gender: "",
        phone: "",
        college: "",
        subject: "",
        grade: "",
        sclass: "",
        building: '',
        room: '',
        bunk: '',
      }
      this.InsertCollege = []
      this.InsertGrade = []
      this.InsertSubject = []
      this.subject = []
      this.grade = []
      this. sclass = []
      this.InserBuilding = []
      this.InserRoom = []
      this.rooms = []
      this.bunks = []
      },
      cancelUpdate(){
        this.updateDialogShow = false
        this.form = {
        id: "",
        name: "",
        gender: "",
        phone: "",
        college: "",
        subject: "",
        grade: "",
        sclass: "",
        building: '',
        room: '',
        bunk: '',
      }
      this.InsertCollege = []
      this.InsertGrade = []
      this.InsertSubject = []
      this.subject = []
      this.grade = []
      this. sclass = []
      this.InserBuilding = []
      this.InserRoom = []
      this.rooms = []
      this.bunks = []
      },
      async update(){
        let row = this.form
        console.log(row)
        const res = await request.put("/student/update",row)
        console.log(res)
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
        this.cancelUpdate()
        this.load()
      },
      async impor(){},
      async expor(){
        window.open("http://localhost:80/api/student/export")
        this.$message({
          showClose: true,
          message: '执行成功',
          type: 'success'
        });
      },
      },

    
}
</script>
<style>
.el-pagination .btn-next, .el-pagination .btn-prev{
  width: 60PX;
}
.el-radio__label{
  color: black;
}
.el-dialog__headerbtn{
  font-size: 0px;
}
</style>