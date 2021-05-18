package com.example.backend.service.rank;

import com.example.backend.domain.RankResult;
import com.example.backend.mapper.collspec.RankMapper;
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
    RankMapper rankMapper;

    @Autowired
    UserMapper userMapper;


    @Test
    void 학년비교등급() {

        //given
        int idx = 26;

        //when
        RankResult rank = rankService.change_Rank_Grade(26);

        //then

        Assertions.assertThat(rankMapper.findByUser_idxDivision(idx,2).get().getAll_rank()).isEqualTo("5등급");


    }

    @Test
    void 학교비교등급() {

        //given
        int idx = 26;

        //when
        RankResult rank = rankService.change_Rank_College(26);

        //then

        Assertions.assertThat(rankMapper.findByUser_idxDivision(idx,3).get().getAll_rank()).isEqualTo("5등급");


    }

    @Test
    void 학교학년비교등급() {

        //given
        int idx = 26;

        //when
        RankResult rank = rankService.change_Rank_College_Grade(26);

        //then

        Assertions.assertThat(rankMapper.findByUser_idxDivision(idx,4).get().getAll_rank()).isEqualTo("5등급");


    }


    @Test
    void 전체등급제알고리즘() {

        //given
        int idx = 26;

        //when
        rankService.change_Rank_All(26);

        //then
        Assertions.assertThat(rankMapper.findByUser_idxDivision(idx,1).get().getAll_rank()).isEqualTo("3등급");



    }


}