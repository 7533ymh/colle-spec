package com.example.backend.domain.company.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CompareCompany {

    private int idx;
    private String name;
    private String industry;
    private String people;
    private String division;
    private String establishment;
    private String capital;
    private String take;
    private String representative;
    private String salary;
    private String bussiness;
    private String insurance;
    private String url;
    private String address;
    private int award_rank;
    private int career_rank;
    private int certificate_rank;
    private int education_rank;
    private int experience_rank;
    private int grade_rank;
    private int language_rank;

}
