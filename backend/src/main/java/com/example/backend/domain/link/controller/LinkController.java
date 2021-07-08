package com.example.backend.domain.link.controller;

import com.example.backend.domain.link.service.LinkService;
import com.example.backend.domain.user.service.UserService;
import com.example.backend.global.exception.exception.CLinkException;
import com.example.backend.global.response.CommonResult;
import com.example.backend.global.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"4. Link"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LinkController {

    private final LinkService linkservice;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "연동 로그인 ", notes = "아이디와 비밀번호를 입력하면 연동정보를 데이터베이스에 입력한다. ( 로그인은 한번만 하면 됨 )")
    @PostMapping("/link")
    public CommonResult linksignin(@ApiParam(value = "어플 회원 아이디(id) ", required = true) @RequestParam String id,
                                   @ApiParam(value = "어플 회원 비밀번호(pass) ", required = true) @RequestParam String pass) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        if (userService.findByIdx(idx).getLink() != 0){
            throw new CLinkException("잘못된 접근입니다. 이미 연동되어있습니다.");
        }

        int appidx = linkservice.linkLogin(id, pass);

        if(userService.findByLink(appidx).isPresent()){
            throw new CLinkException("이미 다른아이디에서 연동된 계정입니다. 다시 시도해주세요.");
        }


        linkservice.linkInfo(appidx, idx);
        linkservice.linkGrade(appidx, idx);


        return responseService.getSuccessResultMsg("연동이 완료되었습니다.");

    }


    @ApiOperation(value = "연동 업데이트 ", notes = "연동정보를 데이터베이스에 업데이트한다.")
    @GetMapping("/link/update")
    public CommonResult linkupdate() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);


        int appidx = userService.findByIdx(idx).getLink();
        if (appidx == 0 ){
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");
        }
        linkservice.linkInfo(appidx, idx);
        linkservice.linkGrade(appidx, idx);


        return responseService.getSuccessResultMsg("연동정보가 업데이트되었습니다.");

    }


}
