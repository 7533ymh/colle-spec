<template>
    <div id="app">
        <div class="container">
            <form >
                <div class="form-group">
                    <input type="text" v-model="id" placeholder="아이디를 입력합니다"/>
                    <input type="password" v-model="pass" placeholder="비밀번호를 입력합니다"/>
                    <input type="text" v-model="name" placeholder="이름을 입력합니다"/>
                    <input type="text" v-model="sex" placeholder="성별을 입력합니다"/>
                    <input type="text" v-model="mail" placeholder="이메일주소 입력합니다"/>
                    <input type="text" v-model="phone" placeholder="폰번호 입력합니다"/>
                    <input type="text" v-model="objective" placeholder="희망직종 입력합니다"/>
                    <input type="text" v-model="enterprise" placeholder="희망기업 입력합니다"/>
                    <button type="submit" v-on:submit.prevent="updateuser" @click="updateuser">회원수정</button>
                </div>
            </form>
            
        </div>
        <div>
     <h2>회원탈퇴</h2>
     <div>
       <button type="button" @click='deleteuser'>계정삭제하기</button>
     </div>
   </div>
    </div>
</template>

<script>
    //v-on:submit.prevent="btn_register"
    import axios from 'axios';
    import 'url-search-params-polyfill';

    export default {
        data() {
            return {
                id: '',
                pass: '',
                name: '',
                sex: '',
                mail: '',
                phone: '',
                objective: '',
                enterprise: '',
                result: null
            }
        },
        created(){
            axios.get("http://49.50.166.108:4000/api/user")
          .then(user=>{
            this.id = user.data.data.id
            this.pass=user.data.data.pass
            this.name = user.data.data.name
            this.sex = user.data.data.sex
            this.phone = user.data.data.phone
            this.objective = user.data.data.objective
            this.enterprise = user.data.data.enterprise
            this.mail = user.data.data.mail
          })
          .catch(err=>{
            console.log(err)
          })
        },
        methods: {
            
            //정보수정 함수
            updateuser(event) {
                event.preventDefault();
                var url = "http://49.50.166.108:4000/api/user";
                var params = new URLSearchParams();
                params.append('id', this.id); //모든컬럼이다 선언되야 수정이됨 아이디를 무조건바꿔야바낌..
                params.append('pass', this.pass);//선택한값만 바꿀수있도록해야함
                params.append('name', this.name);
                params.append('sex', this.sex);
                params.append('mail', this.mail);
                params.append('phone', this.phone);
                params.append('objective', this.objective);
                params.append('enterprise', this.enterprise);

                axios
                    .put(url, params)
                    .then(response => {
                        alert(response.data.msg)
                        console.log(response);
                    })
                    .catch(err => {
                        alert("실패.")
                        console.log(err)
                    });
            },
            //계정데이터삭제
             deleteuser(){
          axios.delete("http://49.50.166.108:4000/api/user")
          .then(()=>{
            alert('성공적으로 데이터를 삭제했습니다.')
            this.$router.push(returnPath)
          })
        },
        }
    }
</script>

<style>
    @import url("http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.css");
    #app {
        font-family: 'Avenir', Helvetica, Arial, sans-serif;
        -webkit-font-smoothing: antialiased;
        -moz-osz-font-smoothing: grayscale;
        text-align: center;
        color: #2c3e50;
        margin-top: 60px;
    }

    .container {
        border: solid 1px gray;
        padding: 10px;
        margin-bottom: 10px;
        text-align: left;
    }

    #result {
        text-align: left;
        padding: 20px;
        border: solid 1px black;
    }

    .form-group {
        border: dashed 1px gray;
        padding: 5px 5px 5px 20px;
    }
</style>