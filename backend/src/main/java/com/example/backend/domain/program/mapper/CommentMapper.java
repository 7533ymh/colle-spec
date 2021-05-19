package com.example.backend.domain.program.mapper;

import com.example.backend.domain.program.domain.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CommentMapper {

    @Insert("INSERT INTO comment(board_idx, user_id, content, edit) VALUES (#{comment.board_idx}, #{comment.user_id}, #{comment.content}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "comment.idx")
    void save(@Param("comment") Comment comment);

    @Select("SELECT * FROM comment WHERE board_idx = #{board_idx} ORDER BY idx")
    List<Comment> findByBoard_idx(@Param("board_idx") int board_idx);

    @Update("UPDATE comment SET content = #{comment.content}, edit = NOW() WHERE idx = #{comment.idx} AND user_id = #{comment.user_id}")
    void update(@Param("comment") Comment comment);


    @Delete("DELETE FROM comment WHERE idx = #{idx} and user_id = #{user_id} ")
    void delete(@Param("idx") int idx , @Param("user_id") String user_id);

    @Select("SELECT * FROM comment WHERE idx = #{idx} ")
    Optional<Comment> findByIdx(@Param("idx") int idx);


}
