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
            

<div>학점 내용</div>
<!--
 여기에 자격증  넣으셈
-->
      <b-table responsive="sm" striped :fields="fields" hover :items="grade" v-if="show"  @row-clicked="click" >                        
      
      <template #cell(편집)="row">
         <b-button size="sm" @click="mvedit(row)" class="mr-2">
          수정
        </b-button>
         <b-button size="sm" @click="deletepj(row)" class="mr-2">
          삭제
        </b-button>
        
        
      </template>
    </b-table> 
            <div style="text-align: right; margin:10px;">
            <b-button router-link to="/Portfolio/gradeWrite" type="submit" variant="primary" >등록하기</b-button>
            </div>
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
      grade:[{}],
      fields:[{key:'grade',label:'학년'},{key:'semester',label:'학기'},{key:'avg_score',label:'학점'},{key:'edit',label:'작성일'},{key:'편집',label:''}],
      }},
      components: {
      
        BaseProgress,
        StatsCard,
        
      },
      mounted(){
        this.gradeView();
        // this.mycertificate.edit=new Date().toJSON().slice(0,10).replace(/-/g,'.');
      },
      methods:{
        click(row){
          console.log(row)
        },
        gradeView(){
          const moment = require('moment')
          axios.get(`${url}/grade`)
                    .then(res=>{
                    this.grade=res.data.list
                    for(var i=0; i<res.data.list.length; i++){
                    this.grade[i].grade +="학년";
                    this.grade[i].semester +="학기";
                    const editdate = moment(res.data.list[i].edit).format('YYYY-MM-DD')
                    res.data.list[i].edit=editdate
                    //this.grade[i].edit=this.grade[i].edit.slice(0,10).replace(/-/g,'.');
                    this.show=true
                    }
        })
      },
      
      deletepj(item){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/grade`,{params:{
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
              this.$router.push({path:'/Portfolio/Modify/Grade',query:row.item})
            }
          
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