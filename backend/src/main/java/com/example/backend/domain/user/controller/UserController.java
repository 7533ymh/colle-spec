package com.example.backend.domain.user.controller;

import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.service.UserService;
import com.example.backend.global.response.CommonResult;
import com.example.backend.global.response.ResponseService;
import com.example.backend.global.response.SingleResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. User"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @ApiOperation(value = "회원 정보 조회", notes = "회원번호(idx)로 회원을 조회한다")
    @GetMapping("/user")
    public SingleResult<User> findUserById() {
        logger.info("회원 정보 조회");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return responseService.getSingleResult(userService.findById(id));

    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다")
    @PutMapping(value = "/user")
    public CommonResult modify(@ApiParam(value = "회원 아이디", required = true) @RequestParam String id,
                               @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String pass,
                               @ApiParam(value = "회원 이름", required = true) @RequestParam String name,
                               @ApiParam(value = "회원 학년", required = true) @RequestParam int grade,
                               @ApiParam(value = "회원 성별", required = true) @RequestParam String sex,
                               @ApiParam(value = "회원 이메일", required = true) @RequestParam String mail,
                               @ApiParam(value = "회원 휴대폰 번호", required = true) @RequestParam String phone,
                               @ApiParam(value = "회원 목표직종", required = true) @RequestParam String objective,
                               @ApiParam(value = "회원 목표기업", required = true) @RequestParam String enterprise) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        userService.modify(User.builder()
                .idx(idx)
                .id(id)
                .pass(passwordEncoder.encode(pass))
                .name(name)
                .grade(grade)
                .sex(sex)
                .mail(mail)
                .phone(phone)
                .objective(objective)
                .enterprise(enterprise)
                .build(),AuthId);

        return responseService.getSuccessResultMsg("회원 정보가 수정되었습니다.");
    }

    @ApiOperation(value = "회원 삭제", notes = "회원을 삭제한다")
    @DeleteMapping(value = "/user")
    public CommonResult delete( ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int idx = userService.findIdxById(AuthId);

        userService.delete(idx);

        return responseService.getSuccessResultMsg("회원 탈퇴가 완료되었습니다.");
    }



}
