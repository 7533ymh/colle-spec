import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'
import router from '../routes/router'
Vue.use(Vuex)

const resourceHost = 'http://49.50.166.108:4000/api'
const enhanceAccessToken = () => {
  const {accessToken} = localStorage
  if (!accessToken) return
  axios.defaults.headers.common['X-AUTH-TOKEN'] = `${accessToken}`; //헤더에 포함
}
enhanceAccessToken() //화면을 갱신할때 로컬 스토리지에 저장된 토큰을 axios 헤더에 설정하는 로직

export default new Vuex.Store({
  //실제 데이터 저장
  state: {
    accessToken:null,
    isLogin: false,
    loginError:false,
    userinfo:[{}],
    //userrank:[{}],
    C_userrank:[],
    resourceHost : 'http://49.50.166.108:4000/api'
  },
  //computed에서 함수불러올때 사용
  getters: {
    isAuthenticated (state) {
      state.accessToken = state.accessToken || localStorage.accessToken
      return state.accessToken
    },
    userid(state){
      return state.accessToken ? state.userinfo.id :null;
    },
    userlink(state){
      return state.accessToken ? state.userinfo.link :null;
    },
    islogin(state){
      return state.accessToken ? state.isLogin :false;
    }
  },
  mutations: {
    LOGIN (state, accessToken) {
      state.accessToken = accessToken
      localStorage.accessToken = accessToken //토큰 로컬스토리지에 저장
      state.isLogin =true
      state.loginError =false
    },
    loginError(state){
      state.isLogin=false
      state.loginError=true
    },
    LOGOUT (state) {
      state.accessToken = null
      state.userinfo=null
      delete localStorage.accessToken //로컬스토리지에서 토큰 삭제
    },
    USERINFO(state,payload){
      state.userinfo=payload
      state.isLogin=true
      state.loginError=false
    },
    USERRANK(state,payload){
      //state.userrank=payload
      state.C_userrank=payload
    }
    //actions에서 커밋을 하면 mutations에 변이된 내용으로 변경
  },
  //dispatch로 불러와서 사용 -> dispatch('actions함수','...')
  actions: {
    async LOGIN ({commit,dispatch},{id,pass}) {
      var params =new URLSearchParams();
      params.append('id', id);
      params.append('pass', pass);
     await axios.post(`${resourceHost}/signin`,params)
        .then(res => {
          commit('LOGIN', res.data.data) //커밋: mutations LOGIN값 변경
          // 로그인 이후 모든 HTTP 요청 헤더에 X-AUTH-TOKEN 을 추가한다.
          axios.defaults.headers.common['X-AUTH-TOKEN'] = `${res.data.data}`;
          console.log('res.data.data:',res.data.data)
          //로그인과 동시에 회원정보 요청
          dispatch("getMyinfo") //비동기식처리한 getmyinfo를 불러오면서 getmyinfo함수가 실행되고나서 then을 실행한다 로그인할때 isLogin만 true고 userinfo는 적용안되는 점을 고쳤다.
          dispatch("a_rankView")
          .then(()=>{          
            if(this.state.isLogin===true){
              alert(this.state.userinfo.name+'님 반값습니다.')
              router.push({path:'/main'})

              }
          })
          
            
        })
        .catch(error=>{
          commit('loginError')
          alert(error.response.data.msg)//에러메시지
          window.location.reload()

        })
    },
    LOGOUT ({commit}) {
      axios.defaults.headers.common['X-AUTH-TOKEN'] = undefined //로그아웃시 토큰값 비운다
      commit('LOGOUT')
      localStorage.clear(); //로그아웃시 로컬스토리 전체 비우기
      location.reload()
    },
   async getMyinfo({commit}){
       await axios.get(`${resourceHost}/user`)
        .then(user=>{
          console.log('user:',user)
          commit('USERINFO',user.data.data)
          
        })
        .catch(()=>{
          console.log('유저조회에러')
        })
    },
    async a_rankView({commit}){
      await axios.get(`${resourceHost}/rank`)
                 .then(res=>{
                   localStorage.setItem("rank",JSON.stringify(res.data.data))
                   if(res.data.data.award_rank==='1등급'){
                     //this.radarChartData.datasets[0].data[0]=5
                     var a1=5
                     
                   }
                   else if(res.data.data.award_rank==='2등급'){
                     var a1=4
                     
                     //this.radarChartData.datasets[0].data[0]=4                      
                     }
                   else if(res.data.data.award_rank==='4등급'){
                     var a1=2
                     
                     //this.radarChartData.datasets[0].data[0]=2
                   }
                   else if(res.data.data.award_rank==='5등급'){
                     var a1=1
                     
                     //this.radarChartData.datasets[0].data[0]=1
                   }else{
                     var a1=3
                     
                    //this.radarChartData.datasets[0].data[0]=3
        
                   }

                   //경력
                   if(res.data.data.career_rank==='1등급'){
                     var a2=5
                     
                     //this.radarChartData.datasets[0].data[1]=5
                   }
                   else if(res.data.data.career_rank==='2등급'){
                     var a2=4
                     
                     //this.radarChartData.datasets[0].data[1]=4
                   }
                   else if(res.data.data.career_rank==='4등급'){
                     var a2=2
                    
                    //this.radarChartData.datasets[0].data[1]=2
                   }
                   else if(res.data.data.career_rank==='5등급'){
                    var a2=1
                     
                     //this.radarChartData.datasets[0].data[1]=1
                   }else{
                     var a2=3
                     
                    // this.radarChartData.datasets[0].data[1]=3
                   }
                   
                   //자격증
                    if(res.data.data.certificate_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[2]=5
                     var a3=5
                     
                   }
                   else if(res.data.data.certificate_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[2]=4
                    var a3=4
                    
                   }
                   else if(res.data.data.certificate_rank==='4등급'){
                   // this.radarChartData.datasets[0].data[2]=2
                   var a3=2
                    
                   }
                   else if(res.data.data.certificate_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[2]=1
                     var a3=1
                     
                   }
                   else{
                    // this.radarChartData.datasets[0].data[2]=3
                     var a3=3
                     
                   }

                   //교육
                   if(res.data.data.education_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a4=5
                     
                   }
                   else if(res.data.data.education_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a4=4
                     
                   }
                   else if(res.data.data.education_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a4=2
                     
                   }
                   else if(res.data.data.education_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a4=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a4=3
                    
                   }
                   //해외경험
                   if(res.data.data.experience_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a5=5
                     
                   }
                   else if(res.data.data.experience_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a5=4
                    
                   }
                   else if(res.data.data.experience_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a5=2
                     
                   }
                   else if(res.data.data.experience_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a5=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a5=3
                     
                   }
                   //학점
                   if(res.data.data.grade_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a6=5
                     
                   }
                   else if(res.data.data.grade_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a6=4
                     
                   }
                   else if(res.data.data.grade_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a6=2
                     
                   }
                   else if(res.data.data.grade_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a6=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a6=3
                     
                   }
                   //프로젝트
                   if(res.data.data.project_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a7=5
                     
                   }
                   else if(res.data.data.project_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a7=4
                     
                   }
                   else if(res.data.data.project_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a7=2
                     
                   }
                   else if(res.data.data.project_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a7=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a7=3
                     
                   }
                   //어학
                   if(res.data.data.language_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a8=5
                     
                   }
                   else if(res.data.data.language_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a8=4
                     
                   }
                   else if(res.data.data.language_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a8=2
                     
                   }
                   else if(res.data.data.language_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a8=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a8=3
                     
                   }
                   var result=[a1,a2,a3,a4,a5,a6,a7,a8]
                   localStorage.setItem("rating",JSON.stringify(result))
                   commit('USERRANK',res.data.data)
             })
             .catch(err=>{
               alert(err.response.data.msg)
             })
     },
  }
})


