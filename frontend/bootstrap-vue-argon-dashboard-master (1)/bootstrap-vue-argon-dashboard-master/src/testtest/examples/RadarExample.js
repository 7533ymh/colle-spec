import { Radar } from '../BaseCharts'
var getdata=[10,10,10,10,10,10,10]
export default {
  
  extends: Radar,
  data(){return{
    aaaaaaaaaaaaaaaaaaaaaaaaaaa:'dsdaddasdadadasdasda',
  
  }},
 
  mounted () {
    console.log('radar',this.getdata)
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
          data:getdata
        },
        
      ]
    }, {responsive: true, maintainAspectRatio: false})
  }
}
