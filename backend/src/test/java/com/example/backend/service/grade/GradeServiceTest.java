package com.example.backend.service.grade;

import com.example.backend.advice.exception.CFindSameGradeException;
import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Grade;
import com.example.backend.domain.Score;
import com.example.backend.mapper.collspec.GradeMapper;
import com.example.backend.mapper.collspec.RankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class GradeServiceTest {
    @Autowired
    GradeService gradeService;
    @Autowired
    GradeMapper gradeMapper;
    @Autowired
    RankMapper rankMapper;


    @Test
    void save() {

        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("하계계절");
        test.setAvg_score(4.5);

        Score finduser = rankMapper.FindScore(test.getUser_idx());

        //when
        gradeService.save(test);

        //then
        assertThat(4.5).isEqualTo(gradeMapper.findByIdx(test.getIdx()).get().getAvg_score());


        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(100).isEqualTo(checkuser.getGrade_score());
        assertThat(100).isEqualTo(checkuser.getScore());

    }

    @Test
    void findByUserIdx() {
        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("하계계절");
        test.setAvg_score(4.50);

        //when
        gradeService.save(test);

        //then
        assertThat(1).isEqualTo(gradeService.findByUserIdx(test.getUser_idx()).size());
        assertThat("하계계절").isEqualTo(gradeService.findByUserIdx(test.getUser_idx()).get(0).getSemester());


    }

    @Test
    void modify() {

        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("1");
        test.setAvg_score(4.5);

        Grade change = new Grade();
        change.setUser_idx(37);
        change.setGrade("1");
        change.setSemester("1");
        change.setAvg_score(3.0);

        Score finduser = rankMapper.FindScore(test.getUser_idx());

        //when
        gradeService.save(test);
        change.setIdx(test.getIdx());
        gradeService.modify(change);

        //then
        assertThat(3.0).isEqualTo(gradeMapper.findByIdx(test.getIdx()).get().getAvg_score());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(70).isEqualTo(checkuser.getGrade_score());
        assertThat(70).isEqualTo(checkuser.getScore());


    }

    @Test
    void delete() {

        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("1");
        test.setAvg_score(4.5);

        Grade change = new Grade();
        change.setUser_idx(37);
        change.setGrade("1");
        change.setSemester("하계계절");
        change.setAvg_score(3.0);

        //when
        gradeService.save(test);
        Score finduser = rankMapper.FindScore(test.getUser_idx());
        gradeService.save(change);
        gradeService.delete(change.getIdx(), change.getUser_idx());

        //then
        assertThat(1).isEqualTo(gradeService.findByUserIdx(test.getUser_idx()).size());

        Score checkuser = rankMapper.FindScore(test.getUser_idx());
        assertThat(finduser.getGrade_score()).isEqualTo(checkuser.getGrade_score());
        assertThat(finduser.getScore()).isEqualTo(checkuser.getScore());


    }

    @Test
    void changeSemester() {

        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("하계계절");
        test.setAvg_score(4.5);

        //when
        gradeService.save(test);

        //then
        assertThat("2").isEqualTo(gradeMapper.findByIdx(test.getIdx()).get().getSemester());


    }

    @Test
    void changetoNumber() {

        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("1");
        test.setAvg_score(4.5);

        Grade test2 = new Grade();
        test2.setUser_idx(37);
        test2.setGrade("1");
        test2.setSemester("하계계절");
        test2.setAvg_score(4.0);

        Grade test3 = new Grade();
        test3.setUser_idx(37);
        test3.setGrade("1");
        test3.setSemester("2");
        test3.setAvg_score(3.5);

        Grade test4 = new Grade();
        test4.setUser_idx(37);
        test4.setGrade("1");
        test4.setSemester("동계계절");
        test4.setAvg_score(3.0);

        Grade test5 = new Grade();
        test5.setUser_idx(37);
        test5.setGrade("2");
        test5.setSemester("1");
        test5.setAvg_score(2.5);

        Grade test6 = new Grade();
        test6.setUser_idx(37);
        test6.setGrade("2");
        test6.setSemester("하계계절");
        test6.setAvg_score(2.0);

        Grade test7 = new Grade();
        test7.setUser_idx(37);
        test7.setGrade("1");
        test7.setSemester("2");
        test7.setAvg_score(1.5);

        Grade test8 = new Grade();
        test8.setUser_idx(37);
        test8.setGrade("1");
        test8.setSemester("동계계절");
        test8.setAvg_score(1.0);

        //when
        gradeService.save(test);

        //then
        assertThat(100).isEqualTo(rankMapper.FindScore(37).getGrade_score());
        gradeService.delete(test.getIdx(), test.getUser_idx());


        gradeService.save(test2);
        //then
        assertThat(90).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test2.getIdx(), test2.getUser_idx());


        gradeService.save(test3);
        //then
        assertThat(80).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test3.getIdx(), test3.getUser_idx());


        gradeService.save(test4);
        //then
        assertThat(70).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test4.getIdx(), test4.getUser_idx());


        gradeService.save(test5);
        //then
        assertThat(60).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test5.getIdx(), test5.getUser_idx());


        gradeService.save(test6);
        //then
        assertThat(50).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test6.getIdx(), test6.getUser_idx());


        gradeService.save(test7);
        //then
        assertThat(40).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test7.getIdx(), test7.getUser_idx());


        gradeService.save(test8);
        //then
        assertThat(30).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        gradeService.delete(test8.getIdx(), test8.getUser_idx());


        //when
        gradeService.save(test);
        gradeService.save(test5);

        assertThat(80).isEqualTo(rankMapper.FindScore(37).getGrade_score());

        //when
        gradeService.save(test8);
        assertThat(60).isEqualTo(rankMapper.FindScore(37).getGrade_score());


    }

    @Test
    void checkGradeUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> gradeService.findByUserIdx(user_idx));


        //then
        assertThat("해당 회원의 학점 정보가 없습니다.").isEqualTo(e.getMessage());


    }

    @Test
    void checkAll() {

        //given
        Grade test = new Grade();
        test.setUser_idx(37);
        test.setGrade("1");
        test.setSemester("1");
        test.setAvg_score(4.5);

        //when
        gradeService.save(test);

        CNotHaveAccessInfoException e = assertThrows(CNotHaveAccessInfoException.class, () -> gradeService.delete(test.getIdx(), 24));
        CNotFoundInfoByIdxException ex = assertThrows(CNotFoundInfoByIdxException.class, () -> gradeService.delete(222, 37));


        //then
        assertThat("해당 회원의 학점 번호가 아닙니다.").isEqualTo(e.getMessage());
        assertThat("해당 학점 번호의 정보가 없습니다.").isEqualTo(ex.getMessage());

        //given
        Grade test2 = new Grade();
        test2.setUser_idx(37);
        test2.setGrade("1");
        test2.setSemester("1");
        test2.setAvg_score(4.5);

        //when
        CFindSameGradeException ey = assertThrows(CFindSameGradeException.class, () -> gradeService.save(test2));


    }
}