package com.example.backend.service.rank;

import com.example.backend.advice.exception.CLinkException;
import com.example.backend.advice.exception.CNotFoundScoreException;
import com.example.backend.domain.Rank;
import com.example.backend.domain.RankResult;
import com.example.backend.mapper.collspec.RankMapper;
import com.example.backend.service.user.UserService;
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

        if ( rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankAll(user_idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(1);

        insertOrUpdate(rankResult);

        return rankResult;

    }


    public RankResult change_Rank_Grade(int user_idx) {

        logger.info("학년 랭크 조회");

        if ( rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankByGrade(user_idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(2);

        insertOrUpdate(rankResult);


        return rankResult;

    }

    public RankResult change_Rank_College(int user_idx) {

        logger.info("대학교 랭크 조회");

        if (userService.findByIdx(user_idx).getLink() == 0 )
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");

        if ( rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankByCollege(user_idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(3);

        insertOrUpdate(rankResult);


        return rankResult;

    }

    public RankResult change_Rank_College_Grade(int user_idx) {

        logger.info("대학교&학년 랭크 조회");

        if (userService.findByIdx(user_idx).getLink() == 0 )
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");

        if ( rankMapper.checkScore(user_idx) == 0)
            throw new CNotFoundScoreException();

        Rank rank = rankMapper.checkRankByCollegeGrade(user_idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setUser_idx(rank.getUser_idx());
        rankResult.setDivision(4);

        insertOrUpdate(rankResult);

        return rankResult;

    }


    private RankResult changeResult(Rank rank) {

        RankResult result = new RankResult();

        result.setAll_rank(rankchange(rank.getAll_rank()));
        result.setAward_rank(rankchange(rank.getAward_rank()));
        result.setCareer_rank(rankchange(rank.getCareer_rank()));
        result.setCertificate_rank(rankchange(rank.getCertificate_rank()));
        result.setEducation_rank(rankchange(rank.getEducation_rank()));
        result.setExperience_rank(rankchange(rank.getExperience_rank()));
        result.setGrade_rank(rankchange(rank.getGrade_rank()));
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

    private void insertOrUpdate(RankResult rankResult) {
        rankMapper.findByUser_idxDivision(rankResult.getUser_idx(), rankResult.getDivision()).ifPresentOrElse(
                update -> rankMapper.updateRank(rankResult) ,
                ()-> rankMapper.insertRank(rankResult)
        );
    }


}
