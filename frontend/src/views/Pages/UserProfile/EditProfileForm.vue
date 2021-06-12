<template>
  <card>
    <b-row align-v="center" slot="header" >
      <b-col cols="8">
        <h3 class="mb-0">회원정보 수정 </h3>
      </b-col>
      <b-col cols="4" class="text-right">
        

        <span v-if="linkcheck"><button class="el-button" @click="update">연동업데이트</button></span>
        <span v-else><button class="el-button" @click="linklo">연동하기</button></span>
        

      </b-col>
      
    </b-row>

    <b-form @submit.prevent="com">
      <!--<h6 class="heading-small text-muted mb-4">User information</h6>-->

      <div class="pl-lg-4">
        <b-row>
          <b-col lg="4">
            <base-input
              type="text"
              label="이름"
              placeholder="Username"
              v-model="user.name"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="아이디"
              placeholder="아이디"
              v-model="user.id"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="password"
              label="비밀번호"
              placeholder="password"
              v-model="user.pass"
            >
            </base-input>
          </b-col>
          
        </b-row>
          <b-row>
          <b-col lg="6">
            <base-input
              type="email"
              label="이메일"
              placeholder="ex)xxxx@email.com"
              v-model="user.mail"
            >
            </base-input>
          </b-col>
          <b-col md="4">
            <base-input
              type="text"
              label="휴대폰 번호"
              placeholder="PhoneNum"
              v-model="user.phone"
            >
            </base-input>
          </b-col>
          <b-col lg="2">
            <base-input
              type="text"
              label="성별"
              placeholder="성별"
              v-model="user.sex"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row >
          <b-col lg="2">
            <base-input
              type="text"
              label="연동"
              placeholder="연동"
              v-model="link"
            >
            </base-input>
          </b-col>
          <b-col lg="2">
            <base-input
              type="text"
              label="학년"
              placeholder="학년"
              v-model="user.grade"
              :disabled="isDisabled"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="대학교"
              placeholder="대학교이름"
              v-model="user.college"
              :disabled="Disabled"
            >
            </base-input>
          </b-col>
          
          <b-col md="4">
            <base-input
              type="text"
              label="전공"
              placeholder="전공"
              v-model="user.major"
              :disabled="Disabled"
            >
            </base-input>
          </b-col>
        </b-row>
        <b-row>
          <b-col lg="4">
            <base-input
              type="text"
              label="관심직종"
              placeholder="관심직종"
              v-model="user.objective"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="관심기업"
              placeholder="관심기업"
              v-model="user.enterprise"
            >
            </base-input>
          </b-col>
        </b-row>
      </div>
      <hr class="my-4">

      <!-- Address -->
    </b-form>
    <button class="el-button" style="margin-left:90%" @click.prevent="com">저장</button>
    
    <div class="pl-lg-4">
        
          <form @submit.prevent="linklogin">
                <div class="black-bg" v-if="modalshow == true">
                  <div class="white-bg">
                    <button type="button" @click="close" tabindex="-1" class="close wj-hide">&times;</button>
                    <h2>Colleasy</h2>
                      <label>
                        ID:<br />
                    <input id="input" type="text" v-model="colleasy.id" required>
                      </label>
                    <br />
                    <label>
                        PW:<br />
                    <input id="input" type="password" v-model="colleasy.pass" required>
                    <div>
                    <button type="submit" class="el-button" id="login">
                        Log in
                    </button>
                    </div>
                    </label>
                    
                  </div>
          </div>
          </form>
      </div>
  </card>
</template>
<script>
import store from '@/store'
import axios from 'axios'
let url=store.state.resourceHost; //서버주소 api
export default {
  data() {
    return {
      user:this.$store.state.userinfo,
      link:'',
      disa:false,
      modalshow:false,
      colleasy:{
      id:'',
      pass:''
    }
    };
  },
  computed:{
    linkcheck(){
    return store.state.userinfo.link!==0
    },
    isDisabled() {
    return this.link==='on';
  },
    Disabled() {
    return this.disa===false;
  }
  },
  methods: {
    //모달창닫기
    close(){{
      this.modalshow=false
    }},
    //연동버튼 클릭으로 창열고 닫기
     linklo(){
      if(this.modalshow===true){
        this.modalshow=false
      }else{
        this.modalshow=true
      }
    },
    //연동데이터 업데이트
    update(){
      axios.get(`${url}/link/update`)
          .then(res=>{
            alert(res.data.msg)
            this.modalshow=false;
          })
          .catch(err=>{
            alert(err.response.data.msg)
          })
    },
    //연동 로그인
      async linklogin(){
        var params = new URLSearchParams()
        params.append('id',this.colleasy.id)
        params.append('pass',this.colleasy.pass)
      await axios.post(`${url}/link`,params)
        .then(res=>{
          alert(res.data.msg)
          axios.get(`${url}/link/update`)
          .then(res=>{
            alert(res.data.msg)
            this.modalshow=false;
            location.reload()
          })
          .catch(err=>{
            alert(err.response.data.msg)
          })
        })
        .catch(err=>{
          alert(err.response.data.msg)
        })
      },

   

    userlink(){
      if(this.$store.getters.userlink === 0){
        this.link='off';
      }else{
        this.link="on"
      }
  },
  com(event){
    event.preventDefault()
    var result=confirm("변경하시겠습니까?");
    if(result){
      this.edituser();
      
    }else{
      alert('취소하셨습니다.')
    }

    
    
  },
  //로그아웃 메소드
      onClickLogout(){
        store.dispatch('LOGOUT')
        .then(res=>{
          this.$router.push({path:'/login'})
        })
      },
  edituser(){
    var params = new URLSearchParams();
                params.append('id', this.user.id); 
                params.append('pass', this.user.pass);
                params.append('name', this.user.name);
                params.append('grade', this.user.grade);
                params.append('sex', this.user.sex);
                params.append('mail', this.user.mail);
                params.append('phone', this.user.phone);
                params.append('objective', this.user.objective);
                params.append('enterprise', this.user.enterprise);
                axios.put(`${url}/user`,params)
                .then(response => {
                        alert(response.data.msg)
                        console.log(response);
                        window.location.reload()
                    })
                    .catch(err => {
                        alert(err.response.data.msg)
                        console.log(err)
                    });
  }
  },
  mounted(){
    this.userlink();
  }
};
</script>
<style>

.black-bg{
  width: 16%;
  height: 43%;
  background: rgb(221, 221, 221);
  position: fixed;
  padding: 0px;
  left:50%;
  top:100px;
  border:2px solid rgb(170, 156, 155);
    border-radius: 15px;
}
.white-bg{
  width: 100%;
  height: 100%;
  background: white;
  border-radius: 15px;
  padding: 20px;
}
#input{
  margin-bottom: 6px;
}
input:invalid {
        border-color: red;
    }
  #login{
    font-size: 10px;
    margin-top: 10px;
    margin-left: 100px;
  }
</style>
