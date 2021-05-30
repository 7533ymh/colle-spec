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
     <b-container fluid="fluid" class="mt--0" id= "header" style="margin-left : 185px;">
        <b-row>
            <b-col xl="7" class="mb-xl-">
                <card header-classes="bg-transparent">
                    <b-row align-v="center" slot="header">

                        <b-col>
                            
                        </b-col>
                    </b-row>




                     <!-- 폼시작 -->
        <b-form @submit.prevent="onSubmit" v-if="show">
            <b-form-group id="input-group-1" label="자격증이름" label-for="input-1">
                    <!-- <b-form-input
                        id="title"
                        v-model="certificate.title"
                        type="text"
                        placeholder="예) 컴퓨터활용능력1급(2급), 정보처리기사, 정보처리산업기사"
                        required="required"></b-form-input> -->
                        <b-form-select @change="optionclick" id="title" name="select">
                            <option value='null' disabled selected>자격증 선택</option>
                            <option v-for="(item, index) in cert" :key="index" :value="item">{{ item }}</option>
                        </b-form-select>
                </b-form-group>
                   <b-form-group id="input-group-1" label="내용" label-for="input-1">
                    <b-form-textarea
                        id="content"
                        v-model="certificate.content"
                        type="text"
                        placeholder="내용을 입력하세요"
                        required="required"></b-form-textarea>
                </b-form-group>

            <b-form-group id="input-group-1" label="발행기관" label-for="input-1">
                <b-form-input
                    id="publisher"
                    v-model="certificate.publisher"
                    type="text"
                    placeholder="발행기관을 입력하세요"
                    required="required"></b-form-input>

                
                <b-form-group id="input-group-1" label="취득일자" label-for="input-1" style="margin-left : 0%; margin-top : 15px;">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="certificate.date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                <!-- 폼끝 -->

                <b-button type="submit" variant="primary">제출</b-button>
                <b-button @click="onReset" type="reset" variant="danger">초기화</b-button>
            </b-form-group>
        </b-form>
        <!-- <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ certificate.title }}</pre>
            </b-card> -->
      
    

                    




                </card>
            </b-col>
        </b-row>
    </b-container>
    
    </div>
</template>
<script>
import axios from 'axios';
import store from '@/store';
import cert from './certificate'
let url=store.state.resourceHost; //서버주소 api
export default {
    mounted(){
        console.log(this.cert)
    },
    data(){
        return{
            certificate: {
                    title: '',      //자격증 이름
                    content: '',    //자격증 내용
                    publisher:'',   //발급기관
                    date:'',        //취득날짜
                },
                title:'',
                show:true,
                cert,
        }
    },
    methods:{
        optionclick(event){
            this.certificate.title=event;
            console.log(this.certificate.title);
        },
        onSubmit(event) {
                event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
        
                var certificate = new URLSearchParams();
                certificate.append('title', this.certificate.title);
                certificate.append('content', this.certificate.content);
                certificate.append('publisher', this.certificate.publisher);
                certificate.append('date', this.certificate.date);
                axios.post(`${url}/certificate`,certificate)
                .then(certificate=>{
                    console.log(certificate)
                    alert(certificate.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })
    },
    onReset() {
                // Reset our form values
                this.certificate.agency = ''
                this.certificate.content = ''
                this.certificate.division = ''
                this.certificate.year =''
                this.certificate.title =''                
            },
    }
}
</script>
<style scoped="scoped">
    /*#content {
        width: 50%;
        height: 100px;
        resize: none;
    }
    #agency,#division {
        width: 50%;
    }
    #title,
    #year, #start_date, #end_date,#publisher{
        width: 20%;
    }*/
    #input-group-1{
        width : 50%;
        margin-left : 10%;
    }
    #content{
        height : 300px;
    }
    
</style>