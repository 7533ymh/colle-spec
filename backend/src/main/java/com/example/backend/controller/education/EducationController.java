package com.example.backend.controller.education;

import com.example.backend.domain.Education;
import com.example.backend.response.CommonResult;
import com.example.backend.response.ListResult;
import com.example.backend.service.education.EducationService;
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

@Api(tags = {"6. Education"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class EducationController {

    private final EducationService educationService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "교육 입력", notes = "교육정보를 저장한다.")
    @PostMapping(value = "/education")
    public CommonResult insert(@ApiParam(value = "교육 제목 ", required = true) @RequestParam String title,
                               @ApiParam(value = "교육 기관", required = true) @RequestParam String agency,
                               @ApiParam(value = "교육 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "교육 마감년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "교육 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        educationService.save(Education.builder()
                .user_idx(user_idx)
                .title(title)
                .agency(agency)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("교육 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "교육 조회", notes = "교육정보를 조회한다.")
    @GetMapping(value = "/education")
    public ListResult<Education> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(educationService.findByUserIdx(user_idx),"교육 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "교육 수정", notes = "교육정보를 수정한다")
    @PutMapping(value = "/education")
    public CommonResult modify(@ApiParam(value = "교육 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "교육 제목 ", required = true) @RequestParam String title,
                               @ApiParam(value = "교육 기관", required = true) @RequestParam String agency,
                               @ApiParam(value = "교육 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "교육 마감년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "교육 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        educationService.modify(Education.builder()
                .idx(idx)
                .user_idx(user_idx)
                .title(title)
                .agency(agency)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("교육 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "교육 삭제", notes = "교육정보를 삭제한다")
    @DeleteMapping(value = "/education")
    public CommonResult delete(@ApiParam(value = "교육 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        educationService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("교육 삭제가 완료되었습니다.");
    }
}
