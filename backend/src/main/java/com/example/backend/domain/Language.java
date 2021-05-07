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
