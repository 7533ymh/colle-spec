package com.example.backend.service.link;

import com.example.backend.advice.exception.CUserNotFoundException;
import com.example.backend.domain.Grade;
import com.example.backend.domain.User;
import com.example.backend.mapper.collspec.UserMapper;
import com.example.backend.service.grade.GradeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class LinkserviceTest {

    @Autowired
    Linkservice linkservice;

    @Autowired
    UserMapper userMapper;

    @Autowired
    GradeService gradeService;

    @Test
    void 어플로그인() {

        //given
        String id = "abc";
        String pw = "abc";


        //when
        int appidx = linkservice.linkLogin(id, pw);

        //then
        assertThat(1).isEqualTo(appidx);

    }

    @Test
    void 기본정보입력() {

        //given
        int appidx = 1;
        int idx = 25;


        //when
       linkservice.linkInfo(appidx,idx);


        //then
        User check = userMapper.findByIdx(25)
                .orElseThrow(CUserNotFoundException::new);

        assertThat("경상국립대학교").isEqualTo(check.getCollege());
        assertThat("컴퓨터과학과").isEqualTo(check.getMajor());
        assertThat(3).isEqualTo(check.getGrade());


    }

    @Test
    void 학점정보입력() {

        //given
        int appidx = 1;
        int idx = 25;
        Grade test = new Grade();
        test.setUser_idx(25);
        test.setGrade("1");
        test.setSemester("1");
        test.setAvg_score(4.5);

        //when
        gradeService.save(test);
        linkservice.linkGrade(appidx,idx);


        //then

        assertThat(4).isEqualTo(gradeService.findByUserIdx(25).size());


    }


}

