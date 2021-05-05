package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Project {

    private int idx;
    private int user_idx;
    private String title;
    private Date start_date;
    private Date end_date;
    private String content;
    private java.util.Date edit;
    private int score;
    private int success;
    private List<Project_img> project_imgList;


}
