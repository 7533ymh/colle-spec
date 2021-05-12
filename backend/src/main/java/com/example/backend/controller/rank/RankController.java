package com.example.backend.controller.rank;

import com.example.backend.domain.RankResult;
import com.example.backend.response.CommonResult;
import com.example.backend.response.SingleResult;
import com.example.backend.service.rank.RankService;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"5. Rank"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RankController {

    private final RankService rankService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "전체회원 랭크 ", notes = "랭크정보(전체회원기준)를 데이터베이스에 업데이트한다.")
    @GetMapping("/rank")
    public CommonResult rankAll() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        rankService.change_Rank_All(idx);


        return responseService.getSuccessResultMsg("랭크정보가 업데이트되었습니다.");

    }

    @ApiOperation(value = "학년 랭크 ", notes = "학년랭크정보를 조회한다.")
    @GetMapping("/rank/grade")
    public SingleResult<RankResult> rankGrade() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        return  responseService.getSingleResult(rankService.change_Rank_Grade(idx));

    }

    @ApiOperation(value = "대학교 랭크 ", notes = "대학교 랭크정보를 조회한다.")
    @GetMapping("/rank/college")
    public SingleResult<RankResult> rankCollege() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        return  responseService.getSingleResult(rankService.change_Rank_College(idx));

    }

    @ApiOperation(value = "대학교&학년 랭크 ", notes = "대학교&학년 랭크정보를 조회한다.")
    @GetMapping("/rank/college/grade")
    public SingleResult<RankResult> rankCollegeGrade() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        return  responseService.getSingleResult(rankService.change_Rank_College_Grade(idx));

    }






}
