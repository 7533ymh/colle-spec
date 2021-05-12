package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Rank {

    private int idx;
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
