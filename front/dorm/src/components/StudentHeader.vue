<template>
    <div style="width: 100%; height: 100%; display: flex;">
          <img src="../assets/cduestc.png" alt="logo" style="width: 60px; height: 60px; float: left;">
          <div style="font-size: 30px; margin-left: 500px;">科成寝室管理系统</div>
          <img :src=imgurl :alt=sname style="border-radius: 50%;margin-left: 530px;">
          <i class="el-icon-switch-button" style="font-size: 30px; margin-top: 20px; margin-left: 20px;" @click="to"></i>
        </div>
    </template>
    <script>
    export default {
      name: 'DormHeader',
      props: {
        imgurl: String
      },
      data(){
        return{
          sname: ''
        }
      },
      methods: {
        to(){
          sessionStorage.clear()
          this.$router.push('/login')
        }
      }
    }
    </script>
    <style>
    
    </style>