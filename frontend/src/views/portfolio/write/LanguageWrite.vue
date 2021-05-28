<template>
    <div>
        <base-header class="pb-5 pt-md-5 bg-gradient-success" >
    <b-col class="ml-9" xl="6">
    <stats-card title=""
                      type="gradient-red"
                      sub-title="포트폴리오 작성"
                      align="center"
                      class="mb-3 ml-5">

            
          </stats-card>
    </b-col>
      
    </base-header>
     <b-container fluid="fluid" class="mt--0">
        <b-row>
            <b-col xl="11" class="mb-7 mb-xl-0">
                <card header-classes="bg-transparent">
                    <b-row align-v="center" slot="header">

                        <b-col>
                            <b-nav class="nav-pills justify-content-end">
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/awardWrite">
                                    <span class="d-none d-md-block">수상 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/careerWrite">
                                    <span class="d-none d-md-block">경력 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/certificateWrite">
                                    <span class="d-none d-md-block">자격증 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/educationWrite">
                                    <span class="d-none d-md-block">교육이수 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/experienceWrite">
                                    <span class="d-none d-md-block">해외경험 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/languageWrite">
                                    <span class="d-none d-md-block">어학시험 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/projectWrite">
                                    <span class="d-none d-md-block">프로젝트 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3"
                                    router-link="router-link"
                                    to="/portfolio/introductionWrite">
                                    <span class="d-none d-md-block">자기소개서 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>
                            </b-nav>
                        </b-col>
                    </b-row>
                </card>
            </b-col>
        </b-row>
    </b-container>
    <!-- 폼시작 -->
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="어학시험" label-for="input-1">
                    <b-form-input
                        id="exam"
                        v-model="lang.exam"
                        type="text"
                        placeholder="예) toeic, teps, toefl, hsk, opic"
                        required="required"></b-form-input>
                </b-form-group>
                   <b-form-group id="input-group-1" label="어학구분" label-for="input-1">
                    <b-form-input
                        id="division"
                        v-model="lang.division"
                        type="text"
                        placeholder="예) 영어, 중국어, 일본어 .."
                        required="required"></b-form-input>
                </b-form-group>

            <b-form-group id="input-group-1" label="어학점수" label-for="input-1">
                <b-form-input
                    id="exam_score"
                    v-model="lang.exam_score"
                    type="text"
                    placeholder="점수를 입력하세요"
                    required="required"></b-form-input>

                <b-form-group id="input-group-1" label="어학내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="lang.content"
                        placeholder="어학시험내용 입력"
                        required="required"></textarea>
                </b-form-group>
                
                <b-form-group id="input-group-1" label="취득일자" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="lang.date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
            <!-- 폼끝 -->

                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form-group>
            </b-form>
    </div>
</template>
<script>
import axios from 'axios';
import store from '@/store';


let url=store.state.resourceHost; //서버주소 api
export default {
    data(){
        return{
            lang: {
                    exam: '',       //시험명    (셀렉트로 고르도록)
                    content: '',    //시험내용
                    division:'',    //시험 언어 구분 ....영어, 중국어, 일본어(셀렉트로 고르도록)
                    date:'',        //시험 친 날짜
                    exam_score:'',  //시험 점수
                },
                show:true,
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                //수상 작성
                var language = new URLSearchParams();
                language.append('exam', this.lang.exam);
                language.append('content', this.lang.content);
                language.append('division', this.lang.division);
                language.append('exam_score', this.lang.exam_score);
                language.append('date', this.lang.date);
                axios.post(`${url}/language`,language)
                .then(lang=>{
                    console.log(lang)
                    alert(lang.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })

    },
    onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.agency = ''
                this.content = ''
                this.division = ''
                this.year =''
                this.title =''

                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            },
    }
}
</script>
