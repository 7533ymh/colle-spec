package com.example.backend.mapper.collspec;


import com.example.backend.domain.Language;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface LanguageMapper {

    @Insert("INSERT INTO language(user_idx, divsion, exam, exam_score, date, content, edit, score) VALUES (#{language.user_idx}, #{language.divsion}, #{language.exam}," +
            "#{language.exam_score}, #{language.date}, #{language.content}, NOW(), #{language.score})")
    @Options(useGeneratedKeys = true, keyProperty = "language.idx")
    int save(@Param("language") Language language);

    @Select("SELECT * FROM language WHERE user_idx = #{user_idx} ORDER BY date")
    List<Language> findByUserIdx(@Param("user_idx") int user_idx);

    @Select("SELECT * FROM language WHERE idx = #{idx}")
    Optional<Language> findByIdx(@Param("idx") int idx);

    @Select("SELECT * FROM language WHERE user_idx = #{language.user_idx} and exam = #{language.exam}")
    Optional<Language> findsame(@Param("language") Language language);

    @Update("UPDATE language SET divsion = #{language.divsion}, exam = #{language.exam}, exam_score = #{language.exam_score}, date = #{language.date}, " +
            "content = #{language.content}, edit = NOW(), score = #{language.score} WHERE idx = #{language.idx} AND user_idx = #{language.user_idx}")
    void update(@Param("language") Language language);

    @Delete("DELETE FROM language WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);

}
