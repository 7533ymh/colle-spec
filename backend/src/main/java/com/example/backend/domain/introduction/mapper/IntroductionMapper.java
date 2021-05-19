package com.example.backend.domain.introduction.mapper;

import com.example.backend.domain.introduction.domain.Introduction;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface IntroductionMapper {

    @Insert("INSERT INTO introduction(user_idx, filepath, filename, origfilename, edit) VALUES (#{introduction.user_idx}, #{introduction.filepath}, #{introduction.filename}," +
            "#{introduction.origfilename}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "introduction.idx")
    int save(@Param("introduction") Introduction introduction);

    @Select("SELECT * FROM introduction WHERE user_idx = #{user_idx}")
    List<Introduction> findByuser_idx(@Param("user_idx") int user_idx);

    @Select("SELECT * FROM introduction WHERE idx = #{idx}")
    Optional<Introduction> findByidx (@Param("idx") int idx);


    @Delete("DELETE FROM introduction WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);

}
