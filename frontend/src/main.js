/*!

=========================================================
* BootstrapVue Argon Dashboard - v1.0.0
=========================================================

* Product Page: https://www.creative-tim.com/product/bootstrap-vue-argon-dashboard
* Copyright 2020 Creative Tim (https://www.creative-tim.com)

* Coded by www.creative-tim.com

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

*/
import Vue from 'vue';
import DashboardPlugin from './plugins/dashboard-plugin';
import App from './App.vue';
import store from './store'

import VueMoment from 'vue-moment'
Vue.use(VueMoment)
// router setup
import router from './routes/router';
// plugin setup
// import VModal from 'vue-js-modal';
Vue.use(DashboardPlugin);
// Vue.use(VModal, { dynamic: true });
export const eventBus =new Vue()
/* eslint-disable no-new */
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
