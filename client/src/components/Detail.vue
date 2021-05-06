<template>
    <div>
        <b-form @submit="edit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="프로젝트제목" label-for="input-1">
                    <b-form-input
                        id="title"
                        v-model="project.title"
                        type="text"
                        placeholder="제목입력"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-1" label="프로젝트내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="project.content"
                        placeholder="교육내용을 입력하세요"
                        required="required"></textarea>
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
                    <b-form-file v-model="project.files" class="mt-3" plain></b-form-file>
                   <b-form-file multiple v-model="project.files">
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
        </div>
</template>
<script>
import axios from 'axios';
import store from '../store';


let url=store.state.resourceHost; //서버주소 api
export default {
        data() {
            return {
                project: {
                    title: '',
                    content: '',
                    success:'',
                    start_date:'',
                    end_date:'',
                    files:[]
                },
                fields:['idx','title','content','score','success','start_date','end_date','edit&Del'],
                myproject:[{}]
                ,
                show:true
            }
        },
          created(){
                    
                    axios.get(`${url}/project`,{params:{
                        idx:17
                    }})
                    .then(get=>{
                    this.project.title=get.data.list.title
                    this.project.content=get.data.list.content
                    this.project.success=get.data.list.success
                    this.project.start_date=get.data.list.start_date
                    this.project.end_date=get.data.list.end_date
                    this.project.files=get.data.list.files
                    console.log('myproject: ',this.myproject)
                    console.log("get.data:",get.data.list[0])
                })
                },
                
        methods: {
            //프로젝트내용작성
            edit(event) {
                event.preventDefault() //submit버튼 클릭시 초기화되지않도록
                var params = new FormData(); //파일업로드가 포함되어 formdata를 이용한다
                
                params.append('title', this.project.title);
                params.append('success', this.project.success);
                params.append('content', this.project.content);
                params.append('start_date', this.project.start_date);
                params.append('end_date', this.project.end_date);
                 for (var i = 0; i < this.project.files.length; i++) {
                params.append('files', this.project.files[i]);
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
                this.title = ''
                this.content = ''
                this.start_date = ''
                this.end_date =''
                this.success =''
                this.files=''

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
