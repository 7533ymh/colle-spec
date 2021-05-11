<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="교육제목" label-for="input-1">
                    <b-form-input
                        id="title"
                        v-model="edu.title"
                        type="text"
                        placeholder="제목입력"
                        required="required"></b-form-input>
                </b-form-group>

            <b-form-group id="input-group-1" label="교육기관" label-for="input-1">
                <b-form-input
                    id="agency"
                    v-model="edu.agency"
                    type="text"
                    placeholder="교육기관을 입력하세요"
                    required="required"></b-form-input>

                <b-form-group id="input-group-1" label="교육내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="edu.content"
                        placeholder="교육내용을 입력하세요"
                        required="required"></textarea>
                </b-form-group>
                
                <b-form-group id="input-group-1" label="시작년도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="edu.start_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                <b-form-group id="input-group-1" label="마감년도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="edu.end_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>

                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form-group>
            </b-form>
            <!-- <b-form @submit="getedu" v-if="show">
                <b-button type="button" variant="primary" @click="getedu">조회하기</b-button>
            </b-form>   -->
            <div>
                <b-table responsive="sm" striped :fields="fields" hover :items="myedu" @row-clicked="click">
                  <template #cell(edit&Del)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    수정
                    </b-button>
                    <b-button size="sm" @click="deleteEdu(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ edu }}</pre>
            </b-card>
        </div>
</template>

<script>
import axios from 'axios';
import store from '../store';


let url=store.state.resourceHost; //서버주소 api
export default {
        data() {
            return {
                edu: {
                    agency: '',
                    content: '',
                    title:'',
                    start_date:'',
                    end_date:'',
                    score:''
                },
                fields:['title','agency','content','start_date','end_date','score','edit&Del'],
                myedu:[{}]
                ,
                show:true
            }
        },
          created(){
                    axios.get(`${url}/education`)
                    .then(get=>{
                    this.myedu=get.data.list
                    console.log('myedu: ',this.myedu)
                })
                },
                
        methods: {
            //교육내용작성
            onSubmit(event) {
                event.preventDefault()
                var params = new URLSearchParams();
                params.append('title', this.edu.title);
                params.append('agency', this.edu.agency);
                params.append('content', this.edu.content);
                params.append('start_date', this.edu.start_date);
                params.append('end_date', this.edu.end_date);
                axios.post(`${url}/education`,params)
                .then(edu=>{
                    console.log(edu)
                    alert(edu.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })
            },
            //교육내용조회
            // getedu(){
            //     axios.get(`${url}/education`)
            //     .then(get=>{
            //         console.log('get.data:',get.data)
            //         console.log('get.data.list:',get.data.list)
            //     })
            // },
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.agency = ''
                this.content = ''
                this.start_date = ''
                this.end_date =''
                this.title =''

                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            },
            click(item,index,e){
                console.log('index: ',index)
                console.log('item: ',item)
            },
            edit(item,index,event) {
                var params = new URLSearchParams(); //파일업로드가 포함되어 formdata를 이용한다
                params.append('title', this.edu.title);
                params.append('agency', this.edu.agency);
                params.append('content', this.edu.content);
                params.append('start_date', this.edu.start_date);
                params.append('end_date', this.edu.end_date);
                axios.put(`${url}/education`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(edu=>{
                    console.log(edu)
                    alert(edu.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })    
            },
            deleteEdu(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/education`,{params:{
                    idx:del
                }})
                .then(res=>{
                    alert(res.data.msg)
                })
                .catch(err=>{
                    alert(err.response.data.msg)
                })
                console.log('delitem: ',item)

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
