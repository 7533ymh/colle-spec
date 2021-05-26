package com.example.backend.domain.user.service;

import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserMapper userMapper;


    @Test
    void signup() {

        int check = 1;

        for (int i = 81; i < 161; i++) {

            if ( i >= 141) {
                check = 4;
            }
            else if (i >= 121){
                check = 3;
            }
            else if (i >= 101){
                check = 2;
            }

            User user = new User();
            user.setId(String.valueOf(i));
            user.setPass(passwordEncoder.encode("1"));
            user.setName("test");
            user.setCollege("경상국립대학교");
            user.setMajor("컴퓨터과학과");
            user.setLink(-1);
            user.setGrade(check);
            user.setSex("t");
            user.setMail("test");
            user.setPhone("test");
            user.setObjective("test");
            user.setEnterprise("test");
            user.setRole("ROLE_USER");



            userService.signup(user);

            userMapper.updateLink(user);






        }





    }
}