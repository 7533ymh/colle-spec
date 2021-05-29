<template>
  <div>
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
                        
                        <!-- 프로그레스바 리스트 들고오기 -->
                            <div>
                              <!-- <div id="wrapper"> -->
                                <div id="contents">
                                    <h3>{{odata.name}}
                                        합격 가능성</h3>
                                    <!-- <p>변경 {{data}}</p> <p>원본 {{odata}}</p> -->

                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color.a" :value="data.award_rank">
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
                                    <h3>여기에 보완할 컨텐츠 제공</h3>
                                    <h4>{{message.award}}</h4>
                                    <h4>{{message.career}}</h4>
                                    <h4>{{message.certificate}}</h4>
                                    <h4>{{message.education}}</h4>
                                    <h4>{{message.experience}}</h4>
                                    <h4>{{message.language}}</h4>
                                </div>
                            <!-- </div> -->
        
                            </div>
                         <div>
                                 <div id=sidebar3>
              <!--회사 정보 리스트 -->
                                        <table>
                                            <colgroup>
                                            <col width="6%"/>
                                            <col width="20%"/>
                                            <col width="6%"/>
                                            <col width="4%"/>
                                            <col width="10%"/>
                                            <col width="10%"/>

                                            </colgroup>
                                            <tr>
                                                <th>회사이름</th>
                                                <th>주소</th>
                                                <th>구분</th>
                                                <th>사원수</th>
                                                
                                                <th>산업</th>
                                                <th>자본금</th>
                                                
                                                
                                                
                                            </tr>
                                            <tr>
                                                <td><a :href="odata.url" target="place">{{odata.name}}</a></td>
                                                <td>{{odata.address}}</td>
                                                <td>{{odata.division}}</td>
                                                <td>{{odata.people}}</td>
                                                <td>{{odata.industry}}</td>
                                                <td>{{odata.capital}}</td>
                                            </tr>
                                            <tr>
                                                <th>설립일</th>
                                                <th>주요사업</th>
                                                <th>대표자</th>
                                                <th>대졸초임</th>
                                                <th>4대보험</th>
                                                <th>매출액</th>
                                            </tr>
                                            <tr>
                                                <td>{{odata.establishment}}</td>
                                                <td>{{odata.bussiness}}</td>
                                                <td>{{odata.representative}}</td>
                                                <td>{{odata.salary}}</td>
                                                <td>{{odata.insurance}}</td>
                                                <td>{{odata.take}}</td>                                
                                            </tr>
                                        </table>
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
            SocialTrafficTable
        },
        data() {
            
            return {
                max: 5,
                view: {
                    //각 division(분류)로 조회한 리스트들이 저장되는 곳
                },
                color:{a:'',b:'',c:'',d:'',e:'',f:'',g:''},

                data: { //원본데이터 프로그레스바 적용위해 등급 변환 
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

                        if (res.data.data.award_rank === 5) {
                            this.data.award_rank = 1;
                            this.msg0 = '매우낮음';
                            this.color.a='danger';
                            this.message.award='수상 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'

                        } else if (res.data.data.award_rank === 4) {
                            this.data.award_rank = 2;
                            this.msg0 = '낮음';
                            this.color.a='warning';
                            this.message.award='수상 등급이 평균보다 낮습니다.. 공모전을 추천해드리겠습니다.'
                        } else if (res.data.data.award_rank === 2) {
                            this.data.award_rank = 4;
                            this.msg0 = '높음';
                            this.color.a='info';
                            this.message.award='수상 등급이 평균이상입니다. 잘하고 있습니다.'
                        } else if (res.data.data.award_rank === 1) {
                            this.data.award_rank = 5;
                            this.msg0 = '매우높음';
                            this.color.a='green';
                            this.message.award='수상 등급이 매우 높습니다. 너무 잘하고 있습니다.'
                        } else {
                            this.data.award_rank = 3;
                            this.msg0 = '평균';
                            this.color.a='primary';
                            this.message.award='수상 등급이 평균입니다. 조금더 노력해보세요.'
                        }

                        if (res.data.data.career_rank === 5) {
                            this.data.career_rank = 1;
                            this.msg1 = '매우낮음';
                            this.color.b='danger';
                            this.message.career='경력 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'


                        } else if (res.data.data.career_rank === 4) {
                            this.data.career_rank = 2;
                            this.msg1 = '낮음';
                            this.color.b='warning';
                            this.message.career='경력 등급이 평균보다 낮습니다.. 공모전을 추천해드리겠습니다.'
                        } else if (res.data.data.career_rank === 2) {
                            this.data.career_rank = 4;
                            this.msg1 = '높음';
                            this.color.b='info';
                            this.message.career='경력 등급이 평균이상입니다. 잘하고 있습니다.'
                        } else if (res.data.data.career_rank === 1) {
                            this.data.career_rank = 5;
                            this.msg1 = '매우높음';
                            this.color.b='green';
                            this.message.career='경력 등급이 매우 높습니다. 너무 잘하고 있습니다.'
                        } else {
                            this.data.career_rank = 3;
                            this.msg1 = '평균';
                            this.color.b='primary';
                            this.message.career='경력 등급이 평균입니다. 조금더 노력해보세요.'

                        }

                        if (res.data.data.certificate_rank === 5) {
                            this.data.certificate_rank = 1;
                            this.msg2 = '매우낮음';
                            this.color.c='danger';
                            this.message.certificate='수상 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'

                        } else if (res.data.data.certificate_rank === 4) {
                            this.data.certificate_rank = 2;
                            this.msg2 = '낮음';
                            this.color.c='warning';
                            this.message.certificate='수상 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'
                        } else if (res.data.data.certificate_rank === 2) {
                            this.data.certificate_rank = 4;
                            this.msg2 = '높음';
                            this.color.c='info';
                            this.message.certificate='수상 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'
                        } else if (res.data.data.certificate_rank === 1) {
                            this.data.certificate_rank = 5;
                            this.msg2 = '매우높음';
                            this.color.c='green';
                            this.message.certificate='수상 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'
                        } else {
                            this.data.certificate_rank = 3;
                            this.msg2 = '평균';
                            this.color.c='primary';
                            this.message.certificate='수상 등급이 매우 낮습니다. 공모전을 추천해드리겠습니다.'
                        }

                        if (res.data.data.education_rank === 5) {
                            this.data.education_rank = 1;
                            this.msg3 = '매우낮음';
                            this.color.d='danger';
                        } else if (res.data.data.education_rank === 4) {
                            this.data.education_rank = 2;
                            this.msg3 = '낮음';
                            this.color.d='warning';
                        } else if (res.data.data.education_rank === 2) {
                            this.data.education_rank = 4;
                            this.msg3 = '높음';
                            this.color.d='info';
                        } else if (res.data.data.education_rank === 1) {
                            this.data.education_rank = 5;
                            this.msg3 = '매우높음';
                            this.color.d='green';
                        } else {
                            this.data.education_rank = 3;
                            this.msg3 = '평균';
                            this.color.d='primary';
                        }

                        if (res.data.data.experience_rank === 5) {
                            this.data.experience_rank = 1;
                            this.msg4 = '매우낮음';
                            this.color.e='danger';
                        } else if (res.data.data.experience_rank === 4) {
                            this.data.experience_rank = 2;
                            this.msg4 = '낮음';
                            this.color.e='warning';
                        } else if (res.data.data.experience_rank === 2) {
                            this.data.experience_rank = 4;
                            this.msg4 = '높음';
                            this.color.e='info';
                        } else if (res.data.data.experience_rank === 1) {
                            this.data.experience_rank = 5;
                            this.msg4 = '매우높음';
                            this.color.e='green';
                        } else {
                            this.data.experience_rank = 3;
                            this.msg4 = '평균';
                            this.color.e='primary';
                        }

                        if (res.data.data.language_rank === 5) {
                            this.data.language_rank = 1;
                            this.msg5 = '매우낮음';
                            this.color.f='danger';
                        } else if (res.data.data.language_rank === 4) {
                            this.data.language_rank = 2;
                            this.msg5 = '못함';
                            this.color.f='warning';
                        } else if (res.data.data.language_rank === 2) {
                            this.data.language_rank = 4;
                            this.msg5 = '높음';
                            this.color.f='info';
                        } else if (res.data.data.language_rank === 1) {
                            this.data.language_rank = 5;
                            this.msg5 = '매우높음';
                            this.color.f='green';
                        } else {
                            this.data.language_rank = 3;
                            this.msg5 = '평균';
                            this.color.f='primary';
                        }

                        if (res.data.data.grade_rank === 5) {
                            this.data.grade_rank = 1;
                            this.msg6 = '매우낮음';
                            this.color.g='danger';
                        } else if (res.data.data.grade_rank === 4) {
                            this.data.grade_rank = 2;
                            this.msg6 = '낮음';
                            this.color.g='warning';
                        } else if (res.data.data.grade_rank === 2) {
                            this.data.grade_rank = 4;
                            this.msg6 = '높음';
                            this.color.g='info';
                        } else if (res.data.data.grade_rank === 1) {
                            this.data.grade_rank = 5;
                            this.msg6 = '매우높음';
                            this.color.g='green';
                        } else {
                            this.data.grade_rank = 3;
                            this.msg6 = '평균';
                            this.color.g='primary';
                        }

                        // if (res.data.data.project_rank === 5) {
                        //     this.data.project_rank = 1;
                        //     this.msg7 = '매우낮음';
                        //     this.color.g='danger';
                        // } else if (res.data.data.project_rank === 4) {
                        //     this.data.project_rank = 2;
                        //     this.msg7 = '낮음';
                        //     this.color.g='warning';
                        // } else if (res.data.data.project_rank === 2) {
                        //     this.data.project_rank = 4;
                        //     this.msg7 = '높음';
                        //     this.color.g='info';
                        // } else if (res.data.data.project_rank === 1) {
                        //     this.data.project_rank = 5;
                        //     this.msg7 = '매우높음';
                        //     this.color.g='green';
                        // } else {
                        //     this.data.project_rank = 3;
                        //     this.msg7 = '평균';
                        //     this.color.g='primary';
                        // }

                    })
                console.log('detailitem', row)
                console.log('바뀐데이터', this.data)
                // localStorage.setItem('company',JSON.stringify(row))
                // this.$router.push({path:'/board/ProgramTeamDetail'});
            }
        },
        mounted() {
            this.getList1();
        }
    }
</script>
<style>

    .el-table .cell {
        padding-left: 0;
        padding-right: 0;
    }

    #wrapper {
        /* border: 1px solid #FFBB00; */
        width: 90%;
        padding: 10px;
        position: absolute;
        top: 80%;
        overflow: hidden;

    }

    #contents {
        /* border: 1px solid #487BE1; */
        width: 65%;
        float: left;
        padding: 10px;
        border: 2px solid blue;

    }

    #sidebar, #sidebar2{
    

        width: 34%;
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
    #sidebar3{
        border: 1px solid blue;
        margin-top:39%;
    }
    #sidebar3 th,td{
        border: 1px solid black;
        text-align: center;
    }
    #sidebar3 th{
        font:bold;
        font-size: 17px;
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


</style>