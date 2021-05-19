package com.example.backend.domain.career.controller;

import com.example.backend.domain.career.domain.Career;
import com.example.backend.domain.career.service.CareerService;
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

import java.sql.Date;


@Api(tags = {"3-2. Career"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareerController {

    private final CareerService careerService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "경력 입력", notes = "경력정보를 저장한다.")
    @PostMapping(value = "/career")
    public CommonResult insert(@ApiParam(value = "경력 구분 ", required = true) @RequestParam String division,
                               @ApiParam(value = "경력 회사", required = true) @RequestParam String company,
                               @ApiParam(value = "경력 부서", required = true) @RequestParam String department,
                               @ApiParam(value = "경력 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "경력 퇴사년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "경력 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        careerService.save(Career.builder()
                .user_idx(user_idx)
                .division(division)
                .company(company)
                .department(department)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("경력 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "경력 조회", notes = "경력정보를 조회한다.")
    @GetMapping(value = "/career")
    public ListResult<Career> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(careerService.findByUserIdx(user_idx),"경력 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "경력 수정", notes = "경력정보를 수정한다")
    @PutMapping(value = "/career")
    public CommonResult modify(@ApiParam(value = "경력 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "경력 구분 ", required = true) @RequestParam String division,
                               @ApiParam(value = "경력 회사", required = true) @RequestParam String company,
                               @ApiParam(value = "경력 부서", required = true) @RequestParam String department,
                               @ApiParam(value = "경력 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "경력 퇴사년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "경력 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        careerService.modify(Career.builder()
                .idx(idx)
                .division(division)
                .company(company)
                .department(department)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .user_idx(user_idx)
                .build());

        return responseService.getSuccessResultMsg("경력 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "경력 삭제", notes = "경력정보를 삭제한다")
    @DeleteMapping(value = "/career")
    public CommonResult delete(@ApiParam(value = "경력 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        careerService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("경력 삭제가 완료되었습니다.");
    }

}
