<template>
    <div style="display: flex;">
        <div class="box" @click="to()">
            返校离校审批
            
        </div>
        <div class="box">
            换寝审批
        </div>
        <div class="box">
            校外居住审批
        </div>  
        <div class="box">
            其他审批
        </div>    
    </div>
</template>
<script>
export default {
    name: 'dormApproval',
    data() {
        return {
            
        }
    },
    methods: {
        to(){
            this.$router.push('/DormManager/dormRegister/SchoolRegister')
        }
    }
}
</script>
<style>
.box{
    width: 200px;
    height: 300px;
    border-radius: 10%;
    background-color: #D3DCE6;
    margin-top: 200px;
    margin-left: 120px;
    text-align: center;
    line-height: 300px;
    font-size: 30px;
}
</style>