<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="자격증이름" label-for="input-1">
                    <b-form-input
                        id="title"
                        v-model="certificate.title"
                        type="text"
                        placeholder="예) 컴퓨터활용능력1급(2급), 정보처리기사, 정보처리산업기사"
                        required="required"></b-form-input>
                </b-form-group>
                   <b-form-group id="input-group-1" label="내용" label-for="input-1">
                    <b-form-input
                        id="content"
                        v-model="certificate.content"
                        type="text"
                        placeholder="예)내용 입력 .."
                        required="required"></b-form-input>
                </b-form-group>

            <b-form-group id="input-group-1" label="발행기관" label-for="input-1">
                <b-form-input
                    id="publisher"
                    v-model="certificate.publisher"
                    type="text"
                    placeholder="발행기관을 입력하세요"
                    required="required"></b-form-input>

                
                <b-form-group id="input-group-1" label="취득일자" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="certificate.date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                

                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form-group>
            </b-form>
            <!-- <b-form @submit="getcertificate" v-if="show">
                <b-button type="button" variant="primary" @click="getcertificate">조회하기</b-button>
            </b-form>   -->
            <div>
                <b-table responsive="sm" striped :fields="fields" hover :items="mycertificate" @row-clicked="click">
                  <template #cell(edit&Del)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    수정
                    </b-button>
                    <b-button size="sm" @click="deleteCert(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ certificate }}</pre>
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
                certificate: {
                    title: '',
                    content: '',
                    publisher:'',
                    date:'',
                    score:''
                },
                fields:['title','content','publisher','date','score','edit&Del'],
                mycertificate:[{}]
                ,
                show:true
            }
        },
          created(){
                    axios.get(`${url}/certificate`)
                    .then(get=>{
                    this.mycertificate=get.data.list
                    console.log('mycertificate: ',this.mycertificate)
                })
                },
                
        methods: {
            //자격증업로드
            onSubmit(event) {
                event.preventDefault()
                var params = new URLSearchParams();
                params.append('title', this.certificate.title);
                params.append('content', this.certificate.content);
                params.append('publisher', this.certificate.publisher);
                params.append('date', this.certificate.date);
                axios.post(`${url}/certificate`,params)
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
            //자격증조회
            // getcertificate(){
            //     axios.get(`${url}/certificate`)
            //     .then(get=>{
            //         console.log('get.data:',get.data)
            //         console.log('get.data.list:',get.data.list)
            //     })
            // },
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.title = ''
                this.content = ''
                this.publisher = ''
                this.date =''

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
                params.append('title', this.certificate.title);
                params.append('content', this.certificate.content);
                params.append('publisher', this.certificate.publisher);
                params.append('date', this.certificate.date);
                axios.put(`${url}/certificate`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
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
            deleteCert(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/certificate`,{params:{
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
    #publisher,#title {
        width: 50%;
    }
    #year {
        width: 20%;
    }
</style>
