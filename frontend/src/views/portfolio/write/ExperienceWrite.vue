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
                        <b-form @submit.prevent="onSubmit" v-if="show"  style="width : 50%; margin-left : 10%;">
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
                            <b-button @click="onReset" type="reset" variant="danger">초기화</b-button>
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
        onSubmit(event) {
                event.preventDefault()
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
                    this.$router.push({path:'/Experience'})
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })
    },
    onReset() {
                
                // Reset our form values
                this.experience.country= '',    //나라이름
                this.experience.content= '',    //내용
                this.experience.start_date='',  //시작날짜
                this.experience.end_date='',    //종료날짜
                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            },
    }
}
</script>