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



                    <div style="padding-left: 100px; width:400px;">
                        <!-- 파일등록부분 -->
                         <b-form-group id="input-group-1" label="자기소개서 파일 첨부" label-for="input-1">
                            <b-form-file   multiple v-model="introduction.files" placeholder="pdf, hwp, doc, ppt.">
                                    <template slot="file-name" slot-scope="{ names }">
                                        <b-badge variant="dark">{{ names[0] }}</b-badge>
                                        <b-badge v-if="names.length > 1" variant="dark" class="ml-1">
                                            + {{ names.length - 1 }} More files
                                        </b-badge>
                                    </template>
                            </b-form-file>
                         </b-form-group>
                        <!-- 파일등록 끝 -->
                        <div style="margin-top:20px;">
                                <b-button router-link to="/Self_Introduction" @click="onSubmit" variant="primary">제출</b-button>
                        </div>
                    </div>





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
           introduction:{
                files:[] //자기소개서 파일 저장
            }, 
                show:true,
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                
                var introduction = new FormData();
                for (var i = 0; i < this.introduction.files.length; i++) {
                        introduction.append('files', this.introduction.files[i]);
                        }
                        axios.post(`${url}/introduction/uplode`,introduction,{
                            headers:{
                                'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                            }
                        })
                .then( response => {
                    console.log(response.data)
                    alert(response.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err.response.data.msg)
                    alert(err.response.data.msg);
                });
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