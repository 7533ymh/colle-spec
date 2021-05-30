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

        <sidebar-item
          :link="{
            name: '포트폴리오',
            path: '/Basic_Information',    
          }"
          >
          <sidebar-item
          :link="{
            name: '나의 포트폴리오',
            path: '/Basic_Information',
            
          }"/>

          <sidebar-item
          :link="{
            name: '등록하기',
            path: '/basic',
            
          }"/>
        </sidebar-item>

        

        <sidebar-item
          :link="{
            name: '나의 등급제',
            path: '/RatingSystem',
            
          }"
        >
        </sidebar-item>

        <sidebar-item
          :link="{
            name: '공모전, 대회, 프로그램',
            path: '/Competition1',
            
          }"
        >
        </sidebar-item>


        <sidebar-item
          :link="{
            name: '기업 스펙 & 정보',
            path: '/Company',
            
          }"
        >
        <sidebar-item
          :link="{
            name: '기업 스펙비교',
            path: '/Company',
            
          }"
        >
        </sidebar-item>
        <sidebar-item
          :link="{
            name: '기업 정보',
            path: '/Company/info',
            
          }"
        >
        </sidebar-item>
        </sidebar-item>


     <a href="" v-if="isAuthenticated" @click.prevent="onClickLogout" >
           <sidebar-item :link="{name:'로그아웃',path:'/'}"></sidebar-item>
           </a>  
            <router-link to="/Login" v-else>
            <sidebar-item  :link="{name:'로그인',path:'/Login'}"></sidebar-item>
        </router-link>
    
      </template>

      <template slot="links-after">

        
        <hr class="my-3">
        <h6 class="navbar-heading p-0 text-muted">Documentation</h6>

        <b-nav class="navbar-nav mb-md-3">
          <b-nav-item
               href="https://www.creative-tim.com/learning-lab/bootstrap-vue/quick-start/argon-dashboard"
               >
              <i class="ni ni-spaceship"></i>
              <b-nav-text class="p-0">Getting started</b-nav-text>
          </b-nav-item>
          <b-nav-item href="https://www.creative-tim.com/learning-lab/bootstrap-vue/colors/argon-dashboard">
              <i class="ni ni-palette"></i>
              <b-nav-text class="p-0">Foundation</b-nav-text>
          </b-nav-item>
          <b-nav-item href="https://www.creative-tim.com/learning-lab/bootstrap-vue/avatar/argon-dashboard">
              <i class="ni ni-ui-04"></i>
              <b-nav-text class="p-0">Components</b-nav-text>
          </b-nav-item>
        </b-nav>

-
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
    components: {
      DashboardNavbar,
      ContentFooter,
      DashboardContent,
      FadeTransition
    },
    methods: {
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
