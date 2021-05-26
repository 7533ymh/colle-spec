package com.example.backend.domain.rank.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rank {

    private int user_idx;
    private double award_rank;
    private double career_rank;
    private int certificate_rank;
    private int education_rank;
    private double experience_rank;
    private int grade_rank;
    private double language_rank;
    private double project_rank;
    private double all_rank;

}
