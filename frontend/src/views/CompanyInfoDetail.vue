<template>
  <div>
        <base-header class="pb-4 pt-md-6 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="6" md="6">
                    
                    <stats-card id="card" title="기업정보" type="gradient-red" :sub-title="odata.name" class="mb-4"></stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
    <b-row>
        <b-col xl="12" class="mb-5 mb-xl-0">
            <card header-classes="bg-transparent">

                <div>
                    
                    <h1><a :href="odata.url" target="place">{{odata.name}}</a></h1>
                    

                     <!---content 시작-->
                        <table id="contents" class="table table-bordered " >
                            <colgroup>
                                    <col width="10%" />
                                    <col width="40%" />
                                    <col width="10%" />
                                    <col width="40%" />

                            </colgroup>
                            <tbody >

                                <tr >
                                <td> <span>대표자</span></td>
                                <td> <h3>{{odata.representative}}</h3></td> 
                                <td> <span>자본금</span></td>
                                <td> <h3>{{odata.capital}}</h3></td>

                                </tr>  
                                <tr>
                                <td> <span>구분</span></td>
                                <td> <h3>{{odata.division}}</h3></td>
                                <td> <span>매출액</span></td>
                                <td> <h3>{{odata.take}}</h3></td>

                                </tr>
                                <tr>
                                <td> <span>산업</span></td>
                                <td> <h3>{{odata.industry}}</h3></td>
                                <td> <span>대졸초임</span></td>
                                <td> <h3>{{odata.salary}}</h3></td>

                                </tr>  
                                <tr>
                                <td> <span>사원수</span></td>
                                <td> <h3> {{odata.people}}</h3></td>
                                <td> <span>설립일</span></td>
                                <td> <h3>{{odata.establishment}}</h3></td>

                                </tr>
                                <tr>
                                <td> <span>4대보험</span></td>
                                <td> <h3>{{odata.insurance}}</h3></td>
                                <td> <span>주소</span></td>
                                <td> <h3>{{odata.address}}</h3></td>
                                </tr>           
                            </tbody>
                        </table>
                    </div>
                        <!---content 끝-->

                        <!---content2 시작-->
                        <div>
                            <table id="contents2" class="table table-bordered" >
                            <tbody >
                                <tr >
                                 <td> <span>주요사업 </span></td>
                                 <td> <h3>{{odata.bussiness}}</h3></td> 
                                </tr>  
                            </tbody>
                            </table>
            </div>
                        <!---content2 끝-->

                        
   
            

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
        width: 100%;
        
        padding: 10px;
        table-layout:fixed; 
    }

     #contents2 {
        /* border: 1px solid #487BE1; */
        width: 30%;
        text-align: left;
         
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