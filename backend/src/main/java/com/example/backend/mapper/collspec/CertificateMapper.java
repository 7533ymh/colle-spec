package com.example.backend.mapper.collspec;

import com.example.backend.domain.Certificate;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CertificateMapper {

    @Insert("INSERT INTO certificate(user_idx, title, publisher, date, content, edit, score) VALUES (#{certificate.user_idx}, #{certificate.title}, #{certificate.publisher}," +
            "#{certificate.date}, #{certificate.content}, NOW(), #{certificate.score})")
    @Options(useGeneratedKeys = true, keyProperty = "certificate.idx")
    int save(@Param("certificate") Certificate certificate);

    @Select("SELECT * FROM certificate WHERE user_idx = #{user_idx} ORDER BY date")
    List<Certificate> findByUserIdx(@Param("user_idx") int user_idx);

    @Select("SELECT * FROM certificate WHERE idx = #{idx}")
    Optional<Certificate> findByIdx(@Param("idx") int idx);

    @Update("UPDATE certificate SET title = #{certificate.title}, publisher = #{certificate.publisher}, date = #{certificate.date}, " +
            "content = #{certificate.content}, edit = NOW(), score = #{certificate.score} WHERE idx = #{certificate.idx} AND user_idx = #{certificate.user_idx}")
    void update(@Param("certificate") Certificate certificate);

    @Delete("DELETE FROM certificate WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);


}
