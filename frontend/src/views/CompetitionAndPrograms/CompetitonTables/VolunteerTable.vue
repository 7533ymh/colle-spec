<template>
    <b-card no-body>
        <b-card-header class="border-0">
            <h3 class="mb-0">봉사활동</h3>
        </b-card-header>

        <el-table class="table-responsive table"
                  header-row-class-name="thead-light"
                  :data="view" :row-class-name="tableRow"
                  @row-click="rowClick"
                  >
            
           
            <el-table-column label="번호"
                             prop='idx'
                             min-width="30px">
                              <template slot-scope="scope">
                                {{scope.$index+1}}
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
                             min-width="60px">
            </el-table-column>
            <el-table-column label="접수마감"
                             prop="receive_date_end"
                             min-width="60px">
            </el-table-column>

            <el-table-column label="행사시작"
                             prop="program_date_start"
                             min-width="60px">
            </el-table-column>
            <el-table-column label="행사종료"
                             prop="program_date_end"
                             min-width="60px">
            </el-table-column>
           <el-table-column fixed="right" label="board" width="120px">
      <template slot-scope="scope">
        <el-button @click="team(scope.row)" size="small">
         Team
        </el-button>
      </template>
    </el-table-column>
    
        </el-table>

        <b-card-footer class="py-4 d-flex justify-content-end">
            <base-pagination v-model="currentPage" :per-page="10" :total="50"></base-pagination>
        </b-card-footer>
    </b-card>
</template>
<script>
  import { Table, TableColumn,Button} from 'element-ui'
  import axios from "axios";
 import store from "@/store";
 var url=store.state.resourceHost
  export default {
    name: 'Volunteer-table',
    components: {
      [Table.name]: Table,
      [TableColumn.name]: TableColumn,
      [Button.name]: Button,
    },
    data() {
      return {
        currentPage: 1,
        view:[{}],
        division:'봉사활동'
        }
      },
      mounted(){
        this.c1()
        
      },
      methods:{
         async c1(){
            await axios.get(`${url}/program`,{params:{
            division:this.division
          }})
          .then(res=>{
            console.log(res.data.list)
            //this.Competition1Projects=res.data.list
            this.view=res.data.list;
            localStorage.setItem("division",this.division)
            console.log('view',this.view)
          })
            },
            tableRow({row,rowIndex}){
            row.index=rowIndex+1;
          },
          rowClick(row){
              console.log('row',row); //행 아이템데이터
              console.log(row.index); //행 인덱스
          },
              team(row) { 
              console.log('program_idx:',row.idx);
              localStorage.setItem('program',JSON.stringify(row))
              this.$router.push({path:'/ProgramList'});
              
          },
        }
    }
</script>
