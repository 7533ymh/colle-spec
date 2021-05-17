<template>
  <div>
    <router-link to="/">Home</router-link>

    <a href="" v-if="isAuthenticated" @click.prevent="onClickLogout">로그아웃</a>
    <router-link to="/login" v-else>로그인</router-link>
    <router-link to="/signup">회원가입</router-link>
    <router-link to="/userupdate">회원수정</router-link>
    <router-link to="/me">MyPage</router-link>   
    <router-link to="/career">Career</router-link>
    <router-link to="/award">Award</router-link>
    <router-link to="/introduction">Introduction</router-link>
    <router-link to="/education">Education</router-link>
    <router-link to="/language">Language</router-link>
    <router-link to="/certificate">Certificate</router-link>
    <router-link to="/experience">Experience</router-link>
    <router-link to="/project">Project</router-link>
    <!-- <router-link to="/board/alllist">전체목록</router-link> -->
    <router-link to="/board/program">프로그램목록</router-link>

  </div>
</template>

<script>
  import store from '../store'
  export default {
    computed: {
      isAuthenticated() {
        return store.getters.isAuthenticated
      }
    },
    methods: {
      onClickLogout() {
        store.dispatch('LOGOUT').then(() => this.$router.push('/'))
        location.reload()
      }
    }
  }
</script>
