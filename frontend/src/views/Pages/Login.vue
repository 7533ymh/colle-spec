<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      <b-container>
        <div class="header-body text-center mb-7">
          <b-row class="justify-content-center">
            
          </b-row>
        </div>
      </b-container>
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
             xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <b-row class="justify-content-center">
        <b-col lg="5" md="7">
          <b-card no-body class="bg-secondary border-0 mb-0">
            
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <h3>로그인이 필요한 서비스입니다.</h3>
              </div>

              <b-form-checkbox v-model="user.rememberMe">아이디 저장</b-form-checkbox>

                <b-form role="form" @submit.prevent="signin">
                  <base-input alternative
                              class="mb-3"
                              name="아이디"
                              :rules="{required: true}"
                              
                              placeholder="아이디"
                              v-model="user.id">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="비밀번호"
                              :rules="{required: true, min: 1}"
                              
                              type="password"
                              placeholder="비밀번호"
                              v-model="user.pass">
                  </base-input>

                  
                  <div class="text-center">
                    <base-button type="primary" native-type="submit" class="my-4">로그인</base-button>
                  </div>

                  <b-col cols="5">
                   <router-link to="/register" >회원가입</router-link>
                   </b-col>

                </b-form>
            </b-card-body>
          </b-card>
          
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
import store from '@/store'
  export default {
    data() {
      return {
        user: {
          id: '',
          pass: '',
          rememberMe: false,
        },
        //name:this.$store.state.userinfo.name
      };
    },
    methods: {
     async signin() {
        const data = {
		    id:this.user.id,
		    pass:this.user.pass,
	      };
       await this.$store.dispatch('LOGIN', data) //store에 있는 action함수 실행 :dispatch
          .then(res=>{
           alert(this.user.id+"님 반갑습니다.")
            this.$router.push({path:'/'})
            location.reload()
          })
          
          .catch(({message}) => {
            this.msg = message
            })
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