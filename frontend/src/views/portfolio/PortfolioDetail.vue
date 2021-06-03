<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="나의 포트폴리오"
                      id="card"
                      class="mb-4">

            <template slot="footer">
              
            </template>
          </stats-card>
        </b-col>
        
        
        
      </b-row> 
    </base-header>

    <!--Charts-->
    <b-container fluid class="mt--7">
      <b-row>
        <b-col xl="10" class="mb-5 mb-xl-0">
          <card  header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              
              <b-col>
                 
              </b-col>
            </b-row>
            

<form>
				<table class="tbAdd">
					<colgroup>
						<col width="20%"/>
            <col width="*" />
            <col width="20%" />
						<col width="20%" />
            
					</colgroup>
					<tr>
						<th>프로젝트명</th>
						<td colspan="3" id="pjtitle">{{items.data.item.title}}</td>
          </tr> 
                    
          <tr>
              <th>개발기간</th>
              <td id="date">{{items.data.item.start_date}}~{{items.data.item.end_date}}</td>
              <th>구현</th>
              <td id="succ">{{items.data.item.success}}</td>
          </tr>
					<tr>
						<th>개발내용</th>
						<td id="content" colspan="3" class="txt_content" v-html="items.data.item.content"></td>
					</tr>

          <tr>
						<th>이미지</th>
						<td colspan="3" class="img">
                         <img id="pjimg" width="340px" height="400px"  v-for="(row, i) in items.imglist" :key="i" :src="'data:image/png;base64,'+items.imglist[i]">
                        </td>
					</tr>
				</table>
			</form>
            <!-- 작성 폼으로 이동 시작-->
            <div style="text-align: right; margin:10px;">
            <b-button router-link to="/PortfolioAndDocument" type="submit" variant="primary" >돌아가기</b-button>
            </div>
            <!-- 작성 폼으로 이동 끝-->

             

          </card>
        </b-col>

       
      </b-row>
      <!-- End charts-->

      
    </b-container>

  </div>

  
</template>
<script>
  // Components
  import BaseProgress from '@/components/BaseProgress';
  import StatsCard from '@/components/Cards/StatsCard';
  import axios from 'axios';
  import store from '@/store';
  let url=store.state.resourceHost; //서버주소 api
    export default {
      data(){return{
   //fields:[{key:'origfilename',label:'파일이름'},{key:'down',label:'down'},{key:'Edit&Del', label:'수정 삭제'}],
      fields:[{key:'title',label:'제목'},{key:'success',label:'구현'},{key:'start_date',label:'시작날짜'},{key:'end_date',label:'종료날짜'},{key:'edit&Del',label:''}],
            items:JSON.parse(localStorage.getItem("items")),
            succ:'',
      }},
      components: {
      
        BaseProgress,
        StatsCard,
        
      },
      mounted(){
        this.suc();
      },
      methods:{
        suc(){
          if(this.items.data.item.success===1){
            this.succ='성공'
          }else{
            this.succ='실패'
          }
        },
        fnList(){
			//
			this.$router.push({path:'/project'});
		},
        click(row){
          console.log(row)
        },
    }
    
  }
</script>





<style style lang="scss" scoped>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
.tbAdd{
  border: 1px solid;
  width: 100%;
}
.tbAdd tr,th{
  border: 1px solid;
}
#pjtitle, #succ, #content, #date, th{
   text-align: center;
}
#pjimg{
    margin: 0px auto;
}

th{
  background: blanchedalmond;
}
#card{
    margin-left: 50%;
    width: 80%;
    text-align: center;
    height: 60%;
}
</style>