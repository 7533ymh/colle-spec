package com.example.backend.controller;

import com.example.backend.config.security.JwtTokenProvider;
import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.response.*;
import com.example.backend.service.ResponseService;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;

@Api(tags = {"1. User"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;

    //로그인
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 입력하면 회원 정보를 반환해준다.")
    @GetMapping("/login")
    public SingleResult<String> login(@ApiParam(value = "회원 아이디(id) ", required = true) @RequestParam String id , @ApiParam(value = "회원 비밀번호(pass) ", required = true) @RequestParam String pass) throws Exception {

        User user = userService.findOne(id)
                .orElseThrow(() -> new  IllegalStateException("계정이 존재하지 않거나 아아디 또는 비밀번호가 정확하지 않습니다."));
        if (!passwordEncoder.matches(pass, user.getPassword()))
            throw new IllegalStateException("계정이 존재하지 않거나 아아디 또는 비밀번호가 정확하지 않습니다.");

        ArrayList<String> roles = new ArrayList<>();
        roles.add(user.getRole());
        return responseService.getSingleResult(jwtTokenProvider.createToken(String.valueOf(user.getIdx()), roles));

    }

    //회원가입
    @ApiOperation(value = "회원 가입", notes = "회원정보를 입력하여 가입한다.")
    @PostMapping("/register")
    public CommonResult register(@ApiParam(value = "회원 아이디", required = true) @RequestParam String id,
                                 @ApiParam(value = "회원 비밀번호", required = true) @RequestParam String pass,
                                 @ApiParam(value = "회원 이름", required = true) @RequestParam String name,
                                 @ApiParam(value = "회원 성별", required = true) @RequestParam String sex,
                                 @ApiParam(value = "회원 이메일", required = true) @RequestParam String mail,
                                 @ApiParam(value = "회원 휴대폰 번호", required = true) @RequestParam String phone,
                                 @ApiParam(value = "회원 목표직종", required = true) @RequestParam String objective,
                                 @ApiParam(value = "회원 목표기업", required = true) @RequestParam String enterprise)  {

        userService.register(User.builder()
                .id(id)
                .pass(passwordEncoder.encode(pass))
                .name(name)
                .sex(sex)
                .mail(mail)
                .phone(phone)
                .objective(objective)
                .enterprise(enterprise)
                .role("ROLE_USER")
                .build());

        return responseService.getSuccessResultMsg("회원가입 성공");

    }

    //회원 아이디 중복 체크
    @ApiOperation(value = "회원 아이디 중복 체크", notes = "회원 아이디가 중복인지 체크한다.")
    @GetMapping("/register/check/{id}")
    public CommonResult registerCheck(@ApiParam(value = "회원 아이디(id)", required = true) @PathVariable("id") String id) {
        userService.validateDuplicateUser(id);
        return responseService.getSuccessResultMsg("해당 아이디 사용이 가능합니다.");

    }




}
