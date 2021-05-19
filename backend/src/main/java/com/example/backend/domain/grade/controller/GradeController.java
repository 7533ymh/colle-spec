package com.example.backend.domain.grade.controller;

import com.example.backend.domain.grade.domain.Grade;
import com.example.backend.domain.grade.service.GradeService;
import com.example.backend.domain.user.service.UserService;
import com.example.backend.global.response.CommonResult;
import com.example.backend.global.response.ListResult;
import com.example.backend.global.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3-6. Grade"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class GradeController {

    private final GradeService gradeService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "학점 입력", notes = "학점정보를 저장한다.")
    @PostMapping(value = "/grade")
    public CommonResult insert(@ApiParam(value = "학점 학년 ", required = true) @RequestParam String grade,
                               @ApiParam(value = "학점 학기 (1,하계계절,2,동계계절)", required = true) @RequestParam String semester,
                               @ApiParam(value = "학점 평균평점", required = true) @RequestParam Double avg_score) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        gradeService.save(Grade.builder()
                .user_idx(user_idx)
                .grade(grade)
                .semester(semester)
                .avg_score(avg_score)
                .build());

        return responseService.getSuccessResultMsg("학점 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "학점 조회", notes = "학점정보를 조회한다.")
    @GetMapping(value = "/grade")
    public ListResult<Grade> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);
        
        return responseService.getListResultMsg(gradeService.findByUserIdx(user_idx),"학점 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "학점 수정", notes = "학점정보를 수정한다")
    @PutMapping(value = "/grade")
    public CommonResult modify(@ApiParam(value = "학점 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "학점 학년 ", required = true) @RequestParam String grade,
                               @ApiParam(value = "학점 학기 (1,하계계절,2,동계계절)", required = true) @RequestParam String semester,
                               @ApiParam(value = "학점 평균평점", required = true) @RequestParam Double avg_score) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        gradeService.modify(Grade.builder()
                .idx(idx)
                .user_idx(user_idx)
                .grade(grade)
                .semester(semester)
                .avg_score(avg_score)
                .build());

        return responseService.getSuccessResultMsg("학점 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "학점 삭제", notes = "학점정보를 삭제한다")
    @DeleteMapping(value = "/grade")
    public CommonResult delete(@ApiParam(value = "학점 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        gradeService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("학점 삭제가 완료되었습니다.");
    }


}
