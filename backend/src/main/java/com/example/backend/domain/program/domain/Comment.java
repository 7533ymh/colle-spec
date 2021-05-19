package com.example.backend.domain.program.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Comment {

    private int idx;
    private int board_idx;
    private String user_id;
    private String content;
    private Date edit;

}
