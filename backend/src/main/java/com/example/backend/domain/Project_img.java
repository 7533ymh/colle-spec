package com.example.backend.domain;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Project_img {

    private int idx;
    private int project_idx;
    private String filepath;
    private String filename;
    private String origfilename;

}
