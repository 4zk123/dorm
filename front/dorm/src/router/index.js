import VueRouter from 'vue-router';
import Vue from 'vue';

// import Manage from '@/views/Manage.vue'
import HelloWorld from '@/components/HelloWorld.vue'
import DormManager from '@/views/DormManager/DormManager'
import DormLogin from "@/views/login/LoginIndex.vue"
import StudentMain from "@/components/Main.vue"
import DormMain from "@/components/DormMain.vue"
import DormApproval from "@/views/DormApproval/DormApproval.vue"
import DormRegister from "@/views/DormRegister/DormRegister.vue"
import DormAffiche from "@/views/DormAffiche/DormAffiche.vue"
import SchoolRegister from "@/views/SchoolRegister/SchoolRegister.vue"
import StudentManage from "@/views/DormStudent/DormStudent.vue"
import SchoolApply from '@/views/SchoolApply/SchoolApply.vue'
import LivingApply from '@/views/ManageApply/LivingApply.vue'
import StudentIndex from '@/views/StudentIndex/StudentIndex.vue'
import DormBuilding from '@/views/ManageDorm/DormBuilding.vue'
import DormBunk from '@/views/ManageDorm/DormBunk.vue'
import DormRoom from '@/views/ManageDorm/DormRoom.vue'
import BackApply from '@/views/ManageApply/BackApply.vue'
import StudentBack from '@/views/SchoolApply/StudentBack.vue'
import OtherApply from '@/views/ManageApply/OtherApply.vue'
import StudentOther from '@/views/SchoolApply/StudentOther.vue'
import PreDorms from '@/views/PreDorm/PreDorm.vue'
import ManagePre from '@/views/ManagePre/ManagePre.vue';
import ChangeApply from '@/views/SchoolApply/ChangeApply.vue';
import ManageChange from '@/views/ManageApply/ManageChange.vue';
Vue.use(VueRouter)
const router = new VueRouter({
  routes:[
    {path: '/', redirect: '/login'},
    {path: '/student',redirect:'/student/index'},
    {path : '/DormManager', component: DormManager,
    children:[
      {
        path: '/DormManager/studentMain',
        name: 'StudentMain',
        component: StudentMain
      },
      {
        path: '/DormManager/dormMain',
        name: 'DormMain',
        component: DormMain
      },
      {
        path: '/DormManager/dormApproval',
        name: 'DormApproval',
        component: DormApproval
      },
      {
        path: '/DormManager/dormRegister',
        name: 'DormRegister',
        component: DormRegister,
      },
      {
        path: '/DormManager/dormAffiche',
        name: 'DormAffiche',
        component: DormAffiche
      },
      {
        path: '/DormManager/dormRegister/SchoolRegister',
        name: 'SchoolRegister',
        component: SchoolRegister
      },
      {
        path: '/ManageApply/LivingApply',
        name: 'LivingApply',
        component: LivingApply
      },
      {
        path: '/ManageApply/BackApply',
        name: 'BackApply',
        component: BackApply
      },
      {
        path: '/ManageApply/ManageChange',
        name: 'ManageChange',
        component: ManageChange
      },
      {
        path: '/ManageApply/OtherApply',
        name: 'OtherApply',
        component: OtherApply
      },
      {
        path: '/DormManager/Building',
        name: 'DormBuilding',
        component: DormBuilding
      },
      {
        path: '/DormManager/Bunk',
        name: 'DormBunk',
        component: DormBunk
      },
      {
        path: '/DormManager/Room',
        name: 'DormRoom',
        component: DormRoom
      },
      {
        path: '/DormManager/Pre',
        name: 'ManagePre',
        component: ManagePre
      },
    ]
  },
    {path: '/student', component: StudentManage,children:[
      {
        path: '/student/SchoolApply',
        name: 'SchoolApply',
        component: SchoolApply
      },
      {
        path: '/student/StudentBack',
        name: 'StudentBack',
        component: StudentBack
      },
      {
        path: '/student/StudentOther',
        name: 'StudentOther',
        component: StudentOther
      },
      {
        path: '/student/index',
        name: 'StudentIndex',
        component: StudentIndex
      },
      {
        path: '/student/predorms',
        name: 'PreDorms',
        component: PreDorms
      },
      {
        path: '/student/changeApply',
        name: 'ChangeApply',
        component: ChangeApply
      },
    ]},
    {path: '/hello', component: HelloWorld},
    {path: '/login', component: DormLogin}
  ]
})
export default router