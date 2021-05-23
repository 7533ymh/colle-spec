<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="팀구하기 게시판"
                      
                      class="mb-4">

            <template slot="footer">
              
            </template>
          </stats-card>
        </b-col>

      </b-row> 
    </base-header>

    <!--Charts-->
    <div>
		<h1>게시판 상세보기</h1>

		<div class="AddWrap">
			<form>
				<table class="tbAdd">
					<colgroup>
						<col width="50%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>수정날짜</th>
						<td>{{editdate}}</td>
					</tr>
					<tr>
						<th>제목</th>
						<td class="ml-4">{{title}}</td>
						
					</tr>

					<tr>
						<th>내용</th>
						<td>{{content}}</td>
					</tr>
                    
				</table>
      </form>
				<div>
				<template v-if="authorized">
					<a class="btn" @click="edit_board">수정</a>&nbsp;
					<a class="btn red" @click="remove = true" v-if="!remove">삭제</a>
					<template v-else>
					<button class="grey" @click="remove = false">취소</button>&nbsp;
					<button class="red" @click="del_board">삭제</button>
					</template>&nbsp;
				</template>
				</div>
     
			<form>
          <form>
					<textarea class="comment" v-model="comment"  placeholder="댓글입력"></textarea>
					<button type="submit" @click="commWrite">댓글작성</button>
          </form>
          <form>
          <textarea class="comment" v-model="edit.comment"  placeholder="댓글입력"></textarea>
					<button type="submit" @click="editcomm">댓글수정</button>
				</form>
			</form>
			<!-- 댓글 수정 누르면 댓글위치에서 수정가능하도록 하게 해줘 
      자기 댓글만 수정 삭제 뜨도록도 되면 좋겠음-->
      
             
			<div>
			<table>
				<colgroup>
				<col width='1%'/>
				<col width='70%'/>
				<col width='5%'/>
				<col width='5%'/>
				<col width='*%'/>
				</colgroup>
        <!-- 댓글 -->
			<tr v-for="(row, i) in view.view" :key="i">
					<td>{{view.view[i].user_id}}: </td>
					<td>{{view.view[i].content}} </td>
					<td v-if="!authorized"><button @click="delcomm(row)">삭제</button></td>
					<td v-if="!authorized"><button @click="btn_edit(row)">수정</button></td>
					<td>작성날짜:{{view.view[i].edit}}</td>
				</tr>
				<tr v-if="view.length == 0">
					<td colspan="4">데이터가 없습니다.</td>
				</tr>
			</table>
			</div>
		</div>

		<div class="btnWrap">
			<a href="javascript:;" @click="fnList" class="btn">목록</a>
		</div>	
		
		
	</div>

  </div>

  
</template>
<script>

  // Components
  import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard';
  import ProgramListTable from "./CompetitonTables/ProgramListTable";
  import axios from 'axios';
  import store from '@/store';
  const url=store.state.resourceHost;

    export default {
      components: {

      BaseProgress,
        StatsCard,
        ProgramListTable
    },
      data() {
		return {
      items:JSON.parse(localStorage.getItem("p_team")),
			content:'', //상세보기 내용
      title:'', //상세보기 제목
      comment:'', //댓글창 댓글내용
	  editdate:'',
			edit:{
        comment:'', //댓글수정 내용
			  idx:''}, //댓글 번호
			view:{
				user:JSON.parse(localStorage.getItem("comment")), //댓글리스트 정보
				view:{} //댓글리스트 저장
				},
			remove: false,

		}
	}
	,mounted() {
		this.detail_view()
	},
	computed: {
    authorized() {	
      return store.getters.userid === this.items.user_id;
    },
    commauthorized() {
      //return store.getters.userid === row.user_id;
      }
    }
	,methods:{
		
		fnList(){
			this.$router.push({path:'/board/list'});
		},
		
		handleNewLine(str) {    
       return String(str).replace(/(?:\r\n|\r|\n)/g,"</br>");
		},
		fnList(){
			this.$router.push({path:'/board/list'});
		},
		del_board(){
			axios.delete(`${url}/program/board`,{params:{
						idx:this.items.idx
					}})
					.then(res=>{
						console.log(res.data.msg)
						alert(res.data.msg);
						this.fnList();
					})
		},
		edit_board(){
			var params=new URLSearchParams();
					params.append('idx',this.items.idx)
					params.append('title',this.title)
					params.append('content',this.comment)
			axios.put(`${url}/program/board`,params)
					.then(res=>{
						console.log(res.data.msg)
					})
		},
		async detail_view(){
			await axios.get(`${url}/program/board`,{params:{
				board_idx:this.items.idx
			}})
			.then(res=>{
				console.log('detail',res.data.data)
				this.view.view=res.data.data.commentList
        this.title=res.data.data.title;
        this.content=res.data.data.content;
		this.editdate=res.data.data.edit;
		this.editdate= new Date().toJSON().slice(0,10).replace(/-/g,'.');
        localStorage.setItem("comment",JSON.stringify(res.data.data.commentList));
			})
		},
		commWrite(){
			if(this.comment===''){
				alert('내용을 입력하세요.')
			}else{
			var params=new URLSearchParams();
			params.append('board_idx',this.items.idx)
			params.append('content',this.comment)
			axios.post(`${url}/program/board/comment`,params)
			.then(res=>{
				console.log('res',res.data.msg)
        location.reload();
			})
			.catch(err=>{
				console.log(err)
				alert(err.response.data.msg)
			})
			}
		},
		delcomm(item){
			axios.delete(`${url}/program/board/comment`,{params:{
				idx:item.idx
			}})
			.then(res=>{
				alert(res.data.msg)
				location.reload()
			})
			.catch(err=>{
				alert(err.response.data.msg)
			})
		},
		btn_edit(item){
			//this.edit=item
			this.edit.comment=item.content
			this.edit.idx=item.idx
		},
		editcomm(){
			if(this.edit.comment===''){
				alert('내용을 입력하세요.')
			}else{
			var params=new URLSearchParams();
			params.append('idx',this.edit.idx)
			params.append('content',this.edit.comment)
			axios.put(`${url}/program/board/comment`,params)
			.then(res=>{
				console.log('res',res.data.msg)
				alert(res.data.msg)
        location.reload();
			})
			.catch(err=>{
				alert(err.response.data.msg)
			})
			}
		},
      
  }
  }
</script>





<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
</style>