package com.example.backend.service.program;

import com.example.backend.domain.program.domain.Board;
import com.example.backend.domain.program.domain.BoardList;
import com.example.backend.domain.program.domain.Comment;
import com.example.backend.domain.program.domain.Program;
import com.example.backend.domain.program.service.ProgramService;
import com.example.backend.domain.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ProgramServiceTest {

    @Autowired
    ProgramService programService;

    @Autowired
    UserService userService;


    @Test
    void 프로그램조회() {

        //given
        String division = "공모전&대회";

        //when
        List<Program> programs = programService.findByDivision(division);


        //then
        //assertThat(programs.size()).isEqualTo(18);


    }

    @Test
    void 게시판작성조회() {

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
        Board boardTest = programService.findOneByIdx(board.getIdx());

        //then
        assertThat(boardTest.getTitle()).isEqualTo("test");


    }


    @Test
    void 게시판리스트조회() {

        //given
        int idx = 25;
        String user_id = userService.findByIdx(idx).getId();
        Board board = new Board();
        board.setProgram_idx(8);
        board.setUser_id(user_id);
        board.setTitle("test");
        board.setContent("test");

        Board board2 = new Board();
        board2.setProgram_idx(8);
        board2.setUser_id(user_id);
        board2.setTitle("test1");
        board2.setContent("test1");

        //when
        programService.saveBoard(board);
        programService.saveBoard(board2);
        List<BoardList> boards = programService.findByProgram_idx(board.getProgram_idx());

        //then
        assertThat(boards.get(1).getTitle()).isEqualTo("test1");


    }

    @Test
    void 게시판수정() {

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
        Board boardTest = programService.findOneByIdx(board.getIdx());

        //then
        assertThat(boardTest.getTitle()).isEqualTo("test1");

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
        List<BoardList> boards = programService.findByProgram_idx(1);
        programService.deleteBoard(board.getIdx(), user_id);


        //then
        List<BoardList> checkBoards = programService.findByProgram_idx(1);
        assertThat(boards.size() - 1).isEqualTo(checkBoards.size());
    }


    @Test
    void 댓글작성() {

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

        Board boards = programService.findOneByIdx(board.getIdx());


        //then
        assertThat(boards.getCommentList().get(0).getContent()).isEqualTo("test");

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
        Board boards = programService.findOneByIdx(board.getIdx());

        //then
        assertThat(boards.getCommentList().size()).isEqualTo(2);


    }

    @Test
    void 댓글수정() {

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
        Board boards = programService.findOneByIdx(board.getIdx());

        //then
        assertThat(boards.getCommentList().get(0).getContent()).isEqualTo("test2");

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


        //when
        programService.deleteComment(comment.getIdx(), comment.getUser_id());
        Board boards = programService.findOneByIdx(board.getIdx());


        //then
        assertThat(boards.getCommentList().size()).isEqualTo(0);

    }


}