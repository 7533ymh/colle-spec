import DashboardLayout from '@/views/Layout/DashboardLayout.vue';
import AuthLayout from '@/views/Pages/AuthLayout.vue';

import NotFound from '@/views/NotFoundPage.vue';

const routes = [
  {
    path: '/',
    redirect: 'dashboard',
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
        path: '/portfolio',
        name: 'portfolio',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/portfolio.vue')
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
        path: '/Login1',
        name: 'Login1',
        
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/Login.vue')
      },
      {
        path: '/icons',
        name: 'icons',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Icons.vue')
      },
      {
        path: '/profile',
        name: 'profile',
        component: () => import(/* webpackChunkName: "demo" */ '../views/Pages/UserProfile.vue')
      },
      {
        path: '/maps',
        name: 'maps',
        component: () => import(/* webpackChunkName: "demo" */ '../views/GoogleMaps.vue')
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
  }
];

export default routes;
