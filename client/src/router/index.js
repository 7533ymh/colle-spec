import Vue from 'vue'
import Router from 'vue-router'
import Home from '../components/Home.vue'
import Login from '../components/Login.vue'
import Signup from '../components/Signup.vue'
import Userupdate from '../components/Userupdate.vue'
import Me from '../components/Me.vue'
import store from '../store'
import Career from '../components/Career.vue'
import Award from '../components/Award.vue'
import Introduction from '../components/Introduction.vue'
import Education from '../components/Education.vue'
import Language from '../components/Language.vue'
import Certificate from '../components/Certificate.vue'
import Experience from '../components/Experience.vue'
import Project from '../components/Project.vue'
import Detail from '../components/Detail.vue'
import test from '../components/test.vue'




Vue.use(Router)

// const requireAuth = (to, from, next) => {
//   if (store.getters.isAuthenticated) 
//   return next()
//   next('/login?returnPath=me')
// }
const loginAuth =(to, from, next) => {
  if (store.state.isLogin===true){
    alert('이미 로그인이 되어있습니다.')
    next('/')
  }else{
    next()
  }
}
const accessloginAuth =(to, from, next) => {
  if (store.state.isLogin===false){
    alert('로그인이 필요합니다.')
    next('/login')
  }else{
    next()
  }
}

export default new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/login',
      name: 'Login',
      component: Login,
      beforeEnter:loginAuth //로그인된 상태로 다시 로그인할수없음
    },
    {
      path: '/signup',
      name: 'Signup',
      component: Signup
    },
    {
      path: '/userupdate',
      name: 'Userupdate',
      component: Userupdate,
      beforeEnter: accessloginAuth
    },
    {
      path: '/career',
      name: 'career',
      component: Career,
    },
    {
      path: '/award',
      name: 'award',
      component: Award,
    },
    {
      path: '/introduction',
      name: 'introduction',
      component: Introduction
    },
    {
      path: '/education',
      name: 'education',
      component:Education
    },
    {
      path: '/language',
      name: 'language',
      component:Language
    },
    {
      path: '/certificate',
      name: 'certificate',
      component:Certificate
    },
    {
      path: '/experience',
      name: 'experience',
      component:Experience
    },
    {
      path: '/project',
      name: 'project',
      component:Project
    },
    {
      path: '/detail',
      name: 'Detail',
      component:Detail
    },
    {
      path: '/test',
      name: 'test',
      component:test
    },
    {
      path: '/me',
      name: 'Me',
      component: Me,
      //beforeEnter: requireAuth(),
      beforeEnter: accessloginAuth //마이페이지는 로그인없이 접근 불가
    }
  ]
})
