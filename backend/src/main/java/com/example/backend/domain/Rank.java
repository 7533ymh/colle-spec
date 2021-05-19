package com.example.backend.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Rank {

    private int user_idx; //user_idx
    private double award_rank;
    private double career_rank;
    private double certificate_rank;
    private double education_rank;
    private double experience_rank;
    private double grade_rank;
    private double language_rank;
    private double project_rank;
    private double all_rank;

}
