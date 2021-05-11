<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="수여기관" label-for="input-1">
                <b-form-input
                    id="agency"
                    v-model="award.agency"
                    type="text"
                    placeholder="수여기관을 입력하세요"
                    required="required"></b-form-input>

                <b-form-group id="input-group-1" label="내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="award.content"
                        placeholder="내용을 입력하세요"
                        required="required"></textarea>
                </b-form-group>
                <b-form-group id="input-group-1" label="상 구분" label-for="input-1">
                    <b-form-input
                        id="division"
                        v-model="award.division"
                        type="text"
                        placeholder="예)대상 최우수상 우수상 금상 은상 동상 장려상"
                        required="required"></b-form-input>
                </b-form-group>
                <b-form-group id="input-group-1" label="수상명" label-for="input-1">
                    <b-form-input
                        id="title"
                        v-model="award.title"
                        type="text"
                        placeholder="상 이름을 입력하세요"
                        required="required"></b-form-input>
                </b-form-group>
                <b-form-group id="input-group-1" label="수상연도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="award.year"
                        type="date yyyy"
                        placeholder="날짜 입력하세요"
                        required="required"></b-form-input>
                </div>
                </b-form-group>

                    
                <b-button type="submit" variant="primary">Submit</b-button>

                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form-group>
            </b-form>
            <!-- <b-form @submit="getaward" v-if="show">
                <b-button type="button" variant="primary" @click="getaward">조회하기</b-button>
            </b-form>   -->
            <div>
                <b-table responsive="sm" striped :fields="fields" hover :items="myaward" @row-clicked="click">
                  <template #cell(edit&Del)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    수정
                    </b-button>
                    <b-button size="sm" @click="deleteAward(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ award }}</pre>
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
                award: {
                    agency: '',
                    content: '',
                    division:'',
                    title:'',
                    year:'',
                    score:'',
                },
                fields:['title','content','agency','division','year','score','edit&Del'],
                myaward:[{}]
                ,
                show:true
            }
        },
          created(){
                    axios.get(`${url}/award`)
                    .then(get=>{
                    this.myaward=get.data.list
                    console.log('myaward: ',this.myaward)
                })
                },
                
        methods: {
            //수상내용작성
            onSubmit(event) {
                event.preventDefault()
                var params = new URLSearchParams();
                params.append('agency', this.award.agency);
                params.append('content', this.award.content);
                params.append('division', this.award.division);
                params.append('title', this.award.title);
                params.append('year', this.award.year);
                axios.post(`${url}/award`,params)
                .then(award=>{
                    console.log(award)
                    alert(award.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })
            },
            //수상조회
            // getaward(){
            //     axios.get(`${url}/award`)
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
                this.division = ''
                this.year =''
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
                params.append('agency', this.award.agency);
                params.append('content', this.award.content);
                params.append('division', this.award.division);
                params.append('title', this.award.title);
                params.append('year', this.award.year);
                axios.put(`${url}/award`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(award=>{
                    console.log(award)
                    alert(award.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })    
            },
            deleteAward(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/award`,{params:{
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
