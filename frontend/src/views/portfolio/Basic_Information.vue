<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="나의 포트폴리오 or 스펙"
                      
                      class="mb-4">

            <template slot="footer">
              
            </template>
          </stats-card>
        </b-col>
        
        
        
      </b-row> 
    </base-header>



    <!--Charts-->
    <b-container fluid class="mt--7">
      <b-row>
        <b-col xl="8" class="mb-5 mb-xl-0">
          <card  header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              
              <b-col>
                <b-nav class="nav-pills justify-content-end">
                  <b-nav-item link-classes="py-2 px-3" router-link to="/basic">
                    <span class="d-none d-md-block">포트폴리오 작성하러가기</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/Self_Introduction">
                    <span class="d-none d-md-block">자기소개서</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/PortfolioAndDocument">
                    <span class="d-none d-md-block">포트폴리오 and 문서</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/Certificate">
                    <span class="d-none d-md-block">자격증</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                  
                  <b-nav-item link-classes="py-2 px-3" router-link to="/LanguageTest">
                    <span class="d-none d-md-block">어학시험</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                </b-nav>
              </b-col>
            </b-row>
            
            <!-- <button @click="gogo">포트폴리오 작성하러가기</button> -->
            <!-- <button @click="pfmodify">포트폴리오 편집</button> -->

<!--
 여기에 기본정보 넣으셈
-->

             

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
  
import axios from 'axios';
import store from '@/store';


let url=store.state.resourceHost; //서버주소 api

    export default {
      data(){return{
        // portfolio:{award:'',career:'',certificate:'',education:'',experience:'',language:'',project:'',introduction:''}
        portfolio:[{}],
      }}
      ,
      mounted(){
        this.portfolioview()
        this.localSet("portfolio",this.portfolio)
        
      },
      components: {
      
        BaseProgress,
        StatsCard,
        
      },

      methods:{
        gogo(){
          this.$router.push({path:'/basic'})
        },
        pfmodify(){
          this.$router.push({path:'/portfolioModify'})
        },
        localSet(name,set){
          localStorage.setItem(name,JSON.stringify(set))
        },
        // portfolioview(){
        //   axios.get(`${url}/award`)
        //             .then(get=>{
        //             this.portfolio.award=get.data.list
        //             console.log('award: ',this.portfolio.award)
        //             this.localSet('award',get.data.list)
        //         })
        //   axios.get(`${url}/career`)
        //             .then(get=>{
        //             this.portfolio.career=get.data.list
        //             console.log('career: ',this.portfolio.career)
        //             this.localSet('career',get.data.list)
        //         })
        //   axios.get(`${url}/certificate`)
        //             .then(get=>{
        //             this.portfolio.certificate=get.data.list
        //             console.log('certificate: ',this.portfolio.certificate)
        //             this.localSet('certificate',get.data.list)
        //         })
        //   axios.get(`${url}/education`)
        //             .then(get=>{
        //             this.portfolio.education=get.data.list
        //             console.log('education: ',this.portfolio.education)
        //             this.localSet('education',get.data.list)
        //         })
        //   axios.get(`${url}/experience`)
        //             .then(get=>{
        //             this.portfolio.experience=get.data.list
        //             console.log('experience: ',this.portfolio.experience)
        //             this.localSet('experience',get.data.list)
        //         })
        //   axios.get(`${url}/language`)
        //             .then(get=>{
        //             this.portfolio.language=get.data.list
        //             console.log('language: ',this.portfolio.language)
        //             this.localSet('language',get.data.list)
        //         })
          
        //   axios.get(`${url}/project`)
        //             .then(get=>{
        //             this.portfolio.project=get.data.list
        //             console.log('project: ',this.portfolio.project)
        //             this.localSet('project',get.data.list)
        //         })
        //   axios.get(`${url}/introduction`)
        //             .then(get=>{
        //             this.portfolio.introduction=get.data.list
        //             console.log('introduction: ',this.portfolio.introduction)
        //             this.localSet('introduction',get.data.list)
        //         })
        // },

      

      
      }
    };
</script>





<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
</style>