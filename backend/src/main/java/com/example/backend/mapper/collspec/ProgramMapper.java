package com.example.backend.mapper.collspec;

import com.example.backend.domain.Program;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProgramMapper {

    @Insert("INSERT INTO program(division, title,agency, receive_date_start, receive_date_end, program_date_start, program_date_end, url ) VALUES (#{program.division}, #{program.title}, #{program.agency}" +
            ", #{program.receive_date_start}" +
            ", #{program.receive_date_end} , #{program.program_date_start}  , #{program.program_date_end} , #{program.url})")
    @Options(useGeneratedKeys = true, keyProperty = "idx")
    int insert(@Param("program") Program program);

    @Select("SELECT * FROM program WHERE division = #{division} ORDER BY receive_date_end")
    List<Program> findByDivision(@Param("division") String division);

    @Select("SELECT * FROM program WHERE idx = #{idx}")
    Optional<Program> findByidx(@Param("idx") int idx);

    @Select("SELECT idx FROM program WHERE title = #{title}")
    Optional<Integer> findByTitle(@Param("title") String title);

    @Delete("DELETE FROM program WHERE division = #{division} ")
    void delete(@Param("division") String division);

    @Delete("DELETE FROM program WHERE idx = #{idx} ")
    void deleteByIdx(@Param("idx") int idx);

    @Delete("DELETE FROM program WHERE receive_date_end < DATE_FORMAT(NOW(),'%Y%m%d')")
    void deleteOverDate();




}
