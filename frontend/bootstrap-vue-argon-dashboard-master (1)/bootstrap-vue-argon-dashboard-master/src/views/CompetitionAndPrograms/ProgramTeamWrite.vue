<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row  >
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="팀구하기 작성"
                      
                      class="mb-4">

            <template slot="footer">
              <div>
		  <h1>게시판 등록</h1>

		<div class="AddWrap" >
			<form>
				<table class="tbAdd">
					<colgroup>
						<col width="15%" />
						<col width="*%" />
					</colgroup>
					<tr>
						<th style="text-align: center; border-bottom:1px solid #eee; " >제목  </th>
						<td><input type="text" v-on:input="title_typing" v-model="board.title" id="title" style="width:600px; height:50px;"/></td>
					</tr>
					<tr>
						<th style="text-align: center;">내용</th>
						<td><textarea v-model="board.content" id="content" style="width:600px; height:400px;"></textarea></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="btnWrap" style="margin-left: 120px ">

      <b-button href="javascript:;"  @click="fnList" class="btn" variant="primary"  >목록</b-button>
      <b-button href="javascript:;"  @click="write" class="btn" variant="primary">등록</b-button>

		</div>	
	</div>
              
            </template>

          </stats-card>
        </b-col>

      </b-row> 
    </base-header>

    <!--Charts-->
    

  </div>

  
</template>
<script>

  // Components
  import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard';
  import ProgramListTable from "./CompetitonTables/ProgramListTable";
  import axios from 'axios';
  import store from '@/store';
  import { Table, TableColumn, Button} from 'element-ui'
  let url=store.state.resourceHost; //서버주소 api

    export default {
      data(){return{
        board:{
                id:JSON.parse(localStorage.getItem('program')), //프로그램 idx
			    title:'', //팀구하기 게시판 제목
			    content:''//팀구하기 게시판 내용
            },
      }},
      components: {

      BaseProgress,
        StatsCard,
        ProgramListTable,
        [Button.name]: Button,
    },
    methods:{
      fnList(){ //리스트 화면으로 이동 함수
			this.$router.push({path:'/ProgramList'});
			
		},
    title_typing(e){    	
            this.max_length(e, 10, '제목', this.title);
        },
    max_length(e, len, title, id){
            var val =  e.target.value;    			
            if (val.length > len){    				
                var msg = title + '의 최대 입력 길이는 ' + len + '입니다.';
                alert(msg);        			
                $(id).val(val.substring(0, len));
            }
        },
      write(){
        if(!this.board.title) { //제목이 없다면 값을 입력하라고 알려준다.
				alert("제목을 입력해 주세요");
				return;
			}
            var params = new URLSearchParams();
                params.append('program_idx',this.board.id.idx) //게시글 등록위해 필요한 프로그램idx
                params.append('title', this.board.title);
                params.append('content', this.board.content);
			
			axios.post(`${url}/program/board`,params)
			.then((res)=>{
				if(res.data.success) {
					alert('등록되었습니다.');
					this.fnList();
				} else {
					alert("실행중 실패했습니다.\n다시 이용해 주세요");
				}
			})
			.catch((err)=>{
				console.log(err);
			})
      }
    }
    
  }
</script>





<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
#title {
  width:300px;
}
#content{
  width:300px;
  height: 300px;
}

</style>