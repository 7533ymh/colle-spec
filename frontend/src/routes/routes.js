import DashboardLayout from '@/views/Layout/DashboardLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';
import NotFound from '@/views/NotFoundPage.vue';

const routes = [
  {
    path: '/',
    redirect: 'main',
    component: DashboardLayout,
    children: [
      {
        path: '/dashboard',
        name: 'dashboard',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/Dashboard.vue')
      },
      {
        path: '/main',
        name: 'main',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/main.vue')
      },
      {
        path: '/chart',
        name: 'chart',
        
        component: () => import(/* webpackChunkName: "demo" */ '@/mixins/chart.vue')
      },
      {
        path: '/basic',
        name: 'basic',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/basic.vue')
      },
      {
        path: '/cccc',
        name: 'cccc',
        
        component: () => import(/* webpackChunkName: "demo" */ '@/testtest/examples/cccc.vue')
      },



      /* 포트폴리오 관련 라우터*/
      {
        path: '/portfolio',
        name: 'portfolio',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio.vue')
      },
      {
        path: '/portfolioWrite',
        name: 'portfolioWrite',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/portfolioWrite.vue')
      },
      {
        path: '/Basic_Information',
        name: '기본정보',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Basic_Information.vue')
      },
      {
        path: '/Self_Introduction',
        name: '자기소개서',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Self_Introduction.vue')
      },
      {
        path: '/Certificate',
        name: '자격증',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Certificate.vue')
      },
      {
        path: '/LanguageTest',
        name: '어학시험',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/LanguageTest.vue')
      },
      {
        path: '/PortfolioAndDocument',
        name: '포트폴리오 & 문서',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/PortfolioAndDocument.vue')
      },




      
      /* 공모전,대회,프로그램 관련 라우터*/
      {
        path: '/Competition',
        name: '공모전, 대회, 프로그램',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/Competition.vue')
      },
      {
        path: '/Competition1',
        name: '공모전',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/Competition1.vue')
      },
      {
        path: '/Competition2',
        name: '대회',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/Competition2.vue')
      },
      {
        path: '/CompetitionProgram',
        name: '프로그램',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/CompetitionProgram.vue')
      },
      {
        path: '/Volunteer',
        name: '봉사활동',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/Volunteer.vue')
      },
      {
        path: '/CompetitionEtc',
        name: '기타',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/CompetitionEtc.vue')
      },
      {
        path: '/ProgramList',
        name: '팀구하기 게시판',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/ProgramList.vue')
      },
      {
        path: '/ProgramList/ProgramListDetail',
        name: '팀구하기 상세페이지',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/ProgramListDetail.vue')
      },
      {
        path: '/ProgramList/ProgramTeamWrite',
        name: '팀구하기 작성페이지',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/ProgramTeamWrite.vue')
      },







      {
        path: '/RatingSystem',
        name: 'RatingSystem',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/RatingSystem.vue')
      },
      {
        path: '/Competition',
        name: 'Competition',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/Competition.vue')
      },
      {
        path: '/Company',
        name: 'Company',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/Company.vue')
      },
    
      
      {
        path: '/profile',
        name: 'profile',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/UserProfile.vue')
      },
      
      {
        path: '/tables',
        name: 'tables',
        component: () => import(/* webpackChunkName: "demo" */ '../views/RegularTables.vue')
      }
    ]
  },
  {
    path: '/',
    redirect: 'login',
    component: AuthLayout,
    children: [
      {
        path: '/login',
        name: 'login',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Login.vue')
      },
      {
        path: '/register',
        name: 'register',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Register.vue')
      },

      

      { path: '*', component: NotFound }
    ]
  },

  


  

];

export default routes;
