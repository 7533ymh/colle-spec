<template>
  <div>
        <base-header class="pb-7 pt-md-5 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="3" md="6">
                    <stats-card title="" type="gradient-red" sub-title="기업 리스트" class="mb-4"></stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
    <b-row>
        <b-col xl="10" class="mb-5 mb-xl-0">
            <card header-classes="bg-transparent">

                <div>
                    <!-- <div id="wrapper"> -->
                    <div id="contents">
                        <el-table
                            class="table-responsive table"
                            header-row-class-name="thead-light"
                            :data="view"
                            :row-class-name="tableRow"
                            @row-click="detail">

                            <el-table-column label="번호" prop='idx' min-width="15%">
                                <template slot-scope="scope">
                                    {{scope.$index+1}}
                                </template>
                            </el-table-column>

                            <el-table-column label="회사이름" prop="name" min-width="100%">
                                <template slot-scope="scope">
                                    {{scope.row.name}}
                                </template>
                            </el-table-column>

                            <!-- <template slot-scope="scope"> </template> -->
                              <!-- <tr>
                                                <th>회사이름</th>
                                                <th>주소</th>
                                                <th>구분</th>
                                                <th>사원수</th>
                                                
                                                <th>산업</th>
                                                <th>자본금</th>
           
                                            </tr>
                                            <tr>
                                                <td><a :href="odata.url" target="place">{{odata.name}}</a></td>
                                                <td>{{odata.address}}</td>
                                                <td>{{odata.division}}</td>
                                                <td>{{odata.people}}</td>
                                                <td>{{odata.industry}}</td>
                                                <td>{{odata.capital}}</td>
                                            </tr>
                                            <tr>
                                                <th>설립일</th>
                                                <th>주요사업</th>
                                                <th>대표자</th>
                                                <th>대졸초임</th>
                                                <th>4대보험</th>
                                                <th>매출액</th>
                                            </tr>
                                            <tr>
                                                <td>{{odata.establishment}}</td>
                                                <td>{{odata.bussiness}}</td>
                                                <td>{{odata.representative}}</td>
                                                <td>{{odata.salary}}</td>
                                                <td>{{odata.insurance}}</td>
                                                <td>{{odata.take}}</td>                                
                                            </tr> -->

                            
                            <el-table-column fixed="right" label="" width="120px">
                                <template slot-scope="scope">
                                    <el-button @click="detailinfo(scope.row)" size="small">
                                        Detail
                                    </el-button>
                                </template>
                            </el-table-column>

                        </el-table>

                    </div>
                    <!---content끝-->
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
                view: {
                    //각 division(분류)로 조회한 리스트들이 저장되는 곳
                },
            }
        },

        methods: {
            getList1() {
                axios
                    .get(`${url}/companyList`) //회사리스트 조회
                    .then((res) => {
                        console.log(res.data.list);
                        console.log(res.data.list[0]);
                        this.view = res.data.list;

                        // this.list = res.data.list; 	this.paging = res.data.paging; 	this.no =
                        // this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp);
                    })
                    .catch((err) => {
                        console.log(err);
                    })
                },
                 tableRow({row,rowIndex}){
            row.index=rowIndex+1;
          },
            async detail(row) { 
                
                await axios
                    .get(`${url}/company`, {
                        params: {
                            company_idx: row.idx
                        }
                    })
                    .then(res => {
                        console.log('company', res.data.data)
                        this.odata = res.data.data
                       localStorage.setItem('companyinfo',JSON.stringify(res.data.data))

                    })
                console.log('row', row)
                // this.$router.push({path:'/board/ProgramTeamDetail'});
            },
            detailinfo(){
                this.$router.push({path:'/Company/info/detail'})
            }
        },
        mounted() {
            this.getList1();
        }
    }
</script>
<style>

    .el-table .cell {
        padding-left: 0;
        padding-right: 0;
    }

    #wrapper {
        /* border: 1px solid #FFBB00; */
        width: 100%;
        padding: 10px;
        position: absolute;
        top: 80%;
        overflow: hidden;

    }

    #contents {
        /* border: 1px solid #487BE1; */
        width: 100%;
        float: left;
        padding: 10px;

    }
</style>