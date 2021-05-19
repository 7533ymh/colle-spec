package com.example.backend.domain.career.domain;

import lombok.*;

import java.sql.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
