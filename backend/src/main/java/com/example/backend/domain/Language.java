package com.example.backend.domain;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Language {

    private int idx;
    private int user_idx;
    private String divsion;
    private String exam;
    private String exam_score;
    private Date date;
    private String content;
    private java.util.Date edit;
    private int score;

}
