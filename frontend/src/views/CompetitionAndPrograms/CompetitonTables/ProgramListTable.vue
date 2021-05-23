<template>
    <b-card no-body>
        <b-card-header class="border-0">
            <h3 class="mb-0">{{division}}</h3>

            <el-button size="mini" @click="write">작성</el-button>
        </b-card-header>

        <el-table class="table-responsive table"
                  header-row-class-name="thead-light"
                  :data="view"
                  :row-class-name="tableRow"
                  @row-click="rowClick"
                  >
            
           
            <el-table-column label="번호"
                             prop='idx'
                             min-width="60px">
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

    <el-table-column fixed="right" label="board" width="120">
      <template slot-scope="scope">
        <el-button @click="detail(scope.row)" size="small">
         Detail
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
  import { Table, TableColumn, Button} from 'element-ui'
  import axios from "axios";
 import store from "@/store";
 var url=store.state.resourceHost
  export default {
    name: 'ProgramList-table',
    components: {
      [Table.name]: Table,
      [TableColumn.name]: TableColumn,
      [Button.name]: Button,
    },
    data() {
      return {
        currentPage: 1,
        view:[{}],
        p_data:JSON.parse(localStorage.getItem('program')), //선택한 program 데이터 불러오기
        division:localStorage.getItem('division')
        ,data:[{}]
        ,
        }
      },
      mounted(){
        this.c1()
        
      },
      methods:{
          //리스트 조회
         async c1(){
             await axios.get(`${url}/program/boardList`,{params:{
            program_idx:this.p_data.idx
                
            }}
            )
			.then((res)=>{
                this.view=res.data.list;
			})
			.catch((err)=>{
				console.log(err);
			})
		},
        //팀구하기 게시글 작성
        write(){
            this.$router.push({path:'/ProgramList/ProgramTeamWrite'});
        }, 
        //팀구하기 리스트 상세보기
        detail(row){
            console.log('detailitem',row)
            localStorage.setItem('p_team',JSON.stringify(row))
            this.$router.push({path:'/ProgramList/ProgramListDetail'});
        },
            tableRow({row,rowIndex}){
            row.index=rowIndex;
          },
          rowClick(row){
              console.log('row',row); //행 아이템데이터
              console.log(row.index); //행 인덱스
          },

       }
    }
</script>
