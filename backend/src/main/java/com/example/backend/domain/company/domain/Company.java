package com.example.backend.domain.company.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Company {

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
    private int pass_award;
    private int pass_career;
    private int pass_certificate;
    private int pass_education;
    private int pass_experience;
    private int pass_grade;
    private int pass_language;


}
