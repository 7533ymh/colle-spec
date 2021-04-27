package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Career {

    int idx;
    int user_idx;
    String division;
    String company;
    String department;
    Date start_date;
    Date end_date;
    String content;
    java.util.Date edit;
    int score;


}
