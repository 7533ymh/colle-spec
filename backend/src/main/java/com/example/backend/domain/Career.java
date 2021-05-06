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

    private int idx;
    private int user_idx;
    private String division;
    private String company;
    private String department;
    private Date start_date;
    private Date end_date;
    private String content;
    private java.util.Date edit;
    private int score;


}
