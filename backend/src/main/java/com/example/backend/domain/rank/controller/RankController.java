package com.example.backend.domain.rank.controller;

import com.example.backend.domain.rank.domain.RankResult;
import com.example.backend.global.response.SingleResult;
import com.example.backend.domain.rank.service.RankService;
import com.example.backend.global.response.ResponseService;
import com.example.backend.domain.user.service.UserService;
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
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RankController {

    private final RankService rankService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "전체회원 랭크 ", notes = "랭크정보(전체회원기준)를 데이터베이스에 업데이트한다.")
    @GetMapping("/rank")
    public SingleResult<RankResult> rankAll() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        return responseService.getSingleResult(rankService.change_Rank_All(user_idx));

    }

    @ApiOperation(value = "학년 랭크 ", notes = "학년랭크정보를 조회한다.")
    @GetMapping("/rank/grade")
    public SingleResult<RankResult> rankGrade() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        return  responseService.getSingleResult(rankService.change_Rank_Grade(user_idx));

    }

    @ApiOperation(value = "대학교 랭크 ", notes = "대학교 랭크정보를 조회한다.")
    @GetMapping("/rank/college")
    public SingleResult<RankResult> rankCollege() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        return  responseService.getSingleResult(rankService.change_Rank_College(user_idx));

    }

    @ApiOperation(value = "대학교&학년 랭크 ", notes = "대학교&학년 랭크정보를 조회한다.")
    @GetMapping("/rank/college/grade")
    public SingleResult<RankResult> rankCollegeGrade() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        return  responseService.getSingleResult(rankService.change_Rank_College_Grade(user_idx));

    }






}
