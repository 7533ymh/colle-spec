package com.example.backend.domain.language.controller;

import com.example.backend.domain.language.domain.Language;
import com.example.backend.global.response.CommonResult;
import com.example.backend.global.response.ListResult;
import com.example.backend.domain.language.service.LanguageService;
import com.example.backend.global.response.ResponseService;
import com.example.backend.domain.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Api(tags = {"3-8. Language"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LanguageController {

    private final LanguageService languageService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "어학 입력", notes = "어학정보를 저장한다.")
    @PostMapping(value = "/language")
    public CommonResult insert(@ApiParam(value = "어학 구분(영어, 중국어 등) ", required = true) @RequestParam String division,
                               @ApiParam(value = "어학 시험", required = true) @RequestParam String exam,
                               @ApiParam(value = "어학 시험점수", required = true) @RequestParam String exam_score,
                               @ApiParam(value = "어학 취득 일자", required = true) @RequestParam Date date,
                               @ApiParam(value = "어학 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        languageService.save(Language.builder()
                .user_idx(user_idx)
                .divsion(division)
                .exam(exam)
                .exam_score(exam_score)
                .date(date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("어학 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "어학 조회", notes = "어학정보를 조회한다.")
    @GetMapping(value = "/language")
    public ListResult<Language> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(languageService.findByUserIdx(user_idx),"어학학 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "어학 수정", notes = "어학정보를 수정한다")
    @PutMapping(value = "/language")
    public CommonResult modify(@ApiParam(value = "어학 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "어학 구분(영어, 중국어 등) ", required = true) @RequestParam String division,
                               @ApiParam(value = "어학 시험", required = true) @RequestParam String exam,
                               @ApiParam(value = "어학 시험점수", required = true) @RequestParam String exam_score,
                               @ApiParam(value = "어학 취득 일자", required = true) @RequestParam Date date,
                               @ApiParam(value = "어학 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        languageService.modify(Language.builder()
                .idx(idx)
                .user_idx(user_idx)
                .divsion(division)
                .exam(exam)
                .exam_score(exam_score)
                .date(date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("어학 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "어학 삭제", notes = "어학정보를 삭제한다")
    @DeleteMapping(value = "/language")
    public CommonResult delete_language(@ApiParam(value = "어학 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        languageService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("어학 삭제가 완료되었습니다.");
    }



}
