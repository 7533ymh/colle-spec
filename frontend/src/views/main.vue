<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="3" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="나의 포트폴리오 or 스펙"
                      
                      class="mb-4">

            <template slot="footer">
                <span type=button @click="aceess('/Basic_Information')">자세히보기</span>

            </template>
          </stats-card>


        </b-col>
        <b-col xl="3" md="6">
          <stats-card title=""
                      type="gradient-orange"
                      sub-title="나의 등급제"                  
                      class="mb-4">
            <template slot="footer"> 
              <span type=button @click="aceess('/RatingSystem')">자세히보기</span> 
              
            </template>
          </stats-card>


        </b-col>
        <b-col xl="3" md="6">
          <stats-card title=""
                      type="gradient-green"
                      sub-title="공모전, 대회, 프로그램"
                      
                      class="mb-4">

            <template slot="footer">
              <span type=button @click="aceess('/Competition1')">자세히보기</span>
            
            </template>
          </stats-card>

        </b-col>
        <b-col xl="3" md="6">
          <stats-card title=""
                      type="gradient-info"
                      sub-title="기업에서 요구하는 스펙"
                      
                      class="mb-4">

            <template slot="footer">
              <span type=button @click="aceess('/Company')">자세히보기</span>
               
            </template>
          </stats-card>
        </b-col>
      </b-row>
    </base-header>

    <!--Charts-->
    <b-container fluid class="mt--7">
      <b-row>

        <b-col xl="6" class="mb-5 mb-xl-0">
          <card header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                
                <h5 class="h3 mb-0">등급 요약</h5>
                <!-- 로그인안되있으면 로그인 하라는 메시지와 클릭시 로그인페이지로 -->
                <a href="" v-if="authorized">

                  <!-- 여기다 보여줄 정보 작성하기 -->
            
           </a>  
            <router-link to="/Login" v-else>
            <sidebar-item  :link="{name:'로그인이필요합니다',path:'/Login'}"></sidebar-item>
        </router-link>
              </b-col>
            </b-row>

            
          </card>
        </b-col>

        <b-col xl="6" class="mb-5 mb-xl-0">
          <card header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                
                <h5 class="h3 mb-0">포트폴리오 요약</h5>
                <a href="" v-if="authorized">

                  <!-- 여기다 보여줄 정보 작성하기 -->

           </a>  
            <router-link to="/Login" v-else>
            <sidebar-item  :link="{name:'로그인이필요합니다',path:'/Login'}"></sidebar-item>
        </router-link>
              </b-col>
            </b-row>

            
          </card>
        </b-col>
      </b-row>
      <!-- End charts-->

      
    </b-container>

  </div>
</template>
<script>
  
  // Components
  import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard';

  import store from '@/store'

  export default {
    components: {
      
      BaseProgress,
      StatsCard,
      
    },
    data() {
      return {
        isLogin:store.state.isLogin,
      };
    },
    computed:{
    authorized(){
      return store.getters.islogin
    }
    },
    methods: {
      aceess(url){
      if(this.isLogin === false ){
        alert('로그인이 필요한 서비스입니다.')
      }
      else{
        this.$router.push({path:url})
      }
    }
    },
    mounted() {
    }
  };
</script>
<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
</style>
