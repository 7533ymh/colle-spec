<template>
  <div>
    <h2>내 정보</h2>
    <div>
            <label>User Info:</label>
      <form>
        idx: {{myinfo.idx}}
        id: {{myinfo.id}}
        <div>
        이름: {{myinfo.name}}
        성별: {{myinfo.sex}}
        메일: {{myinfo.mail}}
        번호: {{myinfo.phone}}
        </div>
        <div>
        희망직종: {{myinfo.objective}}
        희망기업: {{myinfo.enterprise}}
        </div>
        학년: {{myinfo.grade}}
        <div>
        <p>전체등급: {{myinfo.all_rank}}</p>
        <p>
        수상등급: {{myinfo.award_rank}}
        경력등급: {{myinfo.career_rank}}</p>
        <p>자격증등급: {{myinfo.certificate_rank}}
        교육이수등급: {{myinfo.education_rank}}
        </p>
        <p>
        해외경험등급: {{myinfo.experience_rank}}
        학점등급: {{myinfo.grade_rank}}</p>
        <p>어학등급: {{myinfo.language_rank}}
        프로젝트등급: {{myinfo.project_rank}}</p>
        </div>
        점수: {{myinfo.score}}
      </form>
   </div>
   
  </div>

</template>

<script>
import axios from 'axios'

export default {
    data() {
        return {
            // college: '',
            // enterprise: '',
            // grade: '',
            // id: '',
            // idx: '',
            // link: '',
            // mail: '',
            // major: '',
            // name: '',
            // objective: '',
            // phone: '',
            // rank: '',
            // role: '',
            // score: '',
            // sex: '',
            
            myinfo:[]
        }
    },
    //페이지에 접근하면 자동으로 실행 새로고침해도 데이터갱신
    created(){
      axios.get("http://49.50.166.108:4000/api/user")
          .then(user=>{
            this.myinfo=user.data.data
            // this.idx = user.data.data.idx
            // this.id = user.data.data.id
            // this.name = user.data.data.name
            // this.sex = user.data.data.sex
            // this.phone = user.data.data.phone
            // this.objective = user.data.data.objective
            // this.enterprise = user.data.data.enterprise
            // this.rank = user.data.data.all_rank
            // this.score = user.data.data.score
            // this.grade = user.data.data.grade
            // this.mail = user.data.data.mail
            axios.get("http://49.50.166.108:4000/api/rank",{params:{
              idx:this.myinfo.idx
            }})
      .then(rank=>{
        console.log('rank: ',rank)
        axios.get("http://49.50.166.108:4000/api/user")
          .then(user=>{
            this.myinfo=user.data.data
            // this.idx = user.data.data.idx
            // this.id = user.data.data.id
            // this.name = user.data.data.name
            // this.sex = user.data.data.sex
            // this.phone = user.data.data.phone
            // this.objective = user.data.data.objective
            // this.enterprise = user.data.data.enterprise
            // this.rank = user.data.data.all_rank
            // this.score = user.data.data.score
            // this.grade = user.data.data.grade
            // this.mail = user.data.data.mail

          })
          .catch(err=>{
            console.log(err)
          })
        
      })
      .catch(err=>{
            console.log(err)
          })

          })
          .catch(err=>{
            console.log(err)
          })
      
          
    },
    methods: {
       
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
