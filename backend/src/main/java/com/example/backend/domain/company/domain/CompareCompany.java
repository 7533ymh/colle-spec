package com.example.backend.domain.company.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompareCompany {

    private int idx;
    private String name; //회사 이름
    private String industry; // 산업
    private String people; // 사원 수
    private String division; // 기업구분
    private String establishment;// 설립일
    private String capital; //자본금
    private String take; //매출액
    private String representative; //대표자
    private String salary; // 대졸초임
    private String bussiness; // 주요사업
    private String insurance; // 4대 보험
    private String url;  // 홈페이지
    private String address; // 주소
    private int award_rank;
    private int career_rank;
    private int certificate_rank;
    private int education_rank;
    private int experience_rank;
    private int grade_rank;
    private int language_rank;

}
