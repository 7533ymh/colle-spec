package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class RankResult {

    int idx;
    String award_rank;
    String career_rank;
    String certificate_rank;
    String education_rank;
    String experience_rank;
    String grade_rank;
    String language_rank;
    String project_rank;
    String all_rank;


}
