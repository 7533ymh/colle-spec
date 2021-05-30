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

                            <b-form-group id="input-group-1" label="외국어명" label-for="input-1">

                                <b-form-select @change="optionclick" id="division" name="select" v-model="lang.division"  required="required" >
                                        <option value='null' disabled selected >나라 선택</option>
                                        <option v-for="(item, index) in LanguageCountryJs" :key="index" :value="item" >{{ item }}</option>
                                </b-form-select>
                                                         
                                        <!--<b-form-input
                                            id="division" 
                                            
                                            v-model="lang.division"  
                                            type="text"
                                            required="required">
                                        </b-form-input> -->
                                  
                            </b-form-group>


                            <b-form-group id="input-group-1" label="어학시험" label-for="input-1">

                                     <!--<b-form-select @change="optionclick" id="exam" name="select" v-model="lang.exam"  required="required" >
                                        <option value='null' disabled selected >시험 선택</option>
                                        <option v-for="(item, index) in LanguageTestJs" :key="index" :value="item" >{{ item }}</option>
                                    </b-form-select>-->



                                     <b-form-input
                                        id="exam"
                                        v-model="lang.exam"
                                        type="text"
                                        placeholder="예) TOEIC"
                                        required="required"></b-form-input>
                                </b-form-group>
                                

                            <b-form-group id="input-group-1" label="어학점수" label-for="input-1">
                                <b-form-input
                                    id="exam_score"
                                    v-model="lang.exam_score"
                                    type="number"
                                    placeholder="점수를 입력하세요"
                                    required="required"></b-form-input>

                                <b-form-group id="input-group-1" label="어학내용" label-for="input-1">
                                    <b-form-textarea
                                        style="height:300px;"
                                        id="content"
                                        v-model="lang.content"
                                        placeholder="어학시험 내용을 입력하세요"
                                        required="required"></b-form-textarea>
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

                                <b-button router-link to="/LanguageTest" type="submit" variant="primary">제출</b-button>
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
import LanguageTestJs from './LanguageTest';
import LanguageCountryJs from './LanguageCountry';



let url=store.state.resourceHost; //서버주소 api
export default {

    mounted(){
        console.log(this.LanguageTestJs)
        console.log(this.LanguageCountryJs)
    },



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
                LanguageTestJs,
                LanguageCountryJs,
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
