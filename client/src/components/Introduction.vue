<template>
<div>
    <div>
    <!-- Plain mode -->
    <b-form-file v-model="files" class="mt-3" plain></b-form-file>
  </div>
      <b-btn @click="upload" color="primary">Upload</b-btn>
      <b-card class="mt-3" header="Form Data Result">
                <pre class="m-0">{{ files }}</pre>
            </b-card>
            <div>
              <b-table striped hover :items="myintroduction"></b-table>
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
        files: [], 
        myintroduction:[]
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
      fd.append('files', this.files)
        axios.post(`${url}/introduction/uplode`,
            fd
          ).then( response => {
            console.log('SUCCESS!!');
            console.log(response.data)
          })
          .catch(err=>{
            console.log('FAILURE!!');
            console.log(err.response.data.msg)
            alert(err.response.data.msg);
          });
    },

  }
}
</script>