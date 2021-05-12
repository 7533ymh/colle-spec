package com.example.backend.mapper.collspec;

import com.example.backend.domain.Program;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProgramMapper {

    @Select("SELECT * FROM program WHERE division = #{division} ORDER BY receive_date")
    List<Program> findByDivision(@Param("division") String division);

    @Select("SELECT * FROM program WHERE idx = #{idx}")
    Optional<Program> findByidx(@Param("idx") int idx);




}
