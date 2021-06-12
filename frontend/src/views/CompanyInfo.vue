<template>
  <div>
        <base-header class="pb-4 pt-md-6 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="6" md="6">
                    <stats-card title=""
                      type="gradient-red"
                      sub-title="기업 리스트"
                      id="card"
                      class="mb-4">
                        <template slot="footer"></template>
                    </stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
    <b-row>
        <b-col xl="10" class="mb-5 mb-xl-0">
            <card header-classes="bg-transparent">

                <div>
                    <!-- <div id="wrapper"> -->
                    <div id="contents">
                        <el-table
                            class="table-responsive table"
                            header-row-class-name="thead-light"
                            :data="view"
                            :row-class-name="tableRow"
                            @row-click="detail"
                            :overflow="auto"
                            >

                            <el-table-column label="번호" prop='idx' min-width="30%">
                                <template slot-scope="scope">
                                    {{scope.$index+1}}
                                </template>
                            </el-table-column>

                            <el-table-column label="회사이름" prop="name" min-width="100%">
                                <template slot-scope="scope">
                                    {{scope.row.name}}
                                </template>
                            </el-table-column>
                        </el-table>                    </div>
                    <!---content끝-->
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


  import { Table, TableColumn, Button} from 'element-ui'

    export default {
        components: {
            BaseProgress,
            StatsCard,
           
            [Table.name]: Table,
      [TableColumn.name]: TableColumn,
    //   [Button.name]: Button,
        },
        computed:{
           
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
                        console.log("확인1",res.data.list);
                        console.log("확인2",res.data.list[0]);
                        console.log("확인3",res.data);
                        this.view = res.data.list;

                        // this.list = res.data.list; 	this.paging = res.data.paging; 	this.no =
                        // this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp);
                    })
                    .catch((err) => {
                        console.log(err);
                    })
                },
                 tableRow({row,rowIndex}){
            row.index=rowIndex+1;
          },
            // async detail(row) { 
                
            //     await axios.get(`${url}/company`, {params: {
            //                 company_idx: row.idx
            //             }
            //         })
            //         .then(res => {
            //             console.log('확인')
            //             //this.odata = res.data.data
            //             localStorage.setItem('companyinfo',JSON.stringify(res.data.data))
            //             this.$router.push({path:'/Company/info/detail'})
            //         })
            //     console.log('row', row)
            // },

            /////////////////////////////////
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
                        localStorage.setItem("companyinfo",JSON.stringify(res.data.data))

                        if (res.data.data.award_rank === 5) {
                            this.data.award_rank = 1;
                            this.msg0 = '매우낮음';
                            this.color.a='danger';
                            this.message.award='수상 등급이 합격자에 비해 매우 낮습니다.'

                        } else if (res.data.data.award_rank === 4) {
                            this.data.award_rank = 2;
                            this.msg0 = '낮음';
                            this.color.a='warning';
                            this.message.award='수상 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.award_rank === 2) {
                            this.data.award_rank = 4;
                            this.msg0 = '높음';
                            this.color.a='info';
                            this.message.award='수상 등급이 합격자 평균 보다 높습니다.'
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
                            this.message.career='경력 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.career_rank === 2) {
                            this.data.career_rank = 4;
                            this.msg1 = '높음';
                            this.color.b='info';
                            this.message.career='경력 등급이 합격자 평균 보다 높습니다'
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
                            this.message.certificate='자격증 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.certificate_rank === 2) {
                            this.data.certificate_rank = 4;
                            this.msg2 = '높음';
                            this.color.c='info';
                            this.message.certificate='자격증 등급이 합격자 평균 보다 높습니다.'
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
                            this.message.education='교육이수 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.education_rank === 2) {
                            this.data.education_rank = 4;
                            this.msg3 = '높음';
                            this.color.d='info';
                            this.message.education='교육이수 등급이 합격자 평균 보다 높습니다.'
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
                            this.message.experience='해외경험 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.experience_rank === 2) {
                            this.data.experience_rank = 4;
                            this.msg4 = '높음';
                            this.color.e='info';
                            this.message.experience='해외경험 등급이 합격자 평균 보다 높습니다.'
                        } else if (res.data.data.experience_rank === 1) {
                            this.data.experience_rank = 5;
                            this.msg4 = '매우높음';
                            this.color.e='green';
                            this.message.experience='해외경험 등급이 합격자보다 매우 우수합니다.'
                        } else {
                            this.data.experience_rank = 3;
                            this.msg4 = '평균 ';
                            this.color.e='primary';
                            this.message.experience='해외경험 등급이 합격자와 비슷합니다.'
                        }

                        if (res.data.data.language_rank === 5) {
                            this.data.language_rank = 1;
                            this.msg5 = '매우낮음';
                            this.color.f='danger';
                            this.message.language='어학 등급이 합격자에 비해 매우 낮습니다.'
                        } else if (res.data.data.language_rank === 4) {
                            this.data.language_rank = 2;
                            this.msg5 = '못함';
                            this.color.f='warning';
                            this.message.language='어학 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.language_rank === 2) {
                            this.data.language_rank = 4;
                            this.msg5 = '높음';
                            this.color.f='info';
                            this.message.language='어학 등급이 합격자 평균 보다 높습니다.'
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
                            this.message.grade='학점 등급이 합격자 평균 보다 낮습니다.'
                        } else if (res.data.data.grade_rank === 2) {
                            this.data.grade_rank = 4;
                            this.msg6 = '높음';
                            this.color.g='info';
                            this.message.grade='학점 등급이 합격자 평균 보다 높습니다.'
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
                    this.advice()
                console.log('new행데이터 값', row)
                console.log('new바뀐데이터', this.data)
                var msg=[
                    this.msg0,
                    this.msg1,
                    this.msg2,
                    this.msg3,
                    this.msg4,
                    this.msg5,
                    this.msg6
                ]
                var data=[
                    this.data.award_rank,
                    this.data.career_rank,
                    this.data.certificate_rank,
                    this.data.education_rank,
                    this.data.experience_rank,
                    this.data.language_rank,
                    this.data.grade_rank
                ]
                var color=[
                    this.color.a,
                    this.color.b,
                    this.color.c,
                    this.color.d,
                    this.color.e,
                    this.color.f,
                    this.color.g
                ]
                var advurl=[this.advurl.career, this.advurl.certificate, this.advurl.experience, this.advurl.language]
                 var adv=[this.adv.award,
                    this.adv.career,
                    this.adv.certificate,
                    this.adv.education,
                    this.adv.experience,
                    this.adv.language,
                    this.adv.grade]
                var message=[
                    this.message.award,
                    this.message.career,
                    this.message.certificate,
                    this.message.education,
                    this.message.experience,
                    this.message.language,
                    this.message.grade]
                this.$router.push({path:'/Company/info/detail',query:{data:data, color:color, message:message, adv:adv, msg:msg, advurl:advurl}})
               
            }
            /////////////////////////////////
        },
 ////////////////////////////////////////////
        mounted() {
            this.getList1();
        }
    }
</script>
<style style lang="scss" scoped>

    /* .el-table .cell {
        padding-left: 0;
        padding-right: 0;
    } */

    #wrapper {
        /* border: 1px solid #FFBB00; */
        width: 100%;
        padding: 10px;
        position: absolute;
        top: 80%;
        overflow: hidden;

    }

    #contents {
        /* border: 1px solid #487BE1; */
        width: 100%;
        float: left;
        padding: 10px;

    }
//     #card{
//     margin-left: 100%;
//     width: 100%;
//     text-align: center;
// }
</style>