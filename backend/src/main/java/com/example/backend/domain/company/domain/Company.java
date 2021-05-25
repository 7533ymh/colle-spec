package com.example.backend.domain.company.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Company {

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
    private int pass_award;
    private int pass_career;
    private int pass_certificate;
    private int pass_education;
    private int pass_experience;
    private int pass_grade;
    private int pass_language;


}
