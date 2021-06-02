<template>
  <div>
    
    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        

        <b-col xl="3" md="6">
          <span  @click="aceess('/Award')">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="나의 포트폴리오"
                      class="mb-3"
                      id="headercard"
                      >
          </stats-card>
                 </span>       

        </b-col>
        <b-col xl="3" md="6">
          <span  @click="aceess('/RatingSystem')">
          <stats-card title=""
                      type="gradient-orange"
                      sub-title="나의 등급제"                  
                      class="mb-3"
                      id="headercard"
                     >
          </stats-card>
          </span>
        </b-col>
        <b-col xl="3" md="6">
          <span  @click="aceess('/Competition1')">
          <stats-card title=""
                      type="gradient-green"
                      sub-title="공모전, 대회, 프로그램"
                      class="mb-3"
                      id="headercard"
                     >
          </stats-card>
          </span>

        </b-col>
        <b-col xl="3" md="6">
          <span  @click="aceess('/Company/info')">
          <stats-card title=""
                      type="gradient-info"
                      sub-title="기업정보 & 합격자스펙"
                      id="headercard"
                      class="mb-3"
                      >
          </stats-card>
          </span>
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

                <!--로그인된경우-->
                <div id="chartinfo">
                <span v-if="authorized">
                  <span v-if="Rshow===true"><img class="imgbtn" type="button"  @click="reloadbtn" width="24" height="24" src="/reload.png" alt="">
                  <!--<span class="allrank"><h3>전체등급: {{rank.all_rank}}</h3></span>-->
                 
                  <radar-example id="radar"/>
                </span>
                <span v-else>포트폴리오 정보가 없습니다. 먼저 등록해주세요.</span>
               </span>  
           <!--로그인된경우-->

           <!--로그인 안됐을 경우-->
            <router-link to="/login" v-else>
            <sidebar-item  :link="{name:'로그인이필요합니다',path:'/login'}"></sidebar-item>
            </router-link>
          <!--로그인 안됐을 경우-->
                </div>

              </b-col>
            </b-row>
          </card>
        </b-col>

        <b-col xl="6" class="mb-5 mb-xl-0">
          <card header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              <b-col>
                
                
                <h5 class="h3 mb-3">포트폴리오 요약</h5>
                <span v-if="authorized">
                  <!-- 여기다 보여줄 정보 작성하기 -->

                      <table class="table table-bordered"   style="margin: auto;  " >
                      <tbody id="RatingSystemTable" >

                        <tr >
                          <td> <span>{{user.summary.gradeAvg}}</span>점<br>평점</td>
                          <td> <span>{{user.summary.awardCount}}</span>회<br>수상횟수</td> 
                          <td> <span>{{user.summary.careerCount}}</span>회<br>경력횟수</td>
                          <td> <span>{{user.summary.certificateCount}}</span>개<br>자격증</td>
                        </tr>
                        <tr>
                          <td> <span>{{user.summary.educationCount}}</span>회<br>교육이수</td>
                          <td> <span>{{user.summary.experienceCount}}</span>회<br>해외경험</td>
                          <td> <span>{{user.summary.introductionCount}}</span>개<br>자기소개서</td>
                          <td> <span>{{user.summary.languageSummaryList.length}}</span>개<br>어학시험</td>
                        </tr>

                      </tbody>
                    </table>
                  
           </span>  
            <router-link to="/login" v-else>
            <sidebar-item  :link="{name:'로그인이필요합니다',path:'/login'}"></sidebar-item>
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
  import axios from 'axios'
  import store from '@/store'
  import RadarExample from '@/mixins/Radar'
  import $ from 'jquery';
  import { Table, TableColumn, Button} from 'element-ui'
  let url=store.state.resourceHost;
  
  export default {
    components: {
      BaseProgress,
      StatsCard,
      RadarExample,
      [Button.name]: Button,
      
    },
    data() {
      return {
      isLogin:store.state.isLogin,
      p_count:0,
      rank:{},
      page:false,
      user:{
        summary:{}
      },
      Rshow:false
      }
    },
    computed:{
    authorized(){
      return store.getters.islogin
    }
    },
     mounted() {
      this.getrank()
      this.a_rankView()
      this.getsummary()
    },
    methods: {
      getrank(){
        axios.get(`${url}/rank`)
        .then(res=>{
          console.log('success:',res.data.success)
          this.rank=res.data.data
          this.Rshow=true
          
        
        })
        .catch(err=>{
          console.log('success:',err.response.data.success)
          //alert(err.response.data.msg)
          //this.$router.push({path:'/basic'})
          this.Rshow=false
        })
      },
      aceess(url){
      if(this.isLogin === false ){
        alert('로그인이 필요한 서비스입니다.')
        this.$router.push({path:'/login'})
      }
      else{
        this.$router.push({path:url})
      }
    },
    getsummary(){
      axios.get(`${url}/user/summary`)
      .then(res=>{
        this.user.summary=res.data.data
        console.log(res.data.data)
      })
      .catch(err=>{
        alert(err.response.data.msg)
      })

    },
    reloadbtn(){
      // $("#chartinfo").load(location.href + "#chartinfo");
      location.reload()
    },
    ////////
    async a_rankView(){
      await axios.get(`${url}/rank`)
                 .then(res=>{
                   localStorage.setItem("rank",JSON.stringify(res.data.data))
                   if(res.data.data.award_rank==='1등급'){
                     //this.radarChartData.datasets[0].data[0]=5
                     var a1=5
                     
                     
                   }
                   else if(res.data.data.award_rank==='2등급'){
                     var a1=4
                     
                     //this.radarChartData.datasets[0].data[0]=4                      
                     }
                   else if(res.data.data.award_rank==='4등급'){
                     var a1=2
                     
                     //this.radarChartData.datasets[0].data[0]=2
                   }
                   else if(res.data.data.award_rank==='5등급'){
                     var a1=1
                     
                     //this.radarChartData.datasets[0].data[0]=1
                   }else{
                     var a1=3
                     
                    //this.radarChartData.datasets[0].data[0]=3
        
                   }

                   //경력
                   if(res.data.data.career_rank==='1등급'){
                     var a2=5
                     
                     //this.radarChartData.datasets[0].data[1]=5
                   }
                   else if(res.data.data.career_rank==='2등급'){
                     var a2=4
                     
                     //this.radarChartData.datasets[0].data[1]=4
                   }
                   else if(res.data.data.career_rank==='4등급'){
                     var a2=2
                    
                    //this.radarChartData.datasets[0].data[1]=2
                   }
                   else if(res.data.data.career_rank==='5등급'){
                    var a2=1
                     
                     //this.radarChartData.datasets[0].data[1]=1
                   }else{
                     var a2=3
                     
                    // this.radarChartData.datasets[0].data[1]=3
                   }
                   
                   //자격증
                    if(res.data.data.certificate_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[2]=5
                     var a3=5
                     
                   }
                   else if(res.data.data.certificate_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[2]=4
                    var a3=4
                    
                   }
                   else if(res.data.data.certificate_rank==='4등급'){
                   // this.radarChartData.datasets[0].data[2]=2
                   var a3=2
                    
                   }
                   else if(res.data.data.certificate_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[2]=1
                     var a3=1
                     
                   }
                   else{
                    // this.radarChartData.datasets[0].data[2]=3
                     var a3=3
                     
                   }

                   //교육
                   if(res.data.data.education_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a4=5
                     
                   }
                   else if(res.data.data.education_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a4=4
                     
                   }
                   else if(res.data.data.education_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a4=2
                     
                   }
                   else if(res.data.data.education_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a4=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a4=3
                    
                   }
                   //해외경험
                   if(res.data.data.experience_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a5=5
                     
                   }
                   else if(res.data.data.experience_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a5=4
                    
                   }
                   else if(res.data.data.experience_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a5=2
                     
                   }
                   else if(res.data.data.experience_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a5=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a5=3
                     
                   }
                   //학점
                   if(res.data.data.grade_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a6=5
                     
                   }
                   else if(res.data.data.grade_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a6=4
                     
                   }
                   else if(res.data.data.grade_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a6=2
                     
                   }
                   else if(res.data.data.grade_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a6=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a6=3
                     
                   }
                   //프로젝트
                   if(res.data.data.project_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a7=5
                     
                   }
                   else if(res.data.data.project_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a7=4
                     
                   }
                   else if(res.data.data.project_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a7=2
                     
                   }
                   else if(res.data.data.project_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a7=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a7=3
                     
                   }
                   //어학
                   if(res.data.data.language_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     var a8=5
                     
                   }
                   else if(res.data.data.language_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     var a8=4
                     
                   }
                   else if(res.data.data.language_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     var a8=2
                     
                   }
                   else if(res.data.data.language_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     var a8=1
                     
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     var a8=3
                     
                   }
                   var result=[a1,a2,a3,a4,a5,a6,a7,a8]
                   var mRank=[res.data.data.award_rank,
                            res.data.data.career_rank,
                            res.data.data.certificate_rank,
                            res.data.data.education_rank,
                            res.data.data.experience_rank,
                            res.data.data.grade_rank,
                            res.data.data.project_rank,
                            res.data.data.language_rank]

                   localStorage.setItem("mRating",JSON.stringify(result))
                   localStorage.setItem("mRank",JSON.stringify(mRank))
             })
             .catch(err=>{
               console.log(err)
             })
     },
  
    ///////
    },
   

  };
