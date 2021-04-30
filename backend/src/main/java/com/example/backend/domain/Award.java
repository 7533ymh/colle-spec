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

    int idx;
    int user_idx;
    String title;
    String agency;
    String division;
    int year;
    String content;
    Date edit;
    int score;


}
