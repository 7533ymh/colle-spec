package com.example.backend.service.career;

import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Career;
import com.example.backend.domain.Score;
import com.example.backend.mapper.collspec.CareerMapper;
import com.example.backend.mapper.collspec.RankMapper;
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
class CareerServiceTest {

    @Autowired
    CareerService careerService;

    @Autowired
    CareerMapper careerMapper;

    @Autowired
    RankMapper rankMapper;

    @Test
    void save() {

        //given
        Career career = new Career();
        career.setUser_idx(37);
        career.setDivision("aaa");
        career.setCompany("aaa");
        career.setDepartment("aaa");
        career.setStart_date(Date.valueOf("2017-02-01"));
        career.setEnd_date(Date.valueOf("2017-05-01"));
        career.setContent("aaa");

        Score finduser = rankMapper.FindScore(career.getUser_idx());

        //when
        careerService.save(career);

        //then
        Career findCareer = careerMapper.findByIdx(career.getIdx()).get();
        assertThat(career.getContent()).isEqualTo(findCareer.getContent());

        Score checkuser = rankMapper.FindScore(career.getUser_idx());
        assertThat(finduser.getAward_score() + findCareer.getScore()).isEqualTo(checkuser.getCareer_score());
        assertThat(finduser.getScore() + findCareer.getScore()).isEqualTo(checkuser.getScore());


    }

    @Test
    void findByUserIdx() {

        //given
        Career career = new Career();
        career.setUser_idx(37);
        career.setDivision("aaa");
        career.setCompany("aaa");
        career.setDepartment("aaa");
        career.setStart_date(Date.valueOf("2017-02-01"));
        career.setEnd_date(Date.valueOf("2017-05-01"));
        career.setContent("aaa");

        //when
        careerService.save(career);


        //then
        List<Career> careerList = careerService.findByUserIdx(career.getUser_idx());
        assertThat(1).isEqualTo(careerList.size());


    }

    @Test
    void modify() {

        //given
        Career career = new Career();
        career.setUser_idx(37);
        career.setDivision("aaa");
        career.setCompany("aaa");
        career.setDepartment("aaa");
        career.setStart_date(Date.valueOf("2017-02-01"));
        career.setEnd_date(Date.valueOf("2017-05-01"));
        career.setContent("aaa");

        Career changeCareer = new Career();
        changeCareer.setUser_idx(37);
        changeCareer.setDivision("abc");
        changeCareer.setCompany("abc");
        changeCareer.setDepartment("abc");
        changeCareer.setStart_date(Date.valueOf("2017-01-01"));
        changeCareer.setEnd_date(Date.valueOf("2018-01-01"));
        changeCareer.setContent("abc");

        Score finduser = rankMapper.FindScore(career.getUser_idx());


        //when
        careerService.save(career);
        changeCareer.setIdx(career.getIdx());
        careerService.modify(changeCareer);

        //then
        Career findCareer = careerMapper.findByIdx(career.getIdx()).get();
        assertThat("abc").isEqualTo(findCareer.getContent());

        Score checkuser = rankMapper.FindScore(career.getUser_idx());
        assertThat(finduser.getAward_score() + findCareer.getScore()).isEqualTo(checkuser.getCareer_score());
        assertThat(finduser.getScore() + findCareer.getScore()).isEqualTo(checkuser.getScore());


    }

