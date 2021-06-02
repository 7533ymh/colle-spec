<template>
  <div>
      <!--사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -사 용 안 하는 페 이 지 이 니 돌 아 가 시 오 !!!!!!!!!!!!!!!!!!
      -->
        <base-header class="pb-7 pt-md-5 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="3" md="6">
                    <stats-card title="" type="gradient-red" sub-title="기업에서 요구하는 스펙" class="mb-4"></stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
              <b-row>
                <b-col xl="12" class="mb-5 mb-xl-0">
                    <card header-classes="bg-transparent">
                        <div v-if="odatashow===true">
                            <div id="contents2">
                        <h1><a :href="odata.url" target="place">{{odata.name}}</a></h1>
                        <h3>대표자<br>{{odata.representative}}</h3>
                        <h3>구분<br>{{odata.division}}</h3>
                        
                        
                        <h3>산업<br>{{odata.industry}}</h3>
                        
                        <h3>주요사업<br>{{odata.bussiness}}</h3>
                        <h3>4대보험<br>{{odata.insurance}}</h3>
                                                
                    </div>
                    <!---content끝-->
                    <!--contents2-->
                    <div id="contents3">
                        <h3>사원수: {{odata.people}}</h3>
                        <h3>자본금: {{odata.capital}}</h3>
                        <h3>매출액: {{odata.take}}</h3>
                        <h3>대졸초임: {{odata.salary}}</h3>
                        <h3>설립일: {{odata.establishment}}</h3>
                        <h3>주소: {{odata.address}}</h3>
                    </div>
                        </div>
                        
                        <!-- 프로그레스바 리스트 들고오기 -->
                            <div>
                                <div id="contents">
                                    
                                    <h3>{{odata.name}}
                                        합격자와의 비교</h3>
                                    <!-- <p>변경 {{data}}</p> <p>원본 {{odata}}</p> -->

                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.a" :value="data.award_rank" :hover="msg0" >
                                            <span>수상:
                                                <strong>{{ msg0 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.b" :value="data.career_rank">
                                            <span>경력:
                                                <strong>{{ msg1 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.c" :value="data.certificate_rank">
                                            <span>자격증:
                                                <strong>{{ msg2 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.d" :value="data.education_rank">
                                            <span>교육이수:
                                                <strong>{{ msg3 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.e" :value="data.experience_rank">
                                            <span>해외경험:
                                                <strong>{{msg4}}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.f" :value="data.language_rank">
                                            <span>어학:
                                                <strong>{{ msg5 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.g" :value="data.grade_rank">
                                            <span>학점:
                                                <strong>{{ msg6 }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>

                               
                                </div> <!---content끝-->
                                     
                                <div id="sidebar">
                                        
                                    <table class="tbList">
                                      
                                        <colgroup>
                                            <col width="10%"/>
                                            <col width="90%"/>
                                        </colgroup>
                                        <thead>
                                        <tr>
                                            <th width="160px">no</th>
                                            <th >회사</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr v-for="(row, i) in view" :key="i">

                                            <td width="50px">{{i+1}}</td>
                                            <td width="280px" @click="detail(row)">{{view[i].name}}</td>

                                        </tr>
                                        <tr v-if="view.length == 0">
                                            <td colspan="4">데이터가 없습니다.</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                                
                                <div id="sidebar2" mt-4>
                                    <h3>한줄 평가</h3>
                                    <h4>{{message.award}}<router-link to="/Competition1">&nbsp;&nbsp;&nbsp;{{adv.award}}</router-link></h4>
                                    <h4>{{message.career}}<a :href="advurl.career" target="_blank">&nbsp;&nbsp;&nbsp;{{adv.career}}</a></h4>
                                    <h4>{{message.certificate}} <a :href="advurl.certificate" target="_blank">&nbsp;&nbsp;&nbsp;{{adv.certificate}}</a></h4>
                                    <h4>{{message.education}} <router-link to="/CompetitionProgram">&nbsp;&nbsp;&nbsp;{{adv.education}}</router-link></h4>
                                    <h4>{{message.experience}} <a :href="advurl.experience" target="_blank">&nbsp;&nbsp;&nbsp;{{adv.experience}}</a></h4>
                                    <h4>{{message.language}}<router-link to="/languageinfo">&nbsp;&nbsp;&nbsp;{{adv.language}}</router-link></h4>
                                    <h4>{{message.grade}}</h4>
                                </div>
                          
                            
                            </div>
                        </card>                       
                    </b-col>                 
                </b-row>
                <!-- End charts-->    
                
          </b-container>         
  </div>
</template>
<script>
    let url = store.state.resourceHost;

    import axios from 'axios';
    import store from '@/store';

    // Components
    import BaseProgress from '@/components/BaseProgress';
    import StatsCard from '@/components/Cards/StatsCard';

    // Tables
    import SocialTrafficTable from './Dashboard/SocialTrafficTable';
    import PageVisitsTable from './Dashboard/PageVisitsTable';
    export default {
        components: {
            BaseProgress,
            StatsCard,
            PageVisitsTable,
            SocialTrafficTable,
        },
        data() {
            
            return {
                max: 5,
                view: {
                    //각 division(분류)로 조회한 리스트들이 저장되는 곳
                },
                color:{a:'',b:'',c:'',d:'',e:'',f:'',g:''},

                data: { //원본데이터를 프로그레스바 적용위해 등급 변환 
                    award_rank: '',
                    career_rank: '',
                    certificate_rank: '',
                    education_rank: '',
                    experience_rank: '',
                    language_rank: '',
                    grade_rank: '',
                    project_rank: ''

                },
                odata: {}, //원본데이터
                odatashow:false,
                

                msg0: '', //수상메시지
                msg1: '', //경력메시지
                msg2: '', //자격증메시지
                msg3: '', //교육이수메시지
                msg4: '', //해외경험메시지
                msg5: '', //어학메시지
                msg6: '', //학점메시지
                msg7: '', //프로젝트메시지

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
                },
                advurl:{
                    career:'https://www.jobkorea.co.kr/starter/?schLocal=&schPart=&schMajor=&schEduLevel=&schWork=2&schCType=&isSaved=1&LinkGubun=0&LinkNo=0&Page=1&schType=0&schGid=0&schOrderBy=0&schTxt='
                    ,
                    experience:'https://www.ef.co.kr/pg/courses/?source=007959,NVKRS_LAL_00_00_DESKTOP&NaPm=ct%3Dkpaovxso%7Cci%3D0zm0003R5pbuJsaF30Wh%7Ctr%3Dsa%7Chk%3D1d576b61c20a492046e0d65dfa47cf7a735d6613'
                    ,
                    language:'https://www.saha.go.kr/startup/contents.do?mId=0301000000'
                    ,
                    certificate:'http://www.q-net.or.kr/crf005.do?id=crf00501&gSite=Q&gId='
                }

            }
        },

        methods: {
            getList1() {
                axios
                    .get(`${url}/companyList`) //회사리스트 조회
                    .then((res) => {
                        console.log(res.data.list);
                        console.log(res.data.list[0]);
                        //this.view.d1=res.data.list;
                        this.view = res.data.list;

                        // this.list = res.data.list; 	this.paging = res.data.paging; 	this.no =
                        // this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp);
                    })
                    .catch((err) => {
                        console.log(err);
                    })
                },
            advice(){
                if(this.message.award==='수상 등급이 합격자 평균스펙보다 낮습니다.')
                {
                    this.adv.award="추천 정보보기"
                }else if(this.message.award==='수상 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.award="추천 정보보기"
                }
                else{
                    this.adv.award=null
                }

                if(this.message.career==='경력 등급이 합격자 평균스펙보다 낮습니다.'){
                    this.adv.career="추천 정보보기"
                }
                else if(this.message.career==='경력 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.career="추천 정보보기"
                }
                else{
                    this.adv.career=null
                }

                if(this.message.certificate==='자격증 등급이 합격자 평균스펙보다 낮습니다.')
                {
                    this.adv.certificate="추천 정보보기"
                }else if(this.message.certificate==='자격증 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.certificate="추천 정보보기"
                }else{
                    this.adv.certificate=null
                }

                if(this.message.education==='교육이수 등급이 합격자 평균스펙보다 낮습니다.'){
                    this.adv.education="추천 정보보기"
                }
                else if(this.message.v==='교육이수 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.education="추천 정보보기"
                }else{
                    this.adv.education=null
                }

                if(this.message.experience==='해외경험 등급이 합격자 평균스펙보다 낮습니다.')
                {
                    this.adv.experience="추천 정보보기"
                }else if(this.message.experience==='해외경험 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.experience="추천 정보보기"
                }
                else{
                    this.adv.experience=null
                }

                if(this.message.language==='어학 등급이 합격자 평균스펙보다 낮습니다.'){
                    this.adv.language="추천 정보보기"
                }
                else if(this.message.language==='어학 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.language="추천 정보보기"
                }
                else{
                    this.adv.language=null
                }

                if(this.message.grade==='학점 등급이 합격자 평균스펙보다 낮습니다.'){
                    this.adv.grade="추천 정보보기"
                }
                else if(this.message.grade==='학점 등급이 합격자에 비해 매우 낮습니다.'){
                    this.adv.grade="추천 정보보기"
                }
                else{
                    this.adv.grade=null
                }
            },
            async detail(row) { 
                //등급을 받아올때 1등급~5등급을 받아온다. progressbar를 이용할 때 1등급인데 1칸밖에 채워지지않아
                //5칸을 채우려고 받은 값들을 거꾸로 설정 1->5 2->4 3->3 4->2 5->1
                await axios
                    .get(`${url}/company`, {
                        params: {
                            company_idx: row.idx
                        }
                    })
                    .then(res => {
                        console.log('원본', res.data.data)
                        this.odata = res.data.data
                        this.odatashow=true

                        if (res.data.data.award_rank === 5) {
                            this.data.award_rank = 1;
                            this.msg0 = '매우낮음';
                            this.color.a='danger';
                            this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'

                        } else if (res.data.data.award_rank === 4) {
                            this.data.award_rank = 2;
                            this.msg0 = '낮음';
                            this.color.a='warning';
                            this.message.award='수상 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.award_rank === 2) {
                            this.data.award_rank = 4;
                            this.msg0 = '높음';
                            this.color.a='info';
                            this.message.award='수상 등급이 합격자 평균스펙보다 높습니다.'
                        } else if (res.data.data.award_rank === 1) {
                            this.data.award_rank = 5;
                            this.msg0 = '매우높음';
                            this.color.a='green';
                            this.message.award='수상 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.award_rank = 3;
                            this.msg0 = '평균';
                            this.color.a='primary';
                            this.message.award='수상 등급이 합격자와 비슷합니다.'
                        }

                        if (res.data.data.career_rank === 5) {
                            this.data.career_rank = 1;
                            this.msg1 = '매우낮음';
                            this.color.b='danger';
                            this.message.career='경력 등급이 합격자에 비해 매우 낮습니다.'


                        } else if (res.data.data.career_rank === 4) {
                            this.data.career_rank = 2;
                            this.msg1 = '낮음';
                            this.color.b='warning';
                            this.message.career='경력 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.career_rank === 2) {
                            this.data.career_rank = 4;
                            this.msg1 = '높음';
                            this.color.b='info';
                            this.message.career='경력 등급이 합격자 평균스펙보다 높습니다'
                        } else if (res.data.data.career_rank === 1) {
                            this.data.career_rank = 5;
                            this.msg1 = '매우높음';
                            this.color.b='green';
                            this.message.career='경력 등급이 합격자보다 매우 우수합니다'
                        } else {
                            this.data.career_rank = 3;
                            this.msg1 = '평균';
                            this.color.b='primary';
                            this.message.career='경력 등급이 합격자와 비슷합니다.'

                        }

                        if (res.data.data.certificate_rank === 5) {
                            this.data.certificate_rank = 1;
                            this.msg2 = '매우낮음';
                            this.color.c='danger';
                            this.message.certificate='자격증 등급이 합격자에 비해 매우 낮습니다.'

                        } else if (res.data.data.certificate_rank === 4) {
                            this.data.certificate_rank = 2;
                            this.msg2 = '낮음';
                            this.color.c='warning';
                            this.message.certificate='자격증 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.certificate_rank === 2) {
                            this.data.certificate_rank = 4;
                            this.msg2 = '높음';
                            this.color.c='info';
                            this.message.certificate='자격증 등급이 합격자 평균스펙보다 높습니다.'
                        } else if (res.data.data.certificate_rank === 1) {
                            this.data.certificate_rank = 5;
                            this.msg2 = '매우높음';
                            this.color.c='green';
                            this.message.certificate='자격증 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.certificate_rank = 3;
                            this.msg2 = '평균';
                            this.color.c='primary';
                            this.message.certificate='자격증 등급이 합격자와 비슷합니다.'
                        }

                        if (res.data.data.education_rank === 5) {
                            this.data.education_rank = 1;
                            this.msg3 = '매우낮음';
                            this.color.d='danger';
                            this.message.education='교육이수 등급이 합격자에 비해 매우 낮습니다.'
                        } else if (res.data.data.education_rank === 4) {
                            this.data.education_rank = 2;
                            this.msg3 = '낮음';
                            this.color.d='warning';
                            this.message.education='교육이수 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.education_rank === 2) {
                            this.data.education_rank = 4;
                            this.msg3 = '높음';
                            this.color.d='info';
                            this.message.education='교육이수 등급이 합격자 평균스펙보다 높습니다.'
                        } else if (res.data.data.education_rank === 1) {
                            this.data.education_rank = 5;
                            this.msg3 = '매우높음';
                            this.color.d='green';
                            this.message.education='교육이수 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.education_rank = 3;
                            this.msg3 = '평균';
                            this.color.d='primary';
                            this.message.education='교육이수 등급이 합격자와 비슷합니다.'
                        }

                        if (res.data.data.experience_rank === 5) {
                            this.data.experience_rank = 1;
                            this.msg4 = '매우낮음';
                            this.color.e='danger';
                            this.message.experience='해외경험 등급이 합격자에 비해 매우 낮습니다.'
                        } else if (res.data.data.experience_rank === 4) {
                            this.data.experience_rank = 2;
                            this.msg4 = '낮음';
                            this.color.e='warning';
                            this.message.experience='해외경험 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.experience_rank === 2) {
                            this.data.experience_rank = 4;
                            this.msg4 = '높음';
                            this.color.e='info';
                            this.message.experience='해외경험 등급이 합격자 평균스펙보다 높습니다.'
                        } else if (res.data.data.experience_rank === 1) {
                            this.data.experience_rank = 5;
                            this.msg4 = '매우높음';
                            this.color.e='green';
                            this.message.experience='해외경험 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.experience_rank = 3;
                            this.msg4 = '평균';
                            this.color.e='primary';
                            this.message.experience='해외경험 등급이 합격자와 비슷합니다.'
                        }

                        if (res.data.data.language_rank === 5) {
                            this.data.language_rank = 1;
                            this.msg5 = '매우낮음';
                            this.color.f='danger';
                            this.message.language='어학 등급이 매우낮습니다.'
                        } else if (res.data.data.language_rank === 4) {
                            this.data.language_rank = 2;
                            this.msg5 = '못함';
                            this.color.f='warning';
                            this.message.language='어학 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.language_rank === 2) {
                            this.data.language_rank = 4;
                            this.msg5 = '높음';
                            this.color.f='info';
                            this.message.language='어학 등급이 합격자 평균스펙보다 높습니다.'
                        } else if (res.data.data.language_rank === 1) {
                            this.data.language_rank = 5;
                            this.msg5 = '매우높음';
                            this.color.f='green';
                            this.message.language='어학 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.language_rank = 3;
                            this.msg5 = '평균';
                            this.color.f='primary';
                            this.message.language='어학 등급이 합격자와 비슷합니다.'
                        }

                        if (res.data.data.grade_rank === 5) {
                            this.data.grade_rank = 1;
                            this.msg6 = '매우낮음';
                            this.color.g='danger';
                            this.message.grade='학점 등급이 합격자에 비해 매우 낮습니다.'
                        } else if (res.data.data.grade_rank === 4) {
                            this.data.grade_rank = 2;
                            this.msg6 = '낮음';
                            this.color.g='warning';
                            this.message.grade='학점 등급이 합격자 평균스펙보다 낮습니다.'
                        } else if (res.data.data.grade_rank === 2) {
                            this.data.grade_rank = 4;
                            this.msg6 = '높음';
                            this.color.g='info';
                            this.message.grade='학점 등급이 합격자 평균스펙보다 높습니다.'
                        } else if (res.data.data.grade_rank === 1) {
                            this.data.grade_rank = 5;
                            this.msg6 = '매우높음';
                            this.color.g='green';
                            this.message.grade='학점 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.grade_rank = 3;
                            this.msg6 = '평균';
                            this.color.g='primary';
                            this.message.grade='학점 등급이 합격자와 비슷합니다.'
                        }

                    })
                console.log('행데이터 값', row)
                console.log('바뀐데이터', this.data)
                
                this.advice()
            }
        },

        mounted() {
            this.getList1();
        }
    }
</script>
<style style lang="scss" scoped>


    .el-table .cell {
        padding-left: 0;
        padding-right: 0;
    }
    #contents {
        /* border: 1px solid #487BE1; */
        width: 55%;
        float: left;
        padding: 10px;

    }

    #sidebar, #sidebar2{
    

        width: 40%;
        float: left;
        padding: 10px;
        margin-left: 10px;

    }
    #sidebar{
      /* overflow: auto; */
      height: 200px;
    }
    #sidebar table{
        width:500px;
    }

    #sidebar2{
      margin-top:10px;
    }
    #prog {
        margin-top: 1px;
        background-color: rgb(255, 255, 255);
        }
    thead{
        font:bold;
        display:block;
        padding-left: 10px;
        width:70%;
        border: 1px solid grey;
    }
    tbody{
        height: 160px;
        border:1px solid;
        overflow: auto;
        width:70%;
        display: block;
        padding-left: 0px;
    }
 #contents2 {
        /* border: 1px solid #487BE1; */
        width: 70%;
        float: left;
        padding: 10px;

    }
     #contents3 {
        /* border: 1px solid #487BE1; */
        width: 30%;
        float: left;
        padding: 10px;

    }

</style>