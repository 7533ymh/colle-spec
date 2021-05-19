package com.example.backend.domain.company.mapper;

import com.example.backend.domain.company.domain.Company;
import com.example.backend.domain.company.domain.CompanyList;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CompanyMapper {

    @Select("SELECT idx, name FROM company")
    List<CompanyList> findCompanyList();

    @Select("SELECT * FROM company WHERE idx = #{idx}")
    Optional<Company> findCompanyByIdx(@Param("idx") int idx);




}
