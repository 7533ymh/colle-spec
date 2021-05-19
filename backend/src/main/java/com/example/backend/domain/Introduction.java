package com.example.backend.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Introduction {

    private int idx;
    private int user_idx;
    private String filepath;
    private String filename;
    private String origfilename;
    private Date edit;


}
