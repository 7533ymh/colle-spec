<template>
  <div>
        <base-header class="pb-7 pt-md-5 bg-gradient-success">
            <!-- Card stats -->
            <b-row>
                <b-col xl="3" md="6">
                    <stats-card title="" type="gradient-red" sub-title="어학 정보" class="mb-4"></stats-card>
                </b-col>
            </b-row>
        </base-header>
        <!--Charts-->
       
          <b-container fluid="fluid" class="mt--10">
    <b-row>
        <b-col xl="10" class="mb-5 mb-xl-0">
            <card header-classes="bg-transparent">

                <div>
                    <!-- <div id="wrapper"> -->
                    <div id="contents">
                        
                    <h2>TOEIC</h2>
                        <div  id="toeic" >
                    <div >
                        <div >
                            <div >
                                <a href="https://exam.toeic.co.kr/" class="se-oglink-thumbnail" target="_blank" >
                                    <img src="https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fimagesisa.ybmsisa.com%2Fplatform%2Fcommon%2Fmobile%2Fm_logo_toeic.png%22&amp;type=ff120" class="se-oglink-thumbnail-resource" alt="" >
                                </a>
                                <a href="https://exam.toeic.co.kr/" class="se-oglink-info" target="_blank" >
                                    <div>
                                        <strong class="se-oglink-title" >TOEIC</strong>

                                        <p class="se-oglink-summary" >국제 실용영어능력시험</p>
                                        <p class="se-oglink-url" >exam.toeic.co.kr</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <h2>TOEIC Speaking and Writing Tests</h2>
                <div  id="toeicsw" >
                    <div >
                        <div >
                            <div >
                                <a href="https://www.toeicswt.co.kr/" class="se-oglink-thumbnail" target="_blank" >
                                </a>
                                <a href="https://www.toeicswt.co.kr/" class="se-oglink-info" target="_blank" >
                                    <div>
                                        <strong class="se-oglink-title" >TOEIC Speaking and Writing Tests</strong>

                                        <p class="se-oglink-summary" >TOEIC 말하기, 쓰기시험</p>
                                        <p class="se-oglink-url" >www.toeicswt.co.kr</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                    <script type="text/data" class="__se_module_data" data-module="{&quot;type&quot;:&quot;v2_oglink&quot;, &quot;id&quot; :&quot;SE-3dd1f076-052f-49ee-ade0-8e3ccd923aba&quot;, &quot;data&quot; : {&quot;link&quot; : &quot;https://www.toeicswt.co.kr/&quot;, &quot;isVideo&quot; : &quot;false&quot;, &quot;thumbnail&quot; : &quot;https://dthumb-phinf.pstatic.net/?src=%22https%3A%2F%2Fimagesisa.ybmsisa.com%2Fplatform%2Fcommon%2Fmobile%2Fm_logo_toeicswt.png%22&amp;type=ff120&quot;}}" ></script>
                </div>
                <hr>
                <h2>OPIC</h2>
                <div  id="opic" >
                    <div >
                        <div >
                            <div >
                                <a href="https://www.opic.or.kr/opics/jsp/senior/index.jsp" class="se-oglink-thumbnail" target="_blank" >
                                </a>
                                <a href="https://www.opic.or.kr/opics/jsp/senior/index.jsp" class="se-oglink-info" target="_blank" >
                                    <div>
                                        <strong class="se-oglink-title" >외국어말하기평가 OPIc</strong>

                                        <p class="se-oglink-url" >www.opic.or.kr</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <h2>TEPS</h2>
                <div  id="teps" >
                    <div >
                        <div >
                            <div >
                                <a href="https://www.teps.or.kr/" class="se-oglink-thumbnail" target="_blank" >
                                </a>
                                <a href="https://www.teps.or.kr/" class="se-oglink-info" target="_blank" >
                                    <div>
                                        <strong class="se-oglink-title" >민간자격국가공인 영어능력검정</strong>

                                        <p class="se-oglink-url" >www.teps.or.kr</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <h2>HSK</h2>
                <div  id="hsk" >
                    <div >
                        <div >
                            <div >
                                <a href="http://www.hsk.or.kr/" class="se-oglink-thumbnail" target="_blank" >
                                </a>
                                <a href="http://www.hsk.or.kr/" class="se-oglink-info" target="_blank" >
                                    <div>
                                        <strong class="se-oglink-title" >중국어 능력시험</strong>

                                        <p class="se-oglink-url" >www.hsk.or.kr</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                <hr>
                <h2>JLPT</h2>
                <div  id="JLPT" >
                    <div >
                        <div >
                            <div >
                                <a href="https://www.jlpt.or.kr/index.html" class="se-oglink-thumbnail" target="_blank" >
                                </a>
                                <a href="https://www.jlpt.or.kr/index.html" class="se-oglink-info" target="_blank" >
                                    <div>
                                        <strong class="se-oglink-title" >일본어 능력시험</strong>

                                        <p class="se-oglink-url" >www.jlpt.or.kr</p>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
                

                    </div>
                    <!---content끝-->
            </div>
        </card>
    </b-col>
</b-row>
<!-- End charts-->
</b-container>      
  </div>
</template>
<script>
    let url = store.state.resourceHost;

    import axios from 'axios';
    import store from '@/store';

    // Components
    import BaseProgress from '@/components/BaseProgress';
    import StatsCard from '@/components/Cards/StatsCard';


  import { Table, TableColumn, Button} from 'element-ui'

    export default {
        components: {
            BaseProgress,
            StatsCard,
           
      [Table.name]: Table,
      [TableColumn.name]: TableColumn,
      [Button.name]: Button,
        },
        data() {
            
            return {
                
            }
        },

    }
</script>
<style>
    #contents {
        /* border: 1px solid #487BE1; */
        width: 100%;
        float: left;
        padding: 10px;

    }
</style>
