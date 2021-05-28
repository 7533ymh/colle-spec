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
side item을 -->> <span type=button @click="aceess('/Basic_Information')">자세히보기</span> 으로 변경
회원가입 완료후 로그인페이지로 안넘어가던 부분 수정.

---------------
21.05.28
1. 페이지들이 좁아서 xl늘림
2. 업로드 기능은 가능한 기본 작성폼 만들어놨음 (다듬어야함)
3. basic.vue 추가 포트폴리오 작성페이지로 넘어가도록 해놨음 이름 수정하긴해야함 
4. 로컬스토리지를 너무많이 쓰는 것 같은데 괜찮겠지?? 상관없을라나
5. 댓글 수정버튼 본인 댓글아니더라도 눌려지는거 막아놨음 하지만 본인댓글 수정눌렀을 시 textarea창이 모든 댓글에서 열려버림(수정할것!)
6. 등급제페이지 차트로 하는거 시간도 오래걸리고 데이터가 들어가나 새로고침을 해야 값이 변경되고 새로고침 동시에 등급조회데이터가 날라가버림 --> progressbar로 변경
7. 메인페이지에 등급요약, 포트폴리오요약에 들어갈 내용 정할 것.
8. 회사스펙비교 보완점 뭐 할지 정할 것.

슬 마무리해야함

