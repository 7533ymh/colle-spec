package com.example.backend.domain.grade.mapper;

import com.example.backend.domain.grade.domain.Grade;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface GradeMapper {

    @Insert("INSERT INTO grade(user_idx, grade, semester, avg_score, edit) VALUES " +
            "(#{grade.user_idx}, #{grade.grade}, #{grade.semester} ,#{grade.avg_score}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "grade.idx")
    int save(@Param("grade") Grade grade) ;

    @Update("UPDATE score SET grade_score = #{score} " +
            "WHERE user_idx = #{user_idx}")
    void updateGradeScore(@Param("score") double score , @Param("user_idx") int user_idx );

    @Select("SELECT * FROM grade WHERE user_idx = #{user_idx} ORDER BY grade,semester ")
    List<Grade> findByUserIdx(@Param("user_idx") int user_idx);

    @Select("SELECT * FROM grade WHERE idx = #{idx}")
    Optional<Grade> findByIdx(@Param("idx") int idx);

    @Select("SELECT * FROM grade WHERE user_idx = #{grade.user_idx} and grade = #{grade.grade} and semester=#{grade.semester}")
    Optional<Grade> findsame(@Param("grade") Grade grade);

    @Select("SELECT IFNULL(AVG(avg_score),0) FROM " +
            "grade WHERE user_idx = #{user_idx}")
    double checkAvg(@Param("user_idx") int user_idx);

    @Update("UPDATE grade SET grade = #{grade.grade}, semester = #{grade.semester}, avg_score = #{grade.avg_score}, " +
            "edit = NOW() WHERE idx = #{grade.idx} AND user_idx = #{grade.user_idx}")
    void update(@Param("grade") Grade grade);

    @Delete("DELETE FROM grade WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);

    @Delete("DELETE FROM grade WHERE user_idx = #{user_idx} ")
    void delete_all(@Param("user_idx") int user_idx);

}
