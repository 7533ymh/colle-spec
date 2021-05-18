<template>
<div>
		<h1>게시판 상세보기</h1>

		<div class="AddWrap">
			<form>
				<table class="tbAdd">
					<colgroup>
						<col width="15%" />
						<col width="*" />
					</colgroup>
					<tr>
						<th>제목</th>
						<td>{{items.title}}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="txt_content" v-html="items.content"></td>
					</tr>
                    
				</table>
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
					<textarea class="comment" v-model="comment"  placeholder="댓글입력"></textarea>
					<button type="submit" @click="commWrite">댓글작성</button>
				</form>
				<form>
					<textarea class="comment" v-model="edit.comment"  placeholder="댓글입력"></textarea>
					<button type="submit" @click="editcomm">댓글수정</button>
				</form>
				
			</form>
			<div>
			<table>
				<colgroup>
				<col width='1%'/>
				<col width='70%'/>
				<col width='5%'/>
				<col width='5%'/>
				<col width='*%'/>
				</colgroup>
			<tr v-for="(row, i) in view.view" :key="i">
					<td>{{view.view[i].user_id}}: </td>
					<td>{{view.view[i].content}} </td>
					<td v-if="commauthorized"><button @click="delcomm(row)">삭제</button></td>
					<td v-if="commauthorized"><button @click="btn_edit(row)">수정</button></td>
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
</template>

<script>
let url = store.state.resourceHost;
import axios from 'axios';
import store from '../../store';
export default {
	data() {
		return {
			//getdata:this.$route.query,
            items:JSON.parse(localStorage.getItem("p_team")),
			comment:'',
			edit:{comment:'',
			idx:''},
			//singleitems:JSON.parse(localStorage.getItem("board_singlelist"))
			view:{
				user:{},
				view:{}
				},
			remove: false,

		}
	}
	,mounted() {
		this.comment_view()
		console.log('board_idx:',this.items.idx)
	},
	computed: {
    authorized() {	
      return this.$store.getters.userid === this.items.user_id;
    },
	commauthorized() {	
	
      return this.$store.getters.userid==='user11';
    },
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
		comment_view(){
			axios.get(`${url}/program/board`,{params:{
				board_idx:this.items.idx
			}})
			.then(res=>{
				console.log(res.data.data.commentList)
				this.view.view=res.data.data.commentList
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
			console.log(this.edit)
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
			})
			.catch(err=>{
				alert(err.response.data.msg)
			})
			}
		},
		
		
	}
}
</script>

<style scoped>
	.tbAdd{border-top:1px solid #888;}
	.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0; }
	.tbAdd td{padding:10px 10px; box-sizing:border-box; text-align:left;}
	.tbAdd td.txt_content{height:100px; vertical-align:top;}
	.btnWrap{text-align:center; margin:20px 0 0 0;}
	.btnWrap a{margin:0 10px;}
	.btnAdd {background:#43b984}
	.btnDelete{background:#f00;}
	.comment{border-top:1px solid #888;
	height:100px;
	width:300px;
	}
</style>