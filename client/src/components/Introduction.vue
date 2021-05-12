<template>
<div>
    <div>
         <!-- <b-form-file v-model="files" class="mt-3" plain></b-form-file> -->
            <b-form-file multiple v-model="files">
                <template slot="file-name" slot-scope="{ names }">
                    <b-badge variant="dark">{{ names[0] }}</b-badge>
                    <b-badge v-if="names.length > 1" variant="dark" class="ml-1">
                        + {{ names.length - 1 }} More files
                    </b-badge>
                </template>
            </b-form-file>
    </div>
      <b-btn @click="upload" color="primary">Upload</b-btn>
      <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ files }}</pre>
            </b-card>
            <div>
              <b-table responsive="sm" :fields ="fields" striped hover :items="myintroduction" @row-clicked="click">
                  <template #cell(down)="row">
                    <b-button size="sm" @click="download(row)" class="mr-2">
                    내려받기
                    </b-button>
                
                </template>
                  <template #cell(edit&Del)="row">
                    <b-button size="sm" @click="edit(row)" class="mr-2">
                    수정
                    </b-button>
                    <b-button size="sm" @click="deleteInt(row)" class="mr-2">
                    삭제
                    </b-button>
                </template>
              </b-table>
              </div> 

</div>
</template>

<script>
    import axios from 'axios'
    import store from '../store';

    let url = store.state.resourceHost; //서버주소 api
    export default {
        data() {
            return {
        files:[], 
        myintroduction:[],
        fields:['origfilename','down','edit&Del'],
            }
        },
         created(){
                    axios.get(`${url}/introduction`)
                    .then(get=>{
                    this.myintroduction=get.data.list
                    console.log('myintroduction: ',this.myintroduction)
                })
                },
         methods: {
            upload() {
                var fd = new FormData();
                for (var i = 0; i < this.files.length; i++) {
                        fd.append('files', this.files[i]);
                        }
                        axios.post(`${url}/introduction/uplode`,fd,{
                            headers:{
                                'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                            }
                        })
                .then( response => {
                    console.log(response.data)
                    alert(response.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err.response.data.msg)
                    alert(err.response.data.msg);
                });
            },
            download(item,index,e){
                let idxx=item.item.idx
                axios.get(`${url}/introduction/download`,{params:{
                    idx:idxx,
                    responseType: "blob",
                }})
                .then(res=>{
                     console.log(res)
                    const url = window.URL.createObjectURL(new Blob([res.data], { type: 'application/pdf' }));
                    const link = document.createElement('a');
                    link.href = url;
                    link.setAttribute('download', 'filename');
                    document.body.appendChild(link);
                    link.click();
                     
                }).catch(err=>{
                    alert(err)
                })
            },
            click(item,index,e){
                console.log('index: ',index)
                console.log('item: ',item)
            },
            edit(item,index,event) {
                var fd = new FormData();
                for (var i = 0; i < this.fd.files.length; i++) {
                        fd.append('files', this.fd.files[i]);
                        }
                axios.put(`${url}/introduction/uplode`,fd,{
                    headers:{
                        'Content-Type' : 'multipart/form-data' //다중파일 업로드하기 위해 헤더 추가
                    }
                })
                .then(res=>{
                    console.log(res)
                    alert(res.data.msg)
                    window.location.reload()
                    
                })
                
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })    
            },
            deleteInt(item,index,e){
                let del=item.item.idx
                
                console.log('del idx: ',del)
                axios.delete(`${url}/introduction`,{params:{
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

  }
}
</script>