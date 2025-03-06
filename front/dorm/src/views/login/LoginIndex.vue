<!-- <template>
    <div class="body">
      <div style="width: 1420px; height: 900px;">
        <div class="login_box">
    <h2 class="h2">成都学院寝室管理系统登录</h2>
    <div class="input_box">
      <input type="text" placeholder="请输入帐号" v-model="ID" class="input">
    </div>
    <div class="input_box">
      <input type="password" placeholder="请输入密码" v-model="pwd" class="input">
    </div>
    <el-radio-group v-model="type" style=" margin-top: 20px;">
    <el-radio :label=0>学生</el-radio>
    <el-radio :label=1>管理员</el-radio>
  </el-radio-group>
    <button @click="login" class="button">登录</button><br>
  </div>
      </div>
    

  </div>
</template>
<script>
import axios from '@/utils/request';
export default { 
    name: 'DormLogin',
    data(){
        return{
          ID: "",
          pwd: "",
          type:'',
        }
    },
    methods:{
      async SLoginTest(){
        const res = await axios.get("login/student",{
          params: {
            pwd: this.pwd,
            ID: this.ID
          }
        })
        console.log(res)
        if(res.data != null){
          this.$router.push("/student")
        }
      },
      async MLoginTest(){
        const res = await axios.get("login/manager",{
          params: {
            pwd: this.pwd,
            ID: this.ID
          }
        })
        console.log(res)
        if(res.data != null){
          sessionStorage.setItem('manager',res.data.name)
          sessionStorage.setItem('managerId',res.data.id)
          sessionStorage.setItem('permission',res.data.permission)
          this.$router.push("/DormManager/dormMain")
        }
      },
      login(){
        if(this.type == 0){
          this.SLoginTest()         
        }
        else if(this.type == 1){
          this.MLoginTest()
        }
        else{
          console.log("------")
        }
      },
    }
}
</script>
<style scoped>
.body {
      /* background: url('https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg') no-repeat; */
      background-image: url("../../assets/LoginBackground.jpg");
      background-size: cover;
      margin-top: 0;
    }

    .login_box {
      width: 24%;
      height: 400px;
      background-color: #00000060;
      text-align: center;
      border-radius: 10px;
      padding: 50px 50px;
      margin-left: 620px;
    }
    .input{ 
   
			width:180px;
			font-size:18px;
			border:0;
			border-bottom:2px solid #fff;
			padding:5px 10px;
			background:#ffffff00;
			color:#fff;
		}
    .h2 {
      color: #ffffff90;
      margin-top: 5%;
    }
    .input {
      border: 0;
      width: 60%;
      font-size: 15px;
      color: #fff;
      background: transparent;
      border-bottom: 2px solid #fff;
      padding: 5px 10px;
      outline: none;
      margin-top: 10px;
    }
    .button {
      margin-top: 50px;
      width: 60%;
      height: 30px;
      border-radius: 10px;
      border: 0;
      color: #fff;
      text-align: center;
      line-height: 30px;
      font-size: 15px;
      background-image: linear-gradient(to right, #30cfd0, #330867);
    }




</style> -->
<template>
  
  <div class="body">
    <el-alert
    :title=alertTitle
    type="warning"
    show-icon
    v-show="alerShow">
  </el-alert>
    <div class="loginbox">
      <div class="login_left">
        <div class="loginbox clearfix">
          <img id="school_logo" src="../../assets/cduestc.png" alt height="100">
          
          <span id="school_name" class="login_title disNone" style="display: inline;">
          电子科技大学成都学院
          </span>
        
        </div>
        <div class="login_name">
          成都学院寝室管理系统
        </div>
        
      </div>
      <div class="login_form">
        <div class="form_tabs">
                    <span class="act">账号密码登录</span>
                </div>
        <div class="form_tabbox" style="display:block;">
          <div>
    <el-radio v-model="type" :label="0" border style="background-color: #f2f3f9; margin-left: 200px;">学生</el-radio>
    <el-radio v-model="type" :label="1" border style="background-color: #f2f3f9;">管理员</el-radio>
  </div>
  <div style="margin-left: 160px;">
                    <div class="form_line">
                        <i class="el-icon-user" style="font-size: 25px;"></i>
                        <input type="text" id="id" v-model="id" class="form_inp" placeholder="请输入账号" autofocus="" maxlength="30">
                    </div>
                    <div class="form_line">
                        <i class="el-icon-edit" style="font-size: 25px;"></i>
                        <input id="pwd" v-model="pwd" class="form_inp" type="password" placeholder="输入密码" maxlength="30">
                    </div>
                    <!-- <div class="form_line">
                      <input id="captcha" v-model="captcha" class="form_inp" type="password" placeholder="输入验证码" maxlength="10" style="width: 180px;padding-left: 0;border-right-style: none;">
                      <img :src=captchaUrl style="width: 120px; height: 40px;margin-left: 10px;" @click="this.captcha='http://localhost:80/api/login/captcha'">
                    </div> -->

     </div>              
                    
                    
                    <el-button style="background-color: #7288fa; color: white; width: 70%;margin-left: 100px;" @click="login">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</el-button>
                    
                    
                </div>
      </div>
    </div>
    
  </div>
  
</template>
<script>
import axios from '@/utils/request';
export default { 
    name: 'DormLogin',
    data(){
        return{
          type:0,
          id:'',
          pwd:'',
          alerShow:false,
          alertTitle:"",
          captcha:'',
          captchaUrl:"http://localhost:80/api/login/captcha"
        }
    },
    methods:{
      async SLoginTest(){
        const res = await axios.get("login/student",{
          params: {
            pwd: this.pwd,
            ID: this.id
          }
        })
        console.log(res)
        if(res.data != null){
          this.$message({
          showClose: true,
          message: '学生身份登录成功',
          type: 'success'
        });
          sessionStorage.setItem('student',res.data.name)
          sessionStorage.setItem('studentId',res.data.id)
          sessionStorage.setItem('college',res.data.college)
          this.$router.push("/student")
        }
        else{
          this.$message({
          showClose: true,
          message: '账号或密码输入错误',
          type: 'error'
        });
        }
      },
      async MLoginTest(){
        const res = await axios.get("login/manager?ID="+this.id+"&pwd="+this.pwd)
        console.log(res)
        if(res.data != null){
          sessionStorage.setItem('manager',res.data.name)
          sessionStorage.setItem('managerId',res.data.id)
          sessionStorage.setItem('permission',res.data.permission)
          let per = res.data.permission
          if(per == 0){
            this.$message({
          showClose: true,
          message: '系统管理员身份登录成功',
          type: 'success'
        });
          }
          else if(per == 1){
            this.$message({
          showClose: true,
          message: '寝室管理员身份登录成功',
          type: 'success'
        });
          }
          
          
        this.$router.push("/DormManager/studentMain")
        }
        else{
          this.$message({
          showClose: true,
          message: '账号或密码输入错误',
          type: 'error'
        });
        }
      },
     
      async login(){
        console.log(this.type)
        if(this.type == 0){
          this.SLoginTest()         
        }
        else if(this.type == 1){
          this.MLoginTest()
          // let form = {}
          // form.verifyCode = this.captcha.toString()
          // const res = await axios.post("/login/loginc",form)
          // console.log(res)
        }
        else{
          this.alertTitle = "错误身份，请重新选择"
          this.alerShow = true
        }
      },
    },
    }
</script>
<style scoped>
.body{
  width: 100%;
  height: 100vh;
  background-image: url("../../assets/login_bg.jpg"); 
  background-size: 100% 100%;
  position: absolute;
}
.login_left {
    margin-left: 5%;
    width: 55%;
}
.loginbox {
    margin-top: 9%;
}
.loginbox img {
    height: 100px;
    vertical-align: top;
    display: inline-block;
    margin-right: 10px;
}
.login_title {
    font-family: 'STHupo';
    height: 100px;
    vertical-align: middle;
    color: #333;
    font-size: 62px;
    font-size: clamp(40px, 58px, 62px);
    z-index: 2;
    background-color: rgba(0, 0, 0, 0);
    position: relative;
    line-height: 100px;
}
.login_name {
  width: 100%;
    margin-bottom: 30px;
    display: inline-block;
    font-family: 'STHupo';
    font-size: 60px;
    line-height: 1em;
    color: #7288fa;
    white-space: nowrap;
    text-overflow: ellipsis;
    overflow: hidden;
}
.login_title_box{
  transform: skew(-30deg);
    content: '';
    display: block;
    width: 100%;
    height: 50px;
    background-color: #9caef6;
    position: absolute;
    top: 25px;
    z-index: -1;
    left: 12px;
}
.login_form {
    width: 35%;
    min-height: 750px;
    box-shadow: 0px 0px 30px 10px rgb(55 64 79 / 5%);
    border: 2px solid rgba(255, 255, 255, 0.5);
    background: rgba(255, 255, 255, 0.3);
    backdrop-filter: blur(18px);
    border-radius: 40px;
    position: absolute;
    right: 0.8%;
    top: 2%;
    bottom: 2%;
}
.form_tabs {
    display: flex;
    justify-content: center;
    font-size: 24px;
    line-height: 1.6em;
    font-family: "Arial";
    color: #333;
    margin-top: 80px;
    margin-bottom: 20px;
}
.form_tabbox {
    display: block;
}
.tabs_type, .form_tabs {
    display: flex;
}
.form_line {
    margin-bottom: 15px;
    vertical-align: middle;
    position: relative;
    display: flex;
    border-radius: 5px;
    background-color: #f9f8f8;
    width: 300px;
    margin-top: 20px;
    
}
.form_inp{
  padding-left: 45px;
    padding-right: 5px;
    width: 300px;
    background: #f9f8f8;
    border: 1px solid #e9dddd;
    border-radius: 4px;
    border-left-style: none;
    line-height: 40px;
}

</style> 