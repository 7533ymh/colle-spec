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
            

<div>프로젝트</div>
<!--
 여기에 포트폴리오, 문서 넣으셈
--><b-table responsive="sm" striped :fields="fields" hover :items="myproject" v-if="show" @row-clicked="click" >                        
      <template #cell(edit&Del)="row">
        <b-button size="sm" @click="fnView(row)"  class="mr-2">
          내용보기
        </b-button>
        <b-button size="sm" @click="mvedit(row)" class="mr-2">
          수정
        </b-button>
         <b-button size="sm" @click="deletepj(row)" class="mr-2">
          삭제
        </b-button>
      </template>

    </b-table> 

            <!-- 작성 폼으로 이동 시작-->
            <div style="text-align: right; margin:10px;">
            <b-button router-link to="/Portfolio/projectWrite" type="submit" variant="primary" >등록하기</b-button>
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
   //fields:[{key:'origfilename',label:'파일이름'},{key:'down',label:'down'},{key:'Edit&Del', label:'수정 삭제'}],
      fields:[{key:'title',label:'제목'},{key:'success',label:'구현'},{key:'start_date',label:'시작날짜'},{key:'end_date',label:'종료날짜'},{key:'edit&Del',label:''}],
                myproject:[{}],
                send:{
                      imglist:{},
                      data:[]
                    }
                    ,
                    img:[]
                    
      }},
      components: {
      
        BaseProgress,
        StatsCard,
        
      },
      mounted(){
        this.pjView();
        
      },
      methods:{
        click(row){
          console.log(row)
        },
        //프로젝트 조회
        pjView(){
          axios.get(`${url}/project`)
                    .then(get=>{
                    this.myproject=get.data.list
                    for(var i=0; i<get.data.list.length; i++){
                    // this.myproject.filename=get.data.list[i].project_imgList
                    if(this.myproject[i].success===0){
                       this.myproject[i].success="구현실패";
                    }else{
                       this.myproject[i].success="구현성공";
                    }
                   
                    }
                    
                    this.show=true
                    console.log(get)
                })
                
        },
        //클릭시 상세페이지로 데이터넘기면서 이동
      async fnView(item) {
        console.log(item);
			this.send.data=item
                for (var i = 0; i < item.item.project_imgList.length; i++) {
                let idxx=item.item.project_imgList[i].idx
                await axios.get(`${url}/project_img/download`,{params:{
                    idx:idxx
                },responseType: 'arraybuffer'})
                .then(res=>{
                    // this.send.imglist[i]=Buffer.from(res.data, 'binary').toString('base64')
                    this.send.imglist[i]=Buffer.from(res.data, 'binary').toString('base64')
                    
                })
                }
                console.log('이미지',this.send.imglist)
                localStorage.setItem('items',JSON.stringify(this.send)); //클릭한 행의 데이터를 로컬스토리지 저장
                this.$router.push({path:'/PortfolioDetail'})
	},
 
  deletepj(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/project`,{params:{
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
              this.$router.push({path:'/Portfolio/Modify/Project',query:row.item})
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