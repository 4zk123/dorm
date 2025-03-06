<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// import HelloWorld from './components/HelloWorld.vue'
// import Manage from './views/Manage.vue'
// import DormHeader from '@/components/Header.vue'
export default {
  name: 'App',
  components: {
    // Manage,
    // HelloWorld
    // DormHeader
  },
  data() {
    return{
      permission: 0
    }
  }
}
</script>

<style>

</style>
