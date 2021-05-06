package com.example.backend.controller.experience;

import com.example.backend.domain.Experience;
import com.example.backend.response.CommonResult;
import com.example.backend.response.ListResult;
import com.example.backend.service.experience.ExperienceService;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Api(tags = {"9. Experience"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ExperienceController {

    private final ExperienceService experienceService;
    private final ResponseService responseService;
    private final UserService userService;


    @ApiOperation(value = "해외경험 입력", notes = "해외경험험정보를 저장한다.")
    @PostMapping(value = "/experience")
    public CommonResult insert(@ApiParam(value = "경험 나라 ", required = true) @RequestParam String country,
                               @ApiParam(value = "경험 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "경험 마감년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "경험 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        experienceService.save(Experience.builder()
                .user_idx(user_idx)
                .country(country)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("해외경험 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "해외경험 조회", notes = "해외경험정보를 조회한다.")
    @GetMapping(value = "/experience")
    public ListResult<Experience> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(experienceService.findByUserIdx(user_idx),"해외경험 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "해외경험 수정", notes = "해외경험정보를 수정한다")
    @PutMapping(value = "/experience")
    public CommonResult modify(@ApiParam(value = "경험 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "경험 나라 ", required = true) @RequestParam String country,
                               @ApiParam(value = "경험 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "경험 마감년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "경험 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        experienceService.modify(Experience.builder()
                .idx(idx)
                .user_idx(user_idx)
                .country(country)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("해외경험 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "해외경험 삭제", notes = "해외경험정보를 삭제한다")
    @DeleteMapping(value = "/experience")
    public CommonResult delete(@ApiParam(value = "경험 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        experienceService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("해외경험 삭제가 완료되었습니다.");
    }
    
    
    
    
    
}
