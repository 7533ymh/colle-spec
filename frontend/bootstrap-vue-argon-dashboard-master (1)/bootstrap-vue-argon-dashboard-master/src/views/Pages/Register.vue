

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
                <b-form role="form" @submit.prevent="handleSubmit(onSubmit)">
                  
                  
              

                    <div class="form-group mb-3">
                    <input type="text" class="form-control" id="ID" v-model="model.ID" placeholder="아이디" @blur="checkDuplicate" />
                    <span class="badge badge-danger mt-1" v-if="!availableID">이미 사용중인 아이디입니다.</span>
                    </div>
                  
                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="이메일"
                              name="Email"
                              
                              v-model="model.email">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="비밀번호"
                              type="password"
                              name="Password"
                              
                              v-model="model.password">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="이름(실명)"
                              name="Name"
                              
                              v-model="model.name">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="휴대폰 번호"
                              type="text"
                              name="phone"
                              
                              v-model="model.phone">
                  </base-input>

                  
                  
                  <b-form-group label="성별" v-slot="{ ariaDescribedby }" v-model="model.sex">
                    <b-form-radio v-model="selected" :aria-describedby="ariaDescribedby" name="sex" value="male">남</b-form-radio>
                    <b-form-radio v-model="selected" :aria-describedby="ariaDescribedby" name="sex" value="female">녀</b-form-radio>
                  </b-form-group>
                  

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="학년"
                              type="number"
                              name="grade"
                              
                              v-model="model.grade">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="목표기업"
                              name="enterprise"
                              
                              v-model="model.enterprise ">
                  </base-input>

                  <base-input alternative
                              class="mb-3"
                              
                              placeholder="목표직종"
                              name="objective"
                              
                              v-model="model.objective ">
                  </base-input>

                  <!--<div class="text-muted font-italic"><small>password strength: <span
                    class="text-success font-weight-700">strong</span></small></div>
                   <b-row class=" my-4">
                    <b-col cols="12">
                      <base-input :rules="{ required: { allowFalse: false } }" name=Privacy Policy>
                        <b-form-checkbox v-model="model.agree">
                         <span class="text-muted">I agree with the <a href="#!">Privacy Policy</a></span>
                        </b-form-checkbox>
                      </base-input>
                    </b-col>
                  </b-row>-->
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

  export default {
    name: 'register',

    data() {
      return { 
        model: { //여기에 키값 
          ID : '',
          name: '',
          email: '',
          password: '',
          phone : '',
          sex : '',
          grade : '',
          enterprise : '',
          objective : '',
          //agree: false
        }
      }
    },
    methods: {
      onSubmit() {
        // 여기에 데이터 전달
      }
    },


    async checkDuplicate() {
	//일단은 사용가능한 아이디로 true로 초기화 한다.
	this.availableID = true;
    
    //아이디 중복체크를 한다.
	const response = await checkDuplicateID(this.ID);
	if (!response.data) {
		this.availableID = false;
	} else {
		this.availableID = true;
	}
},

    

  };

</script>
<style></style>
