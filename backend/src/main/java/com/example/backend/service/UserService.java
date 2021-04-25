package com.example.backend.service;

import com.example.backend.domain.User;
import com.example.backend.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserMapper userMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


     public User register (User user) {

         logger.info("회원가입");

         validateDuplicateUser(user.getId());

         userMapper.insert(user);

         return user;

     }

     public void validateDuplicateUser(String id) {

         logger.info("아이디 중복 검사");

         userMapper.findById(id)
                 .ifPresent(m->{
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });

    }

    public Optional<User> findOne(String id) {

        logger.info("아이디를 통해 회원찾기");

        return userMapper.findById(id);
    }

    public User login(String id , String pass) {

        logger.info("로그인");

        CheckUserExist(id, pass);

        return userMapper.findByIdPass(id , pass).get();

    }

    private void CheckUserExist(String id, String pass) {

        logger.info("로그인요청받은 값으로 회원정보 있는 지 확인");

        userMapper.findByIdPass(id, pass)
                .orElseThrow(() -> new  IllegalStateException("계정이 존재하지 않거나 아아디 또는 비밀번호가 정확하지 않습니다."));

    }


}
