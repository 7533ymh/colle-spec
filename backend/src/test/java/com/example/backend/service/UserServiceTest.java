package com.example.backend.service;

import com.example.backend.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;


    @Test
    void 회원가입() {
        //when
        User user = new User();
        user.setId("abc123");
        user.setPass("123");
        user.setName("유민환");
        user.setSex("남");
        user.setMail("abc");
        user.setPhone("010");
        user.setObjective("it d");
        user.setEnterprise("kakao");

        //when
        User saveId = userService.register(user);
        //then
        User findUser = userService.findOne(saveId.getId()).get();
        assertThat(user.getName()).isEqualTo(findUser.getName());

    }

    @Test
    void 중복_회원_예외() {

        User user1 = new User();
        user1.setId("abc123");
        user1.setPass("123");
        user1.setName("유민환");
        user1.setSex("남");
        user1.setMail("abc");
        user1.setPhone("010");
        user1.setObjective("it d");
        user1.setEnterprise("kakao");

        User user2 = new User();
        user2.setId("abc123");
        user2.setPass("123");
        user2.setName("유민환");
        user2.setSex("남");
        user2.setMail("abc");
        user2.setPhone("010");
        user2.setObjective("it d");
        user2.setEnterprise("kakao");

        userService.register(user1);
        IllegalStateException e = assertThrows(IllegalStateException.class , ()->userService.register(user2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


    }
}