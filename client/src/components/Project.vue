<template>
    <div>
        <b-form @submit="onSubmit" @reset="onReset" v-if="show">
            <b-form-group id="input-group-1" label="프로젝트제목" label-for="input-1">
                    <b-form-input
                        id="title"
                        v-model="project.title"
                        type="text"
                        placeholder="제목입력"
                        required="required"></b-form-input>
                </b-form-group>

                <b-form-group id="input-group-1" label="프로젝트내용" label-for="input-1">
                    <textarea
                        id="content"
                        v-model="project.content"
                        placeholder="교육내용을 입력하세요"
                        required="required"></textarea>
                </b-form-group>

                <b-form-group label="성공여부" v-slot="{ ariaDescribedby }">
                    <b-form-radio v-model="project.success" :aria-describedby="ariaDescribedby" name="someradios" value=1>구현성공</b-form-radio>
                    <b-form-radio v-model="project.success" :aria-describedby="ariaDescribedby" name="someradios" value=0>구현실패</b-form-radio>
                </b-form-group>

                <b-form-group id="input-group-1" label="시작년도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="project.start_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                <b-form-group id="input-group-1" label="마감년도" label-for="input-1">
                <div id="span_date">
                    <b-form-input
                        id="year"
                        v-model="project.end_date"
                        type="date"
                        required="required"></b-form-input>
                </div>
                </b-form-group>
                <div>
                    <b-form-file v-model="project.files" class="mt-3" plain></b-form-file>
                   <b-form-file multiple v-model="project.files" aria-placeholder="이미지 파일만 가능합니다.">
                        <template slot="file-name" slot-scope="{ names }">
                            <b-badge variant="dark">{{ names[0] }}</b-badge>
                            <b-badge v-if="names.length > 1" variant="dark" class="ml-1">
                            + {{ names.length - 1 }} More files
                            </b-badge>
                        </template>
                    </b-form-file>
                </div>
                <br>
                <div>
                <b-button type="submit" variant="primary">Submit</b-button>
                <b-button type="reset" variant="danger">Reset</b-button>
                </div>
                <br>
            </b-form>
            
            <div>
              <b-table responsive="sm" striped :fields="fields" hover :items="myproject" @row-clicked="fnView" >
                    <template #cell(view)="row">
                        
        <b-button size="sm" @click="imagedown(row)" class="mr-2">
          이미지보기
        </b-button>

        <b-button size="sm" @click="row.toggleDetails" class="mr-2">
          {{ row.detailsShowing ? 'Hide' : 'Show'}}
        </b-button>
                    </template>
                    <template #cell(edit&Del)="row">
        <b-button size="sm" @click="edit(row)" class="mr-2">
          수정
        </b-button>
         <b-button size="sm" @click="deletepj(row)" class="mr-2">
          삭제
        </b-button>
       
                    </template>

        <template #row-details="row">
        <b-card>
          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>title:</b></b-col>
            <b-col>{{ row.item.title }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>content:</b></b-col>
            <b-col>{{ row.item.content }}</b-col>
          </b-row>

          <b-row class="mb-2">
            <b-col sm="3" class="text-sm-right"><b>img:</b></b-col>
            <!-- {{row.item.project_imgList[0].filepath}} -->
            <!-- <b-col><img class="product-thumb" v-bind:src="'/upload/'+scndhand.urlInfo" /></b-col> -->
            <b-col>
                <img v-for="(row, i) in datacode" :key="i" :src="'data:image/png;base64,'+datacode[i]">
            </b-col>
          </b-row>

          <b-button size="sm" @click="row.toggleDetails">Hide Details</b-button>
        </b-card>
      </template>
                    
              </b-table>             
              </div> 
            <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ project }}</pre>
                        
            </b-card>
        </div>
</template>

<script>
import axios from 'axios';
import store from '../store';

import {eventBus} from '../main.js'
let url=store.state.resourceHost; //서버주소 api


