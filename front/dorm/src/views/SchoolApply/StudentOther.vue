<template>
    <el-form :model="form" :rules="rules" ref="form" label-width="100px">
        <el-form-item label="学生姓名" prop="studentName">
          <el-input v-model="form.studentName" placeholder="请输入学生姓名"></el-input>
        </el-form-item>
        <el-form-item label="学号" prop="studentId">
          <el-input v-model="form.studentId" placeholder="请输入学号"></el-input>
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
          <el-button type="primary" @click="submitApply(form)">申请</el-button>
          <el-button @click="resetApply()">重置</el-button>
        </el-form-item>
      </el-form>
      </template>
      <script>
    import request from '@/utils/request'
        export default {
            name: 'StudentOther',
          data() {
            return {
                formData:{},

                rooms:[],
                buildings:[],
                form:{
                    studentName:'',
                    studentId:'',
                    building:'',
                    room:'',
                    apply:'',
                    status:0,
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
            async submitApply(form) {
                form.apply = form.apply.toString() 
                console.log(form)
                await request.post("/apply/other/insert",form)
                this.$message({
          showClose: true,
          message: '申请成功',
          type: 'success'
        });
               this.resetApply()
            },
            resetApply() {
              this.form = {
                studentName:'',
                    studentId:'',
                    building:'',
                    room:'',
                    apply:'',
                    status:0,

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