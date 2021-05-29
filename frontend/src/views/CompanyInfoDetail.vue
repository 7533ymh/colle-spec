<template>
  <div>
        <base-header class="pb-4 pt-md-6 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="3">
                    <stats-card title="기업정보" type="gradient-red" :sub-title="info.name" class=""></stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
    <b-row>
        <b-col xl="10" class="mb-5 mb-xl-0">
            <card header-classes="bg-transparent">

                <div>
                    <div id="contents">
                        <h1><a :href="info.url" target="place">{{info.name}}</a></h1>
                        <h3>대표자<br>{{info.representative}}</h3>
                        <h3>구분<br>{{info.division}}</h3>
                        
                        
                        <h3>산업<br>{{info.industry}}</h3>
                        
                        <h3>주요사업<br>{{info.bussiness}}</h3>
                        <h3>4대보험<br>{{info.insurance}}</h3>
                                                
                    </div>
                    <!---content끝-->
                    <!--contents2-->
                    <div id="contents2">
                        <h3>사원수: {{info.people}}</h3>
                        <h3>자본금: {{info.capital}}</h3>
                        <h3>매출액: {{info.take}}</h3>
                        <h3>대졸초임: {{info.salary}}</h3>
                        <h3>설립일: {{info.establishment}}</h3>
                        <h3>주소: {{info.address}}</h3>
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
                info:JSON.parse(localStorage.getItem("companyinfo"))
            }
        },
    }
</script>
<style>



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
</style>