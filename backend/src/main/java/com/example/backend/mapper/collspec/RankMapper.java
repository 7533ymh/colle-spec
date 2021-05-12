package com.example.backend.mapper.collspec;

import com.example.backend.domain.Rank;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RankMapper {


    @Select("SELECT * FROM ( SELECT  idx, CUME_DIST() OVER (ORDER BY award_score DESC) AS award_rank, " +
            "CUME_DIST() OVER (ORDER BY career_score DESC) AS career_rank, CUME_DIST() OVER (ORDER BY certificate_score DESC) AS certificate_rank, " +
            "CUME_DIST() OVER (ORDER BY experience_score DESC) AS experience_rank, CUME_DIST() OVER (ORDER BY education_score DESC) AS education_rank, " +
            "CUME_DIST() OVER (ORDER BY grade_score DESC) AS grade_rank, CUME_DIST() OVER (ORDER BY language_score DESC) AS language_rank, " +
            "CUME_DIST() OVER (ORDER BY project_score DESC) AS project_rank , CUME_DIST() OVER (ORDER BY score DESC) AS all_rank FROM user )c WHERE idx = #{idx}")
    Rank checkRank(@Param("idx") int idx);

}
