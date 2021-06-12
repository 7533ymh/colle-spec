import Chart from 'chart.js'

export function generateChart (chartId, chartType) {
  return {
    render: function (createElement) {
      return createElement(
        'div', {
          style: this.styles,
          class: this.cssClasses
        },
        [
          createElement(
            'canvas', {
              attrs: {
                id: this.chartId,
                width: this.width,
                height: this.height
              },
              ref: 'canvas'
            }
          )
        ]
      )
    },

    props: {
      chartId: {
        default: chartId,
        type: String
      },
      width: {
        default: 400, //차트 위치
        type: Number
      },
      height: {
        default: 300, //차트 위치(높이)
        type: Number
      },
      cssClasses: {
        type: String,
        default: ''
      },
      styles: {
        type: Object
      },
      plugins: {
        type: Array,
        default () {
          return []
        }
      }
    },

    data () {
      return {
        _chart: null,
        _plugins: this.plugins
      }
    },

    methods: {
      addPlugin (plugin) {
        this.$data._plugins.push(plugin)
      },
      generateLegend () {
        if (this.$data._chart) {
          return this.$data._chart.generateLegend()
        }
      },
      renderChart (data, options) {
        if (this.$data._chart) this.$data._chart.destroy()
        if (!this.$refs.canvas) throw new Error('Please remove the <template></template> tags from your chart component. See https://vue-chartjs.org/guide/#vue-single-file-components')
        this.$data._chart = new Chart(
          this.$refs.canvas.getContext('2d'), {
            type: chartType,
            data: data,
            options: options,
            plugins: this.$data._plugins
          }
        )
      }
    },
    beforeDestroy () {
      if (this.$data._chart) {
        this.$data._chart.destroy()
      }
    }
  }
}


export const Radar = generateChart('radar-chart', 'radar')
export const HorizontalBar = generateChart('horizontalbar-chart', 'horizontalBar')

export default {
  
  Radar,
  HorizontalBar
}