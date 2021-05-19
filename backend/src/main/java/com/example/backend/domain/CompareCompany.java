package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CompareCompany {

    private int idx;
    private String name;
    private String content;
    private int award_rank;
    private int career_rank;
    private int certificate_rank;
    private int education_rank;
    private int experience_rank;
    private int grade_rank;
    private int language_rank;
    private int project_rank;

}
