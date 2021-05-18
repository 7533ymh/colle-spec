package com.example.backend.service.experience;

import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Experience;
import com.example.backend.domain.Score;
import com.example.backend.mapper.collspec.ExperienceMapper;
import com.example.backend.mapper.collspec.RankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class ExperienceServiceTest {

    @Autowired
    RankMapper rankMapper;

    @Autowired
    ExperienceService experienceService;

    @Autowired
    ExperienceMapper experienceMapper;

    @Test
    void save() {

        //given
        Experience test = new Experience();
        test.setUser_idx(37);
        test.setCountry("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        Score finduser =rankMapper.FindScore(test.getUser_idx());


        //when
        experienceService.save(test);

        //then
        assertThat(test.getContent()).isEqualTo(experienceMapper.findByIdx(test.getIdx()).get().getContent());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(finduser.getExperience_score() + experienceMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(checkuser.getExperience_score());
        assertThat(finduser.getScore() + experienceMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(checkuser.getScore());



    }

    @Test
    void findByUserIdx() {

        //given
        Experience test = new Experience();
        test.setUser_idx(37);
        test.setCountry("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        //when
        experienceService.save(test);

        //then
        assertThat(1).isEqualTo(experienceService.findByUserIdx(test.getUser_idx()).size());

    }

    @Test
    void modify() {
        //given
        Experience test = new Experience();
        test.setUser_idx(37);
        test.setCountry("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");


        Experience change = new Experience();
        change.setUser_idx(37);
        change.setCountry("abcde");
        change.setStart_date(Date.valueOf("2017-05-01"));
        change.setEnd_date(Date.valueOf("2017-07-01"));
        change.setContent("abcde");

        Score finduser = rankMapper.FindScore(test.getUser_idx());

        //when
        experienceService.save(test);
        change.setIdx(test.getIdx());
        experienceService.modify(change);

        //then
        assertThat("abcde").isEqualTo(experienceMapper.findByIdx(test.getIdx()).get().getContent());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(finduser.getExperience_score() + experienceMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(checkuser.getExperience_score());
        assertThat(finduser.getScore() + experienceMapper.findByIdx(test.getIdx()).get().getScore()).isEqualTo(checkuser.getScore());

    }

    @Test
    void delete() {

        //given
        Experience test = new Experience();
        test.setUser_idx(37);
        test.setCountry("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");


        Experience change = new Experience();
        change.setUser_idx(37);
        change.setCountry("abcde");
        change.setStart_date(Date.valueOf("2017-05-01"));
        change.setEnd_date(Date.valueOf("2017-07-01"));
        change.setContent("abcde");

        //when
        experienceService.save(test);
        Score finduser = rankMapper.FindScore(test.getUser_idx());
        experienceService.save(change);
        experienceService.delete(change.getIdx(), change.getUser_idx());

        //then
        assertThat(1).isEqualTo(experienceService.findByUserIdx(test.getUser_idx()).size());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(finduser.getExperience_score()).isEqualTo(checkuser.getExperience_score());
        assertThat(finduser.getScore()).isEqualTo(checkuser.getScore());
    }

    @Test
    void changetoNumber() {

        Experience test = new Experience();
        test.setUser_idx(37);
        test.setCountry("abc");
        test.setStart_date(Date.valueOf("2017-01-01"));
        test.setEnd_date(Date.valueOf("2017-02-01"));
        test.setContent("abc");

        Experience test2 = new Experience();
        test2.setUser_idx(37);
        test2.setCountry("abc");
        test2.setStart_date(Date.valueOf("2017-01-01"));
        test2.setEnd_date(Date.valueOf("2017-04-01"));
        test2.setContent("abc");

        Experience test3 = new Experience();
        test3.setUser_idx(37);
        test3.setCountry("abc");
        test3.setStart_date(Date.valueOf("2017-01-01"));
        test3.setEnd_date(Date.valueOf("2017-07-01"));
        test3.setContent("abc");

        Experience test4 = new Experience();
        test4.setUser_idx(37);
        test4.setCountry("abc");
        test4.setStart_date(Date.valueOf("2017-01-01"));
        test4.setEnd_date(Date.valueOf("2018-01-01"));
        test4.setContent("abc");


        //when
        experienceService.save(test);
        experienceService.save(test2);
        experienceService.save(test3);
        experienceService.save(test4);

        assertThat(30).isEqualTo(experienceMapper.findByIdx(test.getIdx()).get().getScore());
        assertThat(60).isEqualTo(experienceMapper.findByIdx(test2.getIdx()).get().getScore());
        assertThat(80).isEqualTo(experienceMapper.findByIdx(test3.getIdx()).get().getScore());
        assertThat(100).isEqualTo(experienceMapper.findByIdx(test4.getIdx()).get().getScore());


    }



    @Test
    void checkExperienceUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> experienceService.findByUserIdx(user_idx));


        //then
        assertThat("해당 회원의 해외경험 정보가 없습니다.").isEqualTo(e.getMessage());


    }

    @Test
    void checkAll() {
        //given
        Experience test = new Experience();
        test.setUser_idx(37);
        test.setCountry("abc");
        test.setStart_date(Date.valueOf("2017-02-01"));
        test.setEnd_date(Date.valueOf("2017-05-01"));
        test.setContent("abc");

        //when
        experienceService.save(test);
        CNotHaveAccessInfoException e = assertThrows(CNotHaveAccessInfoException.class , () -> experienceService.delete(test.getIdx(),14));
        CNotFoundInfoByIdxException ex = assertThrows(CNotFoundInfoByIdxException.class , () -> experienceService.delete(222,37));


        //then
        assertThat("해당 회원의 해외경험 번호가 아닙니다.").isEqualTo(e.getMessage());
        assertThat("해당 해외경험 번호의 정보가 없습니다.").isEqualTo(ex.getMessage());
    }
}