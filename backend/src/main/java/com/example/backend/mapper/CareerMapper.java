package com.example.backend.mapper;

import com.example.backend.domain.Career;
import com.example.backend.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CareerMapper {

    @Insert("INSERT INTO career(user_idx, division, company, department, start_date, end_date, content, edit, score) VALUES (#{career.user_idx}, #{career.division}, #{career.company} ,#{career.department}," +
            "#{career.start_date}, #{career.end_date}, #{career.content}, NOW(), #{career.score})")
    @Options(useGeneratedKeys = true, keyProperty = "idx")
    int save(@Param("career") Career career) ;

    @Select("SELECT * FROM career WHERE user_idx = #{user_idx} ORDER BY start_date")
    List<Career> findByUserIdx(@Param("user_idx") int user_idx);





}
