<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
                <b-form-group id="input-group-1" label="경험나라" label-for="input-1">
                    <b-form-input
                        id="country"
                        v-model="experience.country"
                        type="text"
                        placeholder="예) 미국, 일본, 중국, 호주"
                        required="required"></b-form-input>
                </b-form-group>
                   <b-form-group id="input-group-1" label="내용" label-for="input-1">
                    <b-form-input
                        id="content"
                        v-model="experience.content"
                        type="text"
                        placeholder="예)내용 입력 .."
                        required="required"></b-form-input>
                    </b-form-group>

                
                <b-form-group id="input-group-1" label="시작날짜" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="experience.start_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>

                <b-form-group id="input-group-1" label="종료날짜" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="experience.end_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                

                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
            </b-form>
            <br>
            <b-form @submit="getexperience" v-if="show">
                <b-button type="button" variant="primary" @click="getexperience">조회하기</b-button>
            </b-form>  
            <div>
              <b-table striped hover :items="myexperience"></b-table>
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ experience }}</pre>
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
                experience: {
                    country: '',
                    content: '',
                    start_date:'',
                    end_date:'',
                },
                myexperience:[{}]
                ,
                show:true
            }
        },
          created(){
                    axios.get(`${url}/experience`)
                    .then(get=>{
                    this.myexperience=get.data.list
                    console.log('myexperience: ',this.myexperience)
                })
                },
                
        methods: {
            //자격증업로드
            onSubmit(event) {
                event.preventDefault()
                var params = new URLSearchParams();
                params.append('country', this.experience.country);
                params.append('content', this.experience.content);
                params.append('start_date', this.experience.start_date);
                params.append('end_date', this.experience.end_date);
                axios.post(`${url}/experience`,params)
                .then(experience=>{
                    console.log(experience.msg)
                    console.log(experience)
                    alert(experience.data.msg)
                })
                .catch(err=>{
                    console.log(err)
                    alert("등록 실패")

                })
            },
            //자격증조회
            getexperience(){
                axios.get(`${url}/experience`)
                .then(get=>{
                    console.log('get.data:',get.data)
                    console.log('get.data.list:',get.data.list)
                })
            },
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.country = ''
                this.content = ''
                this.start_date = ''
                this.end_date =''

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
    #country {
        width: 50%;
    }
    #year {
        width: 20%;
    }
</style>
