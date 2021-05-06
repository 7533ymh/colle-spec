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
public class Award {

    private int idx;
    private int user_idx;
    private String title;
    private String agency;
    private String division;
    private int year;
    private String content;
    private Date edit;
    private int score;


}
