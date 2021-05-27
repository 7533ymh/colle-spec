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
                  

                  <b-nav-item link-classes="py-2 px-3" router-link to="/Self_Introduction">
                    <span class="d-none d-md-block">자기소개서</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/PortfolioAndDocument">
                    <span class="d-none d-md-block">포트폴리오 and 문서</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>

                  <b-nav-item link-classes="py-2 px-3" router-link to="/Certificate">
                    <span class="d-none d-md-block">자격증</span>
                    <span class="d-md-none">W</span>
                  </b-nav-item>
                  
                  <b-nav-item link-classes="py-2 px-3" router-link to="/LanguageTest">
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
              <b-table responsive="sm" :fields ="fields" striped hover :items="myIntro" @row-click="click">
                  <template #cell(down)="row">
                    <b-button size="sm" @click="download(row)" class="mr-2">
                        다운
                    </b-button>
                
                </template>
                  <template #cell(edit)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    편집
                    </b-button>
                    <b-button size="sm" @click="deleteInt(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
             

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
      fields:[{key:'origfilename',label:'파일이름'},{key:'down',label:''},{key:'edit', label:''}],

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
                    console.log('mycertificate: ',this.myIntro)
        })
      },
      download(item,index,e){
                let idxx=item.item.idx
                let name=item.item.origfilename
                //let file=item.item
                axios.get(`${url}/introduction/download`,{params:{
                    idx:idxx
                }
                ,responseType: 'blob' }
                  
              )
                .then(res=>{
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
           
            edit(item,index,event) {
                var fd = new FormData();
                for (var i = 0; i < this.fd.files.length; i++) {
                        fd.append('files', this.fd.files[i]);
                        }
                axios.put(`${url}/introduction/uplode`,fd,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(res=>{
                    console.log(res)
                    alert(res.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
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