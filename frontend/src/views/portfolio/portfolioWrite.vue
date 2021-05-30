<template>
    <div>
        <!--작성 폼-->
  </div>
</template>

<script>
export default {
    data(){
        return{
            award: {
                    agency: '',     //수여기관
                    content: '',    //상 내용
                    division:'',    //상 종류 (셀렉트로 고르도록) 대상, 최우수상, 우수상, 금상, 은상, 동상, 장려상
                    title:'',       //상 이름
                    year:'',        //수여날짜
                },
            career: {
                    company: '',    //회사이름
                    content: '',    //업무내용
                    department: '', //부서
                    division:'',    //업무역할
                    start_date:'',  //시작날짜
                    end_date:'',    //종료날짜
                },
            certificate: {
                    title: '',      //자격증 이름
                    content: '',    //자격증 내용
                    publisher:'',   //발급기관
                    date:'',        //취득날짜
                }, 
            edu: {
                    agency: '',     //교육기관
                    content: '',    //교육내용
                    title:'',       //제목
                    start_date:'',  //교육시작날짜
                    end_date:'',    //교육종료날짜
                },
            experience: {
                    country: '',    //나라이름
                    content: '',    //내용
                    start_date:'',  //시작날짜
                    end_date:'',    //종료날짜
                },
            lang: {
                    exam: '',       //시험명    (셀렉트로 고르도록)
                    content: '',    //시험내용
                    division:'',    //시험 언어 구분 ....영어, 중국어, 일본어(셀렉트로 고르도록)
                    date:'',        //시험 친 날짜
                    exam_score:'',  //시험 점수
                },
            project: {
                    title: '',      //제목
                    content: '',    //내용
                    success:'',     //성공여부 0 구현실패 1구현성공
                    start_date:'',  //시작날
                    end_date:'',    //종료날
                    files:[]        //프로젝트 파일 저장
                },
            introduction:{
                files:[] //자기소개서 파일 저장
            }       
        }
    },
    methods:{
        onSubmit() {
                //event.preventDefault()
////////////////////////////////////////////////////////////////////////////////////////////////////
                //수상 작성
                var award = new URLSearchParams();
                award.append('agency', this.award.agency);
                award.append('content', this.award.content);
                award.append('division', this.award.division);
                award.append('title', this.award.title);
                award.append('year', this.award.year);
                axios.post(`${url}/award`,award)
                .then(award=>{
                    console.log(award)
                    alert(award.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)
                })

////////////////////////////////////////////////////////////////////////////////////////////////////
                //경력 작성
                var career = new URLSearchParams();
                career.append('company', this.career.company);
                career.append('content', this.career.content);
                career.append('department', this.career.department);
                career.append('division', this.career.division);
                career.append('end_date', this.career.end_date);
                career.append('start_date', this.career.start_date);
                axios.post(`${url}/career`,career)
                .then(career=>{
                    console.log(career.data.msg)
                    console.log(career)
                    alert(career.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                })

////////////////////////////////////////////////////////////////////////////////////////////////////
                //자격증 작성
                var certificate = new URLSearchParams();
                certificate.append('title', this.certificate.title);
                certificate.append('content', this.certificate.content);
                certificate.append('publisher', this.certificate.publisher);
                certificate.append('date', this.certificate.date);
                axios.post(`${url}/certificate`,certificate)
                .then(certificate=>{
                    console.log(certificate)
                    alert(certificate.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })
////////////////////////////////////////////////////////////////////////////////////////////////////   
                //교육이수 작성
                var education = new URLSearchParams();
                education.append('title', this.edu.title);
                education.append('agency', this.edu.agency);
                education.append('content', this.edu.content);
                education.append('start_date', this.edu.start_date);
                education.append('end_date', this.edu.end_date);
                axios.post(`${url}/education`,education)
                .then(edu=>{
                    console.log(edu)
                    alert(edu.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })

////////////////////////////////////////////////////////////////////////////////////////////////////
                //어학 작성
                var language = new URLSearchParams();
                language.append('exam', this.lang.exam);
                language.append('content', this.lang.content);
                language.append('division', this.lang.division);
                language.append('exam_score', this.lang.exam_score);
                language.append('date', this.lang.date);
                axios.post(`${url}/language`,language)
                .then(lang=>{
                    console.log(lang)
                    alert(lang.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })
////////////////////////////////////////////////////////////////////////////////////////////////////
                //프로젝트 작성
                var project = new FormData(); //파일업로드가 포함되어 formdata를 이용한다
    
                project.append('title', this.project.title);
                project.append('success', this.project.success);
                project.append('content', this.project.content);
                project.append('start_date', this.project.start_date);
                project.append('end_date', this.project.end_date);
                 for (var i = 0; i < this.project.files.length; i++) {
                project.append('files', this.project.files[i]);
                 }
                axios.post(`${url}/project`,project,{
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
////////////////////////////////////////////////////////////////////////////////////////////////////
                //해외경험 작성
                var experience = new URLSearchParams();
                experience.append('country', this.experience.country);
                experience.append('content', this.experience.content);
                experience.append('start_date', this.experience.start_date);
                experience.append('end_date', this.experience.end_date);
                axios.post(`${url}/experience`,experience)
                .then(experience=>{
                    console.log(experience)
                    alert(experience.data.msg)
                    window.location.reload()
                })
                .catch(err=>{
                    console.log(err)
                    alert(err.response.data.msg)

                })

////////////////////////////////////////////////////////////////////////////////////////////////////
                //자기소개서 작성
                var introduction = new FormData();
                for (var i = 0; i < this.introduction.files.length; i++) {
                        fd.append('files', this.introduction.files[i]);
                        }
                        axios.post(`${url}/introduction/uplode`,introduction,{
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

////////////////////////////////////////////////////////////////////////////////////////////////////
               
            },


    },

}
</script>
