package com.example.backend.service.rank;

import com.example.backend.advice.exception.CLinkException;
import com.example.backend.domain.Rank;
import com.example.backend.domain.RankResult;
import com.example.backend.mapper.collspec.RankMapper;
import com.example.backend.mapper.collspec.UserMapper;
import com.example.backend.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankService {

    private final RankMapper rankMapper;
    private final UserMapper userMapper;
    private final UserService userService;

    public void change_Rank_All(int idx) {

        Rank rank = rankMapper.checkRankAll(idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setIdx(rank.getIdx());

        userMapper.updateRank(rankResult);

    }

    public RankResult change_Rank_Grade(int idx) {

        Rank rank = rankMapper.checkRankByGrade(idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setIdx(rank.getIdx());

        return rankResult;

    }

    public RankResult change_Rank_College(int idx) {

        if (userService.findByIdx(idx).getLink() == 0 )
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");

        Rank rank = rankMapper.checkRankByCollege(idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setIdx(rank.getIdx());


        return rankResult;

    }

    public RankResult change_Rank_College_Grade(int idx) {

        if (userService.findByIdx(idx).getLink() == 0 )
            throw new CLinkException("잘못된 접근입니다. 연동을 먼저 해주세요.");

        Rank rank = rankMapper.checkRankByCollegeGrade(idx);
        RankResult rankResult =  changeResult(rank);
        rankResult.setIdx(rank.getIdx());

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

   /* private void rankSet(Rank rank, User user) {
        if (rank.getAll_rank() <= 0.05)
            user.setAll_rank("1등급");
        else if (rank.getAll_rank() <= 0.25)
            user.setAll_rank("2등급");
        else if (rank.getAll_rank() <= 0.75)
            user.setAll_rank("3등급");
        else if (rank.getAll_rank() <= 0.95)
            user.setAll_rank("4등급");
        else
            user.setAll_rank("5등급");

        if (rank.getAward_rank() <= 0.05)
            user.setAward_rank("1등급");
        else if (rank.getAward_rank() <= 0.25)
            user.setAward_rank("2등급");
        else if (rank.getAward_rank() <= 0.75)
            user.setAward_rank("3등급");
        else if (rank.getAward_rank() <= 0.95)
            user.setAward_rank("4등급");
        else
            user.setAward_rank("5등급");

        if (rank.getCareer_rank() <= 0.05)
            user.setCareer_rank("1등급");
        else if (rank.getCareer_rank() <= 0.25)
            user.setCareer_rank("2등급");
        else if (rank.getCareer_rank() <= 0.75)
            user.setCareer_rank("3등급");
        else if (rank.getCareer_rank() <= 0.95)
            user.setCareer_rank("4등급");
        else
            user.setCareer_rank("5등급");

        if (rank.getCertificate_rank() <= 0.05)
            user.setCertificate_rank("1등급");
        else if (rank.getCertificate_rank() <= 0.25)
            user.setCertificate_rank("2등급");
        else if (rank.getCertificate_rank() <= 0.75)
            user.setCertificate_rank("3등급");
        else if (rank.getCertificate_rank() <= 0.95)
            user.setCertificate_rank("4등급");
        else
            user.setCertificate_rank("5등급");

        if (rank.getEducation_rank() <= 0.05)
            user.setEducation_rank("1등급");
        else if (rank.getEducation_rank() <= 0.25)
            user.setEducation_rank("2등급");
        else if (rank.getEducation_rank() <= 0.75)
            user.setEducation_rank("3등급");
        else if (rank.getEducation_rank() <= 0.95)
            user.setEducation_rank("4등급");
        else
            user.setEducation_rank("5등급");

        if (rank.getExperience_rank() <= 0.05)
            user.setExperience_rank("1등급");
        else if (rank.getExperience_rank() <= 0.25)
            user.setExperience_rank("2등급");
        else if (rank.getExperience_rank() <= 0.75)
            user.setExperience_rank("3등급");
        else if (rank.getExperience_rank() <= 0.95)
            user.setExperience_rank("4등급");
        else
            user.setExperience_rank("5등급");

        if (rank.getGrade_rank() <= 0.05)
            user.setGrade_rank("1등급");
        else if (rank.getGrade_rank() <= 0.25)
            user.setGrade_rank("2등급");
        else if (rank.getGrade_rank() <= 0.75)
            user.setGrade_rank("3등급");
        else if (rank.getGrade_rank() <= 0.95)
            user.setGrade_rank("4등급");
        else
            user.setGrade_rank("5등급");

        if (rank.getLanguage_rank() <= 0.05)
            user.setLanguage_rank("1등급");
        else if (rank.getLanguage_rank() <= 0.25)
            user.setLanguage_rank("2등급");
        else if (rank.getLanguage_rank() <= 0.75)
            user.setLanguage_rank("3등급");
        else if (rank.getLanguage_rank() <= 0.95)
            user.setLanguage_rank("4등급");
        else
            user.setLanguage_rank("5등급");

        if (rank.getProject_rank() <= 0.05)
            user.setProject_rank("1등급");
        else if (rank.getProject_rank() <= 0.25)
            user.setProject_rank("2등급");
        else if (rank.getProject_rank() <= 0.75)
            user.setProject_rank("3등급");
        else if (rank.getProject_rank() <= 0.95)
            user.setProject_rank("4등급");
        else
            user.setProject_rank("5등급");
    } */




}
