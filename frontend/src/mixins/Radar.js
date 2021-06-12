import { Radar } from './BaseCharts'

export default {
  extends: Radar,
  data(){
    return{
     
    }
  },
  created(){
    
  },
  mounted () {
    
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
          backgroundColor: 'rgba(255,181,198,0.2)',
          borderColor: 'rgba(255,181,198,1)',
          pointBackgroundColor: 'rgba(255,181,198,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(180,181,198,1)',
          data:JSON.parse(localStorage.getItem("mRating")), //로그인시 등급조회 저장값 불러오기
          datas:JSON.parse(localStorage.getItem("mRank")),       
        }
      ],
    },

    {
      
      scale:{
        gridLines: {
          lineWidth: 1
        },
        angleLines: {
          display: true
        },
        ticks:{
          min:0,
          max:5,
          stepSize: 1,
          display: false
        }}
      
      ,
      tooltips: {
        beforeLabel:'ddd',
        displayColors: false, // 툴팁 바 컬러 표시 여부
        //backgroundColor: 'rgba(180,181,198,1)', // 툴팁 배경
        titleFontColor: '#fff', // 툴팁 폰트 관련
        titleAlign: 'center', // 툴팁 폰트 관련
        bodySpacing: 2, // 툴팁 폰트 관련
        bodyFontColor: '#fff', // 툴팁 폰트 관련
        bodyAlign: 'center', // 툴팁 폰트 관련
        footerFontStyle: 'bold', // 툴팁 폰트 관련
        footerFontColor: '#fff', // 툴팁 폰트 관련
        footerAlign: 'center', // 툴팁 폰트 관련
        
        
        callbacks: {
          title: function(tooltipItem, data) { // 툴팁 타이틀
            return false;
        },
            label: function(tooltipItem, data) {
            return data['labels'][tooltipItem['index']] + ": " + data['datasets'][0]['datas'][tooltipItem['index']];
            },
        }
    },
   
      responsive: true, maintainAspectRatio: false
    })
  }
}