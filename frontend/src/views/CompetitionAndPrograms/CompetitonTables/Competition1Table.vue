<template>
    <b-card no-body>
        <b-card-header class="border-0">
            <h3 class="mb-0">공모전&대회</h3>
        </b-card-header>

        <el-table class="table-responsive table"
                  header-row-class-name="thead-light"
                  :data="view"
                  :row-class-name="tableRow"
                  @row-click="team"
                  >
            
           
            <el-table-column label="번호"
                             prop='idx'
                             min-width="60px">
                              <template slot-scope="scope">
                                {{scope.$index}}
                              </template>
            </el-table-column>
           
              <el-table-column 
                        label="제목"
                        prop="title"
                        min-width="140px">
                        <template slot-scope="scope">
                          <a v-bind:href="scope.row.url">{{scope.row.title}}</a>
                        </template>
              </el-table-column>
              

           <!-- <template slot-scope="scope">
              
          </template> -->

            <el-table-column label="접수시작"
                             prop="receive_date_start"
                             min-width="140px">
            </el-table-column>
            <el-table-column label="접수마감"
                             prop="receive_date_end"
                             min-width="140px">
            </el-table-column>

            <el-table-column label="행사시작"
                             prop="program_date_start"
                             min-width="140px">
            </el-table-column>
            <el-table-column label="행사종료"
                             prop="program_date_end"
                             min-width="140px">
            </el-table-column>
            <!-- <el-table-column fixed="left" label="board" width="120">
      <template slot-scope="row">
        <el-button @click="team(row.$index)" size="small">
         Team
        </el-button>
      </template>
    </el-table-column> -->
    
        </el-table>

        <b-card-footer class="py-4 d-flex justify-content-end">
            <base-pagination v-model="currentPage" :per-page="10" :total="50"></base-pagination>
        </b-card-footer>
    </b-card>
</template>
<script>
  // import Competition1Projects from './Competition1Projects'
  import { Table, TableColumn} from 'element-ui'
  import axios from "axios";
 import store from "@/store";
 var url=store.state.resourceHost
  export default {
    name: 'Competition1-table',
    components: {
      [Table.name]: Table,
      [TableColumn.name]: TableColumn
    },
    data() {
      return {
        currentPage: 1,
        view:[{}],
        
        }
      },
      mounted(){
        this.c1()
        
      },
      methods:{
         async c1(){
            await axios.get(`${url}/program`,{params:{
            division:"공모전&대회"
          }})
          .then(res=>{
            console.log(res.data)
            //this.Competition1Projects=res.data.list
            this.view=res.data.list;
            console.log('view',this.view)
          })
            },
            tableRow({row,rowIndex}){
            row.index=rowIndex;
          },
              team(row) { 
              //  api\
              console.log(row);
              console.log(row.index);
          },
       }
    }
</script>
