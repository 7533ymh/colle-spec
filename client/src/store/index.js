import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

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
    userinfo:{
      idx:'',id:'',name:'',sex:'',mail:'',phone:'',objective:'',enterprise:'',
      score:'',link:'',rank:''
    },
    resourceHost : 'http://49.50.166.108:4000/api'
  },
  //computed에서 함수불러올때 사용
  getters: {
    isAuthenticated (state) {
      state.accessToken = state.accessToken || localStorage.accessToken
      return state.accessToken
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
    LOGIN ({commit,dispatch},{id,pass}) {
      return axios.get(`${resourceHost}/signin`,{
        params:{
        'id':id,
        'pass':pass}
      })
        .then(res => {
          commit('LOGIN', res.data.data) //커밋: mutations LOGIN값 변경

          // 로그인 이후 모든 HTTP 요청 헤더에 X-AUTH-TOKEN 을 추가한다.
          axios.defaults.headers.common['X-AUTH-TOKEN'] = `${res.data.data}`;
          console.log('res.data.data:',res.data.data)
          alert(res.data.msg)//성공메시지
          
          //로그인과 동시에 회원정보 요청
          dispatch("getMyinfo")
        })
        .catch(error=>{
          commit('loginError')
          alert(error.response.data.msg)//에러메시지
        })
    },
    LOGOUT ({commit}) {
      axios.defaults.headers.common['X-AUTH-TOKEN'] = undefined //로그아웃시 토큰값 비운다
      commit('LOGOUT')
    },
    getMyinfo({commit}){
        axios.get(`${resourceHost}/user`)
        .then(user=>{
          console.log('user:',user)
          commit('USERINFO',user.data.data)
        })
        .catch(()=>{
          console.log('에러')

        })
    }
  }
})


