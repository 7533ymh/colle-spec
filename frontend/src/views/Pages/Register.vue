<template>
  <div>
    <!-- Header -->
    <div class="header bg-gradient-success py-7 py-lg-8 pt-lg-9">
      
      <div class="separator separator-bottom separator-skew zindex-100">
        <svg x="0" y="0" viewBox="0 0 2560 100" preserveAspectRatio="none" version="1.1"
             xmlns="http://www.w3.org/2000/svg">
          <polygon class="fill-default" points="2560 0 2560 100 0 100"></polygon>
        </svg>
      </div>
    </div>
    <!-- Page content -->
    <b-container class="mt--8 pb-5">
      <!-- Table -->
      <b-row class="justify-content-center">
        <b-col lg="6" md="8" >
          <b-card no-body class="bg-secondary border-0">
            
            <b-card-body class="px-lg-5 py-lg-5">
              <div class="text-center text-muted mb-4">
                <h3>회원가입</h3>
              </div>
              <!-- <validation-observer v-slot="{handleSubmit}" ref="formValidator"> -->
                <b-form role="form">

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="아이디"
                              :rules="{required: true}"
                              v-model="user.id">
                  </base-input>
                  <button type="submit" @click="check">아이디중복체크</button>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="이름(실명)"
                              name="Name"
                              :rules="{required: true}"
                              v-model="user.name">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-email-83"
                              placeholder="이메일"
                              name="Email"
                              :rules="{required: true, email: true}"
                              v-model="user.mail">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-lock-circle-open"
                              placeholder="비밀번호"
                              type="password"
                              name="Password"
                              :rules="{required: true, min: 6}"
                              v-model="user.pass">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="학년(1~4)"
                              
                              :rules="{required: true}"
                              v-model="user.grade">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="성별(남,여)"
                              
                              :rules="{required: true}"
                              v-model="user.sex">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="휴대폰 번호"
                              
                              :rules="{required: true}"
                              v-model="user.phone">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="희망직종"
                              
                              :rules="{required: true}"
                              v-model="user.objective">
                  </base-input>
                  <base-input alternative
                              class="mb-3"
                              prepend-icon="ni ni-hat-3"
                              placeholder="희망기업"
                              
                              :rules="{required: true}"
                              v-model="user.enterprise">
                  </base-input>
                  
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4" @click="btn_register">가입하기</b-button>
                  </div>
                </b-form>
              <!-- </validation-observer> -->
            </b-card-body>
          </b-card>
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>
<script>
    import axios from 'axios';
    import 'url-search-params-polyfill';
  export default {
    name: 'register',
    data() {
      return {
        user: [{
       //유저정보
        }]
      }
    },
    methods: {
      btn_register(event) {
                event.preventDefault();
                
                var url = "http://49.50.166.108:4000/api/signup";

                var params = new URLSearchParams();
                params.append('id', this.user.id);
                params.append('pass', this.user.pass);
                params.append('grade', this.user.grade);
                params.append('name', this.user.name);
                params.append('sex', this.user.sex);
                params.append('mail', this.user.mail);
                params.append('phone', this.user.phone);
                params.append('objective', this.user.objective);
                params.append('enterprise', this.user.enterprise);
                axios
                    .post(url, params)
                    .then(response => {
                        if (response.status === 200) {
                            alert(response.data.msg)
                            // 성공적으로 회원가입이 되었을 경우
                            this
                                .$routes.routes
                                .push({name: 'Login1'}); //로그인화면으로 넘어간다.
                        }
                        console.log(response);
                    })
                    .catch(error => {
                        //console.error(error);
                        console.log(error.response.data.msg)
                        alert(error.response.data.msg) //회원가입 실패시 에러메시지
                    });
            },

            //아이디 중복체크 함수
            check(event) {
                event.preventDefault();
                var url = "http://49.50.166.108:4000/api/signup/check/";
                axios
                    .get(url + this.user.id)
                    .then(response => {
                        alert(response.data.msg)
                        console.log(response);
                    })
                    .catch(err => {
                        alert(err.response.data.msg)
                        console.log(err.response.data.msg)
                    });
            },
        }
    }
</script>