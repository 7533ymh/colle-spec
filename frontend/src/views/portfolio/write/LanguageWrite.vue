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
                            
                        </b-col>
                    </b-row>
                </card>
            </b-col>
        </b-row>
    </b-container>
    <!-- 폼시작 -->
        <b-form @submit.prevent="onSubmit"  v-if="show">
            
            <b-form-group id="input-group-1" label="어학시험" label-for="input-1">
                   <b-form-select @change="optionclick" id="exam" name="select" v-model="lang.exam"  required="required" >
                                        <option value='null' disabled selected >시험 선택</option>
                                        <option v-for="(item, index) in langexam" :key="index" :value="item" >{{ item }}</option>
                                </b-form-select>
                        
                        <!-- <button type="button" class="el-button" @click="scorecheck">점수입력</button> -->
                    
                        
                </b-form-group>
                   <b-form-group id="input-group-1" label="어학구분" label-for="input-1">
                    <b-form-input
                        id="division"
                        v-model="lang.division"
                        type="text"
                        
                        required="required"></b-form-input>
                </b-form-group>

            <b-form-group id="input-group-1" label="어학점수" label-for="input-1" v-if="view">
                <b-form-input v-if="type==='toeic'"
                    id="exam_score"
                    name="토익"
                    v-model="lang.exam_score"
                    type="number"
                    min=0
                    max=990
                    :placeholder="place"
                    required="required"></b-form-input>

                    <b-form-input v-if="type==='teps'"
                    id="exam_score"
                    name="텝스"
                    v-model="lang.exam_score"
                    type="number"
                    min=0
                    max=990
                    :placeholder="place"
                    required="required"></b-form-input>

                    <b-form-input v-if="type==='toefl'"
                    id="exam_score"
                    name="토플"
                    v-model="lang.exam_score"
                    type="number"
                    min=0
                    max=30
                    :placeholder="place"
                    required="required"></b-form-input>

                    <b-form-select v-if="type==='hsk'" @change="scoreclick" id="exam_score" name="select" v-model="lang.exam_score"  required="required" >
                                        <option value='null' disabled selected >등급 선택</option>
                                        <option v-for="(item, index) in hsk" :key="index" :value="item" >{{ item }}</option>
                    </b-form-select>
                    <b-form-select v-if="type==='jlpt'" @change="scoreclick" id="exam_score" name="select" v-model="lang.exam_score"  required="required" >
                                        <option value='null' disabled selected >등급 선택</option>
                                        <option v-for="(item, index) in jlpt" :key="index" :value="item" >{{ item }}</option>
                    </b-form-select>
                    <b-form-select v-if="type==='opic'" @change="scoreclick" id="exam_score" name="select" v-model="lang.exam_score"  required="required" >
                                        <option value='null' disabled selected >등급 선택</option>
                                        <option v-for="(item, index) in opic" :key="index" :value="item" >{{ item }}</option>
                    </b-form-select>

                </b-form-group>
                
                <b-form-group id="input-group-1" label="어학내용" label-for="input-1">
                    <textarea
                        id="content"
                        name="jlpt, hsk, opic"
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
                
                <b-button type="reset" @click="rreset" variant="danger">Reset</b-button>

            </b-form>
    </div>
</template>
<script>
import axios from 'axios';
import store from '@/store';
import langexam from './language.js'
import hsk from './hsk.js'
import jlpt from './jlpt.js'
import opic from './opic.js'
let url=store.state.resourceHost; //서버주소 api
export default {
    data(){
        return{
            langexam,hsk,jlpt,opic,
            place:'',
            type:'',
            view:false,
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
    created(){

    },
    methods:{
        rreset(){
                 this.exam= '',       
                 this.content='',    
                 this.division='',  
                 this.date='',      
                 this.exam_score='',
                 this.type=null
        },
        scoreclick(event){
            this.lang.exam_score=event
            console.log(event)
        },
        optionclick(event){
            this.lang.exam=event;
            console.log(this.lang.exam);
            if(event==="TOEIC" ||event==="TOEFL Writing"||event==="TOEFL Reading"||event==="TOEFL Lisenting"||event==="TOEFL Speaking"||event==="TEPS"||event==="OPIC"){
                this.lang.division="영어"
            }
            else if(event==="HSK"){
                this.lang.division="중국어"
            }
            else if(event==="JLPT"){
                this.lang.division="일본어"
            }
            else{
                this.lang.division=""
            }

            if(this.lang.exam === "TOEIC"){
                    this.type="toeic"
                    this.place="토익 점수를 입력하세요"
                    this.view=true
                }
                else if(this.lang.exam==="TEPS"){
                    this.type="teps"
                    this.place="텝스 점수를 입력하세요"
             
                    this.view=true
                }
                else if(this.lang.exam==="TOEFL Reading" ||this.lang.exam==="TOEFL Writing" ||this.lang.exam==="TOEFL Listening" || this.lang.exam==="TOEFL Speaking"){
                    this.type="toefl"
                    this.place="토플 점수를 입력하세요"
                
                    this.view=true
                }
                else if(this.lang.exam==="JLPT"){
                    this.view=true
                    this.type="jlpt"
                }
                else if(this.lang.exam==="OPIC"){
                    this.view=true
                    this.type="opic"
                }
                else if(this.lang.exam==="HSK"){
                    this.type="hsk"
                    this.view=true
                }
                else if(this.lang.exam===""){
                    this.view=false
                }
                else{
                    this.view=false
                    alert("없는 시험입니다.")
                }

        },
        onSubmit(event) {
            event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                //어학등록
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
                    this.$router.push({path:'/LanguageTest'})
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })

    },
    onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.exam= '',       
                 this.content='',    
                 this.division='',  
                 this.date='',      
                 this.exam_score='', 

                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            },
            // scorecheck(){
            //     if(this.lang.exam === "TOEIC"){
            //         this.type="toeic"
            //         this.place="토익 점수를 입력하세요"
            //         this.view=true
            //     }
            //     else if(this.lang.exam==="TEPS"){
            //         this.type="teps"
            //         this.place="텝스 점수를 입력하세요"
             
            //         this.view=true
            //     }
            //     else if(this.lang.exam==="TOEFL Reading" ||this.lang.exam==="TOEFL Writing" ||this.lang.exam==="TOEFL Listening" || this.lang.exam==="TOEFL Speaking"){
            //         this.type="toefl"
            //         this.place="토플 점수를 입력하세요"
                
            //         this.view=true
            //     }
            //     else if(this.lang.exam==="HSK" ||this.lang.exam==="JLPT"||this.lang.exam==="OPIC"){
            //         this.place="등급을 입력하세요"
            //         this.view=true
            //         this.type="string"
            //     }
            //     else{
            //         this.view=false
            //         alert("없는 시험입니다.")
            //     }
            // }
    }
}
</script>
  <style scoped="scoped">
    #content {
        width: 50%;
        height: 100px;
        resize: none;
    }
    #agency,#division, #exam, #exam_score {
        width: 50%;
    }
    #title,
    #year {
        width: 20%;
    }
    .el-button{
        font-size:1pt;
        
        
    }
</style>