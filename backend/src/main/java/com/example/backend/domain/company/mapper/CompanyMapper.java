package com.example.backend.domain.company.mapper;

import com.example.backend.domain.company.domain.Company;
import com.example.backend.domain.company.domain.CompanyList;
import org.apache.ibatis.annotations.Insert;
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

    @Insert("INSERT INTO company(name, industry, people, division, establishment, capital, take, representative, salary, bussiness, insurance," +
            "url, address, pass_award, pass_career, pass_certificate, pass_education,  pass_experience, pass_grade, pass_language) VALUES (#{company.name}," +
            "#{company.industry}, #{company.people}, #{company.division}, #{company.establishment}, #{company.capital}, #{company.take}, #{company.representative}, #{company.salary}," +
            "#{company.bussiness}, #{company.insurance}, #{company.url}, #{company.address}, #{company.pass_award}, #{company.pass_career}, #{company.pass_certificate}," +
            "#{company.pass_education}, #{company.pass_experience}, #{company.pass_grade}, #{company.pass_language})")
    void insert(@Param("company") Company company);


}
