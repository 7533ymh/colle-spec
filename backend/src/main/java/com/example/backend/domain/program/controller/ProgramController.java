package com.example.backend.domain.program.controller;

import com.example.backend.domain.program.domain.Board;
import com.example.backend.domain.program.domain.BoardList;
import com.example.backend.domain.program.domain.Comment;
import com.example.backend.domain.program.domain.Program;
import com.example.backend.domain.program.service.ProgramService;
import com.example.backend.domain.user.service.UserService;
import com.example.backend.global.response.CommonResult;
import com.example.backend.global.response.ListResult;
import com.example.backend.global.response.ResponseService;
import com.example.backend.global.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"6. Program"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProgramController {

    private final ProgramService programService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "프로그램 조회", notes = "프로그램 정보를 조회한다.")
    @GetMapping(value = "/program")
    public ListResult<Program> showProgram(@ApiParam(value = "프로그램 구분(공모전&대회, 프로그램, 봉사활동)", required = true) @RequestParam String division) {

        return responseService.getListResultMsg(programService.findByDivision(division), "프로그램 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "게시판 입력", notes = "게시판 정보를 저장한다.")
    @PostMapping(value = "/program/board")
    public CommonResult insertBoard(@ApiParam(value = "프로그램 번호 ", required = true) @RequestParam int program_idx,
                                    @ApiParam(value = "게시판 제목", required = true) @RequestParam String title,
                                    @ApiParam(value = "게시판 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();

        programService.saveBoard(Board.builder()
                .program_idx(program_idx)
                .user_id(AuthId)
                .title(title)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("게시판이 등록되었습니다.");
    }

    @ApiOperation(value = "게시판 리스트 조회", notes = "게시판 리스트 정보를 조회한다.")
    @GetMapping(value = "/program/boardList")
    public ListResult<BoardList> showBoardList(@ApiParam(value = "프로그램 번호", required = true) @RequestParam int program_idx) {


        return responseService.getListResultMsg(programService.findByProgram_idx(program_idx), "게시판 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "게시판 단건 조회", notes = "게시판 하나의 정보를 조회한다.")
    @GetMapping(value = "/program/board")
    public SingleResult<Board> showBoard(@ApiParam(value = "게시판 번호", required = true) @RequestParam int board_idx) {


        return responseService.getSingleResult(programService.findOneByIdx(board_idx));
    }


    @ApiOperation(value = "게시판 수정", notes = "게시판 정보를 수정한다")
    @PutMapping(value = "/program/board")
    public CommonResult modifyBoard(@ApiParam(value = "게시판 번호 ", required = true) @RequestParam int idx,
                                    @ApiParam(value = "게시판 제목", required = true) @RequestParam String title,
                                    @ApiParam(value = "게시판 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();

        programService.updateBoard(Board.builder()
                .idx(idx)
                .user_id(AuthId)
                .title(title)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("게시판 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "게시판 삭제", notes = "게시판 정보를 삭제한다")
    @DeleteMapping(value = "/program/board")
    public CommonResult deleteBoard(@ApiParam(value = "게시판 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();

        programService.deleteBoard(idx, AuthId);

        return responseService.getSuccessResultMsg("게시판 삭제가 완료되었습니다.");
    }

    @ApiOperation(value = "댓글 입력", notes = "댓글 정보를 저장한다.")
    @PostMapping(value = "/program/board/comment")
    public CommonResult insertComment(@ApiParam(value = "게시판 번호 ", required = true) @RequestParam int board_idx,
                                      @ApiParam(value = "댓글 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();

        programService.saveComment(Comment.builder()
                .board_idx(board_idx)
                .user_id(AuthId)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("댓글이 등록되었습니다.");
    }


    @ApiOperation(value = "댓글 수정", notes = "댓글글 정보 수정한다")
    @PutMapping(value = "/program/board/comment")
    public CommonResult modifyComment(@ApiParam(value = "댓글 번호 ", required = true) @RequestParam int idx,
                                      @ApiParam(value = "댓글 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();

        programService.updateComment(Comment.builder()
                .idx(idx)
                .user_id(AuthId)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("댓글 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글 정보를 삭제한다")
    @DeleteMapping(value = "/program/board/comment")
    public CommonResult deleteComment(@ApiParam(value = "댓글 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();

        programService.deleteComment(idx, AuthId);

        return responseService.getSuccessResultMsg("댓글 삭제가 완료되었습니다.");
    }


}
