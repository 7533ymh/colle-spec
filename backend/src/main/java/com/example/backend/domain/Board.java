package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Board {

    private int idx;
    private int program_idx;
    private String user_id;
    private String title;
    private String content;
    private List<Comment> commentList;
    private Date edit;

}
