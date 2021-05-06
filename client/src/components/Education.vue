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
            <b-form @submit="getedu" v-if="show">
                <b-button type="button" variant="primary" @click="getedu">조회하기</b-button>
            </b-form>  
            <div>
              <b-table striped hover :items="myedu"></b-table>
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
                },
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
            //수상내용작성
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
                    console.log(data.msg)
                    console.log(edu)
                    alert(edu.data.msg)
                })
                .catch(err=>{
                    console.log(err)
                    alert("등록 실패")

                })
            },
            //수상조회
            getedu(){
                axios.get(`${url}/education`)
                .then(get=>{
                    console.log('get.data:',get.data)
                    console.log('get.data.list:',get.data.list)
                })
            },
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
            }
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
