package com.example.backend.domain.user.domain;

import com.example.backend.domain.language.domain.LanguageSummary;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Summary {

    private int awardCount;
    private int careerCount;
    private int certificateCount;
    private int educationCount;
    private int experienceCount;
    private Double gradeAvg;
    private int introductionCount;
    private List<LanguageSummary> languageSummaryList;
    private int projectCount;

}
