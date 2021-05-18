<template>
	<div>
		<h2>프로그램 리스트</h2>
		<a href="javascript:;" @click="getList1">공모전&대회</a>
        <a href="javascript:;" @click="getList2">프로그램</a>
		<a href="javascript:;" @click="getList3">봉사활동조회</a>


	
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
					<col width="5%" />
				</colgroup>
				<tr>
					<th>no</th>
					<th>제목</th>
                    <th>팀구하기</th>
                    <th>프로그램시작</th>
                    <th>프로그램종료</th>
                    <th>접수시작</th>
                    <th>접수마감</th>
        
				</tr>
				<tr v-for="(row, i) in view" :key="i">
                    
					<td>{{i}}</td>
                    <td><a v-bind:href="view[i].url" target="link.place">{{view[i].title}}</a></td>
                    <td><b-button size="sm" @click="team(row)" class="mr-2">
          팀구하기
        </b-button></td>
                    <td>{{view[i].program_date_start}}</td>
                    <td>{{view[i].program_date_end}}</td>
                    <td>{{view[i].receive_date_start}}</td>
                    <td>{{view[i].receive_date_end}}</td>
				</tr>
				<tr v-if="view.length == 0">
					<td colspan="4">데이터가 없습니다.</td>
				</tr>
			</table>
		</div>

		<!-- <div class="pagination" v-if="paging.totalCount > 0">
			<a href="javascript:;" @click="fnPage(1)" class="first">&lt;&lt;</a>
			<a href="javascript:;" v-if="paging.start_page > 10" @click="fnPage(`${paging.start_page-1}`)"  class="prev">&lt;</a>
			<template v-for=" (n,index) in paginavigation()">
				<template v-if="paging.page==n">
					<strong :key="index">{{n}}</strong>
				</template>
				<template v-else>
					<a href="javascript:;" @click="fnPage(`${n}`)" :key="index">{{n}}</a>
				</template>
			</template>
			<a href="javascript:;" v-if="paging.total_page > paging.end_page" @click="fnPage(`${paging.end_page+1}`)"  class="next">&gt;</a>
			<a href="javascript:;" @click="fnPage(`${paging.total_page}`)" class="last">&gt;&gt;</a>
		</div> -->
	</div>
</template>


<script>
let url = store.state.resourceHost;
import axios from 'axios';
import store from '../../store';
export default {
    data(){
    return{
      division:{
          d1:"공모전&대회",
          d2:"프로그램",
          d3:"봉사활동",
          //d4:"봉사활동"
      },
      view:{
       //각 division(분류)로 조회한 리스트들이 저장되는 곳
      },
      data:{
        //   board_idx:'',
      },

     }

    },
    mounted() { //페이지 시작하면은 자동 함수 실행
		this.getList1()
	},
	methods:{
		getList1() {
			axios.get(`${url}/program`,{params:{
                division:this.division.d1
            }})
			.then((res)=>{
				console.log(res.data.list);
                console.log(res.data.list[0]);
                //this.view.d1=res.data.list;
                this.view=res.data.list;

                // this.list = res.data.list;
				// 	this.paging = res.data.paging;
				// 	this.no = this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp);
			})
			.then((err)=>{
				console.log(err);
			})
		},
        getList2() {
			axios.get(`${url}/program`,{params:{
                division:this.division.d2
            }})
			.then((res)=>{
				console.log(res.data);
                //this.view.d2=res.data.list
                this.view=res.data.list;

			})
			.then((err)=>{
				console.log(err);
			})
		},
        getList3() {
			axios.get(`${url}/program`,{params:{
                division:this.division.d3,
            }})
			.then((res)=>{
				console.log(res.data.list);
                //this.view.d3=res.data.list
                this.view=res.data.list;
			})
			.then((err)=>{
				console.log(err);
			})
		},
            team(item,index){
            console.log('item',item)
            this.data=item
            localStorage.setItem('program',JSON.stringify(this.data))
            this.$router.push({path:'./list',query:this.data});

        },
        fnSearch() { //검색
			this.paging.page = 1;
			this.fnGetList();
		}
		, fnPage(n) {//페이징 이
			if(this.page != n) {
				this.page = n;
				this.fnGetList();
			}
		}
	}
}

</script>
<style scoped>

</style>

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