</script>
<style lang="scss" scoped >
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}

.black-bg{
  width: 16%;
  height: 43%;
  background: rgb(221, 221, 221);
  position: fixed;
  padding: 0px;
  left:50%;
  top:100px;
  border:2px solid rgb(170, 156, 155);
    border-radius: 15px;
}
.white-bg{
  width: 100%;
  height: 100%;
  background: white;
  border-radius: 15px;
  padding: 20px;
}
#input{
  margin-bottom: 6px;
}
input:invalid {
        border-color: red;
    }
  #login{
    font-size: 10px;
    margin-top: 10px;
    margin-left: 100px;
  }
  #el{
    right:3%;
    top:65px;
    border-radius: 5px;
    position: fixed;
    font-size:11px;
    background: rgb(47, 51, 47);
    color: aliceblue;
    
    
  }
  #radar{
    margin-left: 10%;
  }
  .allrank{
    text-align: center;

  }
  #headercard{
    text-align: center;
    height: 70%;
    cursor: pointer;
  }
  #UserSummary{
  margin: 3%;
  
  }
 
 #UserSummary span{
    font-weight:bold;
}
.imgbtn{
  margin-left: 90%;

}

#RatingSystemTable table {
    
   border-collapse: collapse;
    
  }
 #RatingSystemTable th, td {
   text-align: center;
   padding : 17px;
   border: 1px solid;
   border-color: #b1b1b4;
   font-size: medium ;
}

#RatingSystemTable span{
  color: #3235f5;
  font-weight : bold;
  font-size:  x-large ;
}
  
</style>
