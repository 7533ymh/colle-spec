<template>
  <b-card no-body class="card-profile" alt="Image placeholder" img-top>
    <b-row class="justify-content-center">
      <b-col lg="3" class="order-lg-2">
        <div class="card-profile-image">
          <a href="#">
            <b-img src="img/theme/profile.jpg" rounded="circle" />
          </a>
        </div>
      </b-col>
    </b-row>

    <b-card-header class="text-center border-0 pt-8 pt-md-4 pb-0 pb-md-4 ">
      <div class="d-flex justify-content-between">
        <a href="#" class="btn btn-sm btn-info mr-4">Connect</a>
      </div>
    </b-card-header>

    <b-card-body class="pt-0">
      <b-row>
        <b-col >
          <div class="card-profile-stats d-flex justify-content-center mt-md-5">
            <div>
              <span class="heading">{{p_count}}</span>
              <span class="description">포트폴리오</span>
            </div>
            <div>
              <span class="heading">{{viewRank}}</span>
              <span class="description">전체등급</span>
            </div>
            
          </div>
        </b-col>
      </b-row>
      <div class="text-center">
        <h5 class="h3">
         {{name}}
        </h5>
      </div>
    </b-card-body>
  </b-card>
</template>
<script>
import store from '@/store'
import axios from 'axios'
let url=store.state.resourceHost; //서버주소 api
export default {
  data(){
    return{
    p_count:0,
    comment_count:0,
    viewRank:'',
    userinfo:store.state.userinfo,
    name:'',

  }},
  methods:{
    count(){
      axios.get(`${url}/award`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/career`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/certificate`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/introduction`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/project`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/language`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/education`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
      axios.get(`${url}/experience`)
      .then(res=>{
        this.p_count +=res.data.list.length
      })
    },
    a_rankView(){
           axios.get(`${url}/rank`)
                    .then(res=>{
                      //this.allRank=res.data.data 
                      this.viewRank=res.data.data.all_rank   
                })
                .catch(err=>{
                  alert(err.response.data.msg)
                })
        },
      username(){
        axios.get(`${url}/user`)
        .then(res=>{
          this.name=res.data.data.name
        })
      }
  },
  mounted(){
    this.count();
    this.a_rankView();
    this.username();
  }
};
</script>
<style></style>
