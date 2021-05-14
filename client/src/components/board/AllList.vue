<template>
	<div>
		<!-- <h2>프로그램 리스트</h2>
		<a href="javascript:;" @click="getList1">공모전조회</a>
        <a href="javascript:;" @click="getList2">프로그램조회</a>
		<a href="javascript:;" @click="getList3">대회조회</a>
		<a href="javascript:;" @click="getList4">봉사활동조회</a> -->


	
		<h2>게시판 리스트</h2>

		<!-- <div class="searchWrap">
			<input type="text" v-model="keyword" @keyup.enter="fnSearch" /><a href="javascript:;" @click="fnSearch" class="btnSearch btn">검색</a>
		</div> -->

		<div class="listWrap">
			<table class="tbList">
				<colgroup>
					<col width="5%" />
					<col width="10%" />
					<col width="*" />
					<!-- <col width="15%" /> -->
				</colgroup>
				<tr>
					<th>no</th>
					<th>제목</th>
                    <th>내용</th>
					<!-- <th>아이디</th> -->
					<!-- <th>날짜</th> -->
				</tr>
				<tr v-for="(row, i) in view" :key="i">
                    
					<td>{{view[i].idx}}</td>
					<td class="txt_left"><a href="javascript:;">{{view[i].title}}</a></td>
                    <!-- <td>{{view[i].title}}</td> -->
                    <td>{{view[i].content}}</td>

					<!-- <td>{{row.view.d1.list[0].idx}}</td> -->
					<!-- <td>{{row.regdate.substring(0,10)}}</td> -->
				</tr>
				<tr v-if="view.length == 0">
					<td colspan="4">데이터가 없습니다.</td>
				</tr>
			</table>
		</div>

		<!--페이지 번호 
            <div class="pagination" v-if="paging.totalCount > 0">
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
          d1:"공모전",
          d2:"프로그램",
          d3:"대회",
          d4:"봉사활동"
      },
      view:{
      },
      d1:'',
      d2:'',
      d3:'',
      d4:'',
    
     }
    },
    mounted() { //페이지 시작하면은 자동 함수 실행
		this.getAllList();
	},
	methods:{
        getAllList() {
			axios.get(`${url}/program`,{params:{
                division:this.division.d1,
            }})
			.then((res)=>{
				console.log(res.data.list);
                this.d1=res.data.list;

                axios.get(`${url}/program`,{params:{
                division:this.division.d2,
                }})
                .then((res)=>{
                    console.log(res.data.list);
                    //this.view.d3=res.data.list
                    this.d2=res.data.list;
                    console.log('this.d2: ',this.d2)

                    axios.get(`${url}/program`,{params:{
                    division:this.division.d3,
                    }})
                    .then((res)=>{
                        console.log(res.data.list);
                        //this.view.d3=res.data.list
                        this.d3=res.data.list;
                        console.log('this.d3: ',this.d3)

                        axios.get(`${url}/program`,{params:{
                        division:this.division.d4,
                        }})
                        .then((res)=>{
                        console.log(res.data.list);
                        //this.view.d3=res.data.list
                        this.d4=res.data.list;
                        console.log('this.d4: ',this.d4)

                        const newArr=[
                            ...this.d1,
                            ...this.d2,
                            ...this.d3,
                            ...this.d4
                        ]
                        // this.view= [this.d1,this.d2,this.d3,this.d4]
                        this.view=newArr
                        console.log('view: ',this.view)
			            })
			        })
			    })
			})
            
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