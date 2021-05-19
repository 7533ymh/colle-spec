package com.example.backend.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Company {

    private int idx;
    private String name;
    private String content;
    private int pass_award;
    private int pass_career;
    private int pass_certificate;
    private int pass_education;
    private int pass_experience;
    private int pass_grade;
    private int pass_language;
    private int pass_project;


}
