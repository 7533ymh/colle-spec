<template>
	<div>
		<h2>게시판 리스트</h2>

		<!-- <div class="searchWrap">
			<input type="text" v-model="keyword" @keyup.enter="fnSearch" /><a href="javascript:;" @click="fnSearch" class="btnSearch btn">검색</a>
		</div> -->

		 <div class="listWrap">
			<table class="tbList">
				<colgroup>
					<col width="5%" />
					<col width="30%" />
					<col width="5%" />
					<col width="5%" />
                    <col width="5%" />
					<col width="5%" />
				</colgroup>
				<tr>
					<th>no</th>
					<th>제목</th>
                    <th>프로그램시작</th>
                    <th>프로그램종료</th>
                    <th>접수시작</th>
                    <th>접수마감</th>
        
				</tr>
				<tr v-for="(row, i) in view" :key="i">
                    
					<td>{{i}}</td>
                    <td @click="detail(row)">{{view[i].title}}</td>
                    <td>{{p_data.program_date_start}}</td>
                    <td>{{p_data.program_date_end}}</td>
                    <td>{{p_data.receive_date_start}}</td>
                    <td>{{p_data.receive_date_end}}</td>
				</tr>
				<tr v-if="view.length == 0">
					<td colspan="4">데이터가 없습니다.</td>
				</tr>
			</table>
		</div>
        <div>
            <button @click="write">글작성</button>
        </div>

	</div>
</template>
<script>
let url = store.state.resourceHost;
import axios from 'axios';
import store from '../../store';

export default {
    data(){
        return{
            data:this.$route.query //program에서 보내온 데이터
            ,
            p_data:JSON.parse(localStorage.getItem('program')) //program페이지에서 저장한 데이터 불러오기
            ,
            view:[]
        }
    },
    mounted() { //페이지 시작하면은 자동 함수 실행
		this.getList()
	},
	methods:{
		async getList() {
			await axios.get(`${url}/program/boardList`,{params:{
                program_idx:this.p_data.idx
                
            }}
            )
			.then((res)=>{
                this.view=res.data.list
			})
			.catch((err)=>{
				console.log(err);
			})
		},
        write(){
            this.$router.push({path:'/board/write'});
        }, 
        detail(item){
            console.log('detailitem',item)
            localStorage.setItem('p_team',JSON.stringify(item))
            this.$router.push({path:'/board/ProgramTeamDetail'});
        }
	}
}
</script>

<style scoped>
	.searchWrap{border:1px solid #888; border-radius:5px; text-align:center; padding:20px 0; margin-bottom:40px;}
	.searchWrap input{width:60%; height:36px; border-radius:3px; padding:0 10px; border:1px solid #888;}
	.searchWrap .btnSearch{display:inline-block; margin-left:10px;}
	.tbList th{border-top:1px solid #888;}
	.tbList th, .tbList td{border-bottom:1px solid #eee; padding:5px 0;}
	.tbList td.txt_left{text-align:left;}
	.btnRightWrap{text-align:right; margin:10px 0 0 0;}

	.pagination{margin:20px 0 0 0; text-align:center;}
	.first, .prev, .next, .last{border:1px solid #666; margin:0 5px;}
	.pagination span{display:inline-block; padding:0 5px; color:#333;}
	.pagination a{text-decoration:none; display:inline-blcok; padding:0 5px; color:#666;}
</style>