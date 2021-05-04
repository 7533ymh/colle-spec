package com.example.backend.controller.award;

import com.example.backend.domain.Award;
import com.example.backend.response.CommonResult;
import com.example.backend.response.ListResult;
import com.example.backend.service.award.AwardService;
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



@Api(tags = {"4. Award"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AwardController {

    private final AwardService awardService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "수상 입력", notes = "수상정보를 저장한다.")
    @PostMapping(value = "/award")
    public CommonResult insert(@ApiParam(value = "수상명 ", required = true) @RequestParam String title,
                               @ApiParam(value = "수여 기관", required = true) @RequestParam String agency,
                               @ApiParam(value = "상 구분", required = true) @RequestParam String division,
                               @ApiParam(value = "수상 연도", required = true) @RequestParam int year,
                               @ApiParam(value = "내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        awardService.save(Award.builder()
                .user_idx(user_idx)
                .title(title)
                .agency(agency)
                .division(division)
                .year(year)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("수상 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "수상 조회", notes = "수상정보를 조회한다.")
    @GetMapping(value = "/award")
    public ListResult<Award> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(awardService.findByUserIdx(user_idx),"수상 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "수상 수정", notes = "수상정보를 수정한다")
    @PutMapping(value = "/award")
    public CommonResult modify(@ApiParam(value = "수상 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "수상명 ", required = true) @RequestParam String title,
                               @ApiParam(value = "수여 기관", required = true) @RequestParam String agency,
                               @ApiParam(value = "상 구분", required = true) @RequestParam String division,
                               @ApiParam(value = "수상 연도", required = true) @RequestParam int year,
                               @ApiParam(value = "내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        awardService.modify(Award.builder()
                .idx(idx)
                .title(title)
                .agency(agency)
                .division(division)
                .year(year)
                .content(content)
                .user_idx(user_idx)
                .build());

        return responseService.getSuccessResultMsg("수상 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "수상 삭제", notes = "수상정보를 삭제한다")
    @DeleteMapping(value = "/award")
    public CommonResult delete(@ApiParam(value = "수상 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        awardService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("수상 삭제가 완료되었습니다.");
    }



}
