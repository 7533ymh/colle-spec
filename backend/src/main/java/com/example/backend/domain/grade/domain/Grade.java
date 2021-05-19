package com.example.backend.domain.grade.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Grade {

    int idx;
    int user_idx;
    String grade;
    String semester;
    Double avg_score;
    Date edit;


}
