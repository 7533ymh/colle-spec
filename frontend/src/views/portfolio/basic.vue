<template>
<div>
     <base-header class="pb-6 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6" >
          <stats-card title=""
                      type="gradient-red"
                      sub-title="포트폴리오 작성"
                      id="card"
                      class="mb-4">
          </stats-card>
        </b-col>
        
        
        
      </b-row> 
      
    </base-header>
    <b-container fluid="fluid" class="mt--0">
        <b-row>
            <b-col xl="12" class="mb-7 mb-xl-0">
                <card header-classes="bg-transparent">
                    <b-row align-v="center" slot="header">

                        <b-col>
                            <b-nav class="nav-pills justify-content-end">
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/gradeWrite">
                                    <span class="d-none d-md-block">학점 등록</span>
                                    
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/awardWrite">
                                    <span class="d-none d-md-block">수상 등록</span>
                                    
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/careerWrite">
                                    <span class="d-none d-md-block">경력 등록</span>
                                    
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/certificateWrite">
                                    <span class="d-none d-md-block">자격증 등록</span>
                                    
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/educationWrite">
                                    <span class="d-none d-md-block">교육이수 등록</span>
                                    
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/experienceWrite">
                                    <span class="d-none d-md-block">해외경험 등록</span>
                                    
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/languageWrite">
                                    <span class="d-none d-md-block">어학시험 등록</span>
                                    
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/projectWrite">
                                    <span class="d-none d-md-block">프로젝트 등록</span>
                                    
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/introductionWrite">
                                    <span class="d-none d-md-block">자기소개서 등록</span>
                                    
                                </b-nav-item>
                            </b-nav>
                        </b-col>
                    </b-row>
                </card>
            </b-col>
        </b-row>
    </b-container>
    <template slot="footer">
              
            </template>
</div>
</template>

<script>
import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard';
 export default {
     name:"portfolio-basic",
     methods: {

     },
     components: {
      
        BaseProgress,
        StatsCard,
        
      },
    }
</script>
<style>
#card{
    margin-left: 50%;
    width: 80%;
    text-align: center;
    height: 60%;
}
</style>