package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserMapper userMapper;



     public User register (User user) {

         validateDuplicateUser(user.getId());

         userMapper.insert(user);

         return user;

     }

     public void validateDuplicateUser(String id) {
         userMapper.findById(id)
                 .ifPresent(m->{
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });

    }

    public Optional<User> findOne(String id) {
        return userMapper.findById(id);
    }





}
