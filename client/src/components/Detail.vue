<template>
    <div>
        <b-form @submit="edit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="프로젝트제목" label-for="input-1">
                    <b-form-input
                        id="title"
                        v-model="detail.title"
                        type="text"
                        placeholder="제목입력"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-1" label="프로젝트내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="detail.content"
                        placeholder="교육내용을 입력하세요"
                        required="required"></textarea>
                </b-form-group>

                <b-form-group label="성공여부" v-slot="{ ariaDescribedby }">
                    <b-form-radio v-model="detail.success" :aria-describedby="ariaDescribedby" name="someradios" value=1>구현성공</b-form-radio>
                    <b-form-radio v-model="detail.success" :aria-describedby="ariaDescribedby" name="someradios" value=0>구현실패</b-form-radio>
                </b-form-group>

                <b-form-group id="input-group-1" label="시작년도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="detail.start_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                <b-form-group id="input-group-1" label="마감년도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="detail.end_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                <div>
                   <b-form-file multiple v-model="detail.files">
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
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
                </div>
        </b-form>
        <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ detail }}</pre>
            </b-card>
        </div>
        
</template>
<script>
import axios from 'axios';
import store from '../store';
import {eventBus} from '../main.js'

let url=store.state.resourceHost; //서버주소 api
export default {
        data() {
            return {
                detail:{
                    idx:null,
                    title:'',
                    content:'',
                    success:'',
                    start_date:'',
                    end_date:'',
                    files:[]
                    }
                ,
                myproject:[{}]
                ,
                show:true
            }
        },
          created(){
                eventBus.$on('senddata',function(value){
                    this.detail.idx=value.idx
                    this.detail.title=value.title
                    this.detail.content=value.content
                    this.detail.success=value.success
                    this.detail.start_date=value.start_date
                    this.detail.end_date=value.end_date
                    // this.detail.files=value.project_imgList
                    //this.detail=value
                    console.log('detail 값: ', this.detail)

                        console.log('idx 값: ', this.detail.idx)
                        console.log('title 값: ', this.detail.title)
                        console.log('content 값: ', this.detail.content)
                        console.log('success 값: ', this.detail.success)
                        console.log('start_date 값: ', this.detail.start_date)
                        console.log('end_Date 값: ', this.detail.end_date)
                        // for (var i = 0; i < this.value.project_imgList[i].filename.length; i++) {
                        //     console.log('detail.files 값: ', this.detail.files[i])
                        //  } 
                }.bind(this));
                console.log('idxxxxx:',this.detail.idx)
          
                },
                
        methods: {
            //프로젝트내용작성
            edit(event) {
        
                event.preventDefault() //submit버튼 클릭시 초기화되지않도록
                var params = new FormData(); //파일업로드가 포함되어 formdata를 이용한다
                params.append('idx',this.detail.idx);
                params.append('title', this.detail.title);
                params.append('success', this.detail.success);
                params.append('content', this.detail.content);
                params.append('start_date', this.detail.start_date);
                params.append('end_date', this.detail.end_date);
                 for (var i = 0; i < this.detail.files.length; i++) {
                params.append('files', this.detail.files[i]);
                 }
                axios.put(`${url}/project`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(project=>{
                    console.log(project.data.msg)
                    alert(project.data.msg)
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })
            },
            
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.detail.title = ''
                this.detail.content = ''
                this.detail.start_date = ''
                this.detail.end_date =''
                this.detail.success =''
                this.detail.files=''

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
    #agency {
        width: 50%;
    }
    #title,
    #year {
        width: 20%;
    }
</style>
