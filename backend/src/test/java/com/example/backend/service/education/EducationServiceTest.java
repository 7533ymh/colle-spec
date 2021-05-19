package com.example.backend.service.education;

import com.example.backend.global.exception.exception.CNotFoundInfoByIdxException;
import com.example.backend.global.exception.exception.CNotFoundInfoByUserException;
import com.example.backend.global.exception.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.education.domain.Education;
import com.example.backend.domain.education.service.EducationService;
import com.example.backend.domain.rank.domain.Score;
import com.example.backend.domain.education.mapper.EducationMapper;
import com.example.backend.domain.rank.mapper.RankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class EducationServiceTest {

    @Autowired
    EducationService educationService;

    @Autowired
    EducationMapper educationMapper;

    @Autowired
    RankMapper rankMapper;


    @Test
    void save() {

        //given
        Education test = new Education();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setAgency("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        Score finduser = rankMapper.FindScore(test.getUser_idx());


        //when
        educationService.save(test);

        //then
        assertThat(test.getContent()).isEqualTo(educationMapper.findByIdx(test.getIdx()).get().getContent());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(checkuser.getEducation_score()).isEqualTo(finduser.getEducation_score() + educationMapper.findByIdx(test.getIdx()).get().getScore());
        assertThat(checkuser.getScore()).isEqualTo(finduser.getScore() + educationMapper.findByIdx(test.getIdx()).get().getScore());

    }

    @Test
    void findByUserIdx() {

        //given
        Education test = new Education();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setAgency("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        //when
        educationService.save(test);

        //then
        //then
        List<Education> list = educationService.findByUserIdx(test.getUser_idx());
        assertThat(list.size()).isEqualTo(1);



    }

    @Test
    void modify() {

        //given
        Education test = new Education();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setAgency("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        Education change = new Education();
        change.setUser_idx(37);
        change.setTitle("abcde");
        change.setAgency("abcde");
        change.setStart_date(Date.valueOf("2017-03-01"));
        change.setEnd_date(Date.valueOf("2017-07-01"));
        change.setContent("abcde");

        Score finduser = rankMapper.FindScore(test.getUser_idx());

        //when
        educationService.save(test);
        change.setIdx(test.getIdx());
        educationService.modify(change);

        //then
        assertThat("abcde").isEqualTo(educationMapper.findByIdx(test.getIdx()).get().getContent());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(checkuser.getEducation_score()).isEqualTo(finduser.getEducation_score() + educationMapper.findByIdx(test.getIdx()).get().getScore());
        assertThat(checkuser.getScore()).isEqualTo(finduser.getScore() + educationMapper.findByIdx(test.getIdx()).get().getScore());


    }

    @Test
    void delete() {

        //given
        Education test = new Education();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setAgency("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        Education change = new Education();
        change.setUser_idx(37);
        change.setTitle("abcde");
        change.setAgency("abcde");
        change.setStart_date(Date.valueOf("2017-03-01"));
        change.setEnd_date(Date.valueOf("2017-07-01"));
        change.setContent("abcde");

        //when
        educationService.save(test);
        Score finduser = rankMapper.FindScore(test.getUser_idx());
        educationService.save(change);
        educationService.delete(change.getIdx(), change.getUser_idx());

        //then
        //then
        List<Education> list = educationMapper.findByUserIdx(test.getUser_idx());
        assertThat(1).isEqualTo(list.size());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(checkuser.getEducation_score()).isEqualTo(finduser.getEducation_score());
        assertThat(checkuser.getScore()).isEqualTo(finduser.getScore());



    }

    @Test
    void changetoNumber() {

        //given
        Education test = new Education();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setAgency("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");


        //when
        educationService.save(test);

        //then
        assertThat(educationMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(100);



    }

    @Test
    void checkEducationUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> educationService.findByUserIdx(user_idx));


        //then
        assertThat(e.getMessage()).isEqualTo("해당 회원의 교육 정보가 없습니다.");



    }

    @Test
    void checkAll() {
        //given
        Education test = new Education();
        test.setUser_idx(37);
        test.setTitle("abc");
        test.setAgency("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");


        //when
        educationService.save(test);
        CNotHaveAccessInfoException e = assertThrows(CNotHaveAccessInfoException.class , () -> educationService.delete(test.getIdx(),14));
        CNotFoundInfoByIdxException ex = assertThrows(CNotFoundInfoByIdxException.class , () -> educationService.delete(222,37));


        //then
        assertThat(e.getMessage()).isEqualTo("해당 회원의 교육 번호가 아닙니다.");
        assertThat(ex.getMessage()).isEqualTo("해당 교육 번호의 정보가 없습니다.");

    }
}