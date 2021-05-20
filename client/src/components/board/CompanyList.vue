<template>
	<div>
		<h2>회사 리스트</h2>

		<!-- <div class="searchWrap">
			<input type="text" v-model="keyword" @keyup.enter="fnSearch" /><a href="javascript:;" @click="fnSearch" class="btnSearch btn">검색</a>
		</div> -->

		<div id="wrapper">
  <div id="contents">
  <h3>{{odata.name}} 합격 가능성</h3>
  <p>변경 {{data}}</p>
  <p>원본 {{odata}}</p>

  <b-progress id="prog" :max="max" height="2rem">
      <b-progress-bar :value="data.award_rank">
        <span>수상: <strong>{{ msg0 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	 <b-progress id="prog" :max="max" height="2rem">
      <b-progress-bar :value="data.career_rank">
        <span>경력: <strong>{{ msg1 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	<b-progress id="prog"  :max="max" height="2rem">
      <b-progress-bar :value="data.certificate_rank">
        <span>자격증: <strong>{{ msg2 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	<b-progress id="prog"  :max="max" height="2rem">
      <b-progress-bar :value="data.education_rank">
        <span>교육이수: <strong>{{ msg3 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	<b-progress id="prog"  :max="max" height="2rem">
      <b-progress-bar :value="data.experience_rank">
        <span>해외경험: <strong>{{msg4}}</strong></span>
      </b-progress-bar>
    </b-progress>
	 <b-progress id="prog"  :max="max" height="2rem">
      <b-progress-bar :value="data.language_rank">
        <span>어학: <strong>{{ msg5 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	<b-progress id="prog"  :max="max" height="2rem">
      <b-progress-bar :value="data.grade_rank">
        <span>학점: <strong>{{ msg6 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	<b-progress id="prog"  :max="max" height="2rem">
      <b-progress-bar :value="data.project_rank">
        <span>프로젝트: <strong>{{ msg7 }}</strong></span>
      </b-progress-bar>
    </b-progress>
	
	
 
  </div>
  <div id="sidebar">
			<table class="tbList">
				<colgroup>
					<col width="5%" />
					<col width="30%" />
				
				</colgroup>
				<tr>
					<th>no</th>
					<th>회사</th>
				</tr>
				<tr v-for="(row, i) in view" :key="i">
                    
					<td>{{i}}</td>
                    <td @click="detail(row)">{{view[i].name}}</td>
                  
				</tr>
				<tr v-if="view.length == 0">
					<td colspan="4">데이터가 없습니다.</td>
				</tr>
				
			</table>
			
  </div>
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
		max:5,
      view:{
       //각 division(분류)로 조회한 리스트들이 저장되는 곳
      },
      data:{
		  award_rank:'',
		  career_rank:'',
		  certificate_rank:'',
		  education_rank:'',
		  experience_rank:'',
		  language_rank:'',
		  grade_rank:'',
		  project_rank:''

	  },
	  odata:{},

	  msg1:'',
	  msg2:'',
	  msg3:'',
	  msg4:'',
	  msg5:'',
	  msg6:'',
	  msg7:'',
	  msg0:'',
	  }

    },
    mounted() { //페이지 시작하면은 자동 함수 실행
		this.getList1()
	},
	methods:{
		getList1() {
			axios.get(`${url}/companyList`)
			.then((res)=>{
				console.log(res.data.list);
                console.log(res.data.list[0]);
                //this.view.d1=res.data.list;
                this.view=res.data.list;

                // this.list = res.data.list;
				// 	this.paging = res.data.paging;
				// 	this.no = this.paging.totalCount - ((this.paging.page-1) * this.paging.ipp);
			})
			.catch((err)=>{
				console.log(err);
			})
		},
		async detail(row){
			await axios.get(`${url}/company`,{params:{
				company_idx:row.idx
			}})
			.then(res=>{
				console.log('원본',res.data.data)
				this.odata=res.data.data
				
				if(res.data.data.award_rank===5){
					this.data.award_rank=1;
					this.msg0='매우부족';

				}else if(res.data.data.award_rank===4){
					this.data.award_rank=2;
					this.msg0='부족';
				}
				else if(res.data.data.award_rank===2){
					this.data.award_rank=4;
					this.msg0='잘함';
				}
				else if(res.data.data.award_rank===1){
					this.data.award_rank=5;
					this.msg0='매우잘함';
				}else{
					this.data.award_rank=3;
					this.msg0='비슷함';
				}
				
				if(res.data.data.career_rank===5){
					this.data.career_rank=1;
					this.msg1='매우부족';

				}else if(res.data.data.career_rank===4){
					this.data.career_rank=2;
					this.msg1='부족';
				}
				else if(res.data.data.career_rank===2){
					this.data.career_rank=4;
					this.msg1='잘함';
				}
				else if(res.data.data.career_rank===1){
					this.data.career_rank=5;
					this.msg1='매우잘함';
				}else{
					this.data.career_rank=3;
					this.msg1='비슷함';
				}

				if(res.data.data.certificate_rank===5){
					this.data.certificate_rank=1;
					this.msg2='매우부족';
				}else if(res.data.data.certificate_rank===4){
					this.data.certificate_rank=2;
					this.msg2='부족';
				}
				else if(res.data.data.certificate_rank===2){
					this.data.certificate_rank=4;
					this.msg2='잘함';
				}
				else if(res.data.data.certificate_rank===1){
					this.data.certificate_rank=5;
					this.msg2='매우잘함';
				}
				else{
					this.data.certificate_rank=3;
					this.msg2='비슷함';
				}



				if(res.data.data.education_rank===5){
					this.data.education_rank=1;
					this.msg3='매우부족';
				}else if(res.data.data.education_rank===4){
					this.data.education_rank=2;
					this.msg3='부족';
				}
				else if(res.data.data.education_rank===2){
					this.data.education_rank=4;
					this.msg3='잘함';
				}
				else if(res.data.data.education_rank===1){
					this.data.education_rank=5;
					this.msg3='매우잘함';
				}else{
					this.data.education_rank=3;
					this.msg3='비슷함';
				}

				if(res.data.data.experience_rank===5){
					this.data.experience_rank=1;
					this.msg4='매우부족';
				}else if(res.data.data.experience_rank===4){
					this.data.experience_rank=2;
					this.msg4='부족';
				}
				else if(res.data.data.experience_rank===2){
					this.data.experience_rank=4;
					this.msg4='잘함';
				}
				else if(res.data.data.experience_rank===1){
					this.data.experience_rank=5;
					this.msg4='매우잘함';
				}
				else{
					this.data.experience_rank=3;
					this.msg4='비슷함';
				}


				if(res.data.data.language_rank===5){
					this.data.language_rank=1;
					this.msg5='매우부족';
				}else if(res.data.data.language_rank===4){
					this.data.language_rank=2;
					this.msg5='못함';
				}
				else if(res.data.data.language_rank===2){
					this.data.language_rank=4;
					this.msg5='잘함';
				}
				else if(res.data.data.language_rank===1){
					this.data.language_rank=5;
					this.msg5='매우잘함';
				}
				else{
					this.data.language_rank=3;
					this.msg5='비슷함';
				}

				if(res.data.data.grade_rank===5){
					this.data.grade_rank=1;
					this.msg6='매우부족';
				}else if(res.data.data.grade_rank===4){
					this.data.grade_rank=2;
					this.msg6='부족';
				}
				else if(res.data.data.grade_rank===2){
					this.data.grade_rank=4;
					this.msg6='잘함';
				}
				else if(res.data.data.grade_rank===1){
					this.data.grade_rank=5;
					this.msg6='매우잘함';
				}
				else{
					this.data.grade_rank=3;
					this.msg6='비슷함';
				}

				if(res.data.data.project_rank===5){
					this.data.project_rank=1;
					this.msg7='매우부족';
				}else if(res.data.data.project_rank===4){
					this.data.project_rank=2;
					this.msg7='부족';
				}
				else if(res.data.data.project_rank===2){
					this.data.project_rank=4;
					this.msg7='잘함';
				}
				else if(res.data.data.project_rank===1){
					this.data.project_rank=5;
					this.msg7='매우잘함';
				}
				else{
					this.data.project_rank=3;
					this.msg7='비슷함';
				}

				

			})
            console.log('detailitem',row)
			console.log('바뀐데이터',this.data)
            // localStorage.setItem('company',JSON.stringify(row))
            //this.$router.push({path:'/board/ProgramTeamDetail'});
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
	.listWrap{
		width:30%;
	}
#wrapper{
border: 1px solid #FFBB00;
width:100%;
padding: 10px;
position: absolute;
top: 15%;
overflow: hidden; 

}


#contents{
border: 1px solid #487BE1;
width:60%;
float: left;
padding: 10px; 

}


#sidebar{
border: 1px solid #487BE1;
width:39%;
float: left;
padding: 10px;
margin-left: 10px;

}
#prog{
	margin-top:1px;
	background-color: white;
}
</style>