package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import com.example.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    //회원가입
    @PostMapping("/register")
    public User register(@RequestBody User user) {
       userService.register(user);
       return user;
    }

    //회원 아이디 중복 체크
    @GetMapping("/register/{id}")
    public void registerCheck(@PathVariable("id") String id) {
        userService.validateDuplicateUser(id);
    }


}
