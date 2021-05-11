<template>
    <div id="app">
        <div class="container">
            <form >
                <div class="form-group">
                    <input type="text" size='50px' v-model="id" placeholder="아이디를 입력합니다"/>
                    <button type="submit" @click="check">아이디중복체크1</button>
                    <div id="c">
                    <input type="password" size='50px' v-model="pass" placeholder="비밀번호를 입력합니다"/>
                    </div>
                    <div id="c">
                    <input type="text" size='50px' v-model="grade" placeholder="학년을 입력합니다"/>
                    </div>
                    <input type="text" size='50px' v-model="name" placeholder="이름을 입력합니다"/>
                    <div id="c">
                    <input type="text" size='50px' v-model="sex" placeholder="성별을 입력합니다"/>
                    </div>
                    <input type="text" size='50px' v-model="mail" placeholder="이메일주소 입력합니다"/>
                    <div id="c">
                    <input type="text" size='50px' v-model="phone" placeholder="폰번호 입력합니다"/>
                    </div>
                    <input type="text" size='50px' v-model="objective" placeholder="희망직종 입력합니다"/>
                    <input type="text" size='50px' v-model="enterprise" placeholder="희망기업 입력합니다"/>
                    <button type="submit"  v-on:submit.prevent="btn_register" @click="btn_register">가입하기</button>
                </div>
            </form>
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
                grade:'',
                name: '',
                sex: '',
                mail: '',
                phone: '',
                objective: '',
                enterprise: '',
                result: null
            }
        },
       
        methods: {
            //회원가입 기능
            btn_register(event) {
                event.preventDefault();
                
                var url = "http://49.50.166.108:4000/api/signup";

                var params = new URLSearchParams();
                params.append('id', this.id);
                params.append('pass', this.pass);
                params.append('grade', this.grade);
                params.append('name', this.name);
                params.append('sex', this.sex);
                params.append('mail', this.mail);
                params.append('phone', this.phone);
                params.append('objective', this.objective);
                params.append('enterprise', this.enterprise);
                axios
                    .post(url, params)
                    .then(response => {
                        if (response.status === 200) {
                            alert(response.data.msg)
                            // 성공적으로 회원가입이 되었을 경우
                            this
                                .$router
                                .push({name: 'Login'}); //로그인화면으로 넘어간다.
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
                    .get(url + this.id)
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
    #c{
        margin-top:10px;
        margin-bottom: 10px;
    }
    .container {
        border: solid 13px gray;
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