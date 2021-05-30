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
    }
  }
})


