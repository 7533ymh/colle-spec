package com.example.backend.mapper.collspec;

import com.example.backend.domain.GradeCal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface GradeCalMapper {


    @Select("SELECT * FROM grade_cal WHERE user_idx = #{user_idx}")
    GradeCal findByUserIdx(@Param("user_idx") int user_idx);

    @Update("UPDATE grade_cal SET score = #{gradeCal.score} WHERE idx = #{gradeCal.idx} and user_idx = #{gradeCal.user_idx}")
    void update(@Param("gradeCal") GradeCal gradeCal);


}
