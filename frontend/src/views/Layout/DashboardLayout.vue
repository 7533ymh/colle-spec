<template>
  <div class="wrapper">
    <notifications></notifications>
    <side-bar>
      <template slot="links">
        

        <sidebar-item
          :link="{
            name: '메인',
            path: '/main',
            
          }"
        >
        </sidebar-item>

          <span  @click="access('/Grade')">
          <sidebar-item
          :link="{
            name: '나의 포트폴리오',
            path: '/Grade',
            
          }"/>
          </span>

      <span @click="access('/RatingSystem')">
        <sidebar-item 
          :link="{
            name: '나의 등급제',
            path: '/RatingSystem',
            
          }"
        >
        </sidebar-item>
      </span>
        <span @click="access('/Competition1')">
        <sidebar-item
          :link="{
            name: '공모전, 대회, 프로그램',
            path: '/Competition1',
            
          }"
        >
        </sidebar-item>
        </span>

       
        <span @click="access('/Company/info')">
        <sidebar-item
          :link="{
            name: '기업정보',
            path: '/Company/info',
            
          }"
        >
        </sidebar-item>
        </span>
     
      </template>
    </side-bar>
    <div class="main-content">
      <dashboard-navbar :type="$route.meta.navbarType"></dashboard-navbar>

      <div @click="$sidebar.displaySidebar(false)">
        <fade-transition :duration="200" origin="center top" mode="out-in">
          <!-- your content here -->
          <router-view></router-view>
        </fade-transition>
      </div>
      <content-footer v-if="!$route.meta.hideFooter"></content-footer>
    </div>
  </div>
</template>
<script>
  /* eslint-disable no-new */
  import PerfectScrollbar from 'perfect-scrollbar';
  import 'perfect-scrollbar/css/perfect-scrollbar.css';

  function hasElement(className) {
    return document.getElementsByClassName(className).length > 0;
  }

  function initScrollbar(className) {
    if (hasElement(className)) {
      new PerfectScrollbar(`.${className}`);
    } else {
      // try to init it later in case this component is loaded async
      setTimeout(() => {
        initScrollbar(className);
      }, 100);
    }
  }


  import DashboardNavbar from './DashboardNavbar.vue';
  import ContentFooter from './ContentFooter.vue';
  import DashboardContent from './Content.vue';
  import { FadeTransition } from 'vue2-transitions';

  import store from '@/store';

  export default {
    data(){
      return{
        isLogin:store.state.isLogin
      }
    },
    components: {
      DashboardNavbar,
      ContentFooter,
      DashboardContent,
      FadeTransition
    },
    methods: {
      access(url){
      if(this.isLogin === false ){
        alert('로그인이 필요한 서비스입니다.')
        this.$router.push({path:'/login'})
      }
      else{
        this.$router.push({path:url})
      }
    },
      initScrollbar() {
        let isWindows = navigator.platform.startsWith('Win');
        if (isWindows) {
          initScrollbar('sidenav');
        }
      },
      //로그아웃 메소드
      onClickLogout(){
        store.dispatch('LOGOUT').then(res=>{this.$router.push('/')
        location.reload()
        })
      }
    },
    mounted() {
      this.initScrollbar()
    },
    computed:{
     //로그인 로그아웃 : 로그인 되있으면 로그아웃으로 변함 
    isAuthenticated(){
      return store.getters.islogin
    }
    }
  };
</script>
<style lang="scss">
</style>
