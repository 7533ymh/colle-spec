<template>
    <div>
        <base-header
            class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="6" md="6">
                    <stats-card title="" type="gradient-red" sub-title="나의 등급제" class="mb-4">
                        <template slot="footer"></template>
                    </stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
        <b-container fluid="fluid" class="mt--7">
            <b-row>
                <b-col xl="8" class="mb-5 mb-xl-0">
                    <card header-classes="bg-transparent">


                        <b-row align-v="center" slot="header"> <!-- 등급제 버튼 -->
                
                          <b-col>
                            <b-nav class="nav-pills justify-content-end">
                              <b-nav-item v-if="!authorized" type="button" size="mini" @click="a_rankView">    
                              <span class="d-none d-md-block"> 전체등급</span>
                                  
                                  
                              </b-nav-item>

                              <b-nav-item v-if="!authorized" type="button" size="mini" @click="g_rankView">
                                <span class="d-none d-md-block">학년등급</span>
                                
                              </b-nav-item>
                              <b-nav-item v-if="!authorized" type="button" size="mini" @click="c_rankView">
                                <span class="d-none d-md-block">대학교별등급</span>
                                
                                

                              </b-nav-item>
                              <b-nav-item v-if="!authorized" type="button" size="mini" @click="c_g_rankView">
                                <span class="d-none d-md-block">대학교학년별등급</span>
                                
                              </b-nav-item>
                              
                            </b-nav>
                          </b-col>
                        </b-row> <!-- 등급제 버튼 끝-->

                        
                        <div>
                            <!--<el-button  id="rank_btn" >전체등급 {{viewRank.all_rank}}</el-button>-->
                        </div>
                        
                        <div  style="padding-left: 10%;">
                            <!--<p>전체 등급:{{viewRank.all_rank}}</p>
                              <el-button  id="rank_btn" >수상 {{viewRank.award_rank}} </el-button>
                              <el-button  id="rank_btn" >경력 {{viewRank.career_rank}}</el-button>
                              <el-button  id="rank_btn" >자격증 {{viewRank.certificate_rank}}</el-button>
                              <el-button  id="rank_btn" >교육이수 {{viewRank.education_rank}}</el-button>
                              <el-button  id="rank_btn" >해외경험 {{viewRank.experience_rank}}</el-button>
                              <el-button  id="rank_btn" >학점 {{viewRank.grade_rank}}</el-button>
                              <el-button  id="rank_btn" >프로젝트 {{viewRank.project_rank}}</el-button>
                              <el-button  id="rank_btn" >어학 {{viewRank.language_rank}}</el-button> -->

                        



                            <div class="row"> <!-- 등급제 내용-->
                         

                                <b-list-group class="col-md-3" style=" text-align: justify;">  <!-- 등급제 등급 리스트-->

                                    <b-list-group-item style="font-weight: bold;">전체등급 : 등급</b-list-group-item> 
                                    <b-list-group-item>수상 : 등급</b-list-group-item>
                                    <b-list-group-item>경력 : 등급</b-list-group-item>
                                    <b-list-group-item>자격증 : 등급</b-list-group-item>
                                    <b-list-group-item>교육이수 : 등급</b-list-group-item>
                                    <b-list-group-item>해외경험 : 등급</b-list-group-item>
                                    <b-list-group-item>학점 : 등급</b-list-group-item>
                                    <b-list-group-item>프로젝트 : 등급</b-list-group-item>
                                    <b-list-group-item>어학 : 등급</b-list-group-item>

                                </b-list-group>  <!-- 등급제 등급 리스트 끝-->



                                <div class="col-md-5" style="padding-left: 0;"> <!-- 차트 UI -->
                                    <mdb-container>
                                        <mdb-radar-chart :data="radarChartData" :options="radarChartOptions" :width="600" :height="400">

                                        </mdb-radar-chart>
                                    </mdb-container>
                                </div> <!-- 차트 UI 끝 -->


                            </div> <!-- 등급제 내용 끝-->


                            <!-- <p>전체 등급:{{viewRank.all_rank}}</p>
                              <p>수상 등급:{{viewRank.award_rank}}</p>
                              <p>경력 등급:{{viewRank.career_rank}}</p>
                              <p>자격증 등급:{{viewRank.certificate_rank}}</p>
                              <p>교육이수 등급:{{viewRank.education_rank}}</p>
                              <p>해외경험 등급:{{viewRank.experience_rank}}</p>
                              <p>학점 등급:{{viewRank.grade_rank}}</p>
                              <p>프로젝트 등급:{{viewRank.project_rank}}</p>
                              <p>어학 등급:{{viewRank.language_rank}}</p>
                            </div>
                              <p>어학 등급:{{viewRank.language_rank}}</p>-->
                        </div>
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
  import { Table, TableColumn, Button} from 'element-ui'
  
  let url=store.state.resourceHost; //서버주소 api
    export default {
      data(){return{
        viewRank:[{}],
        rank:{},
        allRank:{},
        gradeRank:{},
        collegeRank:{},
        collegeGradeRank:{},
        fields:[{key:"all_rank",label:'전체등급'},{key:'award_rank',label:'수상'},{key:'career_rank',label:'경력'},{key:'certificate_rank',label:'자격증'},{key:'education_rank',label:'교육이수'},{key:'experience_rank',label:'해외경험'},{key:'grade_rank',label:'학점'},{key:'project_rank',label:'프로젝트'},{key:'language_rank',label:'어학'}],
      

    
      }},
      components: {
      
        BaseProgress,
        StatsCard,
        [Button.name]: Button,
                
      },
      mounted(){
        this.a_rankView();
      },
      methods:{
        //프로젝트 조회
        a_rankView(row){
           axios.get(`${url}/rank`)
                    .then(res=>{
                      this.allRank=res.data.data 
                      this.viewRank=res.data.data   
                      console.log('rank',this.rank)
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
        },
        g_rankView(row){
          axios.get(`${url}/rank/grade`)
                    .then(res=>{
                      this.gradeRank=res.data.data
                      this.viewRank=res.data.data 
                      console.log('rank',this.rank)
 
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
        },
        c_rankView(row){
          axios.get(`${url}/rank/college`)
                    .then(res=>{
                      this.collegeRank=res.data.data
                      this.viewRank=res.data.data
                      console.log('rank',this.rank)
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
        },
        c_g_rankView(row){
          axios.get(`${url}/rank/college/grade`)
                    .then(res=>{
                      this.collegeGradeRank=res.data.data
                      this.viewRank=res.data.data
                      console.log('rank',this.rank)  
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
        },
        // ra(){
        // this.rank.all_rank=this.viewRank.all_rank,
        // this.rank.award_rank=this.viewRank.award_rank,
        // this.rank.career_rank=this.viewRank.career_rank,
        // this.rank.certificate_rank=this.viewRank.certificate_rank,
        // this.rank.education_rank=this.viewRank.education_rank,
        // this.rank.experience_rank=this.viewRank.experience_rank,
        // this.rank.grade_rank=this.viewRank.grade_rank,
        // this.rank.project_rank=this.viewRank.project_rank,
        // this.rank.language_rank=this.viewRank.language_rank
        // }
        //클릭시 상세페이지로 데이터넘기면서 이동
    },
    computed:{
     //로그인 로그아웃 : 로그인 되있으면 로그아웃으로 변함 
    authorized(){
      return store.getters.userlink===1
    }
    }
    
  }
</script>
<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
#rank_btn{
  width: 130px;
  height: 70px;
  margin-top: 2%;
  margin-left: 10px;
}
</style>

<style>
#RatingSystemImg {
  width: 25px;
  height: 30px;
  
}

</style>





<script>  //차트 스크립트
  import { mdbRadarChart, mdbContainer } from "mdbvue";
  export default {
    name: "ChartPage",
    components: {
      mdbRadarChart,
      mdbContainer
    },
    data() {
      return {
        radarChartData: {
          labels: [
            "수상",
            "경력",
            "자격증",
            "교육이수",
            "해외경험",
            "학점",
            "프로젝트",
            "어학"
          ],
          datasets: [
            {
              label: "등급 차트",
              backgroundColor: "rgba(255, 99, 132, 0.1)",
              borderColor: "rgba(255, 99, 132, 1)",
              borderWidth: 3,
              
              data: [6-1, 6-2, 6-3, 6-4, 6-5, 6-1, 6-1, 6-1] //여기에 등급 데이터  
            }
            
          ]
        },
        radarChartOptions: {
          responsive: false,
          maintainAspectRatio: false
        }
      };
    }
  };
</script>