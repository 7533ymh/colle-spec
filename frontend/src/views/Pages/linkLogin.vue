<template>
    <div>
        <form @submit.prevent="linkLogin">
                <div class="black-bg">
                  <div class="white-bg">
                    <button type="button" @click="close" tabindex="-1" class="close wj-hide">&times;</button>
                    <h2>Colleasy</h2>
                      <label>
                        ID:<br />
                    <input id="input" type="text" v-model="id" required>
                      </label>
                    <br />
                    <label>
                        PW:<br />
                    <input id="input" type="password" v-model="pass" required>
                    <div>
                    <button type="submit" class="el-button" id="login">
                        Log in
                    </button>
                    </div>
                    </label>
                    
                  </div>
          </div>
          </form>
    </div>
</template>
<script>
import axios from 'axios'
  import store from '@/store'
  let url=store.state.resourceHost;
export default{
    data() {return{
        id:'',
        pass:'',
        //modalshow:true
    }},

    
   
}
</script>
