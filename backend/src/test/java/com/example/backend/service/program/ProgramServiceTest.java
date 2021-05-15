package com.example.backend.service.program;

import com.example.backend.advice.exception.CNotFoundDataException;
import com.example.backend.domain.Board;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Program;
import com.example.backend.service.user.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ProgramServiceTest {

    @Autowired
    ProgramService programService;

    @Autowired
    UserService userService;



    @Test
    void 프로그램조회(){

        //given
        String division = "공모전&대회";

        //when
        List<Program> programs = programService.findByDivision(division);


        //then
        assertThat(18).isEqualTo(programs.size());



    }

    @Test
    void 게시판작성(){

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");

        //when
        programService.saveBoard(board);
        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());

        //then
        assertThat("test").isEqualTo(boards.get(0).getTitle());


    }


    @Test
    void 게시판조회(){

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");

        Board board2 = new Board();
        board2.setProgram_idx(1);
        board2.setUser_id(user_id);
        board2.setTitle("test1");
        board2.setContent("test1");

        //when
        programService.saveBoard(board);
        programService.saveBoard(board2);
        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());

        //then
        assertThat(2).isEqualTo(boards.size());


    }

    @Test
    void 게시판수정(){

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");

        //when
        programService.saveBoard(board);
        Board board2 = new Board();
        board2.setIdx(board.getIdx());
        board2.setProgram_idx(1);
        board2.setUser_id(user_id);
        board2.setTitle("test1");
        board2.setContent("test1");
        programService.updateBoard(board2);
        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());

        //then
        assertThat("test1").isEqualTo(boards.get(0).getTitle());

    }

    @Test
    void 게시판삭제() {

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");

        //when
        programService.saveBoard(board);
        programService.deleteBoard(board.getIdx(), user_id);

        //then
        CNotFoundDataException e = assertThrows(CNotFoundDataException.class , ()->programService.findByProgram_idx(board.getProgram_idx()));

    }


    @Test
    void 댓글작성(){

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");


        //when
        programService.saveBoard(board);

        Comment comment = new Comment();
        comment.setBoard_idx(board.getIdx());
        comment.setUser_id("test");
        comment.setContent("test");
        programService.saveComment(comment);

        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());


        //then
        assertThat("test").isEqualTo(boards.get(0).getCommentList().get(0).getContent());

    }

    @Test
    void 댓글조회() {


        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");
        programService.saveBoard(board);

        Comment comment = new Comment();
        comment.setBoard_idx(board.getIdx());
        comment.setUser_id("test");
        comment.setContent("test");

        Comment comment2 = new Comment();
        comment2.setBoard_idx(board.getIdx());
        comment2.setUser_id("test2");
        comment2.setContent("test2");

        //when
        programService.saveComment(comment);
        programService.saveComment(comment2);
        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());

        //then
        assertThat(2).isEqualTo(boards.get(0).getCommentList().size());


    }

    @Test
    void 댓글수정(){

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");
        programService.saveBoard(board);

        Comment comment = new Comment();
        comment.setBoard_idx(board.getIdx());
        comment.setUser_id("test");
        comment.setContent("test");
        programService.saveComment(comment);

        Comment comment2 = new Comment();
        comment2.setIdx(comment.getIdx());
        comment2.setBoard_idx(board.getIdx());
        comment2.setUser_id("test");
        comment2.setContent("test2");

        //when
        programService.updateComment(comment2);
        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());

        //then
        assertThat("test2").isEqualTo(boards.get(0).getCommentList().get(0).getContent());

    }

    @Test
    void 댓글삭제() {

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(1);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");
        programService.saveBoard(board);

        Comment comment = new Comment();
        comment.setBoard_idx(board.getIdx());
        comment.setUser_id("test");
        comment.setContent("test");
        programService.saveComment(comment);

        Comment comment2 = new Comment();
        comment2.setIdx(comment.getIdx());
        comment2.setBoard_idx(board.getIdx());
        comment2.setUser_id("test2");
        comment2.setContent("test2");
        programService.saveComment(comment2);


        //when
        programService.deleteComment(comment.getIdx(),comment.getUser_id());
        List<Board> boards = programService.findByProgram_idx(board.getProgram_idx());


        //then
        assertThat(1).isEqualTo(boards.get(0).getCommentList().size());




    }


}