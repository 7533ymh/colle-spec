package com.example.backend.domain.rank.mapper;

import com.example.backend.domain.rank.domain.AvgCheck;
import com.example.backend.domain.rank.domain.Rank;
import com.example.backend.domain.rank.domain.RankResult;
import com.example.backend.domain.rank.domain.Score;
import org.apache.ibatis.annotations.*;

import java.util.Optional;

@Mapper
public interface RankMapper {


    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, certificate_score AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, education_score AS education_rank, " +
            "grade_score AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM score )c WHERE user_idx = #{idx}")
    Rank checkRankAll(@Param("idx") int idx);

    @Select("SELECT ROUND(avg(certificate_score),-2) AS avg_Certificate , ROUND(avg(education_score),-2) AS avg_Education, ROUND(avg(grade_score),-1) AS avg_Grade FROM score")
    AvgCheck checkAvgAll();

    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, grade, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, certificate_score AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, education_score AS education_rank, " +
            "grade_score AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "score where grade = (SELECT grade FROM user WHERE idx = #{idx}) )c WHERE user_idx = #{idx}")
    Rank checkRankByGrade(@Param("idx") int idx);

    @Select("SELECT ROUND(avg(certificate_score),-2) AS avg_Certificate , ROUND(avg(education_score),-2) AS avg_Education, ROUND(avg(grade_score),-1) AS avg_Grade FROM score where grade = (SELECT grade FROM user WHERE idx = #{idx})")
    AvgCheck checkAvgByGrade(@Param("idx") int idx);

    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, grade ,college, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, certificate_score AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, education_score AS education_rank, " +
            "grade_score AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "score where college = (SELECT college FROM user WHERE idx = #{idx})  )c WHERE user_idx = #{idx}")
    Rank checkRankByCollege(@Param("idx") int idx);

    @Select("SELECT ROUND(avg(certificate_score),-2) AS avg_Certificate , ROUND(avg(education_score),-2) AS avg_Education, ROUND(avg(grade_score),-1) AS avg_Grade FROM score where college = (SELECT college FROM user WHERE idx = #{idx})")
    AvgCheck checkAvgByCollege(@Param("idx") int idx);

    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, college, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, certificate_score AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, education_score AS education_rank, " +
            "grade_score AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "score where grade = (SELECT grade FROM user WHERE idx = #{idx}) and college = (SELECT college FROM user WHERE idx = #{idx})  )c WHERE user_idx = #{idx}")
    Rank checkRankByCollegeGrade(@Param("idx") int idx);

    @Select("SELECT ROUND(avg(certificate_score),-2) AS avg_Certificate , ROUND(avg(education_score),-2) AS avg_Education, ROUND(avg(grade_score),-1) AS avg_Grade FROM score where grade = (SELECT grade FROM user WHERE idx = #{idx}) and college = (SELECT college FROM user WHERE idx = #{idx})")
    AvgCheck checkAvgByCollegeGrade(@Param("idx") int idx);

    @Insert("INSERT INTO `rank`(user_idx, division, award_rank, career_rank, certificate_rank ,education_rank, experience_rank, language_rank, grade_rank, project_rank, all_rank) " +
            "VALUES ( #{rank.user_idx}, #{rank.division} ,#{rank.award_rank}, #{rank.career_rank}, #{rank.certificate_rank}, #{rank.education_rank}," +
            "#{rank.experience_rank}, #{rank.language_rank}, #{rank.grade_rank}, #{rank.project_rank}, #{rank.all_rank})")
    @Options(useGeneratedKeys = true, keyProperty = "rank.idx")
    int insertRank(@Param("rank") RankResult rank);

    @Update("UPDATE rank SET award_rank = #{rank.award_rank}, career_rank = #{rank.career_rank}, certificate_rank = #{rank.certificate_rank}, education_rank =  #{rank.education_rank}," +
            "experience_rank = #{rank.experience_rank}, language_rank = #{rank.language_rank}, grade_rank = #{rank.grade_rank}, project_rank = #{rank.project_rank},  all_rank = #{rank.all_rank}" +
            " WHERE user_idx = #{rank.user_idx} AND division = #{rank.division} ")
    void updateRank(@Param("rank") RankResult rank);

    @Select("SELECT * FROM rank WHERE user_idx = #{user_idx} AND division = #{division}")
    Optional<RankResult> findByUser_idxDivision(@Param("user_idx") int user_idx, @Param("division") int division);

    @Select("SELECT * FROM score WHERE user_idx = #{user_idx}")
    Score FindScore(@Param("user_idx") int user_idx);

    @Select("SELECT score FROM score WHERE user_idx = #{user_idx}")
    int checkScore(@Param("user_idx") int user_idx);


    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, grade, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "score where grade = (SELECT grade FROM user WHERE idx = #{idx}) )c WHERE user_idx = #{idx}")
    Rank checkRankByGrade_test(@Param("idx") int idx);

    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, grade ,college, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "score where college = (SELECT college FROM user WHERE idx = #{idx})  )c WHERE user_idx = #{idx}")
    Rank checkRankByCollege_test(@Param("idx") int idx);

    @Select("SELECT user_idx,award_rank, career_rank , certificate_rank, education_rank, experience_rank, grade_rank, language_rank, project_rank, all_rank FROM ( SELECT  user_idx, college, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM " +
            "score where grade = (SELECT grade FROM user WHERE idx = #{idx}) and college = (SELECT college FROM user WHERE idx = #{idx})  )c WHERE user_idx = #{idx}")
    Rank checkRankByCollegeGrade_test(@Param("idx") int idx);


}
