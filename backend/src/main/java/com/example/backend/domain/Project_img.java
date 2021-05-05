package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Project_img {

    private int idx;
    private int project_idx;
    private String filepath;
    private String filename;
    private String origfilename;

}
