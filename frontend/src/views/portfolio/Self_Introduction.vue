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
        <b-col xl="10" class="mb-5 mb-xl-0">
          <card  header-classes="bg-transparent">
            <b-row align-v="center" slot="header">
              
              <b-col>
                 <b-nav class="nav-pills justify-content-end " >
                  

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
            

<div>자기소개서 내용</div>
<!--
 여기에 자기소개서 넣으셈
-->
              <b-table responsive="sm" :fields ="fields" striped hover :items="myIntro" @row-clicked="click">
                  <template #cell(down)="row">
                    <b-button size="sm" @click="download(row)" class="mr-2">
                        다운
                    </b-button>
                
                </template>
                  <template #cell(edit)="row">
                  
                    <b-button size="sm" @click="deleteInt(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>

              <!-- 작성 폼으로 이동 시작-->
              <div style="text-align: right; margin:10px;">
              <b-button router-link to="/Portfolio/introductionWrite" type="submit" variant="primary" >등록하기</b-button>
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
      myIntro:[{}],
      fields:[{key:'origfilename',label:'파일이름'},{key:'down',label:''}],
      }},
      components: {
      
        BaseProgress,
        StatsCard,
        
      },
      mounted(){
        this.IntroView();
      },
      methods:{
        click(row){
          console.log(row)
        },
        IntroView(){
          axios.get(`${url}/introduction`)
                    .then(res=>{
                    this.myIntro=res.data.list
                    console.log('myint: ',this.myIntro)
        })
      },
      download(item,index,e){
                let idxx=item.item.idx
                let name=item.item.origfilename
                //let file=item.item
                axios.get(`${url}/introduction/download`,{params:{
                    idx:idxx
                }
                ,responseType: 'blob'
                
                }
                  
              )
                .then(res=>{
                    console.log('res',res)
                    const downurl = window.URL.createObjectURL(new Blob([res.data]));
                    const link = document.createElement('a');
                    link.href = downurl;
                    link.setAttribute('download', name);
                    document.body.appendChild(link);
                    link.click();
                    link.remove();
                    console.log('url:', downurl)
                    console.log('link:', link)
                }).catch(err=>{
                    alert(err)
                })
            },
           
           
            deleteInt(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/introduction`,{params:{
                    idx:del
                }})
                .then(res=>{
                    alert(res.data.msg)
                    location.reload();
                })
                .catch(err=>{
                    alert(err.response.data.msg)
                })
                console.log('delitem: ',item)
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