<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="어학시험" label-for="input-1">
                    <b-form-input
                        id="exam"
                        v-model="lang.exam"
                        type="text"
                        placeholder="예) toeic, teps, toefl, hsk, opic"
                        required="required"></b-form-input>
                </b-form-group>
                   <b-form-group id="input-group-1" label="어학구분" label-for="input-1">
                    <b-form-input
                        id="division"
                        v-model="lang.division"
                        type="text"
                        placeholder="예) 영어, 중국어, 일본어 .."
                        required="required"></b-form-input>
                </b-form-group>

            <b-form-group id="input-group-1" label="어학점수" label-for="input-1">
                <b-form-input
                    id="exam_score"
                    v-model="lang.exam_score"
                    type="text"
                    placeholder="점수를 입력하세요"
                    required="required"></b-form-input>

                <b-form-group id="input-group-1" label="어학내용" label-for="input-1">
                    <textarea
                        id="content"
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
                

                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form-group>
            </b-form>
            <!-- <b-form @submit="getlang" v-if="show">
                <b-button type="button" variant="primary" @click="getlang">조회하기</b-button>
            </b-form>   -->
            <div>
              <b-table responsive="sm" striped :fields="fields" hover :items="mylang" @row-clicked="click">
                  <template #cell(edit&Del)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    수정
                    </b-button>
                    <b-button size="sm" @click="deletelang(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ lang }}</pre>
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
                lang: {
                    exam: '',
                    content: '',
                    division:'',
                    date:'',
                    exam_score:'',
                },
                fields:['exam','content','division','date','exam_score','edit&Del'],

                mylang:[{}]
                ,
                show:true
            }
        },
          created(){
                    axios.get(`${url}/language`)
                    .then(get=>{
                    this.mylang=get.data.list
                    console.log('mylang: ',this.mylang)
                })
                },
                
        methods: {
            //어학시험작성
            onSubmit(event) {
                event.preventDefault()
                var params = new URLSearchParams();
                params.append('exam', this.lang.exam);
                params.append('content', this.lang.content);
                params.append('division', this.lang.division);
                params.append('exam_score', this.lang.exam_score);
                params.append('date', this.lang.date);
                axios.post(`${url}/language`,params)
                .then(lang=>{
                    console.log(lang)
                    alert(lang.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })
            },
            //어학내용조회
            // getlang(){
            //     axios.get(`${url}/language`)
            //     .then(get=>{
            //         console.log('get.data:',get.data)
            //         console.log('get.data.list:',get.data.list)
            //     })
            // },
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.exam = ''
                this.content = ''
                this.division = ''
                this.date =''
                this.exam_score =''

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
                var params = new URLSearchParams();
                params.append('exam', this.lang.exam);
                params.append('content', this.lang.content);
                params.append('division', this.lang.division);
                params.append('exam_score', this.lang.exam_score);
                params.append('date', this.lang.date);
                axios.put(`${url}/language`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(res=>{
                    console.log(res)
                    alert(res.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })    
            },
            deletelang(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/language`,{params:{
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
    #agency,#division {
        width: 50%;
    }
    #title,
    #year {
        width: 20%;
    }
</style>
