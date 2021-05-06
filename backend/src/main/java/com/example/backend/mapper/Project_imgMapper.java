package com.example.backend.mapper;

import com.example.backend.domain.Project_img;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface Project_imgMapper {

    @Insert("INSERT INTO project_img(project_idx, filepath, filename, origfilename) VALUES (#{project_img.project_idx}, #{project_img.filepath}, #{project_img.filename}," +
            "#{project_img.origfilename})")
    @Options(useGeneratedKeys = true, keyProperty = "idx")
    int save(@Param("project_img") Project_img project_img);

    @Select("SELECT * FROM project_img WHERE project_idx = #{project_idx}")
    List<Project_img> findByProject_Idx(@Param("project_idx") int project_idx);

    @Select("SELECT * FROM project_img WHERE idx = #{idx}")
    Optional<Project_img> findByidx (@Param("idx") int idx);

    @Select("SELECT project_idx FROM project_img WHERE idx = #{idx}")
    Optional<Integer> findproject_idxByIdx(@Param("idx") int idx);

    @Delete("DELETE FROM project_img WHERE project_idx = #{project_idx} ")
    void delete( @Param("project_idx") int project_idx);

}
