package com.example.backend.mapper.colleasy;

import com.example.backend.domain.Grade;
import com.example.backend.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@com.example.backend.config.database.EasyMapper
public interface LinkMapper {

    @Select("SELECT idx,user_pw FROM user_schema WHERE user_id = #{id}")
    @Result(property = "pass", column = "user_pw")
    Optional<User> findById(@Param("id") String id);


    @Select("SELECT college, student_major, student_grade FROM sic_db WHERE idx = #{idx}")
    @Results(value = {
            @Result(property = "college", column = "college"),
            @Result(property = "major", column = "student_major"),
            @Result(property = "grade", column = "student_grade")
    })
    User InfoFindByIdx(@Param("idx") int idx);

    @Select("SELECT year,semester,avg_rating FROM sic_grade WHERE idx = #{idx} ORDER BY year")
    @Results(value = {
            @Result(property = "avg_score", column = "avg_rating"),
            @Result(property = "grade", column = "year")
    })
    List<Grade> gradeFindByIdx(@Param("idx") int idx);
}
