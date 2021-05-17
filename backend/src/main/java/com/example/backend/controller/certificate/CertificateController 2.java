package com.example.backend.controller.certificate;

import com.example.backend.domain.Certificate;
import com.example.backend.domain.Language;
import com.example.backend.response.CommonResult;
import com.example.backend.response.ListResult;
import com.example.backend.service.certificate.CertificateService;
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

@Api(tags = {"8. Certificate"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CertificateController {

    private final CertificateService certificateService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "자격증 입력", notes = "자격증정보를 저장한다.")
    @PostMapping(value = "/certificate")
    public CommonResult insert(@ApiParam(value = "자격증 이름", required = true) @RequestParam String title,
                               @ApiParam(value = "자격증 발행기관", required = true) @RequestParam String publisher,
                               @ApiParam(value = "자격증 취득 날짜", required = true) @RequestParam Date date,
                               @ApiParam(value = "자격증 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        certificateService.save(Certificate.builder()
                .user_idx(user_idx)
                .title(title)
                .publisher(publisher)
                .date(date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("자격증 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "자격증 조회", notes = "자격증정보를 조회한다.")
    @GetMapping(value = "/certificate")
    public ListResult<Certificate> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(certificateService.findByUserIdx(user_idx),"자격증 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "자격증 수정", notes = "자격증정보를 수정한다")
    @PutMapping(value = "/certificate")
    public CommonResult modify(@ApiParam(value = "자격증 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "자격증 이름", required = true) @RequestParam String title,
                               @ApiParam(value = "자격증 발행기관", required = true) @RequestParam String publisher,
                               @ApiParam(value = "자격증 취득 날짜", required = true) @RequestParam Date date,
                               @ApiParam(value = "자격증 내용", required = true) @RequestParam String content) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        certificateService.modify(Certificate.builder()
                .idx(idx)
                .user_idx(user_idx)
                .title(title)
                .publisher(publisher)
                .date(date)
                .content(content)
                .build());

        return responseService.getSuccessResultMsg("자격증 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "자격증 삭제", notes = "자격증정보를 삭제한다")
    @DeleteMapping(value = "/certificate")
    public CommonResult delete(@ApiParam(value = "자격증 번호 ", required = true) @RequestParam int idx) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        certificateService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("자격증 삭제가 완료되었습니다.");
    }

}
