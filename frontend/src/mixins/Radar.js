import { Radar } from './BaseCharts'
import {eventBus} from '../main.js'

export default {
  extends: Radar,
  data(){
    return{
     
    }
  },
  created(){
    // eventBus.$on('senddata',function(value){
    //   this.get=value
    //       console.log('get 값: ', this.get)}.bind(this));
  
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
          data:JSON.parse(localStorage.getItem("rating"))
        }
        
      ]
    }, {responsive: false, maintainAspectRatio: false})
  }
}