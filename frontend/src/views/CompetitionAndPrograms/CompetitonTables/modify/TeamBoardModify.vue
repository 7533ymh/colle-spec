<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="팀구하기 작성"
                      
                      class="mb-4">

            <template slot="footer">
              <div>
		<h1>게시판 등록</h1>

		<div class="AddWrap">
			<form>
				<table class="tbAdd">
					<colgroup>
						<col width="50%" />
						<col width="50%" />
					</colgroup>
					<tr>
						<th>제목</th>
						<td><input type="text" v-on:input="title_typing" v-model="board.title" id="title"/></td>
					</tr>
					<tr>
						<th>내용</th>
						<td><textarea v-model="content" id="content"></textarea></td>
					</tr>
				</table>
			</form>
		</div>

		<div class="btnWrap">
			<a href=""  @click="fnList" class="btn">목록</a>
			<a href=""  @click="write" class="btn">수정완료</a>
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
  import axios from 'axios';
  import store from '@/store';
  import { Table, TableColumn, Button} from 'element-ui'
  let url=store.state.resourceHost; //서버주소 api

    export default {
      data(){return{
        board:this.$route.query.data,
        content:this.$route.query.content
      }},
      components: {

      BaseProgress,
        StatsCard,
        [Button.name]: Button,
    },
    methods:{
      fnList(){ //리스트 화면으로 이동 함수
			this.$router.push({path:'/ProgramList'});
			
		},
    title_typing(e){    	
            this.max_length(e, 20, '제목', this.title);
        },
    max_length(e, len, title, id){
            var val =  e.target.value;    			
            if (val.length > len){    				
                var msg = title + '의 최대 입력 길이는 ' + len + '입니다.';
                alert(msg);        			
                $(id).val(val.substring(0, len));
            }
        },
     async write(){
        if(!this.board.title) { //제목이 없다면 값을 입력하라고 알려준다.
				alert("제목을 입력해 주세요");
				return;
			}
            var params=new URLSearchParams();
					params.append('idx',this.board.idx)
					params.append('title',this.board.title)
					params.append('content',this.content)
		await	axios.put(`${url}/program/board`,params)
					.then(res=>{
						console.log(res.data.msg)
                        this.$router.go(-1); 
					})
                    .catch(err=>{
                        alert(err.response.data.msg)
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