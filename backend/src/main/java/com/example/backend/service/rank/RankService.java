package com.example.backend.service.rank;

import com.example.backend.advice.exception.CUserNotFoundException;
import com.example.backend.domain.Rank;
import com.example.backend.domain.User;
import com.example.backend.mapper.collspec.RankMapper;
import com.example.backend.mapper.collspec.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RankService {

    private final RankMapper rankMapper;
    private final UserMapper userMapper;

    public void changetoRank(int idx) {

        Rank rank = rankMapper.checkRank(idx);
        User user = userMapper.findByIdx(idx)
                .orElseThrow(CUserNotFoundException::new);

        rankSet(rank, user);

        userMapper.updateRank(user);

    }

    private void rankSet(Rank rank, User user) {
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
    }


}
