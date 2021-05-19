package com.example.backend.domain.certificate.domain;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Certificate {

    private int idx;
    private int user_idx;
    private String title;
    private String publisher;
    private Date date;
    private String content;
    private java.util.Date edit;
    private int score;


}
