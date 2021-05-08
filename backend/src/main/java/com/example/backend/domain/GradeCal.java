package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class GradeCal {

    int idx;
    int user_idx;
    double all_avg_score;
    int all_semester;
    int score;


}
