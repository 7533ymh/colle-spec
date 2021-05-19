package com.example.backend.domain;

import lombok.*;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
