import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'regenerator-runtime/runtime'

Vue.use(BootstrapVue)

new Vue({
  el: '#app',
  router,
  store,
  //로그인 이후에 새로고침해도 데이터가 그대로 유지되도록 항상 정보를 불러옴
  beforeCreate(){
    this.$store.dispatch('getMyinfo')
  },
  render: h => h(App)
})
