package com.example.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Program {

    private int idx;
    private String division;
    private String title;
    private String agency;
    private String receive_date;
    private String program_date;
    private String content;
    private String url;

}