export default {
        data() {
            return {
                project: {
                    title: '',
                    content: '',
                    success:'',
                    start_date:'',
                    end_date:'',
                    files:[]
                },
                fields:['title','content','score','success','start_date','end_date','view','edit&Del'],
                
                myproject:[{}],
                send:{
                    imglist:{
                        // imgdata1:'',
                        // imgdata2:'',
                        // imgdata3:'',
                        // imgdata4:'',
                    },
                    data:[]
                    }
                ,
                datacode:[{}]
                    // a1:'',
                    // a2:'',
                    // a3:''
                ,
                imgsrc:`data:image/png;base64,${this.datacode}`,
                
                show:true
            }
        },
          created(){
                    axios.get(`${url}/project`)
                    .then(get=>{
                    for(var i=0; i<get.data.list.length; i++){
                    this.myproject.filename=get.data.list[i].project_imgList
                    this.fileimg=get.data.list[i].project_imgList
                    }
                    this.myproject=get.data.list
                    console.log('myproject',this.myproject)
                })
                },
                
        methods: {
            async fnView(item,index) {
			this.send.data=item
            console.log('idxxxx',this.send.idx)
            console.log('item', item)
            console.log('length111: ', item.project_imgList.length)
            console.log('data1',this.send.imglist.imgdata1)

                for (var i = 0; i < item.project_imgList.length; i++) {
                    console.log(i)
                let idxx=item.project_imgList[i].idx
                await axios.get(`${url}/project_img/download`,{params:{
                    idx:idxx
                },responseType: 'arraybuffer'})
                .then(res=>{
                    this.send.imglist[i]=Buffer.from(res.data, 'binary').toString('base64')
                    //this.send.imglist[i]=Buffer.from(res.data, 'binary').toString('base64')

                })
                }
                localStorage.setItem('items',JSON.stringify(this.send)); //클릭한 행의 데이터를 로컬스토리지 저장
                this.$router.push({path:'./board/ProjectDetail'}); //추가한 상세페이지 라우터
                
	},
            //프로젝트내용작성
            onSubmit(event) {
                event.preventDefault() //submit버튼 클릭시 초기화되지않도록
                var params = new FormData(); //파일업로드가 포함되어 formdata를 이용한다
                
                params.append('title', this.project.title);
                params.append('success', this.project.success);
                params.append('content', this.project.content);
                params.append('start_date', this.project.start_date);
                params.append('end_date', this.project.end_date);
                 for (var i = 0; i < this.project.files.length; i++) {
                params.append('files', this.project.files[i]);
                 }
                axios.post(`${url}/project`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(project=>{
                    console.log(project.data.msg)
                    alert(project.data.msg)
                    window.location.reload()
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })
            },
            
            //리셋
            onReset(event) {
                event.preventDefault()
                // Reset our form values
                this.title = ''
                this.content = ''
                this.start_date = ''
                this.end_date =''
                this.success =''
                this.files=''

                // Trick to reset/clear native browser form validation state
                this.show = false
                this.$nextTick(() => {
                    this.show = true
                })
            },
            pjclick(item,index,e){
                this.project=item
                console.log('index: ',index)
                console.log('item: ',item)
            },
            //이미지
            imagedown(item){
                console.log('item', item)
                console.log('length: ', item.item.project_imgList.length)
                for (var i = 0; i < item.item.project_imgList.length; i++) {
                let idxx=item.item.project_imgList[i].idx
                console.log(idxx)
                axios.get(`${url}/project_img/download`,{params:{
                    idx:idxx
                },responseType: 'arraybuffer'})
                .then(res=>{
                    // console.log('res',res)
                    // const downurl = window.URL.createObjectURL(new Blob([res.data]));
                    // const link = document.createElement('a');
                    // link.href = downurl;
                    // link.setAttribute('download', 'test.png');
                    // document.body.appendChild(link);
                    // link.click();
                    // link.remove();
                    this.datacode[i]=Buffer.from(res.data, 'binary').toString('base64')
                    //console.log(this.datacode[i])
                })
                }
                console.log('aaa',this.datacode)
            },
            deletepj(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/project`,{params:{
                    idx:del
                }})
                .then(res=>{
                    alert(res.data.msg)
                })
                .catch(err=>{
                    alert(err.response.data.msg)
                })
                console.log('delitem: ',item)

            },
            senddata(item,index,e){
                let pjidx=item.item.idx
                console.log('pjidx: ',pjidx)
                eventBus.$emit('senddata',pjidx)
                console.log('전달한 값: ',pjidx )
        
            },
            //수정버튼 누르면 밑에 숨겨진창 열리도록 값입력후 저장누르면 수정완료하는식으로..
            edit(item,index,event) {
                var params = new FormData(); //파일업로드가 포함되어 formdata를 이용한다
                params.append('idx',this.project.idx);
                params.append('title', this.project.title);
                params.append('success', this.project.success);
                params.append('content', this.project.content);
                params.append('start_date', this.project.start_date);
                params.append('end_date', this.project.end_date);
                 for (var i = 0; i < this.project.files.length; i++) {
                params.append('files', this.project.files[i]);
                 }
                axios.put(`${url}/project`,params,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(project=>{
                    console.log(project.data.msg)
                    alert(project.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })
            },
            imageview(item,index,e){
                console.log('item:::',item)
                for (var i = 0; i < item.item.project_imgList.length; i++) {
                console.log('rrr',item.item.project_imgList[i].filepath)
                this.imgsrc[i]=item.item.project_imgList[i].filepath
                }
                console.log('imgsrc:',this.imgsrc)
                
            },
         }
    }
</script>

   <style scoped="scoped">
    #content {
        width: 50%;
        height: 100px;
        resize: none;
    }
    #agency {
        width: 50%;
    }
    #title,
    #year {
        width: 20%;
    }
</style>
