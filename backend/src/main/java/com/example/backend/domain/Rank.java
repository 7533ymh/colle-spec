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

    int idx;
    double award_rank;
    double career_rank;
    double certificate_rank;
    double education_rank;
    double experience_rank;
    double grade_rank;
    double language_rank;
    double project_rank;
    double all_rank;

}
