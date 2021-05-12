package com.example.backend.mapper.collspec;

import com.example.backend.domain.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RankMapper {


    @Select("SELECT idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  idx, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM user )c WHERE idx = #{idx}")
    Rank checkRankAll(@Param("idx") int idx);

    @Select("SELECT idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  idx, grade, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "user where grade = (SELECT grade FROM user WHERE idx = #{idx}) )c WHERE idx = #{idx}")
    Rank checkRankByGrade(@Param("idx") int idx);

    @Select("SELECT idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  idx, grade ,college, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "user where college = (SELECT college FROM user WHERE idx = #{idx})  )c WHERE idx = #{idx}")
    Rank checkRankByCollege(@Param("idx") int idx);

    @Select("SELECT idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  idx, college, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "user where grade = (SELECT grade FROM user WHERE idx = #{idx}) and college = (SELECT college FROM user WHERE idx = #{idx})  )c WHERE idx = #{idx}")
    Rank checkRankByCollegeGrade(@Param("idx") int idx);



}
