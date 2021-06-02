<template>
    <div>
        <base-header
            class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="6" md="6">
                    <stats-card title=""
                      type="gradient-red"
                      sub-title="나의 등급제"
                      id="card"
                      class="mb-4">
                        <template slot="footer"></template>
                    </stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
        <b-container fluid="fluid" class="mt--7">
            <b-row>
                <b-col xl="12" class="mb-5 mb-xl-0">
                    <card header-classes="bg-transparent">


                        <b-row align-v="center" slot="header"> <!-- 등급제 버튼 -->
                
                          <b-col>
                            <b-nav class="nav-pills justify-content-end">
                              <b-nav-item  type="button" size="mini" @click="a_rankView">    
                              <span class="d-none d-md-block"> 전체등급</span>
                                  
                                  
                              </b-nav-item>

                              <b-nav-item  type="button" size="mini" @click="g_rankView">
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
                        </div>
                        
                        <div  style="padding-left: 10%;">
                            



                            <div class="row"> <!-- 등급제 내용-->
                         

                                
                  


                             <div id="progress" class="col-md-10" style="padding-left: 0; " > <!--테이블 시작-->
                              <table class="table table-bordered"  >
                                <colgroup>
                                  <col width="10%" />
                                  <col width="90%" />
                                </colgroup>
                                <tbody id="RatingSystemTable1" >

                                  <tr >
                                    <td> <span  style="font-weight: bold; font-size:medium ;">전체등급 :{{viewRank.all_rank}}</span></td>
                                    <td> 
                                      <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.a0" :value="data.all_rank">
                                            <span>전체등급:
                                                <strong>{{ msg00 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress>
                                    </td> 
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">수상 : {{viewRank.award_rank}}</span></td>
                                    <td> 
                                      <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.a" :value="data.award_rank">
                                            <span>수상:
                                                <strong>{{ msg0 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">경력 : {{viewRank.career_rank}}</span></td>
                                    <td> 
                                       <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.b" :value="data.career_rank">
                                            <span>경력:
                                                <strong>{{ msg1 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">자격증 : {{viewRank.certificate_rank}}</span></td>
                                    <td>
                                       <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.c" :value="data.certificate_rank">
                                            <span>자격증:
                                                <strong>{{ msg2 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress> </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">교육이수 : {{viewRank.education_rank}}</span></td>
                                    <td>
                                      <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.d" :value="data.education_rank">
                                            <span>교육이수:
                                                <strong>{{ msg3 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress> </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">해외경험 : {{viewRank.experience_rank}}</span></td>
                                    <td>
                                      <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.e" :value="data.experience_rank">
                                            <span>해외경험:
                                                <strong>{{msg4}}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress> </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">학점 : {{viewRank.grade_rank}}</span></td>
                                    <td> 
                                      <b-progress id="prog" :max="max" height="2rem" >
                                       <b-progress-bar :variant="color.f" :value="data.grade_rank">
                                            <span>학점:
                                                <strong>{{ msg5 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">프로젝트 : {{viewRank.project_rank}}</span></td>
                                    <td>
                                      <b-progress id="prog" :max="max" height="2rem" >  
                                        <b-progress-bar :variant="color.g" :value="data.project_rank">
                                            <span>프로젝트:
                                                <strong>{{ msg6 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress> </td>
                                  </tr>

                                  <tr>
                                    <td> <span id="RatingSystemTable1">어학 : {{viewRank.language_rank}}</span></td>
                                    <td>
                                       <b-progress id="prog" :max="max" height="2rem">
                                       <b-progress-bar :variant="color.h" :value="data.language_rank">
                                            <span>어학:
                                                <strong>{{ msg7 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                      </b-progress> </td>
                                  </tr>

                                </tbody>
                              </table>
                             </div>  <!--테이블 끝-->

                             <div id="contents4" mt-4>
                                    <h3>한줄 평가</h3>
                                    <h4>{{message.award}}<router-link to="/Competition1">&nbsp;&nbsp;&nbsp;{{adv.award}}</router-link></h4>
                                    <h4>{{message.career}}<a :href="advurl.career" target="_blank">&nbsp;&nbsp;&nbsp;{{adv.career}}</a></h4>
                                    <h4>{{message.certificate}} <a :href="advurl.certificate" target="_blank">&nbsp;&nbsp;&nbsp;{{adv.certificate}}</a></h4>
                                    <h4>{{message.education}} <router-link to="/CompetitionProgram">&nbsp;&nbsp;&nbsp;{{adv.education}}</router-link></h4>
                                    <h4>{{message.experience}} <a :href="advurl.experience" target="_blank">&nbsp;&nbsp;&nbsp;{{adv.experience}}</a></h4>
                                    <h4>{{message.grade}}</h4>
                                    <h4>{{message.project}}</h4>
                                    <h4>{{message.language}}<router-link to="/languageinfo">&nbsp;&nbsp;&nbsp;{{adv.language}}</router-link></h4>

                                </div>

                            </div> <!-- 등급제 내용 끝-->
                        </div>
                    </card>
                </b-col>
            </b-row>
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
  import { mdbRadarChart, mdbContainer } from "mdbvue";
// import RadarExample from '@/mixins/RadarExample'
  let url=store.state.resourceHost; //서버주소 api

  
    export default {
      name: "ChartPage",
      //props: ['chartdata', 'radarChartOptions'],
      mounted(){
        this.a_rankView();
        //this.renderChart(this.chartdata, this.radarChartOptions)
        
      },
      data(){
        return{
          max:5,
          dataPoints: null,
          data: { //원본데이터 값 reverse
                    all_rank:'',
                    award_rank: '',
                    career_rank: '',
                    certificate_rank: '',
                    education_rank: '',
                    experience_rank: '',
                    language_rank: '',
                    grade_rank: '',
                    project_rank: ''

                },
        color:{a0:'',a:'',b:'',c:'',d:'',e:'',f:'',g:'',h:''},
        msg00:'',
        msg0: '', //수상메시지
        msg1: '', //경력메시지
        msg2: '', //자격증메시지
        msg3: '', //교육이수메시지
        msg4: '', //해외경험메시지
        msg5: '', //학점메시지
        msg6: '', //프로젝트
        msg7: '', //어학메시지

        message:{
                    award:'',
                    career:'',
                    certificate:'',
                    education:'',
                    experience:'',
                    language:'',
                    grade:'',
                    project:''
                },
        adv:{
                    award:'',
                    career:'',
                    certificate:'',
                    education:'',
                    experience:'',
                    language:'',
                    grade:'',
                    project:''
                },
                advurl:{
                    career:'https://www.jobkorea.co.kr/starter/?schLocal=&schPart=&schMajor=&schEduLevel=&schWork=2&schCType=&isSaved=1&LinkGubun=0&LinkNo=0&Page=1&schType=0&schGid=0&schOrderBy=0&schTxt='
                    ,
                    experience:'https://www.ef.co.kr/pg/courses/?source=007959,NVKRS_LAL_00_00_DESKTOP&NaPm=ct%3Dkpaovxso%7Cci%3D0zm0003R5pbuJsaF30Wh%7Ctr%3Dsa%7Chk%3D1d576b61c20a492046e0d65dfa47cf7a735d6613'
                    ,
                    language:'https://www.saha.go.kr/startup/contents.do?mId=0301000000'
                    ,
                    certificate:'http://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId='
                },
        viewRank:[{}],
        fields:[{key:"all_rank",label:'전체등급'},{key:'award_rank',label:'수상'},{key:'career_rank',label:'경력'},{key:'certificate_rank',label:'자격증'},{key:'education_rank',label:'교육이수'},{key:'experience_rank',label:'해외경험'},{key:'grade_rank',label:'학점'},{key:'project_rank',label:'프로젝트'},{key:'language_rank',label:'어학'}],
        
      // cha:JSON.parse(localStorage.getItem("rank")),
      
        
      }},
        
      components: {
      
        BaseProgress,
        StatsCard,
        [Button.name]: Button,
        mdbRadarChart,
      mdbContainer
   //   RadarExample
      
                
      },
     
      methods:{
        advice(){
                if(this.message.award==='수상 등급이 합격자 평균 보다 낮습니다.')
                {
                    this.adv.award="추천 정보보기"
                }else if(this.message.award==='수상 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.award="추천 정보보기"
                }
                else{
                    this.adv.award=null
                }

                if(this.message.career==='경력 등급이 합격자 평균 보다 낮습니다.'){
                    this.adv.career="추천 정보보기"
                }
                else if(this.message.career==='경력 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.career="추천 정보보기"
                }
                else{
                    this.adv.career=null
                }

                if(this.message.certificate==='자격증 등급이 합격자 평균 보다 낮습니다.')
                {
                    this.adv.certificate="추천 정보보기"

                }else if(this.message.certificate==='자격증 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.certificate="추천 정보보기"

                }else{
                    this.adv.certificate=null
                }

                if(this.message.education==='교육이수 등급이 합격자 평균 보다 낮습니다.'){
                    this.adv.education="추천 정보보기"
                }
                else if(this.message.education==='교육이수 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.education="추천 정보보기"
                }else{
                    this.adv.education=null
                }

                if(this.message.experience==='해외경험 등급이 합격자 평균 보다 낮습니다.')
                {
                    this.adv.experience="추천 정보보기"
                }else if(this.message.experience==='해외경험 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.experience="추천 정보보기"
                }
                else{
                    this.adv.experience=null
                }

                if(this.message.language==='어학 등급이 합격자 평균 보다 낮습니다.'){
                    this.adv.language="추천 정보보기"
                }
                else if(this.message.language==='어학 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.language="추천 정보보기"
                }
                else{
                    this.adv.language=null
                }

                if(this.message.grade==='학점 등급이 합격자 평균 보다 낮습니다.'){
                    this.adv.grade="추천 정보보기"
                }
                else if(this.message.grade==='학점 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.grade="추천 정보보기"
                }
                else{
                    this.adv.grade=null
                }

                if(this.message.project==='프로젝트 등급이 합격자 평균 보다 낮습니다.'){
                    this.adv.project="추천 정보보기"
                }
                else if(this.message.project==='프로젝트 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.project="추천 정보보기"
                }
                else{
                    this.adv.project=null
                }
            },
        
       async a_rankView(){
         await axios.get(`${url}/rank`)
                    .then(res=>{
                      console.log('cha: ',JSON.parse(localStorage.getItem("rating")))
                      localStorage.setItem("rank",JSON.stringify(res.data.data))
                      this.viewRank=res.data.data
                      console.log(this.viewRank)

                      if(res.data.data.all_rank==='1등급'){
                        var a0=5
                        this.color.a0='green'
                        this.msg00='매우잘함'
                        this.data.all_rank=5
                        //this.message.award='수상 등급이 합격자보다 매우 우수합니다.'

                      }
                      else if(res.data.data.all_rank==='2등급'){
                        var a0=4
                        this.color.a0='info'
                        this.msg00='잘함'
                        this.data.all_rank=4
                        //this.message.award='수상 등급이 합격자 평균 보다 높습니다.'
                        }
                      else if(res.data.data.all_rank==='4등급'){
                        var a0=2
                        this.color.a0='warning'
                        this.msg00='부족'
                        this.data.all_rank=2
                        //this.message.award='수상 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.all_rank==='5등급'){
                        var a0=1
                        this.color.a0='danger'
                        this.msg00='매우부족'
                        this.data.all_rank=1
                        //this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                        var a0=3
                        this.color.a0='primary'
                        this.msg00='평균'
                        this.data.all_rank=3
                        //this.message.award='수상 등급이 합격자와 비슷합니다.'
                      }

                      if(res.data.data.award_rank==='1등급'){
                        //this.radarChartData.datasets[0].data[0]=5
                        var a1=5
                        this.color.a='green'
                        this.msg0='매우잘함'
                        this.data.award_rank=5
                        this.message.award='수상 등급이 합격자보다 매우 우수합니다'
                      }
                      else if(res.data.data.award_rank==='2등급'){
                        var a1=4
                        this.color.a='info'
                        this.msg0='잘함'
                        this.data.award_rank=4
                        this.message.award='수상 등급이 합격자 평균 보다 높습니다'
                        }
                      else if(res.data.data.award_rank==='4등급'){
                        var a1=2
                        this.color.a='warning'
                        this.msg0='부족'
                        this.data.award_rank=2
                        this.message.award='수상 등급이 합격자 평균 보다 낮습니다.'
                        
                      }
                      else if(res.data.data.award_rank==='5등급'){
                        var a1=1
                        this.color.a='danger'
                        this.msg0='매우부족'
                        this.data.award_rank=1
                        this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                        var a1=3
                        this.color.a='primary'
                        this.msg0='평균'
                        this.data.award_rank=3
                        this.message.award='수상 등급이 합격자와 비슷합니다.'
           
                      }

                      //경력
                      if(res.data.data.career_rank==='1등급'){
                        var a2=5
                        this.color.b='green'
                        this.msg1='매우잘함'
                        this.data.career_rank=5
                                                    this.message.career='경력 등급이 합격자보다 매우 우수합니다'

                      }
                      else if(res.data.data.career_rank==='2등급'){
                        var a2=4
                        this.color.b='info'
                        this.msg1='잘함'
                        this.data.career_rank=4
                            this.message.career='경력 등급이 합격자 평균 보다 높습니다'
                      }
                      else if(res.data.data.career_rank==='4등급'){
                        var a2=2
                        this.color.b='warning'
                        this.msg1='부족'
                        this.data.career_rank=2
                                                   this.message.career='경력 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.career_rank==='5등급'){
                        var a2=1          
                        this.color.b='danger'
                        this.msg1='매우부족'
                        this.data.career_rank=1
                            this.message.career='경력 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                        var a2=3
                        this.color.b='primary'
                        this.msg1='평균'
                        this.data.career_rank=3
                        this.message.career='경력 등급이 합격자와 비슷합니다.'
                      }
                      
                      //자격증
                       if(res.data.data.certificate_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[2]=5
                        var a3=5
                        this.color.c='green'
                        this.msg2='매우잘함'
                        this.data.certificate_rank=5
                            this.message.certificate='자격증 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.certificate_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[2]=4
                       var a3=4
                        this.color.c='info'
                        this.msg2='잘함'
                        this.data.certificate_rank=4
                            this.message.certificate='자격증 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.certificate_rank==='4등급'){
                      // this.radarChartData.datasets[0].data[2]=2
                      var a3=2
                       this.color.c='warning'
                        this.msg2='부족'
                        this.data.certificate_rank=2
                            this.message.certificate='자격증 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.certificate_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[2]=1
                        var a3=1
                        this.color.c='danger'
                        this.msg2='매우부족'
                        this.data.certificate_rank=1
                            this.message.certificate='자격증 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                       // this.radarChartData.datasets[0].data[2]=3
                        var a3=3
                        this.color.c='primary'
                        this.msg2='평균'
                        this.data.certificate_rank=3
                            this.message.certificate='자격증 등급이 합격자와 비슷합니다.'
                      }

                      //교육
                      if(res.data.data.education_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a4=5
                        this.color.d='green'
                        this.msg3='매우잘함'
                        this.data.education_rank=5
                                                    this.message.education='교육이수 등급이 합격자보다 매우 우수합니다.'

                      }
                      else if(res.data.data.education_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a4=4
                        this.color.d='info'
                        this.msg3='잘함'
                        this.data.education_rank=4
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.education_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a4=2
                        this.color.d='warning'
                        this.msg3='부족'
                        this.data.education_rank=2
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.education_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a4=1
                        this.color.d='danger'
                        this.msg3='매우부족'
                        this.data.education_rank=1
                                                    this.message.education='교육이수 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a4=3
                       this.color.d='primary'
                        this.msg3='평균'
                        this.data.education_rank=3
                                                    this.message.education='교육이수 등급이 합격자와 비슷합니다.'

                      }
                      //해외경험
                      if(res.data.data.experience_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a5=5
                        this.color.e='green'
                        this.msg4='매우잘함'
                        this.data.experience_rank=5
                            this.message.experience='해외경험 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.experience_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a5=4
                        this.color.e='info'
                        this.msg4='잘함'
                        this.data.experience_rank=4
                            this.message.experience='해외경험 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.experience_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a5=2
                        this.color.e='warning'
                        this.msg4='부족'
                        this.data.experience_rank=2
                            this.message.experience='해외경험 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.experience_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a5=1
                        this.color.e='danger'
                        this.msg4='매우부족'
                        this.data.experience_rank=1
                            this.message.experience='해외경험 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a5=3
                        this.color.e='primary'
                        this.msg4='평균'
                        this.data.experience_rank=3
                                                    this.message.experience='해외경험 등급이 합격자와 비슷합니다.'

                      }
                      //학점
                      if(res.data.data.grade_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a6=5
                        this.color.f='green'
                        this.msg5='매우잘함'
                        this.data.grade_rank=5
                            this.message.grade='학점 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.grade_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a6=4
                        this.color.f='info'
                        this.msg5='잘함'
                        this.data.grade_rank=4
                                                    this.message.grade='학점 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.grade_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a6=2
                        this.color.f='warning'
                        this.msg5='부족'
                        this.data.grade_rank=2
                                                    this.message.grade='학점 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.grade_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a6=1
                        this.color.f='danger'
                        this.msg5='매우부족'
                        this.data.grade_rank=1
                                                    this.message.grade='학점 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a6=3
                        this.color.f='primary'
                        this.msg5='평균'
                        this.data.grade_rank=3
                                                    this.message.grade='학점 등급이 합격자와 비슷합니다.'

                      }
                      //프로젝트
                      if(res.data.data.project_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a7=5
                        this.color.g='green'
                        this.msg6='매우잘함'
                        this.data.project_rank=5
                            this.message.project='프로젝트 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.project_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a7=4
                        this.color.g='info'
                        this.msg6='잘함'
                        this.data.project_rank=4
                            this.message.project='프로젝트 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.project_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a7=2
                        this.color.g='warning'
                        this.msg6='부족'
                        this.data.project_rank=2
                        this.message.project='프로젝트 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.project_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a7=1
                        this.color.g='danger'
                        this.msg6='매우부족'
                        this.data.project_rank=1
                            this.message.project='프로젝트 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a7=3
                        this.color.g='primary'
                        this.msg6='평균'
                        this.data.project_rank=3
                            this.message.project='프로젝트 등급이 합격자와 비슷합니다.'
                      }
                      //어학
                      if(res.data.data.language_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a8=5
                        this.color.h='green'
                        this.msg7='매우잘함'
                        this.data.language_rank=5
                        this.message.language='어학 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.language_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a8=4
                        this.color.h='info'
                        this.msg7='잘함'
                        this.data.language_rank=4
                        this.message.language='어학 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.language_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a8=2
                        this.color.h='warning'
                        this.msg7='부족'
                        this.data.language_rank=2
                            this.message.language='어학 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.language_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a8=1
                        this.color.h='danger'
                        this.msg7='매우부족'
                        this.data.language_rank=1
                            this.message.language='어학 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a8=3
                        this.color.h='primary'
                        this.msg7='평균'
                        this.data.language_rank=3
                        this.message.language='어학 등급이 합격자와 비슷합니다.'
                      }
                      var result=[a0,a1,a2,a3,a4,a5,a6,a7,a8]
                      localStorage.setItem("rating",JSON.stringify(result))
                      
                      console.log('message:',this.message)
                      console.log('adv',this.adv)
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
                this.advice()
        },
      async  g_rankView(){
        await  axios.get(`${url}/rank/grade`)
                    .then(res=>{
                      this.viewRank=res.data.data
                      localStorage.setItem("rank",JSON.stringify(res.data.data))
                      console.log(this.viewRank)
                      if(res.data.data.all_rank==='1등급'){
                        var a0=5
                        this.color.a0='green'
                        this.msg00='매우잘함'
                        this.data.all_rank=5
                      }
                      else if(res.data.data.all_rank==='2등급'){
                        var a0=4
                        this.color.a0='info'
                        this.msg00='잘함'
                        this.data.all_rank=4
                        }
                      else if(res.data.data.all_rank==='4등급'){
                        var a0=2
                        this.color.a0='warning'
                        this.msg00='부족'
                        this.data.all_rank=2
                      }
                      else if(res.data.data.all_rank==='5등급'){
                        var a0=1
                        this.color.a0='danger'
                        this.msg00='매우부족'
                        this.data.all_rank=1
                      }else{
                        var a0=3
                        this.color.a0='primary'
                        this.msg00='평균'
                        this.data.all_rank=3
           
                      }
                       if(res.data.data.award_rank==='1등급'){
                        //this.radarChartData.datasets[0].data[0]=5
                        var a1=5
                        this.color.a='green'
                        this.msg0='매우잘함'
                        this.data.award_rank=5
                        this.message.award='수상 등급이 합격자보다 매우 우수합니다'
                      }
                      else if(res.data.data.award_rank==='2등급'){
                        var a1=4
                        this.color.a='info'
                        this.msg0='잘함'
                        this.data.award_rank=4
                        this.message.award='수상 등급이 합격자 평균 보다 높습니다'
                        }
                      else if(res.data.data.award_rank==='4등급'){
                        var a1=2
                        this.color.a='warning'
                        this.msg0='부족'
                        this.data.award_rank=2
                        this.message.award='수상 등급이 합격자 평균 보다 낮습니다.'
                        
                      }
                      else if(res.data.data.award_rank==='5등급'){
                        var a1=1
                        this.color.a='danger'
                        this.msg0='매우부족'
                        this.data.award_rank=1
                        this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                        var a1=3
                        this.color.a='primary'
                        this.msg0='평균'
                        this.data.award_rank=3
                        this.message.award='수상 등급이 합격자와 비슷합니다.'
           
                      }

                      //경력
                      if(res.data.data.career_rank==='1등급'){
                        var a2=5
                        this.color.b='green'
                        this.msg1='매우잘함'
                        this.data.career_rank=5
                                                    this.message.career='경력 등급이 합격자보다 매우 우수합니다'

                      }
                      else if(res.data.data.career_rank==='2등급'){
                        var a2=4
                        this.color.b='info'
                        this.msg1='잘함'
                        this.data.career_rank=4
                            this.message.career='경력 등급이 합격자 평균 보다 높습니다'
                      }
                      else if(res.data.data.career_rank==='4등급'){
                        var a2=2
                        this.color.b='warning'
                        this.msg1='부족'
                        this.data.career_rank=2
                                                   this.message.career='경력 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.career_rank==='5등급'){
                        var a2=1          
                        this.color.b='danger'
                        this.msg1='매우부족'
                        this.data.career_rank=1
                            this.message.career='경력 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                        var a2=3
                        this.color.b='primary'
                        this.msg1='평균'
                        this.data.career_rank=3
                        this.message.career='경력 등급이 합격자와 비슷합니다.'
                      }
                      
                      //자격증
                       if(res.data.data.certificate_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[2]=5
                        var a3=5
                        this.color.c='green'
                        this.msg2='매우잘함'
                        this.data.certificate_rank=5
                            this.message.certificate='자격증 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.certificate_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[2]=4
                       var a3=4
                        this.color.c='info'
                        this.msg2='잘함'
                        this.data.certificate_rank=4
                            this.message.certificate='자격증 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.certificate_rank==='4등급'){
                      // this.radarChartData.datasets[0].data[2]=2
                      var a3=2
                       this.color.c='warning'
                        this.msg2='부족'
                        this.data.certificate_rank=2
                            this.message.certificate='자격증 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.certificate_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[2]=1
                        var a3=1
                        this.color.c='danger'
                        this.msg2='매우부족'
                        this.data.certificate_rank=1
                            this.message.certificate='자격증 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                       // this.radarChartData.datasets[0].data[2]=3
                        var a3=3
                        this.color.c='primary'
                        this.msg2='평균'
                        this.data.certificate_rank=3
                            this.message.certificate='자격증 등급이 합격자와 비슷합니다.'
                      }

                      //교육
                      if(res.data.data.education_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a4=5
                        this.color.d='green'
                        this.msg3='매우잘함'
                        this.data.education_rank=5
                                                    this.message.education='교육이수 등급이 합격자보다 매우 우수합니다.'

                      }
                      else if(res.data.data.education_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a4=4
                        this.color.d='info'
                        this.msg3='잘함'
                        this.data.education_rank=4
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.education_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a4=2
                        this.color.d='warning'
                        this.msg3='부족'
                        this.data.education_rank=2
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.education_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a4=1
                        this.color.d='danger'
                        this.msg3='매우부족'
                        this.data.education_rank=1
                                                    this.message.education='교육이수 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a4=3
                       this.color.d='primary'
                        this.msg3='평균'
                        this.data.education_rank=3
                                                    this.message.education='교육이수 등급이 합격자와 비슷합니다.'

                      }
                      //해외경험
                      if(res.data.data.experience_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a5=5
                        this.color.e='green'
                        this.msg4='매우잘함'
                        this.data.experience_rank=5
                            this.message.experience='해외경험 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.experience_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a5=4
                        this.color.e='info'
                        this.msg4='잘함'
                        this.data.experience_rank=4
                            this.message.experience='해외경험 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.experience_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a5=2
                        this.color.e='warning'
                        this.msg4='부족'
                        this.data.experience_rank=2
                            this.message.experience='해외경험 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.experience_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a5=1
                        this.color.e='danger'
                        this.msg4='매우부족'
                        this.data.experience_rank=1
                            this.message.experience='해외경험 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a5=3
                        this.color.e='primary'
                        this.msg4='평균'
                        this.data.experience_rank=3
                                                    this.message.experience='해외경험 등급이 합격자와 비슷합니다.'

                      }
                      //학점
                      if(res.data.data.grade_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a6=5
                        this.color.f='green'
                        this.msg5='매우잘함'
                        this.data.grade_rank=5
                            this.message.grade='학점 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.grade_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a6=4
                        this.color.f='info'
                        this.msg5='잘함'
                        this.data.grade_rank=4
                                                    this.message.grade='학점 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.grade_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a6=2
                        this.color.f='warning'
                        this.msg5='부족'
                        this.data.grade_rank=2
                                                    this.message.grade='학점 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.grade_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a6=1
                        this.color.f='danger'
                        this.msg5='매우부족'
                        this.data.grade_rank=1
                                                    this.message.grade='학점 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a6=3
                        this.color.f='primary'
                        this.msg5='평균'
                        this.data.grade_rank=3
                                                    this.message.grade='학점 등급이 합격자와 비슷합니다.'

                      }
                      //프로젝트
                      if(res.data.data.project_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a7=5
                        this.color.g='green'
                        this.msg6='매우잘함'
                        this.data.project_rank=5
                            this.message.project='프로젝트 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.project_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a7=4
                        this.color.g='info'
                        this.msg6='잘함'
                        this.data.project_rank=4
                            this.message.project='프로젝트 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.project_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a7=2
                        this.color.g='warning'
                        this.msg6='부족'
                        this.data.project_rank=2
                        this.message.project='프로젝트 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.project_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a7=1
                        this.color.g='danger'
                        this.msg6='매우부족'
                        this.data.project_rank=1
                            this.message.project='프로젝트 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a7=3
                        this.color.g='primary'
                        this.msg6='평균'
                        this.data.project_rank=3
                            this.message.project='프로젝트 등급이 합격자와 비슷합니다.'
                      }
                      //어학
                      if(res.data.data.language_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a8=5
                        this.color.h='green'
                        this.msg7='매우잘함'
                        this.data.language_rank=5
                        this.message.language='어학 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.language_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a8=4
                        this.color.h='info'
                        this.msg7='잘함'
                        this.data.language_rank=4
                        this.message.language='어학 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.language_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a8=2
                        this.color.h='warning'
                        this.msg7='부족'
                        this.data.language_rank=2
                            this.message.language='어학 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.language_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a8=1
                        this.color.h='danger'
                        this.msg7='매우부족'
                        this.data.language_rank=1
                            this.message.language='어학 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a8=3
                        this.color.h='primary'
                        this.msg7='평균'
                        this.data.language_rank=3
                        this.message.language='어학 등급이 합격자와 비슷합니다.'
                      }
                      var result=[a0,a1,a2,a3,a4,a5,a6,a7,a8]
                      localStorage.setItem("rating",JSON.stringify(result))

                      
  
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
       this.advice() },
        c_rankView(){
          axios.get(`${url}/rank/college`)
                    .then(res=>{
                      this.viewRank=res.data.data
                      localStorage.setItem("rank",JSON.stringify(res.data.data))
                      console.log(this.viewRank)
                      if(res.data.data.all_rank==='1등급'){
                        var a0=5
                        this.color.a0='green'
                        this.msg00='매우잘함'
                        this.data.all_rank=5
                      }
                      else if(res.data.data.all_rank==='2등급'){
                        var a0=4
                        this.color.a0='info'
                        this.msg00='잘함'
                        this.data.all_rank=4
                        }
                      else if(res.data.data.all_rank==='4등급'){
                        var a0=2
                        this.color.a0='warning'
                        this.msg00='부족'
                        this.data.all_rank=2
                      }
                      else if(res.data.data.all_rank==='5등급'){
                        var a0=1
                        this.color.a0='danger'
                        this.msg00='매우부족'
                        this.data.all_rank=1
                      }else{
                        var a0=3
                        this.color.a0='primary'
                        this.msg00='평균'
                        this.data.all_rank=3
           
                      }
                      if(res.data.data.award_rank==='1등급'){
                        //this.radarChartData.datasets[0].data[0]=5
                        var a1=5
                        this.color.a='green'
                        this.msg0='매우잘함'
                        this.data.award_rank=5
                        this.message.award='수상 등급이 합격자보다 매우 우수합니다'
                      }
                      else if(res.data.data.award_rank==='2등급'){
                        var a1=4
                        this.color.a='info'
                        this.msg0='잘함'
                        this.data.award_rank=4
                        this.message.award='수상 등급이 합격자 평균 보다 높습니다'
                        }
                      else if(res.data.data.award_rank==='4등급'){
                        var a1=2
                        this.color.a='warning'
                        this.msg0='부족'
                        this.data.award_rank=2
                        this.message.award='수상 등급이 합격자 평균 보다 낮습니다.'
                        
                      }
                      else if(res.data.data.award_rank==='5등급'){
                        var a1=1
                        this.color.a='danger'
                        this.msg0='매우부족'
                        this.data.award_rank=1
                        this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                        var a1=3
                        this.color.a='primary'
                        this.msg0='평균'
                        this.data.award_rank=3
                        this.message.award='수상 등급이 합격자와 비슷합니다.'
           
                      }

                      //경력
                      if(res.data.data.career_rank==='1등급'){
                        var a2=5
                        this.color.b='green'
                        this.msg1='매우잘함'
                        this.data.career_rank=5
                                                    this.message.career='경력 등급이 합격자보다 매우 우수합니다'

                      }
                      else if(res.data.data.career_rank==='2등급'){
                        var a2=4
                        this.color.b='info'
                        this.msg1='잘함'
                        this.data.career_rank=4
                            this.message.career='경력 등급이 합격자 평균 보다 높습니다'
                      }
                      else if(res.data.data.career_rank==='4등급'){
                        var a2=2
                        this.color.b='warning'
                        this.msg1='부족'
                        this.data.career_rank=2
                                                   this.message.career='경력 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.career_rank==='5등급'){
                        var a2=1          
                        this.color.b='danger'
                        this.msg1='매우부족'
                        this.data.career_rank=1
                            this.message.career='경력 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                        var a2=3
                        this.color.b='primary'
                        this.msg1='평균'
                        this.data.career_rank=3
                        this.message.career='경력 등급이 합격자와 비슷합니다.'
                      }
                      
                      //자격증
                       if(res.data.data.certificate_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[2]=5
                        var a3=5
                        this.color.c='green'
                        this.msg2='매우잘함'
                        this.data.certificate_rank=5
                            this.message.certificate='자격증 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.certificate_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[2]=4
                       var a3=4
                        this.color.c='info'
                        this.msg2='잘함'
                        this.data.certificate_rank=4
                            this.message.certificate='자격증 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.certificate_rank==='4등급'){
                      // this.radarChartData.datasets[0].data[2]=2
                      var a3=2
                       this.color.c='warning'
                        this.msg2='부족'
                        this.data.certificate_rank=2
                            this.message.certificate='자격증 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.certificate_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[2]=1
                        var a3=1
                        this.color.c='danger'
                        this.msg2='매우부족'
                        this.data.certificate_rank=1
                            this.message.certificate='자격증 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                       // this.radarChartData.datasets[0].data[2]=3
                        var a3=3
                        this.color.c='primary'
                        this.msg2='평균'
                        this.data.certificate_rank=3
                            this.message.certificate='자격증 등급이 합격자와 비슷합니다.'
                      }

                      //교육
                      if(res.data.data.education_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a4=5
                        this.color.d='green'
                        this.msg3='매우잘함'
                        this.data.education_rank=5
                                                    this.message.education='교육이수 등급이 합격자보다 매우 우수합니다.'

                      }
                      else if(res.data.data.education_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a4=4
                        this.color.d='info'
                        this.msg3='잘함'
                        this.data.education_rank=4
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.education_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a4=2
                        this.color.d='warning'
                        this.msg3='부족'
                        this.data.education_rank=2
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.education_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a4=1
                        this.color.d='danger'
                        this.msg3='매우부족'
                        this.data.education_rank=1
                                                    this.message.education='교육이수 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a4=3
                       this.color.d='primary'
                        this.msg3='평균'
                        this.data.education_rank=3
                                                    this.message.education='교육이수 등급이 합격자와 비슷합니다.'

                      }
                      //해외경험
                      if(res.data.data.experience_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a5=5
                        this.color.e='green'
                        this.msg4='매우잘함'
                        this.data.experience_rank=5
                            this.message.experience='해외경험 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.experience_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a5=4
                        this.color.e='info'
                        this.msg4='잘함'
                        this.data.experience_rank=4
                            this.message.experience='해외경험 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.experience_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a5=2
                        this.color.e='warning'
                        this.msg4='부족'
                        this.data.experience_rank=2
                            this.message.experience='해외경험 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.experience_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a5=1
                        this.color.e='danger'
                        this.msg4='매우부족'
                        this.data.experience_rank=1
                            this.message.experience='해외경험 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a5=3
                        this.color.e='primary'
                        this.msg4='평균'
                        this.data.experience_rank=3
                                                    this.message.experience='해외경험 등급이 합격자와 비슷합니다.'

                      }
                      //학점
                      if(res.data.data.grade_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a6=5
                        this.color.f='green'
                        this.msg5='매우잘함'
                        this.data.grade_rank=5
                            this.message.grade='학점 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.grade_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a6=4
                        this.color.f='info'
                        this.msg5='잘함'
                        this.data.grade_rank=4
                                                    this.message.grade='학점 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.grade_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a6=2
                        this.color.f='warning'
                        this.msg5='부족'
                        this.data.grade_rank=2
                                                    this.message.grade='학점 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.grade_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a6=1
                        this.color.f='danger'
                        this.msg5='매우부족'
                        this.data.grade_rank=1
                                                    this.message.grade='학점 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a6=3
                        this.color.f='primary'
                        this.msg5='평균'
                        this.data.grade_rank=3
                                                    this.message.grade='학점 등급이 합격자와 비슷합니다.'

                      }
                      //프로젝트
                      if(res.data.data.project_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a7=5
                        this.color.g='green'
                        this.msg6='매우잘함'
                        this.data.project_rank=5
                            this.message.project='프로젝트 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.project_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a7=4
                        this.color.g='info'
                        this.msg6='잘함'
                        this.data.project_rank=4
                            this.message.project='프로젝트 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.project_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a7=2
                        this.color.g='warning'
                        this.msg6='부족'
                        this.data.project_rank=2
                        this.message.project='프로젝트 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.project_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a7=1
                        this.color.g='danger'
                        this.msg6='매우부족'
                        this.data.project_rank=1
                            this.message.project='프로젝트 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a7=3
                        this.color.g='primary'
                        this.msg6='평균'
                        this.data.project_rank=3
                            this.message.project='프로젝트 등급이 합격자와 비슷합니다.'
                      }
                      //어학
                      if(res.data.data.language_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a8=5
                        this.color.h='green'
                        this.msg7='매우잘함'
                        this.data.language_rank=5
                        this.message.language='어학 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.language_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a8=4
                        this.color.h='info'
                        this.msg7='잘함'
                        this.data.language_rank=4
                        this.message.language='어학 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.language_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a8=2
                        this.color.h='warning'
                        this.msg7='부족'
                        this.data.language_rank=2
                            this.message.language='어학 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.language_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a8=1
                        this.color.h='danger'
                        this.msg7='매우부족'
                        this.data.language_rank=1
                            this.message.language='어학 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a8=3
                        this.color.h='primary'
                        this.msg7='평균'
                        this.data.language_rank=3
                        this.message.language='어학 등급이 합격자와 비슷합니다.'
                      }
                      var result=[a0,a1,a2,a3,a4,a5,a6,a7,a8]
                      localStorage.setItem("rating",JSON.stringify(result))
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
       this.advice() },
        c_g_rankView(){
          axios.get(`${url}/rank/college/grade`)
                    .then(res=>{
                      this.viewRank=res.data.data
                      localStorage.setItem("rank",JSON.stringify(res.data.data))
                      console.log(this.viewRank) 

                      if(res.data.data.all_rank==='1등급'){
                        var a0=5
                        this.color.a0='green'
                        this.msg00='매우잘함'
                        this.data.all_rank=5
                      }
                      else if(res.data.data.all_rank==='2등급'){
                        var a0=4
                        this.color.a0='info'
                        this.msg00='잘함'
                        this.data.all_rank=4
                        }
                      else if(res.data.data.all_rank==='4등급'){
                        var a0=2
                        this.color.a0='warning'
                        this.msg00='부족'
                        this.data.all_rank=2
                      }
                      else if(res.data.data.all_rank==='5등급'){
                        var a0=1
                        this.color.a0='danger'
                        this.msg00='매우부족'
                        this.data.all_rank=1
                      }else{
                        var a0=3
                        this.color.a0='primary'
                        this.msg00='평균'
                        this.data.all_rank=3
           
                      }
                       if(res.data.data.award_rank==='1등급'){
                        //this.radarChartData.datasets[0].data[0]=5
                        var a1=5
                        this.color.a='green'
                        this.msg0='매우잘함'
                        this.data.award_rank=5
                        this.message.award='수상 등급이 합격자보다 매우 우수합니다'
                      }
                      else if(res.data.data.award_rank==='2등급'){
                        var a1=4
                        this.color.a='info'
                        this.msg0='잘함'
                        this.data.award_rank=4
                        this.message.award='수상 등급이 합격자 평균 보다 높습니다'
                        }
                      else if(res.data.data.award_rank==='4등급'){
                        var a1=2
                        this.color.a='warning'
                        this.msg0='부족'
                        this.data.award_rank=2
                        this.message.award='수상 등급이 합격자 평균 보다 낮습니다.'
                        
                      }
                      else if(res.data.data.award_rank==='5등급'){
                        var a1=1
                        this.color.a='danger'
                        this.msg0='매우부족'
                        this.data.award_rank=1
                        this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                        var a1=3
                        this.color.a='primary'
                        this.msg0='평균'
                        this.data.award_rank=3
                        this.message.award='수상 등급이 합격자와 비슷합니다.'
           
                      }

                      //경력
                      if(res.data.data.career_rank==='1등급'){
                        var a2=5
                        this.color.b='green'
                        this.msg1='매우잘함'
                        this.data.career_rank=5
                                                    this.message.career='경력 등급이 합격자보다 매우 우수합니다'

                      }
                      else if(res.data.data.career_rank==='2등급'){
                        var a2=4
                        this.color.b='info'
                        this.msg1='잘함'
                        this.data.career_rank=4
                            this.message.career='경력 등급이 합격자 평균 보다 높습니다'
                      }
                      else if(res.data.data.career_rank==='4등급'){
                        var a2=2
                        this.color.b='warning'
                        this.msg1='부족'
                        this.data.career_rank=2
                                                   this.message.career='경력 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.career_rank==='5등급'){
                        var a2=1          
                        this.color.b='danger'
                        this.msg1='매우부족'
                        this.data.career_rank=1
                            this.message.career='경력 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                        var a2=3
                        this.color.b='primary'
                        this.msg1='평균'
                        this.data.career_rank=3
                        this.message.career='경력 등급이 합격자와 비슷합니다.'
                      }
                      
                      //자격증
                       if(res.data.data.certificate_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[2]=5
                        var a3=5
                        this.color.c='green'
                        this.msg2='매우잘함'
                        this.data.certificate_rank=5
                            this.message.certificate='자격증 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.certificate_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[2]=4
                       var a3=4
                        this.color.c='info'
                        this.msg2='잘함'
                        this.data.certificate_rank=4
                            this.message.certificate='자격증 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.certificate_rank==='4등급'){
                      // this.radarChartData.datasets[0].data[2]=2
                      var a3=2
                       this.color.c='warning'
                        this.msg2='부족'
                        this.data.certificate_rank=2
                            this.message.certificate='자격증 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.certificate_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[2]=1
                        var a3=1
                        this.color.c='danger'
                        this.msg2='매우부족'
                        this.data.certificate_rank=1
                            this.message.certificate='자격증 등급이 합격자에 비해 매우 낮습니다.'
                      }
                      else{
                       // this.radarChartData.datasets[0].data[2]=3
                        var a3=3
                        this.color.c='primary'
                        this.msg2='평균'
                        this.data.certificate_rank=3
                            this.message.certificate='자격증 등급이 합격자와 비슷합니다.'
                      }

                      //교육
                      if(res.data.data.education_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a4=5
                        this.color.d='green'
                        this.msg3='매우잘함'
                        this.data.education_rank=5
                                                    this.message.education='교육이수 등급이 합격자보다 매우 우수합니다.'

                      }
                      else if(res.data.data.education_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a4=4
                        this.color.d='info'
                        this.msg3='잘함'
                        this.data.education_rank=4
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.education_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a4=2
                        this.color.d='warning'
                        this.msg3='부족'
                        this.data.education_rank=2
                                                    this.message.education='교육이수 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.education_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a4=1
                        this.color.d='danger'
                        this.msg3='매우부족'
                        this.data.education_rank=1
                                                    this.message.education='교육이수 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a4=3
                       this.color.d='primary'
                        this.msg3='평균'
                        this.data.education_rank=3
                                                    this.message.education='교육이수 등급이 합격자와 비슷합니다.'

                      }
                      //해외경험
                      if(res.data.data.experience_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a5=5
                        this.color.e='green'
                        this.msg4='매우잘함'
                        this.data.experience_rank=5
                            this.message.experience='해외경험 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.experience_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a5=4
                        this.color.e='info'
                        this.msg4='잘함'
                        this.data.experience_rank=4
                            this.message.experience='해외경험 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.experience_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a5=2
                        this.color.e='warning'
                        this.msg4='부족'
                        this.data.experience_rank=2
                            this.message.experience='해외경험 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.experience_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a5=1
                        this.color.e='danger'
                        this.msg4='매우부족'
                        this.data.experience_rank=1
                            this.message.experience='해외경험 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a5=3
                        this.color.e='primary'
                        this.msg4='평균'
                        this.data.experience_rank=3
                                                    this.message.experience='해외경험 등급이 합격자와 비슷합니다.'

                      }
                      //학점
                      if(res.data.data.grade_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a6=5
                        this.color.f='green'
                        this.msg5='매우잘함'
                        this.data.grade_rank=5
                            this.message.grade='학점 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.grade_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a6=4
                        this.color.f='info'
                        this.msg5='잘함'
                        this.data.grade_rank=4
                                                    this.message.grade='학점 등급이 합격자 평균 보다 높습니다.'

                      }
                      else if(res.data.data.grade_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a6=2
                        this.color.f='warning'
                        this.msg5='부족'
                        this.data.grade_rank=2
                                                    this.message.grade='학점 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.grade_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a6=1
                        this.color.f='danger'
                        this.msg5='매우부족'
                        this.data.grade_rank=1
                                                    this.message.grade='학점 등급이 합격자에 비해 매우 낮습니다.'

                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a6=3
                        this.color.f='primary'
                        this.msg5='평균'
                        this.data.grade_rank=3
                                                    this.message.grade='학점 등급이 합격자와 비슷합니다.'

                      }
                      //프로젝트
                      if(res.data.data.project_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a7=5
                        this.color.g='green'
                        this.msg6='매우잘함'
                        this.data.project_rank=5
                            this.message.project='프로젝트 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.project_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a7=4
                        this.color.g='info'
                        this.msg6='잘함'
                        this.data.project_rank=4
                            this.message.project='프로젝트 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.project_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a7=2
                        this.color.g='warning'
                        this.msg6='부족'
                        this.data.project_rank=2
                        this.message.project='프로젝트 등급이 합격자 평균 보다 낮습니다.'

                      }
                      else if(res.data.data.project_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a7=1
                        this.color.g='danger'
                        this.msg6='매우부족'
                        this.data.project_rank=1
                            this.message.project='프로젝트 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a7=3
                        this.color.g='primary'
                        this.msg6='평균'
                        this.data.project_rank=3
                            this.message.project='프로젝트 등급이 합격자와 비슷합니다.'
                      }
                      //어학
                      if(res.data.data.language_rank==='1등급'){
                       // this.radarChartData.datasets[0].data[3]=5
                        var a8=5
                        this.color.h='green'
                        this.msg7='매우잘함'
                        this.data.language_rank=5
                        this.message.language='어학 등급이 합격자보다 매우 우수합니다.'
                      }
                      else if(res.data.data.language_rank==='2등급'){
                       // this.radarChartData.datasets[0].data[3]=4
                        var a8=4
                        this.color.h='info'
                        this.msg7='잘함'
                        this.data.language_rank=4
                        this.message.language='어학 등급이 합격자 평균 보다 높습니다.'
                      }
                      else if(res.data.data.language_rank==='4등급'){
                       // this.radarChartData.datasets[0].data[3]=2
                        var a8=2
                        this.color.h='warning'
                        this.msg7='부족'
                        this.data.language_rank=2
                            this.message.language='어학 등급이 합격자 평균 보다 낮습니다.'
                      }
                      else if(res.data.data.language_rank==='5등급'){
                       // this.radarChartData.datasets[0].data[3]=1
                        var a8=1
                        this.color.h='danger'
                        this.msg7='매우부족'
                        this.data.language_rank=1
                            this.message.language='어학 등급이 합격자에 비해 매우 낮습니다.'
                      }else{
                       // this.radarChartData.datasets[0].data[3]=3
                        var a8=3
                        this.color.h='primary'
                        this.msg7='평균'
                        this.data.language_rank=3
                        this.message.language='어학 등급이 합격자와 비슷합니다.'
                      }
                      var result=[a0,a1,a2,a3,a4,a5,a6,a7,a8]
                      localStorage.setItem("rating",JSON.stringify(result))
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
       this.advice() },
        
        
    },
    computed:{
    authorized(){
      return store.getters.userlink===0
    },
    // myStyles () {
    //     return {
    //       height: `${this.height}px`,
    //       position: 'relative'
    //     }
    //   }
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
.Chart {
    padding: 20px;
    box-shadow: 0px 0px 20px 2px rgba(0, 0, 0, .4);
    border-radius: 20px;
    margin: 50px 0;
    width:500px;
    height: 500px;
    
  }
  

#prog{
  
  width: 95%;
  
}

#RatingSystemTable1{
  font-size: medium;
}

#contents4{

        width: 60%;
        float: left;
        padding: 10px;
        margin-left: 10px;

    }
</style>


