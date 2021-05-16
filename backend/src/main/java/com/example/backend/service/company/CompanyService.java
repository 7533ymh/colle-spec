package com.example.backend.service.company;

import com.example.backend.advice.exception.CNotFoundDataException;
import com.example.backend.domain.Company;
import com.example.backend.domain.CompanyList;
import com.example.backend.mapper.collspec.CompanyMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CompanyService {

    private final CompanyMapper companyMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());



    public List<CompanyList> showCompanyList() {

        logger.info("회사 리스트 조회");

        List<CompanyList> companyList = companyMapper.findCompanyList();

        if (companyList.isEmpty())
            throw new CNotFoundDataException();


        return companyList;

    }

    public Company showCompany(int idx) {

        logger.info("회사 정보 조회");


        return companyMapper.findCompanyByIdx(idx).orElseThrow(CNotFoundDataException::new);

    }


}
