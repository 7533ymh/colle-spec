package com.example.backend.domain;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Experience {

    private int idx;
    private int user_idx;
    private String country;
    private Date start_date;
    private Date end_date;
    private String content;
    private java.util.Date edit;
    private int score;

}
