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
						<td>{{items.data.title}}</td>
                        <th>시작일</th>
                        <td>{{items.data.start_date}}</td>
                        <th>마감일</th>
                        <td>{{items.data.end_date}}</td>
					</tr>
					<tr>
						<th>내용</th>
						<td class="txt_content" v-html="items.data.content"></td>
					</tr>
                    <tr>
						<th>이미지</th>
						<td class="img">
                         <img v-for="(row, i) in items.imglist" :key="i" :src="'data:image/png;base64,'+items.imglist[i]">
                        </td>
					</tr>
				</table>
			</form>
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
            items:JSON.parse(localStorage.getItem("items")),
		}
	}
	,created() {
		// this.fnGetView();
        console.log('items',this.items)
	}
	,methods:{
		
		fnList(){
			//
			this.$router.push({path:'/project'});
		}
	}
}
</script>

<style scoped>
	.tbAdd{border-top:1px solid #888;}
	.tbAdd th, .tbAdd td{border-bottom:1px solid #eee; padding:5px 0; }
	.tbAdd td{padding:10px 10px; box-sizing:border-box; text-align:left;}
	.tbAdd td.txt_content{height:300px; vertical-align:top;}
	.btnWrap{text-align:center; margin:20px 0 0 0;}
	.btnWrap a{margin:0 10px;}
	.btnAdd {background:#43b984}
	.btnDelete{background:#f00;}
</style>