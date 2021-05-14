<template>
	<div>
		<h2>게시판 리스트</h2>
		<a href="javascript:;" @click="getList">GET 방식 데이터 접근</a>
	</div>
</template>

<script>
let url = store.state.resourceHost;
import axios from 'axios';
import store from '../../store';

export default {
	methods:{
		getList() {
			axios.get(`${url}/program/board`)
			.then((res)=>{
				console.log(res);
			})
			.then((err)=>{
				console.log(err);
			})
		}
	}
}
</script>

<style scoped>

</style>