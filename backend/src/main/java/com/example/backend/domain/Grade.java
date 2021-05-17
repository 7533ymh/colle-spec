package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Grade {

    int idx;
    int user_idx;
    String grade;
    String semester;
    Double avg_score;
    Date edit;


}
