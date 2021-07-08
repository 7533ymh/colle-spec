package com.example.backend.domain.award.service;

import com.example.backend.global.exception.exception.CNotFoundInfoByIdxException;
import com.example.backend.global.exception.exception.CNotFoundInfoByUserException;
import com.example.backend.global.exception.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.award.domain.Award;
import com.example.backend.domain.award.mapper.AwardMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AwardService {

    private final AwardMapper awardMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Award save(Award award) {

        logger.info("수상 등록");

        award.setScore(changetoNumber(award));

        awardMapper.save(award);

        return award;

    }

    public List<Award> findByUserIdx(int user_idx) {

        logger.info("수상 조회");

        checkAwardUserIdx(user_idx);

        return awardMapper.findByUserIdx(user_idx);

    }

    public Award modify(Award award) {

        logger.info("수상 수정");

        checkAwardUserIdx(award.getUser_idx());

        checkAll(award.getIdx(), award.getUser_idx());

        award.setScore(changetoNumber(award));

        awardMapper.update(award);

        return award;

    }

    public void delete(int idx, int user_idx) {

        logger.info("수상 삭제");

        checkAwardUserIdx(user_idx);

        checkAll(idx, user_idx);

        awardMapper.delete(idx, user_idx);

    }


    public int changetoNumber(Award award) {

        switch (award.getDivision()) {
            case "대상":
                return 100;

            case "최우수":
            case "금상":
                return 80;

            case "우수":
            case "은상":
                return 60;

            case "동상":
                return 50;

            default:
                return 30;

        }

    }

    public void checkAwardUserIdx(int user_idx) {
        if (awardMapper.findByUserIdx(user_idx).isEmpty()) {
            throw new CNotFoundInfoByUserException("해당 회원의 수상 정보가 없습니다.");
        }

    }


    public void checkAll(int idx, int user_idx) {

        if (awardMapper.findByIdx(idx).isPresent()) {
            if (awardMapper.findByIdx(idx).get().getUser_idx() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 수상 번호가 아닙니다.");
        } else {
            throw new CNotFoundInfoByIdxException("해당 수상 번호의 정보가 없습니다.");
        }
    }

}
