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

              <b-form-checkbox v-model="model.rememberMe">아이디 저장</b-form-checkbox>

              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  <base-input alternative
                              class="mb-3"
                              name="아이디"
                              :rules="{required: true, email: true}"
                              
                              placeholder="아이디"
                              v-model="model.email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              name="비밀번호"
                              :rules="{required: true, min: 6}"
                              
                              type="password"
                              placeholder="비밀번호"
                              v-model="model.password">
                  </base-input>

                  
                  <div class="text-center">
                    <base-button type="primary" native-type="submit" class="my-4">로그인</base-button>
                  </div>

                  <b-col cols="5">
                   <router-link to="/register" >회원가입</router-link>
                   </b-col>

                </b-form>
              </validation-observer>
            </b-card-body>
          </b-card>
          
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
  export default {
    data() {
      return {
        model: {
          email: '',
          password: '',
          rememberMe: false
        }
      };
    },
    methods: {
      onSubmit() {
        // this will be called only after form is valid. You can do api call here to login 여기에 DB키 넣으면 될듯
      }
    }
  };
</script>
