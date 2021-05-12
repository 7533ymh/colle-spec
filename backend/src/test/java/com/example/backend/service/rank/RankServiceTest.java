package com.example.backend.service.rank;

import com.example.backend.domain.User;
import com.example.backend.mapper.collspec.UserMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class RankServiceTest {

    @Autowired
    RankService rankService;

    @Autowired
    UserMapper userMapper;


    @Test
    void 전체등급제알고리즘() {

        //given
        int idx = 25;

        //when
        rankService.changetoRank(25);

        //then
        User user = userMapper.findByIdx(25).get();
        Assertions.assertThat("2등급").isEqualTo(user.getAll_rank());
        Assertions.assertThat("2등급").isEqualTo(user.getAward_rank());
        Assertions.assertThat("2등급").isEqualTo(user.getCareer_rank());
        Assertions.assertThat("5등급").isEqualTo(user.getCertificate_rank());
        Assertions.assertThat("5등급").isEqualTo(user.getEducation_rank());
        Assertions.assertThat("5등급").isEqualTo(user.getExperience_rank());
        Assertions.assertThat("2등급").isEqualTo(user.getGrade_rank());
        Assertions.assertThat("5등급").isEqualTo(user.getLanguage_rank());
        Assertions.assertThat("5등급").isEqualTo(user.getProject_rank());





    }
/*
    @Test
    void 수상등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getAward_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getAward_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getAward_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getAward_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("2등급").isEqualTo(rank);


    }

    @Test
    void 경력등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getCareer_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getCareer_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getCareer_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getCareer_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("2등급").isEqualTo(rank);


    }

    @Test
    void 자격증등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getCertificate_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getCertificate_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getCertificate_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getCertificate_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("5등급").isEqualTo(rank);


    }

    @Test
    void 교육등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getEducation_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getEducation_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getEducation_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getEducation_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("5등급").isEqualTo(rank);


    }

    @Test
    void 해외경험등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getExperience_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getExperience_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getExperience_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getExperience_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("5등급").isEqualTo(rank);


    }

    @Test
    void 학점등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getGrade_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getGrade_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getGrade_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getGrade_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("2등급").isEqualTo(rank);


    }

    @Test
    void 어학등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getLanguage_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getLanguage_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getLanguage_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getLanguage_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("5등급").isEqualTo(rank);


    }

    @Test
    void 프로젝트등급제알고리즘() {

        //given
        int idx = 25;

        //when
        Rank rankScore = rankMapper.checkRank(idx);
        String rank;
        if(rankScore.getProject_rank() <= 0.05)
            rank = "1등급";
        else if (rankScore.getProject_rank() <= 0.25)
            rank = "2등급";
        else if (rankScore.getProject_rank() <= 0.75)
            rank = "3등급";
        else if (rankScore.getProject_rank() <= 0.95)
            rank = "4등급";
        else
            rank = "5등급";

        //then

        Assertions.assertThat("5등급").isEqualTo(rank);


    }
*/






}