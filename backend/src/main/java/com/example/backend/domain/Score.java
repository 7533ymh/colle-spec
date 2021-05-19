package com.example.backend.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Score {

    private int idx;
    private int user_idx;
    private int award_score;
    private int career_score;
    private int certificate_score;
    private int education_score;
    private int experience_score;
    private int grade_score;
    private int language_score;
    private int project_score;
    private int score;


}
