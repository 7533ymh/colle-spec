package com.example.backend.domain;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class BoardList {

    private int idx;
    private String user_id;
    private String title;
    private Date edit;

}
