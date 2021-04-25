package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.response.*;
import com.example.backend.service.ResponseService;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private ResponseService responseService;

    //로그인
    @ApiOperation(value = "로그인", notes = "아이디와 비밀번호를 입력하면 회원 정보를 반환해준다.")
    @GetMapping("/login")
    public SingleResult<User> login(@ApiParam(value = "회원 아이디(id) ", required = true) @RequestParam String id , @ApiParam(value = "회원 비밀번호(pass) ", required = true) @RequestParam String pass) throws Exception {
        return responseService.getSingleResult(userService.login(id , pass));

    }

    //회원가입
    @ApiOperation(value = "회원 가입", notes = "회원정보를 입력하여 가입한다.")
    @PostMapping("/register")
    public SingleResult<User> register(@ApiParam(value = "회원정보객체(id , pass , name, sex, mail , phone, objective , enterprise만 필요)", required = true) @RequestBody User user)  {
        return responseService.getSingleResult(userService.register(user));

    }

    //회원 아이디 중복 체크
    @ApiOperation(value = "회원 아이디 중복 체크", notes = "회원 아이디가 중복인지 체크한다.")
    @GetMapping("/register/check/{id}")
    public CommonResult registerCheck(@ApiParam(value = "회원 아이디(id)", required = true) @PathVariable("id") String id) {
        userService.validateDuplicateUser(id);
        return responseService.getSuccessResult();

    }




}
