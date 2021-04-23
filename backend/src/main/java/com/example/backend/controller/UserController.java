package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.response.DefaultRes;
import com.example.backend.response.ResponseMessage;
import com.example.backend.response.StatusCode;
import com.example.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"1. User"})
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;


    //회원가입
    @ApiOperation(value = "회원 가입", notes = "회원정보를 입력하여 가입한다.")
    @PostMapping("/register")
    public ResponseEntity register(@ApiParam(value = "회원정보객체(id , pass , sex, mail , phone, objective , enterprise , name 만 필요)", required = true) @RequestBody User user) {
        String result = userService.register(user);

        if ( result == null) {
            return new ResponseEntity(DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.CREATED_USER_FAIL,result), HttpStatus.CONFLICT);
        }
        else {
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.CREATED_USER_SUCCESS,result), HttpStatus.OK);
        }

    }

    //회원 아이디 중복 체크
    @ApiOperation(value = "회원 아이디 중복 체크", notes = "회원 아이디가 중복인지 체크한다.")
    @GetMapping("/register/check/{id}")
    public ResponseEntity registerCheck(@ApiParam(value = "회원 아이디", required = true) @PathVariable("id") String id) {
        boolean result = userService.validateDuplicateUser(id);
        if (result == true)
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.USER_CHECK_SUCCESS), HttpStatus.OK);
        else
            return new ResponseEntity(DefaultRes.res(StatusCode.CONFLICT, ResponseMessage.USER_CHECK_FAIL), HttpStatus.CONFLICT);

    }


}
