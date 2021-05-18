package com.example.backend.service.company;

import com.example.backend.domain.Company;
import com.example.backend.domain.CompanyList;
import com.example.backend.domain.Score;
import com.example.backend.mapper.collspec.RankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CompanyServiceTest {

    @Autowired
    CompanyService companyService;

    @Autowired
    RankMapper rankMapper;

    @Test
    void showCompanyList() {

        //given
        int idx = 26;

        //when
        List<CompanyList> companyList = companyService.showCompanyList(idx);

        //then
        assertThat(companyList.get(0).getName()).isEqualTo("카카오");


    }

    @Test
    void showCompany() {

        //given
        int idx = 1;

        //when
        Company company = companyService.showCompany(idx);

        //then
        assertThat(company.getName()).isEqualTo("삼성전자");


    }

    @Test
    void 점수비교() {

        //given
        int user_idx = 26;
        int company_idx = 1;
        Score user_score = rankMapper.FindScore(user_idx);
        Company company = companyService.showCompany(company_idx);

        int award_check = user_score.getAward_score() - company.getPass_award();
        String award_rank;

        if (award_check > 400)
            award_rank = "매우 잘함";
        else if (award_check >= 200)
            award_rank = "잘함";
        else if (award_check >= -200)
            award_rank = "적정";
        else if (award_check >= -400)
            award_rank = "부족함";
        else
            award_rank = "매우 부족함";

        assertThat(award_rank).isEqualTo("적정");


    }
}