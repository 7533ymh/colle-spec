package com.example.backend.service;

import com.example.backend.mapper.UserMapper;

public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
