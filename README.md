# 대학생활 포트폴리오 관리 시스템
> 2021-1학기 종합설계프로젝트 colle-spec

포트폴리오를 손쉽게 관리할 수 있고 등록한 포트폴리오로 등급을 확인할 수 있는 웹 시스템

<br>

## 1.제작 기간 & 참여 인원 
- 2021.03 ~ 2021.07
- 유민환, 최동민, 이수영 

<br>

## 2.  추진 배경 및 목적 
현재 취업시장에서 학력 , 학점 등도 중요하지만 제일 중요시하는 것이 포트폴리오이다. 기업에서는 포트폴리오를 보고 이 인원이 어느 정도의 실력을 가지고 있고 얼마나 노력과 공부를 했는지에 대한 중요한 척도로 보고 있다. 이 같이 포트폴리오가 매우 중요하다는 것을 느낄 수 있는데 현 시점에서 대학생의 경우 그 중요성을 알고는 있지만 어떻게 관리해야할지는 잘 모르는 상황이다. 그러다가 보면 졸업학년이 되어서야 포트폴리오를 작성하는 경우가 생기지만 이미 몇 년 전의 활동들에 대한 데이터와 경험들이 잘 생각이 나지 않는 경우가 다반사이다. 또한, 저학년의 경우 어떻게 포트폴리오를 구성해 나아가야 할지에 대한 정보를 접하는 경우가 많이 없기 때문에 기회를 놓치는 경우도 많다.

포트폴리오를 관리하는 대학생들 중에서는 개인 블로그나 문서로 작성하는 경우가 많은데 이러한 방법으로 관리하게 되면 한눈에 보기도 어렵고 관리하기도 꽤 까다로운 경우가 많다. 또한, 자신의 포트폴리오가 어느 정도의 완성도를 가지고 있는지에 대한 비교가 쉽지 않다.

이러한 문제점에서 대학 포트폴리오 관리 시스템 Colle-spec의 필요성을 생각하게 되었다. 

이 프로젝트를 통해 졸업학년뿐만 아니라 저학년도 차근차근 포트폴리오를 작성해나갈 수 있고 때에 따라 자신의 포트폴리오를 손쉽게 편집하고 관리할 수 있는 서비스와 등록된 포트폴리오를 기반으로 회원들과의 비교를 통해 등급을 산정하여 자신의 위치를 한눈에 파악할 수 있는 서비스를 제공한다. 또한, 산정된 등급을 통해 부족한 포트폴리오의 경우 프로그램 정보 추천과 기업정보와 해당 기업의 합격자와의 비교를 통해 취업에도 도움이 되고자 한다.

<br>

## 3. 개발 목표 
- 포트폴리오를 업로드 및 관리할 수 있게 하여 손쉽게 정리할 수 있도록 한다.

- 등급제를 통하여 자신의 위치가 어느 정도인지 가늠할 수 있게 한다.

- 공모전 , 프로그램 등 포트폴리오에 도움이 되는 정보를 제공 및 추천한다.

- 기업에서 요구하는 스펙에 맞게 포트폴리오의 보완점을 체크한다.

- 다른 팀과의 연동을 통해 대학생활에 대한 정보를 받아와 포트폴리오에 추가한다.

<br>

## 4. 구현 환경
- 개발환경 :　Window 10, VSCODE, IntelliJ, GitHub

- 사용언어 : Javascript, HTML5, CSS, JAVA 

- 서버 / 데이터베이스 : nginx 1.14.0, ,MariaDB 10.3.29, Jenkins

- 라이브러리 : Vue.js, BootStrap, Spring Boot, JSOUP

<br>

