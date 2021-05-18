package com.example.backend.service.company;

import com.example.backend.advice.exception.CNotFoundDataException;
import com.example.backend.domain.Company;
import com.example.backend.domain.CompanyList;
import com.example.backend.mapper.collspec.CompanyMapper;
import com.example.backend.mapper.collspec.UserMapper;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());



    public List<CompanyList> showCompanyList(int user_idx) {

        logger.info("회사 리스트 조회");

        List<CompanyList> companyList = companyMapper.findCompanyList();

        for ( CompanyList company : companyList){
            if (company.getName().equals(userMapper.findEnterpriseByIdx(user_idx))) {
                Collections.swap(companyList, 0, companyList.indexOf(company));
                break;
            }
        }

        if (companyList.isEmpty())
            throw new CNotFoundDataException();


        return companyList;

    }

    public Company showCompany(int idx) {

        logger.info("회사 정보 조회");


        return companyMapper.findCompanyByIdx(idx).orElseThrow(CNotFoundDataException::new);

    }


}
