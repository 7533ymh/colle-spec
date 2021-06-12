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
     <b-container fluid="fluid" class="mt--0"  style="margin-left : 185px;" >
        <b-row>
            <b-col xl="7" class="mb-xl-">
                <card header-classes="bg-transparent">
                    <b-row align-v="center" slot="header">

                        <b-col>
                            
                        </b-col>
                    </b-row>



                     <!-- 폼시작 -->
                    <b-form @submit.prevent="onSubmit"  v-if="show"  >
                            <b-form-group id="input-group-1" label="학점" label-for="input-1">
                                <b-form-input 
                                    id="avg_score"
                                    v-model="grade.avg_score"
                                    type="number"
                                    min=0
                                    max=4.5
                                    step="0.01"
                                    placeholder="학점을 입력하세요"
                                    required="required"></b-form-input>

                            <b-form-group id="input-group-1" label="학년선택" label-for="input-1" style="margin-top:10px;">
                               <b-form-select @change="optionclick" id="grade" name="select" v-model="grade.grade"  required="required" >
                                    
                                    <option value=null disabled selected >학년 선택</option>
                                    <option v-for="(item, index) in gradejs[0]" :key="index" :value="item">{{ item }}</option>
                                </b-form-select>
                            </b-form-group>

                                <b-form-group class="semester" id="input-group-1" label="학기" label-for="input-1">

                                <b-form-select @change="optionclick2" id="semester" name="select" v-model="grade.semester"  required="required" >
                                    
                                    <option value=null disabled selected >학기 선택</option>
                                    <option v-for="(item, index) in gradejs[1]" :key="index" :value="item">{{ item }}</option>
                                </b-form-select>

 
                            </b-form-group>
                            <!-- 폼끝 -->
                            <b-button type="submit" variant="primary">제출</b-button>
                            <b-button @click="onReset" type="reset" variant="danger">초기화</b-button>
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
import gradejs from '../js/grade.js';


let url=store.state.resourceHost; //서버주소 api
export default {
    data(){
        return{
            grade: {
                    avg_score: '', //평점
                    grade: '',    //학년
                    semester:'',   //학기
               
                },
                show:true,
                gradejs
        }
    },
    methods:{
        onSubmit(event) {
                event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                var grade = new URLSearchParams();
                grade.append('avg_score', this.grade.avg_score);
                grade.append('grade', this.grade.grade);
                grade.append('semester', this.grade.semester);
               
                axios.post(`${url}/grade`,grade)
                .then(res=>{
                    console.log(res)
                    alert(res.data.msg)
                    this.$router.push({path:'/Grade'})
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })

    },
    optionclick(event){
        this.grade.grade=event;
            console.log(this.grade.grade);
    },
    optionclick2(event){
        this.grade.semester=event;
            console.log(this.grade.semester);
    },
    onReset() {
                // Reset our form values
                this.grade.avg_score = ''
                this.grade.grade = ''
                this.grade.semester = ''
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
    #avg_score {
        width: 50%;
    }
    #grade,#semester {
        width: 50%;
    }
</style>