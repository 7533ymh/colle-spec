package com.example.backend.service.link;

import com.example.backend.domain.grade.service.GradeService;
import com.example.backend.domain.link.linkmapper.LinkMapper;
import com.example.backend.domain.link.service.LinkService;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.user.mapper.UserMapper;
import com.example.backend.global.exception.exception.CUserLoginFailException;
import com.example.backend.global.exception.exception.CUserNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LinkServiceTest {

    @Autowired
    LinkService linkservice;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GradeService gradeService;

    @Autowired
    LinkMapper linkMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void 어플로그인() {

        //given
        String id = "test1";
        String pw = "1234";


        //when
        int appidx = linkservice.linkLogin(id, pw);

        //then
        assertThat(appidx).isEqualTo(4);

    }

    @Test
    void 기본정보입력() {

        //given
        int appidx = 4;
        int idx = 26;


        //when
       linkservice.linkInfo(appidx,idx);


        //then
        User check = userMapper.findByIdx(26)
                .orElseThrow(CUserNotFoundException::new);

        assertThat(check.getCollege()).isEqualTo("경상국립대학교");
        assertThat(check.getMajor()).isEqualTo("컴퓨터과학과");
        assertThat(check.getGrade()).isEqualTo(3);


    }

    @Test
    void 학점정보입력() {

        //given
        int appidx = 4;
        int idx = 26;


        //when

        linkservice.linkGrade(appidx,idx);


        //then

        assertThat(gradeService.findByUserIdx(26).size()).isEqualTo(6);


    }

    @Test
    void 비밀번호(){


        //given
        String id = "qaws";
        String pw = "qaws";

        //when
        User user = linkMapper.findById(id)
                .orElseThrow(CUserNotFoundException::new);

        if (!passwordEncoder.matches(pw, "{bcrypt}"+user.getPassword()))
            throw new CUserLoginFailException();


    }

}

