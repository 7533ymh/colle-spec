package com.example.backend.domain.company.service;

import com.example.backend.global.exception.exception.CNotFoundDataException;
import com.example.backend.domain.company.domain.Company;
import com.example.backend.domain.company.domain.CompanyList;
import com.example.backend.domain.company.domain.CompareCompany;
import com.example.backend.domain.rank.domain.Score;
import com.example.backend.domain.company.mapper.CompanyMapper;
import com.example.backend.domain.rank.mapper.RankMapper;
import com.example.backend.domain.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final UserMapper userMapper;
    private final RankMapper rankMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public List<CompanyList> showCompanyList(int user_idx) {

        logger.info("회사 리스트 조회");

        List<CompanyList> companyList = companyMapper.findCompanyList();

        for (CompanyList company : companyList) {
            if (company.getName().equals(userMapper.findEnterpriseByIdx(user_idx))) {
                Collections.swap(companyList, 0, companyList.indexOf(company));
                break;
            }
        }

        if (companyList.isEmpty())
            throw new CNotFoundDataException();


        return companyList;

    }

    public CompareCompany showCompany(int idx, int user_idx) {

        logger.info("회사 정보 조회");

        Score userScore = rankMapper.FindScore(user_idx);
        Company company = companyMapper.findCompanyByIdx(idx).orElseThrow(CNotFoundDataException::new);

        if ( rankMapper.checkScore(user_idx) == 0){
            return CompareCompany.builder()
                    .idx(company.getIdx())
                    .name(company.getName())
                    .content(company.getContent())
                    .award_rank(-1)
                    .career_rank(-1)
                    .certificate_rank(-1)
                    .education_rank(-1)
                    .experience_rank(-1)
                    .grade_rank(-1)
                    .language_rank(-1)
                    .project_rank(-1)
                    .build();
        }

        return compareScore(userScore, company);

    }

    public CompareCompany compareScore(Score userScore, Company company) {

        CompareCompany compareCompany = new CompareCompany();
        compareCompany.setIdx(company.getIdx());
        compareCompany.setName(company.getName());
        compareCompany.setContent(company.getContent());


        int checkAward = userScore.getAward_score() - company.getPass_award();
        int checkCareer = userScore.getCareer_score() - company.getPass_career();
        int checkCertificate = userScore.getCertificate_score() - company.getPass_certificate();
        int checkEducation = userScore.getEducation_score() - company.getPass_education();
        int checkExperience = userScore.getExperience_score() - company.getPass_experience();
        int checkGrade = userScore.getGrade_score() - company.getPass_grade();
        int checkLanguage = userScore.getLanguage_score() - company.getPass_language();
        int checkProject = userScore.getProject_score() - company.getPass_project();

        if (checkAward > 60)
            compareCompany.setAward_rank(1);
        else if (checkAward > 30)
            compareCompany.setAward_rank(2);
        else if (checkAward >= -30)
            compareCompany.setAward_rank(3);
        else if (checkAward >= -60)
            compareCompany.setAward_rank(4);
        else
            compareCompany.setAward_rank(5);

        if (checkCareer > 60)
            compareCompany.setCareer_rank(1);
        else if (checkCareer > 30)
            compareCompany.setCareer_rank(2);
        else if (checkCareer >= -30)
            compareCompany.setCareer_rank(3);
        else if (checkCareer >= -60)
            compareCompany.setCareer_rank(4);
        else
            compareCompany.setCareer_rank(5);

        if (checkCertificate > 400)
            compareCompany.setCertificate_rank(1);
        else if (checkCareer > 200)
            compareCompany.setCertificate_rank(2);
        else if (checkCareer >= -200)
            compareCompany.setCertificate_rank(3);
        else if (checkCareer >= -400)
            compareCompany.setCertificate_rank(4);
        else
            compareCompany.setCertificate_rank(5);

        if (checkEducation > 200)
            compareCompany.setEducation_rank(1);
        else if (checkEducation > 100)
            compareCompany.setEducation_rank(2);
        else if (checkEducation >= -100)
            compareCompany.setEducation_rank(3);
        else if (checkEducation >= -200)
            compareCompany.setEducation_rank(4);
        else
            compareCompany.setEducation_rank(5);

        if (checkExperience > 60)
            compareCompany.setExperience_rank(1);
        else if (checkExperience > 30)
            compareCompany.setExperience_rank(2);
        else if (checkExperience >= -30)
            compareCompany.setExperience_rank(3);
        else if (checkExperience >= -60)
            compareCompany.setExperience_rank(4);
        else
            compareCompany.setExperience_rank(5);

        if (checkGrade == 20)
            compareCompany.setGrade_rank(1);
        else if (checkGrade == 10)
            compareCompany.setGrade_rank(2);
        else if (checkGrade == 0)
            compareCompany.setGrade_rank(3);
        else if (checkGrade == -10)
            compareCompany.setGrade_rank(4);
        else
            compareCompany.setGrade_rank(5);

        if (checkLanguage > 60)
            compareCompany.setLanguage_rank(1);
        else if (checkLanguage > 30)
            compareCompany.setLanguage_rank(2);
        else if (checkLanguage >= -30)
            compareCompany.setLanguage_rank(3);
        else if (checkLanguage >= -60)
            compareCompany.setLanguage_rank(4);
        else
            compareCompany.setLanguage_rank(5);

        if (checkProject > 400)
            compareCompany.setProject_rank(1);
        else if (checkProject > 200)
            compareCompany.setProject_rank(2);
        else if (checkProject >= -200)
            compareCompany.setProject_rank(3);
        else if (checkProject >= -400)
            compareCompany.setProject_rank(4);
        else
            compareCompany.setProject_rank(5);

        return compareCompany;
    }


}
