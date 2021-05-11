<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="경력회사" label-for="input-1">
                <b-form-input
                    id="company"
                    v-model="career.company"
                    type="text"
                    placeholder="회사이름을 입력하세요"
                    required="required"></b-form-input>

                <b-form-group id="input-group-1" label="경력내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="career.content"
                        placeholder="내용을 입력하세요"
                        required="required"></textarea>
                </b-form-group>
                <b-form-group id="input-group-1" label="경력부서" label-for="input-1">
                    <b-form-input
                        id="department"
                        v-model="career.department"
                        type="text"
                        placeholder="부서를 입력하세요"
                        required="required"></b-form-input>
                </b-form-group>
                <b-form-group id="input-group-1" label="경력구분" label-for="input-1">
                    <b-form-input
                        id="division"
                        v-model="career.division"
                        type="text"
                        placeholder="구분"
                        required="required"></b-form-input>
                </b-form-group>
                <div id="span_date">
                    <b-form-input
                        id="start_date"
                        v-model="career.start_date"
                        type="date"
                        placeholder="날짜 입력하세요"
                        required="required"></b-form-input>
                    <b-form-input
                        id="end_date"
                        v-model="career.end_date"
                        type="date"
                        placeholder="날짜 입력하세요"
                        required="required"></b-form-input>
                </div>
                <b-button type="submit" variant="primary">Submit</b-button>

                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form-group>
            </b-form>
            <!-- <b-form @submit="getcareer" v-if="show">


                <b-button type="button" variant="primary" @click="getcareer">조회하기</b-button>
            </b-form>   -->
            <div>
              <b-table responsive="sm" striped :fields="fields" hover :items="mycareer" @row-clicked="click">
                  <template #cell(edit&Del)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    수정
                    </b-button>
                    <b-button size="sm" @click="deleteCareer(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ career }}</pre>
            </b-card>
        </div>
</template>

<script>
import axios from 'axios';
import store from '../store';


let url=store.state.resourceHost;
export default {
        data() {
            return {
                career: {
                    company: '',
                    content: '',
                    department: '',
                    division:'',
                    start_date:'',
                    end_date:'',
                    score:'',
                },
                fields:['company','content','department','division','score','start_date','end_date','edit&Del'],
                mycareer:[{}]
                ,
                show:true
            }
        },
          created(){
                    axios.get(`${url}/career`)
                    .then(get=>{
                    this.mycareer=get.data.list
                    console.log('mycareer: ',this.mycareer)
                })
                },
                
        methods: {
            //경력작성
            onSubmit(event) {
                event.preventDefault()
                var params = new URLSearchParams();
                params.append('company', this.career.company);
                params.append('content', this.career.content);
                params.append('department', this.career.department);
                params.append('division', this.career.division);
                params.append('end_date', this.career.end_date);
                params.append('start_date', this.career.start_date);
                axios.post(`${url}/career`,params)
                .then(career=>{
                    console.log(career.data.msg)
                    console.log(career)
                    alert(career.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                })
            },
            //경력조회
            // getcareer(){
            //     axios.get(`${url}/career`)
            //     .then(get=>{
            //         console.log('get.data:',get.data)
            //         console.log('get.data.list:',get.data.list)
            //     })
            // },
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.career.company = ''
                this.career.content = ''
                this.career.department = ''
                this.career.division =''
                this.career.start_date =''
                this.career.end_date =''

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
                params.append('company', this.career.company);
                params.append('content', this.career.content);
                params.append('department', this.career.department);
                params.append('division', this.career.division);
                params.append('end_date', this.career.end_date);
                params.append('start_date', this.career.start_date);
                axios.put(`${url}/career`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(career=>{
                    console.log(career)
                    alert(career.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })    
            },
            deleteCareer(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/career`,{params:{
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
    #company,#department,#division {
        width: 50%;
    }
    #end_date,
    #start_date {
        width: 20%;
    }
</style>
