<template>
  <div>

    <base-header class="pb-6 pb-8 pt-5 pt-md-8 bg-gradient-success" >
      <!-- Card stats -->
      <b-row>
        <b-col xl="6" md="6">
          <stats-card title=""
                      type="gradient-red"
                      sub-title="나의 포트폴리오 or 스펙"
                      
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
        <b-col xl="8" class="mb-5 mb-xl-0">
          <card  header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              
              <b-col>
                <b-nav class="nav-pills justify-content-end">
                 

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Award">
                    <span class="d-none d-md-block">수상</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Career">
                    <span class="d-none d-md-block">경력</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Education">
                    <span class="d-none d-md-block">교육이수</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Experience">
                    <span class="d-none d-md-block">해외경험</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Self_Introduction">
                    <span class="d-none d-md-block">자기소개서</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/PortfolioAndDocument">
                    <span class="d-none d-md-block">프로젝트</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/Certificate">
                    <span class="d-none d-md-block">자격증</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                  
                  <b-nav-item link-classes="py-2 px-3 mt-1 mb-1" router-link to="/LanguageTest">
                    <span class="d-none d-md-block">어학시험</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                </b-nav>
              </b-col>
            </b-row>
            

<div>어학시험 내용</div>
<!--
 여기에 어학시험  넣으셈
-->
<b-table responsive="sm" striped :fields="fields" hover :items="mylang" @row-click="langView" >                        
      <template #cell(edits)="row">
         <b-button size="sm" @click="mvedit(row)" class="mr-2">
          편집
        </b-button>
         <b-button size="sm" @click="deletepj(row)" class="mr-2">
          삭제
        </b-button>
        
        
      </template>
    </b-table> 
    <!-- 작성 폼으로 이동 시작-->
            <div style="text-align: right; margin:10px;">
            <b-button router-link to="/Portfolio/languageWrite" type="submit" variant="primary" >등록하기</b-button>
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
      mylang:[{}],
      fields:[
      {key:'divsion',label:'구분'},
      {key:'exam',label:'시험명'},
      {key:'exam_score',label:'시험점수'},
      {key:'score',label:'최종점수'},
      {key:'date',label:'시험날짜'},
      {key:'edits',label:''}],


      }},
      components: {
      
        BaseProgress,
        StatsCard,
        
      },
      mounted(){
        this.langView();
      },

      methods:{
        
        langView(){
          axios.get(`${url}/language`)
                    .then(res=>{
                    this.mylang=res.data.list
                    console.log('lang: ',this.mylang)
        })
      },
      deletepj(item){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/language`,{params:{
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
            mvedit(){
              this.$router.push({path:'/portfolioModify',query:this.mycertificate})
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
</style>