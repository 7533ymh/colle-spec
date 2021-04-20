package com.example.backend.controller;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST} )
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
       userMapper.insert(user);
       return user;
    }


}
