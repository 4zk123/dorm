<template>
    <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名"></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号"></el-input>
        </el-form-item>
        <el-form-item label="学院" prop="building">
        <el-select v-model="form.college" value-key="id" clearable placeholder="请选择学院">
    <el-option
      v-for="item in college"
      :key="item.id"
      :label="item.name"
      :value="item.name">
    </el-option>
  </el-select>
</el-form-item>
      <el-form-item label="楼栋" prop="building">
        <el-select v-model="form.building" value-key="id" clearable placeholder="请选择楼栋"  @change="getRoom()">
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
<el-form-item label="床位">
      <el-select v-model="form.bunk" value-key="id" clearable placeholder="请选择床位" >
    <el-option
      v-for="item in bunks"
      :key="item.id"
      :label="item.bunk"
      :value="item.bunk">
    </el-option>
  </el-select>
</el-form-item> 
<el-form-item label="换寝类型">
    <el-select v-model="form.type" value-key="id" clearable placeholder="请选择类型" >
    <el-option
      v-for="item in types"
      :key="item.id"
      :label="item.type"
      :value="item.id">
    </el-option>
  </el-select>
    </el-form-item>
      <el-form-item label="换寝原因">
        <el-input
  type="textarea"
  placeholder="请输入原因"
  v-model="form.reason"
  maxlength="100"
  show-word-limit
>
</el-input>
    </el-form-item>

     <div v-if="form.type==0">
        <el-form-item label="互换学生姓名" prop="exstudentName">
          <el-input v-model="form.exName" placeholder="请输入互换学生姓名"></el-input>
        </el-form-item>
        <el-form-item label="互换学生学号" prop="exstudentId">
          <el-input v-model="form.exId" placeholder="请输入互换学生学号"></el-input>
        </el-form-item>
        <el-form-item label="互换楼栋" prop="exbuilding">
        <el-select v-model="form.exBuilding" value-key="id" clearable placeholder="请选择互换楼栋"  @change="getRoom()">
        <el-option
          v-for="item in buildings"
          :key="item.id"
          :label="item.building"
          :value="item.building">
        </el-option>
      </el-select>
      </el-form-item>
      
      <el-form-item label="互换寝室">
      <el-select v-model="form.exRoom" value-key="id" clearable placeholder="请选择互换寝室" >
    <el-option
      v-for="item in rooms"
      :key="item.id"
      :label="item.room"
      :value="item.room">
    </el-option>
  </el-select>
</el-form-item> 
<el-form-item label="互换床位">
      <el-select v-model="form.exBunk" value-key="id" clearable placeholder="请选择互换床位" >
    <el-option
      v-for="item in bunks"
      :key="item.id"
      :label="item.bunk"
      :value="item.bunk">
    </el-option>
  </el-select>
</el-form-item> 
     </div>
        <el-form-item>
          <el-button type="primary" @click="submitApply(form)">申请</el-button>
          <el-button @click="resetApply()">重置</el-button>
        </el-form-item>
      </el-form>
      </template>
      <script>
    import request from '@/utils/request'
        export default {
            name: 'ChangeApply',
          data() {
            return {
                formData:{},
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
                rooms:[],
                college:[],
                buildings:[],
                bunks:[
                    
                    {
                        id:1,
                        bunk:1
                    },
                    {
                        id:2,
                        bunk:2
                    },
                    {
                        id:3,
                        bunk:3
                    },
                    {
                        id:4,
                        bunk:4
                    },

                ],
                form:{
                    studentName:'',
                    studentId:'',
                    building:'',
                    room:'',
                    bunk:'',
                    reason:'',
                    type:1,
                    extName:'',
                    exId:'',
                    exBuilding:'',
                    exRoom:'',
                    exBunk:'',
                },
              rules: {
                studentName: [
                  { required: true, message: '请输入学生姓名', trigger: 'blur' },
                  { min: 2, max: 5, message: '长度在 2 到 5 个字符', trigger: 'blur' }
                ],
                studentId: [
                  { required: true, message: '请输入学号', trigger: 'blur' }
                ],
                college: [
                  { required: true, message: '请选择学院', trigger: 'change' }
                ],
                livingRegion: [
                  { required: true, message: '请选择活动区域',  trigger: 'change' }
                ],
                livingPlace: [
                  { required: true, message: '请输入校外居住地址', trigger: 'blur' }
                ],
                
              }
            };
          },
          created() {
            this.getBuilding()
            this.getCollege()
          },
          methods: {
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
      async getCollege(){
      const res = await request.get('/apart/college')
      console.log(res)
      this.college = res.data
    },
            async submitApply(form) {
                
                console.log(form)
                const res = await request.post('http://localhost:80/api/apply/change/insert',form)
                console.log(res.data)
                this.$message({
          showClose: true,
          message: '申请成功',
          type: 'success'
        });
                this.form=[]
            },
            resetApply() {
              this.form = {
                

                }
            }
          }
        }
      </script>
    <style scoped>
    .el-input{
        width:600px;
        left:0
    }
    </style>