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
     <b-container fluid="fluid" class="mt--0"  style="margin-left : 185px;">
        <b-row>
            <b-col xl="6" class="mb-xl-">
                <card header-classes="bg-transparent">
                    <b-row align-v="center" slot="header">

                        <b-col>
                            <b-nav class="nav-pills justify-content-end" >
                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/awardWrite">
                                    <span class="d-none d-md-block">수상 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/careerWrite">
                                    <span class="d-none d-md-block">경력 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>
                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/certificateWrite">
                                    <span class="d-none d-md-block">자격증 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/educationWrite">
                                    <span class="d-none d-md-block">교육이수 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/experienceWrite">
                                    <span class="d-none d-md-block">해외경험 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/languageWrite">
                                    <span class="d-none d-md-block">어학시험 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/projectWrite">
                                    <span class="d-none d-md-block">프로젝트 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>

                                <b-nav-item
                                    link-classes="py-2 px-3 m-1"
                                    router-link="router-link"
                                    to="/portfolio/introductionWrite">
                                    <span class="d-none d-md-block">자기소개서 등록</span>
                                    <span class="d-md-none">W</span>
                                </b-nav-item>
                            </b-nav>
                        </b-col>
                    </b-row>



                                    <!-- 폼시작 -->
                        <b-form @submit="onSubmit" @reset="onReset" v-if="show" style="padding-left: 100px; width:400px;">
                            <b-form-group id="input-group-1" label="유학 국적" label-for="input-1">
                                    <b-form-input
                                        
                                        id="country"
                                        v-model="experience.country"
                                        type="text"
                                        placeholder="예) 미국"
                                        required="required"></b-form-input>
                                </b-form-group>
                                <b-form-group id="input-group-1" label="내용" label-for="input-1">
                                    <b-form-textarea
                                        style="height:300px;"
                                        id="content"
                                        v-model="experience.content"
                                        type="text"
                                        placeholder="내용을 입력하세요"
                                        required="required"></b-form-textarea>
                                    </b-form-group>

                                
                                <b-form-group id="input-group-1" label="시작날짜" label-for="input-1">
                                <div id="span_date">
                                    <b-form-input
                                        id="year"
                                        v-model="experience.start_date"
                                        type="date"
                                        required="required"></b-form-input>
                                </div>
                                </b-form-group>

                                <b-form-group id="input-group-1" label="종료날짜" label-for="input-1">
                                <div id="span_date">
                                    <b-form-input
                                        id="year"
                                        v-model="experience.end_date"
                                        type="date"
                                        required="required"></b-form-input>
                                </div>
                            </b-form-group>
                            <!-- 폼끝 -->
                            <b-button type="submit" variant="primary">제출</b-button>
                            <b-button type="reset" variant="danger">초기화</b-button>
                        </b-form>





                </card>
            </b-col>
        </b-row>
    </b-container>
       
    </div>
</template>
<script>
import axios from 'axios';
import store from '@/store';


let url=store.state.resourceHost; //서버주소 api
export default {
    data(){
        return{
            experience: {
                    country: '',    //나라이름
                    content: '',    //내용
                    start_date:'',  //시작날짜
                    end_date:'',    //종료날짜
                },
                show:true,
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                var experience = new URLSearchParams();
                experience.append('country', this.experience.country);
                experience.append('content', this.experience.content);
                experience.append('start_date', this.experience.start_date);
                experience.append('end_date', this.experience.end_date);
                axios.post(`${url}/experience`,experience)
                .then(experience=>{
                    console.log(experience)
                    alert(experience.data.msg)
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
