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
                    <b-form @submit="onSubmit" @reset="onReset" v-if="show" style="width : 50%; margin-left : 10%;" >
                            <b-form-group id="input-group-1" label="수여기관" label-for="input-1">
                                <b-form-input 
                                    id="agency"
                                    v-model="award.agency"
                                    type="text"
                                    placeholder="수여기관을 입력하세요"
                                    required="required"></b-form-input>

                            <b-form-group id="input-group-1" label="내용" label-for="input-1" style="margin-top:10px;">
                                <b-form-textarea 
                                    style=" height : 300px;"
                                    id="content" 
                                    v-model="award.content"
                                    placeholder="내용을 입력하세요"
                                    required="required"></b-form-textarea>
                            </b-form-group>

                                <b-form-group id="input-group-1" label="상 구분" label-for="input-1">

                                <b-form-select @change="optionclick" id="division" name="select" v-model="award.division"  required="required" >

                                    <option value='null' disabled selected >상 선택</option>
                                    <option v-for="(item, index) in AwardJS" :key="index" :value="item">{{ item }}</option>
                                </b-form-select>

                                <!--<b-form-input 
                                    id="division" 
                                    v-model="award.division"
                                    type="text"
                                    placeholder="예)대상 최우수상 우수상 금상 은상 동상 장려상"
                                    required="required"></b-form-input> -->
                            </b-form-group>

                            <b-form-group id="input-group-1" label="수상 명" label-for="input-1">
                                <b-form-input 
                                    id="title"
                                    v-model="award.title"
                                    type="text"
                                    placeholder="상 이름을 입력하세요"
                                    required="required"></b-form-input>
                            </b-form-group>

                            <b-form-group id="input-group-1" label="수상연도" label-for="input-1">
                                <div id="span_date">
                                    <b-form-input 
                                        id="year"
                                        v-model="award.year"
                                        type="number" placeholder="YYYY" min="1900" max="2021"
                                        required="required"></b-form-input>
                                </div>
                            </b-form-group>
                            <!-- 폼끝 -->
                            <b-button router-link to="/Award" type="submit" variant="primary">제출</b-button>
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
import AwardJS from './award.js';


let url=store.state.resourceHost; //서버주소 api
export default {
    data(){
        return{
            award: {
                    agency: '',     //수여기관
                    content: '',    //상 내용
                    division:'',    //상 종류 (셀렉트로 고르도록) 대상, 최우수상, 우수상, 금상, 은상, 동상, 장려상
                    title:'',       //상 이름
                    year:'',        //수여날짜
                },
                show:true,
                AwardJS
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                var award = new URLSearchParams();
                award.append('agency', this.award.agency);
                award.append('content', this.award.content);
                award.append('division', this.award.division);
                award.append('title', this.award.title);
                award.append('year', this.award.year);
                axios.post(`${url}/award`,award)
                .then(award=>{
                    console.log(award)
                    alert(award.data.msg)
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
  <style scoped="scoped">
    #content {
        width: 50%;
        height: 100px;
        resize: none;
    }
    #agency,#division {
        width: 50%;
    }
    #title,
    #year {
        width: 20%;
    }
</style>