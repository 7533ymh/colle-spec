<template>
  <div>
    <h2>Home</h2>
    <!-- <div>{{greeting}}</div> -->
    <button type="button" id='to' @click='tokencall'>토큰호출</button>
    <p>{{$store.state.userinfo.name}}님이 로그인 하셨습니다.</p>
    <p></p>
  </div>
</template>

<script>
  //import axios from 'axios'

  export default {
    data() {
      return {
        greeting: ''
        //홈화면 변수
      }
    },
    // created() {
    //   axios.get('http://49.50.166.108:4000/api/home')
    //     .then(result => this.greeting = result.data.greeting)
    // },
     methods:{
      tokencall(){
        let userToken=this.$store.state.accessToken //스토어js에 있는 state의 accessToken값 불러오기
        console.log("userToken:",userToken); //토큰이 저장됐는지 확인

      }
    }
  }
</script>

<style scoped>

</style>
