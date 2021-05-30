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
            <b-col xl="7" class="mb-xl-">
                <card header-classes="bg-transparent">
                    <b-row align-v="center" slot="header">

                        <b-col>
                           
                        </b-col>
                    </b-row>




                    <!-- 폼시작 -->
                        <b-form @submit="onSubmit" @reset="onReset" v-if="show" style="width : 50%; margin-left : 10%;">
                            <b-form-group id="input-group-1" label="교육제목" label-for="input-1">
                                    <b-form-input
                                        id="title"
                                        v-model="edu.title"
                                        type="text"
                                        placeholder="제목입력"
                                        required="required"></b-form-input>
                                </b-form-group>

                            <b-form-group id="input-group-1" label="교육기관" label-for="input-1">
                                <b-form-input
                                    id="agency"
                                    v-model="edu.agency"
                                    type="text"
                                    placeholder="교육기관을 입력하세요"
                                    required="required"></b-form-input>

                                <b-form-group id="input-group-1" label="교육내용" label-for="input-1" style="margin-top:10px;">
                                    <b-form-textarea
                                        style="height:300px;"
                                        id="content"
                                        v-model="edu.content"
                                        placeholder="교육내용을 입력하세요"
                                        required="required"></b-form-textarea>
                                </b-form-group>
                                
                                <b-form-group id="input-group-1" label="시작년도" label-for="input-1">
                                <div id="span_date">
                                    <b-form-input
                                        id="year"
                                        v-model="edu.start_date"
                                        type="date"
                                        required="required"></b-form-input>
                                </div>
                                </b-form-group>
                                
                                <b-form-group id="input-group-1" label="마감년도" label-for="input-1">
                                <div id="span_date">
                                    <b-form-input
                                        id="year"
                                        v-model="edu.end_date"
                                        type="date"
                                        required="required"></b-form-input>
                                </div>
                                </b-form-group>
                        <!-- 폼끝 -->
                                <b-button router-link to="/Education" type="submit" variant="primary">제출</b-button>
                                <b-button type="reset" variant="danger">초기화</b-button>
                            </b-form-group>
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
            edu: {
                    agency: '',     //교육기관
                    content: '',    //교육내용
                    title:'',       //제목
                    start_date:'',  //교육시작날짜
                    end_date:'',    //교육종료날짜
                },
                show:true,
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                var education = new URLSearchParams();
                education.append('title', this.edu.title);
                education.append('agency', this.edu.agency);
                education.append('content', this.edu.content);
                education.append('start_date', this.edu.start_date);
                education.append('end_date', this.edu.end_date);
                axios.post(`${url}/education`,education)
                .then(edu=>{
                    console.log(edu)
                    alert(edu.data.msg)
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