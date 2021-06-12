<template>
 <div>
    <base-header
        class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success">
        <!-- Card stats -->
        <b-row>
            <b-col xl="10" md="6">


                <stats-card title="" type="gradient-red" sub-title="팀구하기 게시판" class="mb-4 "> <!-- 게시판 카드 시작  -->
                    <template slot="footer">
                        <div>
                            <h1>{{title}}</h1>
                            <div class="AddWrap" >

                                <form  class="  pt-1 pb-5 bg-gradient-success"> <!-- 게시판 글 시작  -->
                                    <table class="tbAdd" style=" font-size:20px;" > 
                                        <colgroup>
                                            <col  width="10%"/>
											<col  width="10%"/>
                                            <col  width="6%"/>
											<col  width="10%"/>
											<col  width="*"/>
                                        </colgroup>
                                        <tr>
											<th>작성자:</th>
                                            <td>{{userid}}</td>
											<td>|</td>
                                            <th >작성일:</th>
                                            <td style="text-align: left;">{{editdate}}</td>
											
                                        </tr>
                                        <tr style="text-align: center; height:200px;">
                                            
                                            <td colspan="5">{{content}}</td>
                                        </tr>
                                    </table>
                                </form>  <!-- 게시판 글 끝  -->




                                <div style="text-align: right;"> <!-- 게시글 수정, 삭제  -->
                                    <template v-if="authorized">
                                        <a class="btn" @click="edit_board">수정</a>&nbsp;
                                        <a class="btn red" @click="remove = true" v-if="!remove">삭제</a>
                                        <template v-else>
                                            <button class="grey" @click="remove = false">취소</button>&nbsp;
                                            <button class="red" @click="del_board">삭제</button>
                                        </template>&nbsp;
                                    </template>
                                </div> <!-- 게시글 수정, 삭제 끝 -->

								<form style="margin-top:30px;">    <!-- 댓글 작성 시작   -->
									<div class="form-group">
										
										<textarea class="form-control" v-model="comment" id="comment-content" rows="3" placeholder="댓글입력"></textarea>
									</div>
									<button type="button" class="btn btn-primary" @click="commWrite"  id="comment-create-btn">댓글작성</button>
								</form>  <!-- 댓글 작성 시작  끝  -->

								
                                <!-- 댓글 수정 누르면 댓글위치에서 수정가능하도록 하게 해줘 
                                      자기 댓글만 수정 삭제 뜨도록도 되면 좋겠음-->


                                <div  style="margin-bottom:30px; "> <!-- 댓글 리스트 시작  -->
                                    <table>
                                        <colgroup>
                                            <col width='1%'/>
                                            <col width='60%'/>
                                            <col width='10%'/>
                                            <col width='10%'/>
                                            <col width='*%'/>
                                        </colgroup>
                                        <!-- 댓글 -->


                                        <tr v-for="(row, i) in view.view" :key="i">
                                            <td>{{view.view[i].user_id}}:</td>
                                            <td>
												<div v-if="!edit.comment"> <!-- 댓글이 보통 상태일때  -->
												{{view.view[i].content}}
												</div>
												<div v-else> <!-- 댓글이 수정 상태일때  -->
													
													<textarea class="comment" v-model="edit.comment" placeholder="댓글입력"></textarea> 
													<b-button type="submit" @click="editcomm" variant="primary" style="font-size: 10px;">댓글수정</b-button>
												</div>


											</td>
                                            <td > <!-- authorized랑 !authorized 차이가 뭐야? -->
                                               
												<b-button @click="delcomm(row)" class="btn" variant="primary" style="font-size: 10px;"  >삭제</b-button>
												
												
                                            </td>
                                            <td>
												<b-button @click="btn_edit(row)" class="btn" variant="primary" style="font-size: 10px;"  >수정</b-button>
												
                                            </td>
                                            <td>수정날짜:{{view.view[i].edit}}</td>
                                        </tr>

                                        <tr v-if="view.length == 0">
                                            <td colspan="4">데이터가 없습니다.</td>
                                        </tr>
                                    </table>
                                </div>  <!-- 댓글 리스트 끝  -->
                            </div>
                            <div class="btnWrap">
                                
								<b-button href="javascript:;" @click="fnList" class="btn" variant="primary"  >목록</b-button>
                            </div>
                        </div>
                    </template>
                </stats-card> <!-- 게시판 카드 끝  -->
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
	  userid:'',
	  editdate:'',
			edit:{
        		comment:'', //댓글수정 내용
			  	idx:''}, //댓글 번호
			view:{
				user:JSON.parse(localStorage.getItem("comment")), //댓글리스트 정보
				view:{} //댓글리스트 저장
				},
			remove: false,
		
		//myid:'',
		
		}
	}
	,mounted() {
		this.detail_view()
		//this.mycomm()
	},
	computed: {
    authorized() {	
      return store.getters.userid === this.items.user_id;
    },
    }
	,methods:{
		
		fnList(){
			this.$router.push({path:'/board/list'});
		},
		
		handleNewLine(str) {    
       return String(str).replace(/(?:\r\n|\r|\n)/g,"</br>");
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
			this.$router.push({path:'/teamboardmodify',query:{data:this.items,content:this.content}})
			
		},
		async detail_view(){
			const moment = require('moment')
			await axios.get(`${url}/program/board`,{params:{
				board_idx:this.items.idx
			}})
			.then(res=>{
				console.log('detail',res.data.data)
				this.view.view=res.data.data.commentList
        this.title=res.data.data.title;
        this.content=res.data.data.content;
		this.userid=res.data.data.user_id
		const editdate = moment(res.data.data.edit).format('YYYY-MM-DD')
        this.editdate=editdate
		console.log('dddddd',res.data.data.commentList)

		for(var i=0; i<res.data.data.commentList.length; i++){
			const editdate1 = moment(res.data.data.commentList[i]).format('YYYY-MM-DD')
        	this.view.view[i].edit=editdate1
		}

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
			console.log('선택한item',item)
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
			console.log("item",item)
			
			if(item.user_id!==store.getters.userid){
				alert("본인 댓글만 수정가능합니다.")
			}else{
				this.edit.comment=item.content
				this.edit.idx=item.idx
			}
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