package com.example.backend.domain.award.mapper;

import com.example.backend.domain.award.domain.Award;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface AwardMapper {

    @Insert("INSERT INTO award(user_idx, title, agency, division, year, content, edit, score) VALUES (#{award.user_idx}, #{award.title}, #{award.agency} ,#{award.division}," +
            "#{award.year}, #{award.content}, NOW(), #{award.score})")
    @Options(useGeneratedKeys = true, keyProperty = "award.idx")
    int save(@Param("award") Award award) ;

    @Select("SELECT * FROM award WHERE user_idx = #{user_idx} ORDER BY year")
    List<Award> findByUserIdx(@Param("user_idx") int user_idx);

    @Select("SELECT * FROM award WHERE idx = #{idx}")
    Optional<Award> findByIdx(@Param("idx") int idx);

    @Update("UPDATE award SET title = #{award.title}, agency = #{award.agency}, division = #{award.division}, year = #{award.year}, content = #{award.content}, " +
            "edit = NOW(), score = #{award.score} WHERE idx = #{award.idx} AND user_idx = #{award.user_idx}")
    void update(@Param("award") Award award);


    @Delete("DELETE FROM award WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);

}
