<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="공모전, 대회, 프로그램"
                      
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
        <b-col xl="12" class="mb-5 mb-xl-0">
          <card  header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              
              <b-col>
                <b-nav class="nav-pills justify-content-end">
                  <b-nav-item link-classes="py-2 px-3" router-link to="/Competition1">    
                    <span class="d-none d-md-block"> 공모전&대회</span>
                    <span class="d-md-none">M</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/CompetitionProgram">
                    <span class="d-none d-md-block">프로그램</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/Volunteer">
                    <span class="d-none d-md-block">봉사활동</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                  
                  <!-- <b-nav-item link-classes="py-2 px-3" router-link to="/CompetitionEtc">
                    <span class="d-none d-md-block">기타</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item> -->
                </b-nav>
              </b-col>
            </b-row>
            

<CompetitionProgram-table/>


             

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
  import CompetitionProgramTable from "./CompetitonTables/CompetitionProgramTable";


    export default {

    


      components: {
      
        BaseProgress,
        StatsCard,
        CompetitionProgramTable
        
      },

      methods:{
     
      

      
      }
    };
</script>





<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
</style>