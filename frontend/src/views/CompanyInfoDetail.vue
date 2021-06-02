<template>
  <div>
        <base-header class="pb-4 pt-md-6 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="4">
                    <stats-card id="card" title="기업정보" type="gradient-red" :sub-title="odata.name" class=""></stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
    <b-row>
        <b-col xl="12" class="mb-5 mb-xl-0">
            <card header-classes="bg-transparent">

                <div>
                    <div id="contents">
                        <h1><a :href="odata.url" target="place">{{odata.name}}</a></h1>
                        <h3>대표자<br>{{odata.representative}}</h3>
                        <h3>구분<br>{{odata.division}}</h3>
                        
                        
                        <h3>산업<br>{{odata.industry}}</h3>
                        
                        <h3>주요사업<br>{{odata.bussiness}}</h3>
                        <h3>4대보험<br>{{odata.insurance}}</h3>
                                                
                    </div>
                    <!---content끝-->
                    <!--contents2-->
                    <div id="contents2">
                        <h3>사원수: {{odata.people}}</h3>
                        <h3>자본금: {{odata.capital}}</h3>
                        <h3>매출액: {{odata.take}}</h3>
                        <h3>대졸초임: {{odata.salary}}</h3>
                        <h3>설립일: {{odata.establishment}}</h3>
                        <h3>주소: {{odata.address}}</h3>
                    </div>
            </div>

            <!-- 프로그레스바 리스트 들고오기 -->
                            <div>
                                <div id="contents3">
                                    
                                    <h3>{{odata.name}}
                                        합격자와의 비교</h3>
                                    <!-- <p>변경 {{data}}</p> <p>원본 {{odata}}</p> -->

                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[0]" :value="data[0]" >
                                            <span>수상:
                                                <strong>{{msg[0]}}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[1]" :value="data[1]">
                                            <span>경력:
                                                <strong>{{msg[1] }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[2]" :value="data[2]">
                                            <span>자격증:
                                                <strong>{{msg[2] }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[3]" :value="data[3]">
                                            <span>교육이수:
                                                <strong>{{msg[3] }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[4]" :value="data[4]">
                                            <span>해외경험:
                                                <strong>{{msg[4]}}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[5]" :value="data[5]">
                                            <span>어학:
                                                <strong>{{msg[5] }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>
                                    <b-progress id="prog" :max="max" height="2rem" >
                                        <b-progress-bar :variant="color[6]" :value="data[6]">
                                            <span>학점:
                                                <strong>{{msg[6] }}</strong>
                                            </span>
                                        </b-progress-bar>
                                    </b-progress>

                               
                                </div> <!---content끝-->

                                <div id="contents4" mt-4>
                                    <h3>한줄 평가</h3>
                                    <h4>{{message[0]}}<router-link to="/Competition1">&nbsp;&nbsp;&nbsp;{{adv[0]}}</router-link></h4>
                                    <h4>{{message[1]}}<a :href="advurl[0]" target="_blank">&nbsp;&nbsp;&nbsp;{{adv[1]}}</a></h4>
                                    <h4>{{message[2]}} <a :href="advurl[1]" target="_blank">&nbsp;&nbsp;&nbsp;{{adv[2]}}</a></h4>
                                    <h4>{{message[3]}} <router-link to="/CompetitionProgram">&nbsp;&nbsp;&nbsp;{{adv[3]}}</router-link></h4>
                                    <h4>{{message[4]}} <a :href="advurl[2]" target="_blank">&nbsp;&nbsp;&nbsp;{{adv[4]}}</a></h4>
                                    <h4>{{message[5]}}<router-link to="/languageinfo">&nbsp;&nbsp;&nbsp;{{adv[5]}}</router-link></h4>
                                    <h4>{{message[6]}}</h4>
                                </div>

                            </div>

        </card>
    </b-col>
</b-row>
<!-- End charts-->
</b-container>      
  </div>
</template>
<script>
    let url = store.state.resourceHost;

    import axios from 'axios';
    import store from '@/store';

    // Components
    import BaseProgress from '@/components/BaseProgress';
    import StatsCard from '@/components/Cards/StatsCard';


  import { Table, TableColumn, Button} from 'element-ui'

    export default {
        components: {
            BaseProgress,
            StatsCard,
           
            [Table.name]: Table,
      [TableColumn.name]: TableColumn,
      [Button.name]: Button,
        },
        data() {
            
            return {
                max: 5,
                
                odata:JSON.parse(localStorage.getItem("companyinfo")),
                data:this.$route.query.data,
                color:this.$route.query.color,
                adv:this.$route.query.adv,
                message:this.$route.query.message,
                msg:this.$route.query.msg,
                advurl:this.$route.query.advurl
            }
        },
        mounted(){
            console.log('data:',this.data)
            console.log('odata:',this.odata)
            console.log('color:',this.color)
            console.log('adv:',this.adv)
            console.log('message:',this.message)
            console.log('msg:',this.msg)
            console.log('advurl:',this.advurl)
     
        }
    }
</script>
<style style lang="scss" scoped>



    #contents {
        /* border: 1px solid #487BE1; */
        width: 70%;
        float: left;
        padding: 10px;

    }
     #contents2 {
        /* border: 1px solid #487BE1; */
        width: 30%;
        float: left;
        padding: 10px;

    }
    #contents3{
       width: 55%;
        float: left;
        padding: 10px; 
    }
     #contents4{
    

        width: 40%;
        float: left;
        padding: 10px;
        margin-left: 10px;

    }
//     #card{
//     margin-left: 100%;
//     width: 100%;
//     text-align: center;
// }
</style>