    @Test
    void delete() {

        //given
        Career career = new Career();
        career.setUser_idx(37);
        career.setDivision("aaa");
        career.setCompany("aaa");
        career.setDepartment("aaa");
        career.setStart_date(Date.valueOf("2017-02-01"));
        career.setEnd_date(Date.valueOf("2017-05-01"));
        career.setContent("aaa");

        Career changeCareer = new Career();
        changeCareer.setUser_idx(37);
        changeCareer.setDivision("abc");
        changeCareer.setCompany("abc");
        changeCareer.setDepartment("abc");
        changeCareer.setStart_date(Date.valueOf("2017-01-01"));
        changeCareer.setEnd_date(Date.valueOf("2018-01-01"));
        changeCareer.setContent("abc");

        //when
        careerService.save(career);
        Score finduser = rankMapper.FindScore(career.getUser_idx());
        careerService.save(changeCareer);
        careerService.delete(changeCareer.getIdx(), changeCareer.getUser_idx());

        //then
        List<Career> careerList = careerService.findByUserIdx(career.getUser_idx());
        assertThat(1).isEqualTo(careerList.size());

        Score checkuser = rankMapper.FindScore(career.getUser_idx());
        assertThat(finduser.getAward_score()).isEqualTo(checkuser.getAward_score());
        assertThat(finduser.getScore()).isEqualTo(checkuser.getScore());


    }

    @Test
    void changetoNumber() {

        //given


        Career career1 = new Career();
        career1.setUser_idx(37);
        career1.setDivision("aaa");
        career1.setCompany("aaa");
        career1.setDepartment("aaa");
        career1.setContent("aaa");
        career1.setStart_date(Date.valueOf("2017-01-01"));
        career1.setEnd_date(Date.valueOf("2017-02-01"));

        Career career2 = new Career();
        career2.setUser_idx(37);
        career2.setDivision("aaa");
        career2.setCompany("aaa");
        career2.setDepartment("aaa");
        career2.setContent("aaa");
        career2.setStart_date(Date.valueOf("2017-01-01"));
        career2.setEnd_date(Date.valueOf("2017-04-01"));

        Career career3 = new Career();
        career3.setUser_idx(37);
        career3.setDivision("aaa");
        career3.setCompany("aaa");
        career3.setDepartment("aaa");
        career3.setContent("aaa");
        career3.setStart_date(Date.valueOf("2017-01-01"));
        career3.setEnd_date(Date.valueOf("2017-07-01"));

        Career career4 = new Career();
        career4.setUser_idx(37);
        career4.setDivision("aaa");
        career4.setCompany("aaa");
        career4.setDepartment("aaa");
        career4.setContent("aaa");
        career4.setStart_date(Date.valueOf("2017-01-01"));
        career4.setEnd_date(Date.valueOf("2018-01-01"));

        //when
        careerService.save(career1);
        careerService.save(career2);
        careerService.save(career3);
        careerService.save(career4);


        //then
        assertThat(30).isEqualTo(careerMapper.findByIdx(career1.getIdx()).get().getScore());
        assertThat(60).isEqualTo(careerMapper.findByIdx(career2.getIdx()).get().getScore());
        assertThat(80).isEqualTo(careerMapper.findByIdx(career3.getIdx()).get().getScore());
        assertThat(100).isEqualTo(careerMapper.findByIdx(career4.getIdx()).get().getScore());


    }

    @Test
    void checkCareerUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> careerService.findByUserIdx(user_idx));


        //then
        assertThat("해당 회원의 경력 정보가 없습니다.").isEqualTo(e.getMessage());

    }

    @Test
    void checkAll() {

        //given
        Career career = new Career();
        career.setUser_idx(37);
        career.setDivision("aaa");
        career.setCompany("aaa");
        career.setDepartment("aaa");
        career.setStart_date(Date.valueOf("2017-02-01"));
        career.setEnd_date(Date.valueOf("2017-05-01"));
        career.setContent("aaa");

        //when
        careerService.save(career);
        CNotHaveAccessInfoException e = assertThrows(CNotHaveAccessInfoException.class , () -> careerService.delete(career.getIdx(),6));
        CNotFoundInfoByIdxException ex = assertThrows(CNotFoundInfoByIdxException.class , () -> careerService.delete(222,37));


        //then
        assertThat("해당 회원의 경력 번호가 아닙니다.").isEqualTo(e.getMessage());
        assertThat("해당 경력 번호의 정보가 없습니다.").isEqualTo(ex.getMessage());
    }
}