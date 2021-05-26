package com.example.backend.domain.rank.service;

import com.example.backend.domain.rank.domain.AvgCheck;
import com.example.backend.global.exception.exception.CLinkException;
import com.example.backend.domain.rank.exception.CNotFoundScoreException;
import com.example.backend.domain.rank.domain.Rank;
import com.example.backend.domain.rank.domain.RankResult;
import com.example.backend.domain.rank.mapper.RankMapper;
import com.example.backend.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankService {

    private final RankMapper rankMapper;
    private final UserService userService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public RankResult change_Rank_All(int user_idx) {

        logger.info("전체 랭크 조회");

        if (rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankAll(user_idx);
        AvgCheck avgCheck = rankMapper.checkAvgAll();
        RankResult rankResult = changeResult(rank, avgCheck);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(1);

        insertOrUpdate(rankResult);

        return rankResult;

    }


    public RankResult change_Rank_Grade(int user_idx) {

        logger.info("학년 랭크 조회");

        if (rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankByGrade(user_idx);
        AvgCheck avgCheck = rankMapper.checkAvgByGrade(user_idx);
        RankResult rankResult = changeResult(rank, avgCheck);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(2);

        insertOrUpdate(rankResult);


        return rankResult;

    }

    public RankResult change_Rank_College(int user_idx) {

        logger.info("대학교 랭크 조회");

        if (userService.findByIdx(user_idx).getLink() == 0)
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");

        if (rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankByCollege(user_idx);
        AvgCheck avgCheck = rankMapper.checkAvgByCollege(user_idx);
        RankResult rankResult = changeResult(rank, avgCheck);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(3);

        insertOrUpdate(rankResult);


        return rankResult;

    }

    public RankResult change_Rank_College_Grade(int user_idx) {

        logger.info("대학교&학년 랭크 조회");

        if (userService.findByIdx(user_idx).getLink() == 0)
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");

        if (rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankByCollegeGrade(user_idx);
        AvgCheck avgCheck = rankMapper.checkAvgByCollegeGrade(user_idx);
        RankResult rankResult = changeResult(rank, avgCheck);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(4);

        insertOrUpdate(rankResult);

        return rankResult;

    }


    private RankResult changeResult(Rank rank, AvgCheck avgCheck) {

        RankResult result = new RankResult();

        result.setAll_rank(rankchange(rank.getAll_rank()));
        result.setAward_rank(rankchange(rank.getAward_rank()));
        result.setCareer_rank(rankchange(rank.getCareer_rank()));
        result.setCertificate_rank(rankchange2(rank.getCertificate_rank(), avgCheck.getAvg_Certificate()));
        result.setEducation_rank(rankchange2(rank.getEducation_rank(), avgCheck.getAvg_Education()));
        result.setExperience_rank(rankchange(rank.getExperience_rank()));
        result.setGrade_rank(rankchange3(rank.getGrade_rank(), avgCheck.getAvg_Grade()));
        result.setLanguage_rank(rankchange(rank.getLanguage_rank()));
        result.setProject_rank(rankchange(rank.getProject_rank()));

        return result;
    }

    private String rankchange(double rank) {
        if (rank <= 0.05)
            return "1등급";
        else if (rank <= 0.25)
            return "2등급";
        else if (rank <= 0.75)
            return "3등급";
        else if (rank <= 0.95)
            return "4등급";
        else
            return "5등급";
    }

    private String rankchange2(int rank, int avg) {

        int score = rank - avg;

        if (score >= 200) {
            return "1등급";
        } else if (score == 100) {
            return "2등급";
        } else if (score == 0) {
            return "3등급";
        } else if (score == -100) {
            return "4등급";
        } else
            return "5등급";

    }

    private String rankchange3(int rank, int avg) {

        int score = rank - avg;

        if (score >= 30) {
            return "1등급";
        } else if (score == 20) {
            return "2등급";
        } else if (score >= -10) {
            return "3등급";
        } else if (score == -20) {
            return "4등급";
        } else
            return "5등급";

    }

    private void insertOrUpdate(RankResult rankResult) {
        rankMapper.findByUser_idxDivision(rankResult.getUser_idx(), rankResult.getDivision()).ifPresentOrElse(
                update -> rankMapper.updateRank(rankResult),
                () -> rankMapper.insertRank(rankResult)
        );
    }


}
