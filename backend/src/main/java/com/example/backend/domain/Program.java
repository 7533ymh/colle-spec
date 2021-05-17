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
    private String agency;
    private String title;
    private String receive_date_start;
    private String receive_date_end;
    private String program_date_start;
    private String program_date_end;
    private String url;


}
