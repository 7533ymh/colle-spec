package com.example.backend.service.company;

import com.example.backend.domain.company.domain.CompanyList;
import com.example.backend.domain.company.domain.CompareCompany;
import com.example.backend.domain.company.service.CompanyService;
import com.example.backend.domain.rank.mapper.RankMapper;
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
    void 점수비교() {

        //given
        int user_idx = 26;
        int company_idx = 1;

        //when
        CompareCompany compareCompany = companyService.showCompany(company_idx, user_idx);

        //then
        assertThat(compareCompany.getAward_rank()).isEqualTo(3);
        assertThat(compareCompany.getCareer_rank()).isEqualTo(1);
        assertThat(compareCompany.getCertificate_rank()).isEqualTo(3);
        assertThat(compareCompany.getEducation_rank()).isEqualTo(3);
        assertThat(compareCompany.getExperience_rank()).isEqualTo(3);
        assertThat(compareCompany.getGrade_rank()).isEqualTo(2);
        assertThat(compareCompany.getLanguage_rank()).isEqualTo(5);


    }
}