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
                <h3>로그인하여 연동하기.</h3>
              </div>
                <b-form role="form" @submit.prevent="linklogin">
                  <base-input alternative
                              class="mb-3"
                              name="아이디"
                              :rules="{required: true}"
                              
                              placeholder="아이디"
                              v-model="link.id">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="비밀번호"
                              :rules="{required: true, min: 1}"
                              
                              type="password"
                              placeholder="비밀번호"
                              v-model="link.pass">
                  </base-input>

                  
                  <div class="text-center">
                    <base-button type="primary" native-type="submit" class="my-4">로그인</base-button>
                  </div>
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
import axios from 'axios'
let url=store.state.resourceHost;
  export default {
    data() {
      return {
        link: {
          id: '',
          pass: ''
        },

      };
    },

    methods: {
      async linklogin(){
        var params = new URLSearchParams()
        params.append('id',this.link.id)
        params.append('pass',this.link.pass)
      await axios.post(`${url}/link`,params)
        .then(res=>{
          alert(res.data.msg)
          axios.get(`${url}/link/update`)
          .then(res=>{
            alert(res.data.msg)
            this.$router.push({path:'/main'})
          })
          .catch(err=>{
            alert(err.response.data.msg)
          })
        })
        .catch(err=>{
          alert(err.response.data.msg)
        })
      },
    }
  }
</script>