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
        <b-form @submit="onSubmit" @reset="onReset.prevent" v-if="show">
            <b-form-group id="input-group-1" label="경력회사" label-for="input-1">
                <b-form-input
                    id="company"
                    v-model="career.company"
                    type="text"
                    placeholder="회사이름을 입력하세요"
                    required="required"></b-form-input>

                <b-form-group id="input-group-1" label="경력내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="career.content"
                        placeholder="내용을 입력하세요"
                        required="required"></textarea>
                </b-form-group>
                <b-form-group id="input-group-1" label="경력부서" label-for="input-1">
                    <b-form-input
                        id="department"
                        v-model="career.department"
                        type="text"
                        placeholder="부서를 입력하세요"
                        required="required"></b-form-input>
                </b-form-group>
                <b-form-group id="input-group-1" label="경력구분" label-for="input-1">
                    <b-form-input
                        id="division"
                        v-model="career.division"
                        type="text"
                        placeholder="구분"
                        required="required"></b-form-input>
                </b-form-group>
                <div id="span_date">
                    <b-form-input
                        id="start_date"
                        v-model="career.start_date"
                        type="date"
                        placeholder="날짜 입력하세요"
                        required="required"></b-form-input>
                    <b-form-input
                        id="end_date"
                        v-model="career.end_date"
                        type="date"
                        placeholder="날짜 입력하세요"
                        required="required"></b-form-input>
                </div>
            </b-form-group>
            <!-- 폼끝 -->
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
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
            career: {
                    company: '',    //회사이름
                    content: '',    //업무내용
                    department: '', //부서
                    division:'',    //업무역할
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
         
                var career = new URLSearchParams();
                career.append('company', this.career.company);
                career.append('content', this.career.content);
                career.append('department', this.career.department);
                career.append('division', this.career.division);
                career.append('end_date', this.career.end_date);
                career.append('start_date', this.career.start_date);
                axios.post(`${url}/career`,career)
                .then(career=>{
                    console.log(career.data.msg)
                    console.log(career)
                    alert(career.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
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
  <style scoped="scoped">
    #content {
        width: 50%;
        height: 100px;
        resize: none;
    }
    #agency,#division, #company, #department {
        width: 50%;
    }
    #title,
    #year, #start_date, #end_date{
        width: 20%;
    }
</style>