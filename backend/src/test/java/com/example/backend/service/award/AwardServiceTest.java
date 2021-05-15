package com.example.backend.service.award;

import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Award;
import com.example.backend.domain.Score;
import com.example.backend.mapper.collspec.AwardMapper;
import com.example.backend.mapper.collspec.RankMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class AwardServiceTest {

    @Autowired
    AwardService awardService;

    @Autowired
    AwardMapper awardMapper;

    @Autowired
    RankMapper rankMapper;

    @Test
    void 수상저장() {

        //given
        Award award = new Award();
        award.setUser_idx(37);
        award.setTitle("abc");
        award.setAgency("abc");
        award.setDivision("은상");
        award.setYear(2017);
        award.setContent("블라블라");

        Score finduser = rankMapper.testFindScore(award.getUser_idx());

        //when
        awardService.save(award);

        //then
        Award findaward = awardMapper.findByIdx(award.getIdx()).get();
        assertThat(award.getIdx()).isEqualTo(findaward.getIdx());

        Score checkuser = rankMapper.testFindScore(award.getUser_idx());
        assertThat(finduser.getAward_score() + findaward.getScore()).isEqualTo(checkuser.getAward_score());
        assertThat(finduser.getScore() + findaward.getScore()).isEqualTo(checkuser.getScore());

    }

    @Test
    void findByUserIdx() {

        //given
        Award award = new Award();
        award.setUser_idx(37);
        award.setTitle("abc");
        award.setAgency("abc");
        award.setDivision("은상");
        award.setYear(2017);
        award.setContent("블라블라");

        //when
        awardService.save(award);
        List<Award> awards = awardService.findByUserIdx(award.getUser_idx());

        //then
        assertThat(awards.size()).isEqualTo(1);

    }

    @Test
    void modify() {

        //given
        Award award = new Award();
        award.setUser_idx(37);
        award.setTitle("abc");
        award.setAgency("abc");
        award.setDivision("은상");
        award.setYear(2017);
        award.setContent("블라블라");

        Award changeAward = new Award();
        changeAward.setUser_idx(37);
        changeAward.setTitle("수정");
        changeAward.setAgency("수정");
        changeAward.setDivision("수정");
        changeAward.setYear(2017);
        changeAward.setContent("수정");

        Score finduser = rankMapper.testFindScore(award.getUser_idx());

        //when
        awardService.save(award);
        changeAward.setIdx(award.getIdx());
        awardService.modify(changeAward);

        //then
        Award findaward = awardMapper.findByIdx(award.getIdx()).get();
        assertThat(changeAward.getTitle()).isEqualTo(findaward.getTitle());

        Score checkuser = rankMapper.testFindScore(award.getUser_idx());
        assertThat(finduser.getAward_score() + findaward.getScore()).isEqualTo(checkuser.getAward_score());
        assertThat(finduser.getScore() + findaward.getScore()).isEqualTo(checkuser.getScore());
    }

    @Test
    void delete() {

        //given
        Award award = new Award();
        award.setUser_idx(37);
        award.setTitle("abc");
        award.setAgency("abc");
        award.setDivision("은상");
        award.setYear(2017);
        award.setContent("블라블라");

        Award changeAward = new Award();
        changeAward.setUser_idx(37);
        changeAward.setTitle("수정");
        changeAward.setAgency("수정");
        changeAward.setDivision("수정");
        changeAward.setYear(2017);
        changeAward.setContent("수정");


        //when
        awardService.save(award);
        Score finduser = rankMapper.testFindScore(award.getUser_idx());
        awardService.save(changeAward);
        awardService.delete(changeAward.getIdx(), changeAward.getUser_idx());


        //then

        List<Award> awards = awardService.findByUserIdx(award.getUser_idx());
        assertThat(awards.size()).isEqualTo(1);


        Score checkuser = rankMapper.testFindScore(award.getUser_idx());
        assertThat(finduser.getAward_score()).isEqualTo(checkuser.getAward_score());
        assertThat(finduser.getScore()).isEqualTo(checkuser.getScore());


    }

    @Test
    void changetoNumber() {

        //given
        Award award = new Award();
        award.setUser_idx(37);
        award.setTitle("abc");
        award.setAgency("abc");
        award.setDivision("동상");
        award.setYear(2017);
        award.setContent("블라블라");

        Award twice = new Award();
        twice.setUser_idx(37);
        twice.setTitle("abc");
        twice.setAgency("abc");
        twice.setDivision("장려상");
        twice.setYear(2017);
        twice.setContent("블라블라");


        //when
        awardService.save(award);
        awardService.save(twice);


        //then
        Award findaward = awardMapper.findByIdx(award.getIdx()).get();
        assertThat(50).isEqualTo(findaward.getScore());
        Award findaward2 = awardMapper.findByIdx(twice.getIdx()).get();
        assertThat(30).isEqualTo(findaward2.getScore());


    }

    @Test
    void checkAwardUserIdx() {

        //given
        int user_idx = 37;


        //when
        CNotFoundInfoByUserException e = assertThrows(CNotFoundInfoByUserException.class, () -> awardService.findByUserIdx(user_idx));


        //then
        assertThat("해당 회원의 수상 정보가 없습니다.").isEqualTo(e.getMessage());
    }

    @Test
    void checkAll() {

        //given
        Award award = new Award();
        award.setUser_idx(37);
        award.setTitle("abc");
        award.setAgency("abc");
        award.setDivision("은상");
        award.setYear(2017);
        award.setContent("블라블라");

        //when
        awardService.save(award);
        CNotHaveAccessInfoException e = assertThrows(CNotHaveAccessInfoException.class , () -> awardService.delete(award.getIdx(),6));
        CNotFoundInfoByIdxException ex = assertThrows(CNotFoundInfoByIdxException.class , () -> awardService.delete(222,37));


        //then
        assertThat("해당 회원의 수상 번호가 아닙니다.").isEqualTo(e.getMessage());
        assertThat("해당 수상 번호의 정보가 없습니다.").isEqualTo(ex.getMessage());

    }
}