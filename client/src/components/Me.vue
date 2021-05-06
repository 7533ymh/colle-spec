<template>
  <div>
    <h2>내 정보</h2>
    <div>
            <label>User Info:</label>
      <form>
        <input type="text" v-model="id">
        <p>idx: {{idx}}</p>
        <p>id: {{id}}</p>
        <p>이름: {{name}}</p>
        <p>성별: {{sex}}</p>
        <p>메일: {{mail}}</p>
        <p>번호: {{phone}}</p>
        <p>희망직종: {{objective}}</p>
        <p>희망기업: {{enterprise}}</p>
        <p>학점: {{grade}}</p>
        <p>등급: {{rank}}</p>
        <p>점수: {{score}}</p>
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
import axios from 'axios'

export default {
    data() {
        return {
            college: '',
            enterprise: '',
            grade: '',
            id: '',
            idx: '',
            link: '',
            mail: '',
            major: '',
            name: '',
            objective: '',
            phone: '',
            rank: '',
            role: '',
            score: '',
            sex: '',
        }
    },
    //페이지에 접근하면 자동으로 실행 새로고침해도 데이터갱신
    created(){
          axios.get("http://49.50.166.108:4000/api/user")
          .then(user=>{
            this.idx = user.data.data.idx
            this.id = user.data.data.id
            this.name = user.data.data.name
            this.sex = user.data.data.sex
            this.phone = user.data.data.phone
            this.objective = user.data.data.objective
            this.enterprise = user.data.data.enterprise
            this.rank = user.data.data.rank
            this.score = user.data.data.score
            this.grade = user.data.data.grade
            this.mail = user.data.data.mail

          })
          .catch(err=>{
            console.log(err)
          })
    },
    methods: {
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
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
