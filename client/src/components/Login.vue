<template>
  <div>
    <h2>Login</h2>
    <form @submit.prevent="signin()">
      <input type="text" v-model="id" placeholder="아이디 입력">
      <input type="password" v-model="pass" placeholder="비밀번호 입력">
      <input type="submit" value="Login">
    </form>
    <p><i>{{msg}}</i></p>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        //email: '',
        idx:0,
        id:'',
        pass: '',
        msg: ''
      }
    },
    methods: {
      signin() {
        const data = {
		    id:this.id,
		    pass:this.pass,
	      };
        this.$store.dispatch('LOGIN', data) //store에 있는 action함수 실행 :dispatch
          .then(() => this.$router.push('/'))
          .catch(({message}) => this.msg = message)
      },
      redirect() {
        const {search} = window.location
        const tokens = search.replace(/^\?/, '').split('&')
        const {returnPath} = tokens.reduce((qs, tkn) => {
          const pair = tkn.split('=')
          qs[pair[0]] = decodeURIComponent(pair[1])
          return qs
        }, {})

        this.$router.push(returnPath)
      }
    }
  }
</script>

<style scoped>

</style>
