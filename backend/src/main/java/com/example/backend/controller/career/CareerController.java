package com.example.backend.controller.career;

import com.example.backend.domain.Career;
import com.example.backend.mapper.CareerMapper;
import com.example.backend.response.CommonResult;
import com.example.backend.response.ListResult;
import com.example.backend.service.career.CareerService;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@Api(tags = {"3. Career"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CareerController {

    private final CareerMapper careerMapper;
    private final CareerService careerService;
    private final ResponseService responseService;
    private final UserService userService;

    //@ApiImplicitParams({@ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")})
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
        int idx = userService.findIdxById(AuthId);

        careerMapper.save(Career.builder()
                .user_idx(idx)
                .division(division)
                .company(company)
                .department(department)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .score(10)
                .build());

        return responseService.getSuccessResultMsg("경력 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "경력 조회", notes = "경력정보를 조회한다.")
    @GetMapping(value = "/career")
    public ListResult<Career> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(careerService.findByUserIdx(idx),"경력 정보를 조회하였습니다.");
    }

}