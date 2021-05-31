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
        path: '/linkLogin',
        name: 'linkLogin',
        //연동 로그인
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/linkLogin.vue')
      },

      {
        path: '/main',
        name: 'main',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/main.vue')
      },
      {
        path: '/basic',
        name: 'basic',
        //포트폴리오 작성 모음 페이지
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/basic.vue')
      },


      /* 포트폴리오 관련 라우터*/
      {
        path: '/portfolioModify',
        name: 'portfolioModify',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/portfolioModify.vue')
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
        path: '/Award',
        name: '수상',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Award.vue')
      },
      {
        path: '/Career',
        name: '경력',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Career.vue')
      },
      {
        path: '/Education',
        name: '교육이수',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Education.vue')
      },
      {
        path: '/Experience',
        name: '해외경험',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/Experience.vue')
      },

      {
        path: '/PortfolioAndDocument',
        name: '포트폴리오 & 문서',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/PortfolioAndDocument.vue')
      },
      //포트폴리오 작성 라우터
      {
        path: '/Portfolio/awardWrite',
        name: '수상 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/AwardWrite.vue')
      },
      {
        path: '/Portfolio/careerWrite',
        name: '경력 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/CareerWrite.vue')
      },
      {
        path: '/Portfolio/certificateWrite',
        name: '자격증 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/CertificateWrite.vue')
      },
      {
        path: '/Portfolio/educationWrite',
        name: '교육이수 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/EducationWrite.vue')
      },
      {
        path: '/Portfolio/experienceWrite',
        name: '해외경험 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/ExperienceWrite.vue')
      },
      {
        path: '/Portfolio/languageWrite',
        name: '어학 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/LanguageWrite.vue')
      },
      {
        path: '/Portfolio/projectWrite',
        name: '프로젝트 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/ProjectWrite.vue')
      },
      {
        path: '/Portfolio/introductionWrite',
        name: '자기소개서 등록',
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio/write/IntroductionWrite.vue')
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
        path: '/languageinfo',
        name: 'languageinfo',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompetitionAndPrograms/languageinfo.vue')
      },
      {
        path: '/Company',
        name: 'Company',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/Company.vue')
      },
      {
        path: '/Company/info',
        name: 'CompanyInfo',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompanyInfo.vue')
      },
      {
        path: '/Company/info/detail',
        name: 'CompanyInfoDetail',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/CompanyInfoDetail.vue')
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
