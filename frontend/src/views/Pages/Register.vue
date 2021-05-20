
<!-- 회원가입 vue -->

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
              <validation-observer v-slot="{handleSubmit}" ref="formValidator">
                <b-form role="form" @submit.prevent="handleSubmit(btn_register)">
                  
                  
              

                    <div class="form-group mb-3">
                    <input type="text" class="form-control" id="ID" v-model="user.id" placeholder="아이디" @blur="checkDuplicate" />
                    <span class="badge badge-danger mt-1" v-if="!availableID">이미 사용중인 아이디입니다.</span>
                    </div>
                  
                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="이메일"
                              name="Email"
                              
                              v-model="user.mail">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="비밀번호"
                              type="password"
                              name="Password"
                              
                              v-model="user.pass">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="이름(실명)"
                              name="Name"
                              
                              v-model="user.name">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="휴대폰 번호"
                              type="text"
                              name="phone"
                              
                              v-model="user.phone">
                  </base-input>

                  
                  
                  <b-form-group label="성별" v-slot="{ ariaDescribedby }" v-model="user.sex">
                    <b-form-radio  v-model="user.sex" :aria-describedby="ariaDescribedby" name="sex" value="male">남</b-form-radio>
                    <b-form-radio  v-model="user.sex" :aria-describedby="ariaDescribedby" name="sex" value="female">여</b-form-radio>
                  </b-form-group>
                  

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="학년"
                              type="number"
                              name="grade"
                              
                              v-model="user.grade">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="목표기업"
                              name="enterprise"
                              
                              v-model="user.enterprise ">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="목표직종"
                              name="objective"
                              
                              v-model="user.objective ">
                  </base-input>

                 
                  <div class="text-center">
                    <b-button type="submit" variant="primary" class="mt-4">가입하기</b-button>
                  </div>
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
    import axios from 'axios';
    import 'url-search-params-polyfill';
  export default {
    name: 'register',
    data() {
      return {
        user: [{
       //유저정보
        }],
        availableID:true
      }
    },
    methods: {
      btn_register() {
                
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
                                .$router
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
          async checkDuplicate() {
            var url = "http://49.50.166.108:4000/api/signup/check/";
                          await axios
                              .get(url + this.user.id)
                              .then(response => {
                                  console.log(response);
                                  this.availableID = true;
                                console.log('avail:',this.availableID)
                              })
                              .catch(err => {
                                  console.log(err.response.data.msg)
                                  this.availableID = false;
                                  console.log('avail:',this.availableID)
                              });
                      },
        }
    }
</script>