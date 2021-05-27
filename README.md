# colle-spec
2021-1학기 종합설계프로젝트 colle-spec

------제거------
21.05.24
company.vue{
    <b-row align-v="center" slot="header">....</b-row>
    불필요하므로 제거


------수정------
21.05.25
1.main.vue
모든메뉴정보들이 로그인없이 봤을 때 아무 정보도 안나오기때문에 로그인시 접근 가능하도록 변경
side item을 -->> <span type=button @click="aceess('/Basic_Information')">자세히보기</span> 으로 변경

함수추가 :
aceess(url){
      if(this.isLogin === false ){
        alert('로그인이 필요한 서비스입니다.')
      }
      else{
        this.$router.push({path:url})
      }
    }

2. store/index.js
로그인뷰에서 로그인시 이름을 띄우는 알림창과 화면이동을 store에서 처리되도록 옮김


3.views/Company.vue
progressbar 합격가능성에 따라 색깔 변경 
사이드바 회사리스트 테이블 변경


4. competitonaTable/아래 파일들
리스트 행 클릭시 화면넘어가는부분 팀버튼 클릭시 이동으로 수정

5. register.vue
회원가입 완료후 로그인페이지로 안넘어가던 부분 수정.
---------------
