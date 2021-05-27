import { Radar } from './BaseCharts'
import {eventBus} from '../main.js'

export default {
  extends: Radar,
  data(){
    return{
      a:7,b:5,
      get:[]
    }
  },
  created(){
    eventBus.$on('senddata',function(value){
      this.get=value
          console.log('get 값: ', this.get)}.bind(this));
  
  },
  mounted () {
    
    this.renderChart({
      labels: ['Eating', 'Drinking', 'Sleeping', 'Designing', 'Coding', 'Cycling', 'Running'],
      datasets: [
        {
          label: 'My First dataset',
          backgroundColor: 'rgba(179,181,198,0.2)',
          borderColor: 'rgba(179,181,198,1)',
          pointBackgroundColor: 'rgba(179,181,198,1)',
          pointBorderColor: '#fff',
          pointHoverBackgroundColor: '#fff',
          pointHoverBorderColor: 'rgba(179,181,198,1)',
          data:this.get
        }
        
      ]
    }, {responsive: false, maintainAspectRatio: false})
  }
}