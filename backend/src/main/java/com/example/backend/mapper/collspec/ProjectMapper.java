package com.example.backend.mapper.collspec;

import com.example.backend.domain.Project;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProjectMapper {

    @Insert("INSERT INTO project(user_idx, title, start_date, end_date, success ,content, edit, score) VALUES (#{project.user_idx}, #{project.title}," +
            "#{project.start_date}, #{project.end_date}, #{project.success}, #{project.content}, NOW(), #{project.score})")
    @Options(useGeneratedKeys = true, keyProperty = "project.idx")
    int save(@Param("project") Project project);

    @Select("SELECT * FROM project WHERE user_idx = #{user_idx} ORDER BY start_date")
    @Result(property = "idx", column = "idx", id = true)
    @Result(property = "project_imgList", column = "idx", many = @Many(select = "com.example.backend.mapper.collspec.Project_imgMapper.findByProject_Idx"))
    List<Project> findByUserIdx(@Param("user_idx") int user_idx);

    @Select("SELECT idx FROM project WHERE content = #{content}")
    int findidxByContent(@Param("content") String content);

    @Select("SELECT user_idx FROM project WHERE idx = #{idx}")
    Optional<Integer> finduser_idxByIdx(@Param("idx") int idx);

    @Update("UPDATE project SET title = #{project.title}, start_date = #{project.start_date}, end_date = #{project.end_date}, success = #{project.success}, " +
            "content = #{project.content}, edit = NOW(), score = #{project.score} WHERE idx = #{project.idx} AND user_idx = #{project.user_idx}")
    void update(@Param("project") Project project);



    @Delete("DELETE FROM project WHERE idx = #{idx} and user_idx = #{user_idx} ")
    void delete(@Param("idx") int idx , @Param("user_idx") int user_idx);


}
