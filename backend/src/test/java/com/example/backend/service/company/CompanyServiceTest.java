package com.example.backend.service.company;

import com.example.backend.domain.Company;
import com.example.backend.domain.CompanyList;
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

    @Test
    void showCompanyList() {

        //given

        //when
        List<CompanyList> companyList = companyService.showCompanyList();

        //then
        assertThat(1).isEqualTo(companyList.size());


    }

    @Test
    void showCompany() {

        //given
        int idx = 1;

        //when
        Company company = companyService.showCompany(idx);

        //then
        assertThat("삼성전자").isEqualTo(company.getName());


    }

    @Test
    void 점수비교() {










    }
}