## 5. 설계 
### 전체적인 설계 
![그림2](https://user-images.githubusercontent.com/65746780/125001791-6d614080-e08e-11eb-9c9b-391443b638b2.jpg)
### 프론트 엔드
![123](https://user-images.githubusercontent.com/65746780/125001910-b44f3600-e08e-11eb-8943-63c1e641dd17.JPG)
### 백엔드 
![그림3](https://user-images.githubusercontent.com/65746780/125001819-7f42e380-e08e-11eb-9918-909bfb257d97.jpg)
### ERD
![Untitled](https://user-images.githubusercontent.com/65746780/139865347-48fe21c5-a75b-4db3-84a2-4f06a6ed067e.png)


<br>

## 6. 개발 내용 
### 알고리즘
1. 포트폴리오 수치화 알고리즘
    + 등록한 포트폴리오를 통해 등급을 산정하기 위해 수치화를 진행한다. 각 입력한 포트폴리오의 정보에 따라 점수 부여방식에 차별을 두어 점수를 산정한다. 점수에 차별을 둠으로써 등급제 알고리즘을 사용 시 각 회원의 등급이 알맞게 산정될 수 있도록 한다.  또한, 등급제 알고리즘을 위해 score테이블에 별도로 수치화 점수를 저장한다.

2. 등급제 알고리즘
    + 회원들의 포트폴리오 비교를 위해 등급을 부여하는 제도로 전체학년, 회원학년, 회원대학교, 회원 대학교&학년별 대상끼리 각 포트폴리오 간의 점수를 비교하여 누적분포를 계산 후 범위를 두어 등급을 산정할 수 있도록 한다. 이는 SQL문의 CUM_DIST를 통해 구현한다. 
 하지만, 자격증과 교육이수, 학점의 경우 회원들 간의 점수가 겹치는 경우가 생겨 알맞은 등급이 산정이 안 되므로 평균을 구해 범위를 두어 등급을 산정하도록 한다. 
 등급의 경우 5등급으로 나누어 매우 잘함, 잘함, 평균, 못함, 매우 못함으로 회원이 한눈에 확인할 수 있도록 한다. 
 
 3. 기업 합격자 평균 스펙 비교 알고리즘 
    + 크롤링으로 가져온 기업 합격자 스펙을 수치화 알고리즘을 통해 수치화 시켜 company테이블에 저장한다. 이후, 합격자 스펙과 회원 스펙의 점수를 비교하여 둘의 차이가 수치화 알고리즘에 입각한 범위에 적용하여 등급을 산정하도록 한다. 등급의 경우 등급제 알고리즘과 마찬가지로 5등급을 기준으로 한다. 

### 서비스 개발 
1. 회원 서비스
    + 백엔드 서버의 경우 REST API 서버로써 Stateless 즉, 상태를 저장하지 않기 때문에 Spring Security와 JWT토큰을 사용하여 로그인 상태를 확인한다. 클라이언트로부터 전달받은 ID, PASSWORD가 데이터베이스에 저장되어 있는 ID와 PASSWORD가 동일한지 확인 후 동일하다면 회원 번호를 가지고 있는 JWT토큰을 발급한다. 클라이언트는 이를 로컬스토리지에 저장하여 서비스 호출 시 헤더에 포함해 서버에 요청하여 해당 회원의 로그인 상태를 인지하게 해주어 요청한 서비스를 제공하도록 한다. 이는 회원가입, 로그인, 아이디 중복 검사 이외에 모든 서비스에 적용된다.

2. 포트폴리오 관리
    + 클라이언트에서는 각 포트폴리오에 맞는 입력 폼을 통해 포트폴리오를 입력하고 서버로 포트폴리오 정보를 전달한다. 이를 받은 서버에서는 포트폴리오 정보를 포트폴리오 테이블에 저장한다. 이때, 점수의 경우 수치화 점수 알고리즘에 입각하여 점수를 산정해 같이 저장하도록 하고 score테이블에도 각 항목에 대한 점수를 저장하도록 한다. 조회, 수정, 삭제는 클라이언트로부터 회원 번호, 해당 포트폴리오의 번호를 전달받아 구분하여 수행하도록 하고 클라이언트에서는 전달받은 정보를 테이블형식으로 보여주도록 한다. 
  
3. 등급제 확인
    + 클라이언트에서 원하는 등급을 요청하게 되면 서버에서는 등급제 알고리즘에 입각하여 등급을 산정해 클라이언트에게 전달한다. 
 전달받은 클라이언트는 해당 정보를 ProgressBar를 통해 한눈에 볼 수 있도록 한다. 또한, 부족한 포트폴리오의 경우 등급을 업그레이드할 수 있는 정보를 제공하여 포트폴리오를 강화할 수 있도록 한다.
 
4. 다른 팀과의 연동 
    + Colleasy팀의 데이터를 가져오기 위해 다중 데이터베이스를 사용한다. 
 Colleasy팀의 데이터베이스에 접속하여 입력한 ID, PASSWORD가 일치하다면 접속 권한을 부여하고 대학생활에 대한 정보를 받아와 Colle-spec팀의 데이터베이스에 저장하도록 한다. 
 이를 통해 등급 확인 시 대학교, 대학교&학년 등급을 확인할 수 있도록 한다.  
 
5. 프로그램 정보
    + 프로그램 정보의 경우 크롤링을 통하여 데이터를 가져온다. 
 크롤링을 매일 00시에 하도록 하여 정보를 최신상태로 유지하도록 한다.
 클라이언트에서 프로그램 정보를 요청하게 되면 서버에서는 원하는 프로그램 정보를 전달해주고 클라이언트는 이를 받아 테이블 형식으로 보여주도록 한다. 
 또한, 팀 게시판은 프로그램 번호와 게시판 번호를 통해 구분하여 저장하도록 한다.

6. 기업 정보 
    + 기업 정보의 경우 크롤링을 통하여 데이터를 가져온다. 
 클라이언트에서 회사 정보 요청 시 서버에서는 정보와 기업 합격자 스펙 비교 알고리즘에 입각하여 산정한 등급을 전달하도록 한다. 
 전달받은 클라이언트는 등급의 경우 ProgressBar를 통해 한눈에 볼 수 있도록 한다.
