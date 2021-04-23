package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


     public String register (User user) {

       if ( validateDuplicateUser(user.getId())) {
           userMapper.insert(user);
           return user.getId();
       }
       else {
           return null;
       }

     }

     public boolean validateDuplicateUser(String id) {
        if( userMapper.findById(id).isPresent() ) {
            return false;
        }
       else {
           return true;
        }
    }

    public Optional<User> findOne(String id) {
        return userMapper.findById(id);
    }





}
