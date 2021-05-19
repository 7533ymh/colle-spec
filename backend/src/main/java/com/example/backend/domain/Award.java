package com.example.backend.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
