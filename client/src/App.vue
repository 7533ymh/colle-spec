<template>
  <div>
    <menus></menus>
    <router-view></router-view>
  </div>
</template>

<script>
  import Menus from './components/Menus.vue'

  export default {
    components: {Menus}, //처음실행화면
  }
</script>

<style>
html,body{padding:0; margin:0;}
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
h1{color:#43b984;}
table{width:100%; border-collapse:collapse;}
.wrap{width:100%;}
.container{width:800px; margin:0 auto;}
a{text-decoration:none;}
.btn{padding:10px; background:#34445c; color:#fff;}
</style>
