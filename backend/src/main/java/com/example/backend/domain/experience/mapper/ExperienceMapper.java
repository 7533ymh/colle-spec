package com.example.backend.domain.experience.mapper;

import com.example.backend.domain.experience.domain.Experience;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ExperienceMapper {

    @Insert("INSERT INTO experience(user_idx, country, start_date, end_date, content, edit, score) VALUES (#{experience.user_idx}, #{experience.country}," +
            "#{experience.start_date}, #{experience.end_date}, #{experience.content}, NOW(), #{experience.score})")
    @Options(useGeneratedKeys = true, keyProperty = "experience.idx")
    int save(@Param("experience") Experience experience);

    @Select("SELECT * FROM experience WHERE user_idx = #{user_idx} ORDER BY start_date")
    List<Experience> findByUserIdx(@Param("user_idx") int user_idx);

    @Select("SELECT * FROM experience WHERE idx = #{idx}")
    Optional<Experience> findByIdx(@Param("idx") int idx);

    @Update("UPDATE experience SET country = #{experience.country}, start_date = #{experience.start_date}, end_date = #{experience.end_date}, " +
            "content = #{experience.content}, edit = NOW(), score = #{experience.score} WHERE idx = #{experience.idx} AND user_idx = #{experience.user_idx}")
    void update(@Param("experience") Experience experience);

    @Delete("DELETE FROM experience WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);


}
