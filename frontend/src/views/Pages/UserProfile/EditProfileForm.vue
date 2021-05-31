<template>
  <card>
    <b-row align-v="center" slot="header" >
      <b-col cols="8">
        <h3 class="mb-0">Edit profile </h3>
      </b-col>
      <b-col cols="4" class="text-right">
        <button class="el-button" @click="com">적용</button>

        <span><button class="el-button" v-if="linkcheck" @click="update">업데이트</button>
        <button class="el-button" v-else @click="linklo">연동하기</button></span>

      </b-col>
      
    </b-row>

    <b-form @submit.prevent="updateProfile">
      <h6 class="heading-small text-muted mb-4">User information</h6>

      <div class="pl-lg-4">
        <b-row>
          <b-col lg="4">
            <base-input
              type="text"
              label="Username"
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
              label="Email address"
              placeholder="ex)xxxx@email.com"
              v-model="user.mail"
            >
            </base-input>
          </b-col>
          <b-col md="4">
            <base-input
              type="text"
              label="PhoneNum"
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
              v-model="user.link"
            >
            </base-input>
          </b-col>
          <b-col lg="2">
            <base-input
              type="text"
              label="학년"
              placeholder="학년"
              v-model="user.grade"
            >
            </base-input>
          </b-col>
          <b-col lg="4">
            <base-input
              type="text"
              label="대학교"
              placeholder="대학교이름"
              v-model="user.college"
            >
            </base-input>
          </b-col>
          
          <b-col md="4">
            <base-input
              type="text"
              label="전공"
              placeholder="전공"
              v-model="user.major"
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
      <h6 class="heading-small text-muted mb-4">Contact information</h6>

      <div class="pl-lg-4">
        
          <form @submit.prevent="linklogin">
                <div class="black-bg" v-if="modalshow == true">
                  <div class="white-bg">
                    <button type="button" @click="close" tabindex="-1" class="close wj-hide">&times;</button>
                    <h2>Colleasy</h2>
                      <label>
                        ID:<br />
                    <input id="input" type="text" v-model="colleazy.id" required>
                      </label>
                    <br />
                    <label>
                        PW:<br />
                    <input id="input" type="password" v-model="colleazy.pass" required>
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
    </b-form>
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
      modalshow:false,
    colleazy:{
      id:'',
      pass:''
    }
    };
  },
  computed:{
    linkcheck(){
    return store.state.userinfo.link!==0
    }
  },
  methods: {
    close(){{
      this.modalshow=false
    }},
     linklo(){
      if(this.modalshow===true){
        this.modalshow=false
      }else{
        this.modalshow=true
      }
    },
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
      async linklogin(){
        var params = new URLSearchParams()
        params.append('id',this.colleazy.id)
        params.append('pass',this.colleazy.pass)
      await axios.post(`${url}/link`,params)
        .then(res=>{
          alert(res.data.msg)
          axios.get(`${url}/link/update`)
          .then(res=>{
            alert(res.data.msg)
            this.modalshow=false;
          })
          .catch(err=>{
            alert(err.response.data.msg)
          })
        })
        .catch(err=>{
          alert(err.response.data.msg)
        })
      },
    updateProfile() {
      alert('Your data: ' + JSON.stringify(this.user));
    },
    userlink(){
      if(this.$store.getters.userlink === 0){
        this.user.link='off';
      }else{
        this.user.link="on"
      }
  },
  com(){
    var result=confirm("변경하시겠습니까?");
    if(result){
      this.edituser();
    }else{
      alert('취소하셨습니다.')
    }

    
    
  },
  edituser(){
//     confirm('Are you ready?')
// .then(result => {
//   console.log(result)
// })
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
                        location.reload()
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
