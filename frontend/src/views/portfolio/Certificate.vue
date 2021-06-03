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
                 <b-nav class="nav-pills justify-content-end " >
                  

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Grade">
                    <span class="d-none d-md-block">학점</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Award">
                    <span class="d-none d-md-block">수상</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Career">
                    <span class="d-none d-md-block">경력</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Education">
                    <span class="d-none d-md-block">교육이수</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Experience">
                    <span class="d-none d-md-block">해외경험</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Self_Introduction">
                    <span class="d-none d-md-block">자기소개서</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/PortfolioAndDocument">
                    <span class="d-none d-md-block">프로젝트</span>
                    
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Certificate">
                    <span class="d-none d-md-block">자격증</span>
                    
                  </b-nav-item>
                  
                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/LanguageTest">
                    <span class="d-none d-md-block">어학시험</span>
                    
                  </b-nav-item>
                </b-nav>
              </b-col>
            </b-row>
            

<div>자격증 내용</div>
<!--
 여기에 자격증  넣으셈
-->
      <b-table responsive="sm" striped :fields="fields" hover :items="mycertificate" v-if="show" @row-clicked="click" >                        
      
      <template #cell(편집)="row">
         <b-button size="sm" @click="row.toggleDetails"  class="mr-2">
          {{ row.detailsShowing ? '닫기' : '내용보기'}}
        </b-button>
        <b-button size="sm" @click="mvedit(row)" class="mr-2">
          수정
        </b-button>
         <b-button size="sm" @click="deletepj(row)" class="mr-2">
          삭제
        </b-button>
      </template>

      <!--하이드 쇼-->
      <template #row-details="row">
        <b-card>
          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>자격증 명:</b></b-col>
            <b-col>{{ row.item.title }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>내용:</b></b-col>
            <b-col>{{ row.item.content }}</b-col>
          </b-row>
          <b-button size="sm" @click="row.toggleDetails">닫기</b-button>
        </b-card>
      </template>

      <!--하이드 쇼 끝-->

    </b-table> 

            <!-- 작성 폼으로 이동 시작-->
            <div style="text-align: right; margin:10px;">
            <b-button router-link to="/Portfolio/certificateWrite" type="submit" variant="primary" >등록하기</b-button>
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
        show:false,
      mycertificate:[{}],
      fields:[{key:'title',label:'자격증명'},{key:'publisher',label:'발급기관'},{key:'date',label:'취득날짜'},{key:'edit',label:'작성일'},{key:'편집',label:''}],
      edit:'1',
      }},
      components: {
      
        BaseProgress,
        StatsCard,
        
      },
      mounted(){
        this.certView();
        // this.mycertificate.edit=new Date().toJSON().slice(0,10).replace(/-/g,'.');
      },
      methods:{
        click(row){
          console.log(row)
        },
        certView(){
          const moment = require('moment')
          axios.get(`${url}/certificate`)
                    .then(res=>{
                    this.mycertificate=res.data.list
                    console.log(res)
                    
                    for(var i=0; i<res.data.list.length; i++){
                      const editdate = moment(res.data.list[i].edit).format('YYYY-MM-DD')
                    res.data.list[i].edit=editdate
                    // this.mycertificate[i].edit=this.mycertificate[i].edit.slice(0,10).replace(/-/g,'.');
                    }
                    this.show=true
        })
      },
      
      deletepj(item){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/certificate`,{params:{
                    idx:del
                }})
                .then(res=>{
                    alert(res.data.msg)
                    location.reload()
                })
                .catch(err=>{
                    alert(err.response.data.msg)
                })
                console.log('delitem: ',item)
            },
            mvedit(row){
                console.log('보낸데이터:',row)
              this.$router.push({path:'/Portfolio/Modify/Certificate',query:row.item})
            }
            //상세페이지에 수정 기능 넣기
    //         edit(item,index,event) {
    //             var params = new URLSearchParams(); //파일업로드가 포함되어 formdata를 이용한다
    //             params.append('title', this.certificate.title);
    //             params.append('content', this.certificate.content);
    //             params.append('publisher', this.certificate.publisher);
    //             params.append('date', this.certificate.date);
    //             axios.put(`${url}/certificate`,params,{
    //                 headers:{
    //                     'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
    //                 }
    //             })
    //             .then(certificate=>{
    //                 console.log(certificate)
    //                 alert(certificate.data.msg)
    //                 window.location.reload()
                    
    //             })
                
    //             .catch(err=>{
    //                 console.log(err)
    //                 alert(err.response.data.msg)
    //             })
    // }
  }
  }
</script>





<style>
.el-table .cell{
  padding-left: 0px;
  padding-right: 0px;
}
#card{
    margin-left: 50%;
    width: 80%;
    text-align: center;
    height: 60%;
}
</style>