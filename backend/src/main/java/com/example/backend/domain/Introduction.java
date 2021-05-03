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
public class Introduction {

    private int idx;
    private int user_idx;
    private String filepath;
    private String filename;
    private String origfilename;
    private Date edit;


}
