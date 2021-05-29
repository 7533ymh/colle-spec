package com.example.backend.domain.user.service;

import com.example.backend.domain.award.mapper.AwardMapper;
import com.example.backend.domain.career.mapper.CareerMapper;
import com.example.backend.domain.certificate.mapper.CertificateMapper;
import com.example.backend.domain.education.mapper.EducationMapper;
import com.example.backend.domain.experience.mapper.ExperienceMapper;
import com.example.backend.domain.grade.mapper.GradeMapper;
import com.example.backend.domain.introduction.mapper.IntroductionMapper;
import com.example.backend.domain.language.mapper.LanguageMapper;
import com.example.backend.domain.project.mapper.ProjectMapper;
import com.example.backend.domain.user.domain.Summary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SummaryService {

    private final AwardMapper awardMapper;
    private final CareerMapper careerMapper;
    private final CertificateMapper certificateMapper;
    private final EducationMapper educationMapper;
    private final ExperienceMapper experienceMapper;
    private final GradeMapper gradeMapper;
    private final IntroductionMapper introductionMapper;
    private final LanguageMapper languageMapper;
    private final ProjectMapper projectMapper;


    public Summary show(int user_idx) {

        return Summary.builder()
                .awardCount(awardMapper.awardCount(user_idx))
                .careerCount(careerMapper.careerCount(user_idx))
                .certificateCount(certificateMapper.certificateCount(user_idx))
                .educationCount(educationMapper.educationCount(user_idx))
                .experienceCount(experienceMapper.experienceCount(user_idx))
                .gradeAvg(Math.round(gradeMapper.checkAvg(user_idx)*100)/100.0)
                .introductionCount(introductionMapper.introductionCount(user_idx))
                .languageSummaryList(languageMapper.languagesummary(user_idx))
                .projectCount(projectMapper.projectCount(user_idx))
                .build();

    }


}
