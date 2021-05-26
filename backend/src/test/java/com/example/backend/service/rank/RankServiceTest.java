package com.example.backend.service.rank;

import com.example.backend.domain.rank.domain.RankResult;
import com.example.backend.domain.rank.mapper.RankMapper;
import com.example.backend.domain.rank.service.RankService;
import com.example.backend.domain.user.mapper.UserMapper;
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
    RankMapper rankMapper;

    @Autowired
    UserMapper userMapper;


    @Test
    void 학년비교등급() {

        //given

        //when
        RankResult rank = rankService.change_Rank_Grade(81);

        //then
        //Assertions.assertThat(rank.getCertificate_rank()).isEqualTo("2등급");
        //Assertions.assertThat(rank.getEducation_rank()).isEqualTo("3등급");
        //Assertions.assertThat(rank.getGrade_rank()).isEqualTo("3등급");


    }

    @Test
    void 학교비교등급() {

        //given
        int idx = 26;

        //when
        RankResult rank = rankService.change_Rank_College(81);

        //then
        Assertions.assertThat(rank.getCertificate_rank()).isEqualTo("3등급");
        Assertions.assertThat(rank.getEducation_rank()).isEqualTo("2등급");
        Assertions.assertThat(rank.getGrade_rank()).isEqualTo("2등급");


    }

    @Test
    void 학교학년비교등급() {

        //given
        int idx = 26;

        //when
        RankResult rank = rankService.change_Rank_College_Grade(81);

        //then
        Assertions.assertThat(rank.getCertificate_rank()).isEqualTo("3등급");
        Assertions.assertThat(rank.getEducation_rank()).isEqualTo("2등급");
        Assertions.assertThat(rank.getGrade_rank()).isEqualTo("2등급");


    }


    @Test
    void 전체등급제알고리즘() {

        //given


        //when
        RankResult rankResult = rankService.change_Rank_All(81);



        //then
        //Assertions.assertThat(rankResult.getCertificate_rank()).isEqualTo("2등급");
       // Assertions.assertThat(rankResult.getEducation_rank()).isEqualTo("4등급");
       // Assertions.assertThat(rankResult.getGrade_rank()).isEqualTo("3등급");



    }


}