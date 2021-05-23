<template>
  <base-nav
    container-classes="container-fluid"
    class="navbar-top navbar-expand"
    :class="{'navbar-dark': type === 'default'}"
  >
    <a href="#" aria-current="page" class="h4 mb-0 text-white text-uppercase d-none d-lg-inline-block active router-link-active"> {{$route.name}} </a>
    <!-- Navbar links -->
    <b-navbar-nav class="align-items-center ml-md-auto">
      <!-- This item dont have <b-nav-item> because item have data-action/data-target on tag <a>, wich we cant add -->
      <li class="nav-item d-sm-none">
        <a class="nav-link" href="#" data-action="search-show" data-target="#navbar-search-main">
          <i class="ni ni-zoom-split-in"></i>
        </a>
      </li>
    </b-navbar-nav>
    <b-navbar-nav class="align-items-center ml-auto ml-md-0">
        
      <base-dropdown menu-on-right
                     class="nav-item"
                     tag="li"
                     title-tag="a"
                     title-classes="nav-link pr-0">
        <a href="#" class="nav-link pr-0" @click.prevent slot="title-container">
          <b-media no-body class="align-items-center">
                  <span class="avatar avatar-sm rounded-circle">
                    <img alt="Image placeholder" src="img/theme/profile.jpg">
                  </span>
            <b-media-body class="ml-2 d-none d-lg-block">
                                                                <!-- 메인화면 프로필 이름표시 -->
              <span class="mb-0 text-sm  font-weight-bold">{{this.$store.state.userinfo.name}}</span>
            </b-media-body>
          </b-media>
        </a>

        <template>

          
          <b-dropdown-item @click="profile()">
            <i class="ni ni-single-02"></i>
            <span >회원정보</span>
          </b-dropdown-item>
          <!-- <b-dropdown-item href="#!">
            <i class="ni ni-settings-gear-65"></i>
            <span>설정</span>
          </b-dropdown-item> -->
         
          
          <div class="dropdown-divider"></div>
          <b-dropdown-item href="#!">
            <i class="ni ni-user-run"></i>
            <span><a href="" v-if="isAuthenticated" @click.prevent="onClickLogout">로그아웃</a>
            <router-link to="/Login1" v-else>로그인</router-link></span>
          </b-dropdown-item>

        </template>
      </base-dropdown>
    </b-navbar-nav>
  </base-nav>
</template>
<script>
import { CollapseTransition } from 'vue2-transitions';
import { BaseNav, Modal } from '@/components';

//store불러오기
import store from '@/store'

export default {
  components: {
    CollapseTransition,
    BaseNav,
    Modal
  },
  props: {
    type: {
      type: String,
      default: 'default', // default|light
      description: 'Look of the dashboard navbar. Default (Green) or light (gray)'
    }
  },
  computed: {
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    },
    //로그인 로그아웃 : 로그인 되있으면 로그아웃으로 변함 
    isAuthenticated(){
      return store.getters.isAuthenticated
    }
  },
  data() {
    return {
      activeNotifications: false,
      showMenu: false,
      searchModalVisible: false,
      searchQuery: '',
      isLogin:this.$store.state.isLogin
    };
  },
  methods: {
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleNotificationDropDown() {
      this.activeNotifications = !this.activeNotifications;
    },
    closeDropDown() {
      this.activeNotifications = false;
    },
    profile(){
      if(this.isLogin === false){
        alert('로그인이 필요한 서비스입니다.')
        this.$router.push({path:'/Login'})
      }else{
        this.$router.push({path:'/profile'})
      }
    }
  },
  mounted(){
    console.log("로그인여부:",this.isLogin)
  }
};
</script>
