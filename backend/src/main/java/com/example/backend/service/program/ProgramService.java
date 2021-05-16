package com.example.backend.service.program;

import com.example.backend.advice.exception.CNotFoundDataException;
import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Board;
import com.example.backend.domain.BoardList;
import com.example.backend.domain.Comment;
import com.example.backend.domain.Program;
import com.example.backend.mapper.collspec.BoardMapper;
import com.example.backend.mapper.collspec.CommentMapper;
import com.example.backend.mapper.collspec.ProgramMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProgramService {

    private final ProgramMapper programMapper;
    private final BoardMapper boardMapper;
    private final CommentMapper commentMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public List<Program> findByDivision(String division) {

        logger.info("프로그램 리스트 조회");

        List<Program> programList = programMapper.findByDivision(division);

        if (programList.isEmpty()) {
            throw new CNotFoundDataException();
        }

        return programList;

    }


    public void saveBoard(Board board) {

        logger.info("게시판 작성");

        programMapper.findByidx(board.getProgram_idx()).orElseThrow(
                () -> new CNotFoundInfoByIdxException("해당 프로그램 번호의 정보가 없습니다."));


        boardMapper.save(board);

    }

    public List<BoardList> findByProgram_idx(int program_idx) {

        logger.info("게시판 리스트 조회");

        List<BoardList> boardList = boardMapper.findByProgram_idx_NoComment(program_idx);

        if (boardList.isEmpty()) {
            throw new CNotFoundDataException();
        }

        return boardList;
    }

    public Board findOneByIdx(int idx) {

        logger.info("게시판 상세 정보 조회");

        return boardMapper.findByIdx(idx).orElseThrow(
                () -> new CNotFoundInfoByIdxException("해당 게시판 번호의 정보가 없습니다. 다시 시도해주세요."));

    }

    public void updateBoard(Board board) {

        logger.info("게시판 수정");

        checkBoard(board.getIdx(), board.getUser_id());

        boardMapper.update(board);

    }

    public void deleteBoard(int idx, String user_id) {

        logger.info("게시판 삭제");

        checkBoard(idx, user_id);

        boardMapper.delete(idx, user_id);

    }

    public void saveComment(Comment comment) {

        logger.info("댓글 작성");

        boardMapper.findByIdx(comment.getBoard_idx()).orElseThrow(
                () -> new CNotFoundInfoByIdxException("해당 게시판 번호의 정보가 없습니다."));

        commentMapper.save(comment);

    }


    public void updateComment(Comment comment) {

        logger.info("댓글 수정");


        checkComment(comment.getIdx(), comment.getUser_id());


        commentMapper.update(comment);

    }

    public void deleteComment(int idx, String user_id) {

        logger.info("댓글 삭제");

        checkComment(idx, user_id);

        commentMapper.delete(idx, user_id);

    }


    private void checkComment(int idx, String user_id) {

        if (commentMapper.findByIdx(idx).isPresent()) {
            if (!commentMapper.findByIdx(idx).get().getUser_id().equals(user_id)) {
                throw new CNotHaveAccessInfoException("해당 회원의 댓글 번호가 아닙니다.");
            }
        } else {
            throw new CNotFoundInfoByIdxException("해당 댓글 번호의 정보가 없습니다.");
        }
    }


    private void checkBoard(int idx, String user_id) {
        if (boardMapper.findByIdx(idx).isPresent()) {
            if (!boardMapper.findByIdx(idx).get().getUser_id().equals(user_id)) {
                throw new CNotHaveAccessInfoException("해당 회원의 게시판 번호가 아닙니다.");
            }
        } else {
            throw new CNotFoundInfoByIdxException("해당 게시판 번호의 정보가 없습니다.");
        }
    }


}
