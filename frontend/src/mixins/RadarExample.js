import { Radar } from './BaseCharts'

export default {
  extends: Radar,
  data(){
    return{
     cha:JSON.parse(localStorage.getItem("rating")),
      //a1:0,a2:0,a3:0,a4:0,a4:0,a5:0,a6:0,a7:0,a8:0,
     // cha:[a1,a2,a3,a4,a5,a6,a7,a8]
     get:''
    }
  },
  mounted () {
    eventBus.$on('senddata',function(value){
      this.get=value
          console.log('get 값: ', this.get)
          // for (var i = 0; i < this.value.project_imgList[i].filename.length; i++) {
          //     console.log('detail.files 값: ', this.detail.files[i])
          //  } 
  }.bind(this));
    console.log('aaaaaa',this.cha)
    this.renderChart({
      labels: ["수상",
      "경력",
      "자격증",
      "교육이수",
      "해외경험",
      "학점",
      "프로젝트",
      "어학"],
      datasets: [
        {
          label: '등급차트',
          backgroundColor: "rgba(255, 99, 132, 0.1)",
          borderColor: "rgba(255, 99, 132, 1)",
          pointBackgroundColor: 'rgba(255,181,198,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(179,181,198,1)',
          borderWidth: 3,
          data: this.cha,
        },
        
      ]
    }, {responsive: false, maintainAspectRatio: false})
  },
  methods:{
    async a_rankView(){
      await axios.get(`${url}/rank`)
                 .then(res=>{
                   console.log('cha: ',this.cha)
                   localStorage.setItem("rank",JSON.stringify(res.data.data))
                   this.viewRank=res.data.data
                   console.log(this.viewRank)
                   if(res.data.data.award_rank==='1등급'){
                     //this.radarChartData.datasets[0].data[0]=5
                     this.cha.a1=5
                   }
                   else if(res.data.data.award_rank==='2등급'){
                     this.cha.a1=4
                     //this.radarChartData.datasets[0].data[0]=4                      
                     }
                   else if(res.data.data.award_rank==='4등급'){
                     this.cha.a1=2
                     //this.radarChartData.datasets[0].data[0]=2
                   }
                   else if(res.data.data.award_rank==='5등급'){
                     this.cha.a1=1
                     //this.radarChartData.datasets[0].data[0]=1
                   }else{
                     this.cha.a1=3
                    //this.radarChartData.datasets[0].data[0]=3
        
                   }

                   //경력
                   if(res.data.data.career_rank==='1등급'){
                     this.cha.a2=5
                     //this.radarChartData.datasets[0].data[1]=5
                   }
                   else if(res.data.data.career_rank==='2등급'){
                     this.cha.a2=4
                     //this.radarChartData.datasets[0].data[1]=4
                   }
                   else if(res.data.data.career_rank==='4등급'){
                     this.cha.a2=2
                    //this.radarChartData.datasets[0].data[1]=2
                   }
                   else if(res.data.data.career_rank==='5등급'){
                     this.cha.a2=1
                     //this.radarChartData.datasets[0].data[1]=1
                   }else{
                     this.cha.a2=3
                    // this.radarChartData.datasets[0].data[1]=3
                   }
                   
                   //자격증
                    if(res.data.data.certificate_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[2]=5
                     this.cha.a3=5
                   }
                   else if(res.data.data.certificate_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[2]=4
                     this.cha.a3=4
                   }
                   else if(res.data.data.certificate_rank==='4등급'){
                   // this.radarChartData.datasets[0].data[2]=2
                    this.cha.a3=2
                   }
                   else if(res.data.data.certificate_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[2]=1
                     this.cha.a3=1
                   }
                   else{
                    // this.radarChartData.datasets[0].data[2]=3
                     this.cha.a3=3
                   }

                   //교육
                   if(res.data.data.education_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a4=5
                   }
                   else if(res.data.data.education_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a4=4
                   }
                   else if(res.data.data.education_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a4=2
                   }
                   else if(res.data.data.education_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a4=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a4=3
                   }
                   //해외경험
                   if(res.data.data.experience_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a5=5
                   }
                   else if(res.data.data.experience_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a5=4
                   }
                   else if(res.data.data.experience_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a5=2
                   }
                   else if(res.data.data.experience_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a5=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a5=3
                   }
                   //학점
                   if(res.data.data.grade_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a6=5
                   }
                   else if(res.data.data.grade_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a6=4
                   }
                   else if(res.data.data.grade_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a6=2
                   }
                   else if(res.data.data.grade_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a6=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a6=3
                   }
                   //프로젝트
                   if(res.data.data.project_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a7=5
                   }
                   else if(res.data.data.project_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a7=4
                   }
                   else if(res.data.data.project_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a7=2
                   }
                   else if(res.data.data.project_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a7=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a7=3
                   }
                   //어학
                   if(res.data.data.language_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a8=5
                   }
                   else if(res.data.data.language_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a8=4
                   }
                   else if(res.data.data.language_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a8=2
                   }
                   else if(res.data.data.language_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a8=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a8=3
                   }
                  //  var result=[a1,a2,a3,a4,a5,a6,a7,a8]
                  //  localStorage.setItem("rating",JSON.stringify(result))
                   
                   
             })
             .catch(err=>{
               alert(err.response.data.msg)
             })
     },
    async g_rankView(){
      await axios.get(`${url}/rank/grade`)
                 .then(res=>{
                   console.log('cha: ',this.cha)
                   localStorage.setItem("rank",JSON.stringify(res.data.data))
                    if(res.data.data.award_rank==='1등급'){
                     //this.radarChartData.datasets[0].data[0]=5
                     this.cha.a1=5
                   }
                   else if(res.data.data.award_rank==='2등급'){
                     this.cha.a1=4
                     //this.radarChartData.datasets[0].data[0]=4                      
                     }
                   else if(res.data.data.award_rank==='4등급'){
                     this.cha.a1=2
                     //this.radarChartData.datasets[0].data[0]=2
                   }
                   else if(res.data.data.award_rank==='5등급'){
                     this.cha.a1=1
                     //this.radarChartData.datasets[0].data[0]=1
                   }else{
                     this.cha.a1=3
                    //this.radarChartData.datasets[0].data[0]=3
        
                   }

                   //경력
                   if(res.data.data.career_rank==='1등급'){
                     this.cha.a2=5
                     //this.radarChartData.datasets[0].data[1]=5
                   }
                   else if(res.data.data.career_rank==='2등급'){
                     this.cha.a2=4
                     //this.radarChartData.datasets[0].data[1]=4
                   }
                   else if(res.data.data.career_rank==='4등급'){
                     this.cha.a2=2
                    //this.radarChartData.datasets[0].data[1]=2
                   }
                   else if(res.data.data.career_rank==='5등급'){
                     this.cha.a2=1
                     //this.radarChartData.datasets[0].data[1]=1
                   }else{
                     this.cha.a2=3
                    // this.radarChartData.datasets[0].data[1]=3
                   }
                   
                   //자격증
                    if(res.data.data.certificate_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[2]=5
                     this.cha.a3=5
                   }
                   else if(res.data.data.certificate_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[2]=4
                     this.cha.a3=4
                   }
                   else if(res.data.data.certificate_rank==='4등급'){
                   // this.radarChartData.datasets[0].data[2]=2
                    this.cha.a3=2
                   }
                   else if(res.data.data.certificate_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[2]=1
                     this.cha.a3=1
                   }
                   else{
                    // this.radarChartData.datasets[0].data[2]=3
                     this.cha.a3=3
                   }

                   //교육
                   if(res.data.data.education_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a4=5
                   }
                   else if(res.data.data.education_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a4=4
                   }
                   else if(res.data.data.education_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a4=2
                   }
                   else if(res.data.data.education_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a4=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a4=3
                   }
                   //해외경험
                   if(res.data.data.experience_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a5=5
                   }
                   else if(res.data.data.experience_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a5=4
                   }
                   else if(res.data.data.experience_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a5=2
                   }
                   else if(res.data.data.experience_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a5=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a5=3
                   }
                   //학점
                   if(res.data.data.grade_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a6=5
                   }
                   else if(res.data.data.grade_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a6=4
                   }
                   else if(res.data.data.grade_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a6=2
                   }
                   else if(res.data.data.grade_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a6=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a6=3
                   }
                   //프로젝트
                   if(res.data.data.project_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a7=5
                   }
                   else if(res.data.data.project_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a7=4
                   }
                   else if(res.data.data.project_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a7=2
                   }
                   else if(res.data.data.project_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a7=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a7=3
                   }
                   //어학
                   if(res.data.data.language_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a8=5
                   }
                   else if(res.data.data.language_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a8=4
                   }
                   else if(res.data.data.language_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a8=2
                   }
                   else if(res.data.data.language_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a8=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a8=3
                   }
                  //  var result=[a1,a2,a3,a4,a5,a6,a7,a8]
                  //  localStorage.setItem("rating",JSON.stringify(result))

                   

             })
             .catch(err=>{
               alert(err.response.data.msg)
             })
     },
   async  c_rankView(){
      await axios.get(`${url}/rank/college`)
                 .then(res=>{
                   this.viewRank=res.data.data
                   console.log('cha: ',this.cha)

                   localStorage.setItem("rank",JSON.stringify(res.data.data))
                   console.log(this.viewRank)
                    if(res.data.data.award_rank==='1등급'){
                     //this.radarChartData.datasets[0].data[0]=5
                     this.cha.a1=5
                   }
                   else if(res.data.data.award_rank==='2등급'){
                     this.cha.a1=4
                     //this.radarChartData.datasets[0].data[0]=4                      
                     }
                   else if(res.data.data.award_rank==='4등급'){
                     this.cha.a1=2
                     //this.radarChartData.datasets[0].data[0]=2
                   }
                   else if(res.data.data.award_rank==='5등급'){
                     this.cha.a1=1
                     //this.radarChartData.datasets[0].data[0]=1
                   }else{
                     this.cha.a1=3
                    //this.radarChartData.datasets[0].data[0]=3
        
                   }

                   //경력
                   if(res.data.data.career_rank==='1등급'){
                     this.cha.a2=5
                     //this.radarChartData.datasets[0].data[1]=5
                   }
                   else if(res.data.data.career_rank==='2등급'){
                     this.cha.a2=4
                     //this.radarChartData.datasets[0].data[1]=4
                   }
                   else if(res.data.data.career_rank==='4등급'){
                     this.cha.a2=2
                    //this.radarChartData.datasets[0].data[1]=2
                   }
                   else if(res.data.data.career_rank==='5등급'){
                     this.cha.a2=1
                     //this.radarChartData.datasets[0].data[1]=1
                   }else{
                     this.cha.a2=3
                    // this.radarChartData.datasets[0].data[1]=3
                   }
                   
                   //자격증
                    if(res.data.data.certificate_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[2]=5
                     this.cha.a3=5
                   }
                   else if(res.data.data.certificate_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[2]=4
                     this.cha.a3=4
                   }
                   else if(res.data.data.certificate_rank==='4등급'){
                   // this.radarChartData.datasets[0].data[2]=2
                    this.cha.a3=2
                   }
                   else if(res.data.data.certificate_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[2]=1
                     this.cha.a3=1
                   }
                   else{
                    // this.radarChartData.datasets[0].data[2]=3
                     this.cha.a3=3
                   }

                   //교육
                   if(res.data.data.education_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a4=5
                   }
                   else if(res.data.data.education_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a4=4
                   }
                   else if(res.data.data.education_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a4=2
                   }
                   else if(res.data.data.education_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a4=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a4=3
                   }
                   //해외경험
                   if(res.data.data.experience_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a5=5
                   }
                   else if(res.data.data.experience_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a5=4
                   }
                   else if(res.data.data.experience_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a5=2
                   }
                   else if(res.data.data.experience_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a5=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a5=3
                   }
                   //학점
                   if(res.data.data.grade_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a6=5
                   }
                   else if(res.data.data.grade_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a6=4
                   }
                   else if(res.data.data.grade_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a6=2
                   }
                   else if(res.data.data.grade_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a6=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a6=3
                   }
                   //프로젝트
                   if(res.data.data.project_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a7=5
                   }
                   else if(res.data.data.project_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a7=4
                   }
                   else if(res.data.data.project_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a7=2
                   }
                   else if(res.data.data.project_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a7=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a7=3
                   }
                   //어학
                   if(res.data.data.language_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a8=5
                   }
                   else if(res.data.data.language_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a8=4
                   }
                   else if(res.data.data.language_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a8=2
                   }
                   else if(res.data.data.language_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a8=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a8=3
                   }
                  //  var result=[a1,a2,a3,a4,a5,a6,a7,a8]
                  //  localStorage.setItem("rating",JSON.stringify(result))
             })
             .catch(err=>{
               alert(err.response.data.msg)
             })
     },
   async c_g_rankView(){
     await  axios.get(`${url}/rank/college/grade`)
                 .then(res=>{
                   this.viewRank=res.data.data
                   console.log('cha: ',this.cha)

                   localStorage.setItem("rank",JSON.stringify(res.data.data))
                   console.log(this.viewRank) 
                    if(res.data.data.award_rank==='1등급'){
                     //this.radarChartData.datasets[0].data[0]=5
                     this.cha.a1=5
                   }
                   else if(res.data.data.award_rank==='2등급'){
                     this.cha.a1=4
                     //this.radarChartData.datasets[0].data[0]=4                      
                     }
                   else if(res.data.data.award_rank==='4등급'){
                     this.cha.a1=2
                     //this.radarChartData.datasets[0].data[0]=2
                   }
                   else if(res.data.data.award_rank==='5등급'){
                     this.cha.a1=1
                     //this.radarChartData.datasets[0].data[0]=1
                   }else{
                     this.cha.a1=3
                    //this.radarChartData.datasets[0].data[0]=3
        
                   }

                   //경력
                   if(res.data.data.career_rank==='1등급'){
                     this.cha.a2=5
                     //this.radarChartData.datasets[0].data[1]=5
                   }
                   else if(res.data.data.career_rank==='2등급'){
                     this.cha.a2=4
                     //this.radarChartData.datasets[0].data[1]=4
                   }
                   else if(res.data.data.career_rank==='4등급'){
                     this.cha.a2=2
                    //this.radarChartData.datasets[0].data[1]=2
                   }
                   else if(res.data.data.career_rank==='5등급'){
                     this.cha.a2=1
                     //this.radarChartData.datasets[0].data[1]=1
                   }else{
                     this.cha.a2=3
                    // this.radarChartData.datasets[0].data[1]=3
                   }
                   
                   //자격증
                    if(res.data.data.certificate_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[2]=5
                     this.cha.a3=5
                   }
                   else if(res.data.data.certificate_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[2]=4
                     this.cha.a3=4
                   }
                   else if(res.data.data.certificate_rank==='4등급'){
                   // this.radarChartData.datasets[0].data[2]=2
                    this.cha.a3=2
                   }
                   else if(res.data.data.certificate_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[2]=1
                     this.cha.a3=1
                   }
                   else{
                    // this.radarChartData.datasets[0].data[2]=3
                     this.cha.a3=3
                   }

                   //교육
                   if(res.data.data.education_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a4=5
                   }
                   else if(res.data.data.education_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a4=4
                   }
                   else if(res.data.data.education_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a4=2
                   }
                   else if(res.data.data.education_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a4=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a4=3
                   }
                   //해외경험
                   if(res.data.data.experience_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a5=5
                   }
                   else if(res.data.data.experience_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a5=4
                   }
                   else if(res.data.data.experience_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a5=2
                   }
                   else if(res.data.data.experience_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a5=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a5=3
                   }
                   //학점
                   if(res.data.data.grade_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a6=5
                   }
                   else if(res.data.data.grade_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a6=4
                   }
                   else if(res.data.data.grade_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a6=2
                   }
                   else if(res.data.data.grade_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a6=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a6=3
                   }
                   //프로젝트
                   if(res.data.data.project_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a7=5
                   }
                   else if(res.data.data.project_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a7=4
                   }
                   else if(res.data.data.project_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a7=2
                   }
                   else if(res.data.data.project_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a7=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a7=3
                   }
                   //어학
                   if(res.data.data.language_rank==='1등급'){
                    // this.radarChartData.datasets[0].data[3]=5
                     this.cha.a8=5
                   }
                   else if(res.data.data.language_rank==='2등급'){
                    // this.radarChartData.datasets[0].data[3]=4
                     this.cha.a8=4
                   }
                   else if(res.data.data.language_rank==='4등급'){
                    // this.radarChartData.datasets[0].data[3]=2
                     this.cha.a8=2
                   }
                   else if(res.data.data.language_rank==='5등급'){
                    // this.radarChartData.datasets[0].data[3]=1
                     this.cha.a8=1
                   }else{
                    // this.radarChartData.datasets[0].data[3]=3
                     this.cha.a8=3
                   }
                  //  var result=[a1,a2,a3,a4,a5,a6,a7,a8]
                  //  localStorage.setItem("rating",JSON.stringify(result))
             })
             .catch(err=>{
               alert(err.response.data.msg)
             })
     },
    
  }
}