<template>
  <div>
    <h2>내 정보</h2>
    <div>
            <label>User Info:</label>
      <form>
        연동: {{myinfo.link}}
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
      <div>
        <button @click="btn_allrank">전체학년알고리즘</button>
        <button @click="btn_grade_rank">학년별알고리즘</button>
        <button @click="btn_c_rank">대학교알고리즘</button>
        <button @click="btn_c_g_rank">대학교학년별알고리즘</button>
        <h2>내정보 로그</h2>
        {{myinfo}}
        <div><h2>1 로그</h2>
        {{allrank}}
        <h2>2 로그</h2>
        {{grade_rank}}
        <h2>3 로그</h2>
        {{college_rank}}
        <h2>4 로그</h2>
        {{college_grade_rank}}
        </div>
      </div>
   </div>
   
  </div>

</template>

<script>
import axios from 'axios'
import store from '../store';

let url=store.state.resourceHost; //서버주소 api
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
            
            myinfo:[{}],
            college_rank:[{}],
            college_grade_rank:[{}],
            grade_rank:[{}],
            allrank:[{}]

        }
    },
    //페이지에 접근하면 자동으로 실행 새로고침해도 데이터갱신
    created(){
      axios.get("http://49.50.166.108:4000/api/user") //유저정보 조회
          .then(user=>{
            console.log('유저정보조회: ',user.data.data)
            this.myinfo=user.data.data
          //   //연동되었을 때
          //   if(this.myinfo.link===1){
          //     axios.get(`${url}/rank/college`,{params:{
          //       idx:this.myinfo.idx
          //     }})
          //     .then(link=>{
          //       console.log('연동됨 college rank: ',link.data.data)
          //       this.college_rank=link.data.data
          //       axios.get(`${url}/rank/college/grade`,{params:{
          //         idx:this.myinfo.idx
          //       }})
          //       .then(link=>{
          //         console.log('연동됨 college grade rank: ',link.data.data)
          //         this.college_grade_rank=link.data.data
          //         axios.get(`${url}/rank`,{params:{
          //           idx:this.myinfo.idx
          //         }})
          //         .then(res=>{
          //           console.log('전체랭크: ',res.data.data)
          //           this.rank=res.data.data
          //         })
          //         .catch(err=>{
          //           console.log(err)
          //         })
          //       })
          //     })
          //   }else{
          //   //연동안했을 때
          //   //유저정보 조회 성공시 등급알고리즘 요청하여 등급적용
          //   axios.get(`${url}/rank`)
          //     .then(res=>{
          //       console.log('연동안됨 rank: ',res.data.data)
          //       this.rank=res.data.data
          //       console.log('전체: ',this.rank)

          //       axios.get(`${url}/rank/grade`,{params:{
          //         idx:this.myinfo.idx
          //       }})
          //       .then(res=>{
          //         console.log('연동안됨 grade_rank: ',res.data.data)
          //         this.grade_rank=res.data.data
          //       })
          //     })        
          //     .catch(err=>{
          //       console.log(err)
          //     })
          //   }
          // })
          // .catch(err=>{
          //   console.log(err)
      })     
    },
    methods: {
      btn_allrank(){
        axios.get(`${url}/rank`)
              .then(res=>{
                this.allrank=res.data
                console.log('전체: ',this.allrank)
      })
      .catch(err=>{
        console.log(err.response.data.msg)
      })
      },
      btn_grade_rank(){
        axios.get(`${url}/rank/grade`)
                .then(res=>{
                  this.grade_rank=res
                  console.log('학년 랭크: ',this.grade_rank)
                })
                .catch(err=>{
        console.log(err.response.data.msg)
      })

      },
      btn_c_rank(){
        axios.get(`${url}/rank/college`)
              .then(res=>{
                this.college_rank=res.data
                console.log('학교 랭크: ',this.college_rank)

              })
              .catch(err=>{
        console.log(err.response.data.msg)
      })
      },
      btn_c_g_rank(){
        axios.get(`${url}/rank/college/grade`)
                .then(res=>{
                  this.college_grade_rank=res.data
                  console.log('연동됨 college grade rank: ',this.college_grade_rank)

                  })
                  .catch(err=>{
        console.log(err.response.data.msg)
      })

      }
       
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
