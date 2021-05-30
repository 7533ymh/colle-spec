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




                            <b-form @submit="onSubmit" @reset="onReset" v-if="show" style="width : 50%; margin-left : 10%;">
                                    <b-form-group id="input-group-1" label="프로젝트제목" label-for="input-1">
                                            <b-form-input
                                                id="title"
                                                v-model="project.title"
                                                type="text"
                                                placeholder="제목입력"
                                                required="required"></b-form-input>
                                        </b-form-group>

                                        <b-form-group id="input-group-1" label="프로젝트내용" label-for="input-1">
                                            <b-form-textarea
                                                style="height:300px;"
                                                id="content"
                                                v-model="project.content"
                                                placeholder="교육내용을 입력하세요"
                                                required="required"></b-form-textarea>
                                        </b-form-group>

                                        <b-form-group label="성공여부" v-slot="{ ariaDescribedby }">
                                            <b-form-radio v-model="project.success" :aria-describedby="ariaDescribedby" name="someradios" value=1>구현성공</b-form-radio>
                                            <b-form-radio v-model="project.success" :aria-describedby="ariaDescribedby" name="someradios" value=0>구현실패</b-form-radio>
                                        </b-form-group>

                                        <b-form-group id="input-group-1" label="시작년도" label-for="input-1">
                                        <div id="span_date">
                                            <b-form-input
                                                id="year"
                                                v-model="project.start_date"
                                                type="date"
                                                required="required"></b-form-input>
                                        </div>
                                        </b-form-group>
                                        <b-form-group id="input-group-1" label="마감년도" label-for="input-1">
                                        <div id="span_date">
                                            <b-form-input
                                                id="year"
                                                v-model="project.end_date"
                                                type="date"
                                                required="required"></b-form-input>
                                        </div>
                                        </b-form-group>
                                        <div>
                                        <b-form-file multiple v-model="project.files" placeholder="파일 업로드">
                                                <template slot="file-name" slot-scope="{ names }">
                                                    <b-badge variant="dark">{{ names[0] }}</b-badge>
                                                    <b-badge v-if="names.length > 1" variant="dark" class="ml-1">
                                                    + {{ names.length - 1 }} More files
                                                    </b-badge>
                                                </template>
                                            </b-form-file>
                                        </div>
                                        <br>
                                        <div>
                                        <b-button router-link to="/PortfolioAndDocument" type="submit" variant="primary">제출</b-button>
                                        <b-button type="reset" variant="danger">초기화</b-button>
                                        </div>
                                        <br>
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
            project: {
                    title: '',      //제목
                    content: '',    //내용
                    success:'',     //성공여부 0 구현실패 1구현성공
                    start_date:'',  //시작날
                    end_date:'',    //종료날
                    files:[]        //프로젝트 파일 저장
                },
                show:true,
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                //수상 작성
                var project = new FormData(); //파일업로드가 포함되어 formdata를 이용한다
    
                project.append('title', this.project.title);
                project.append('success', this.project.success);
                project.append('content', this.project.content);
                project.append('start_date', this.project.start_date);
                project.append('end_date', this.project.end_date);
                 for (var i = 0; i < this.project.files.length; i++) {
                project.append('files', this.project.files[i]);
                 }
                axios.post(`${url}/project`,project,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(project=>{
                    console.log(project.data.msg)
                    alert(project.data.msg)
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
