package com.example.backend.service.experience;

import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Experience;
import com.example.backend.mapper.ExperienceMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ExperienceService {

    private final ExperienceMapper experienceMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Experience save(Experience experience) {

        logger.info("해외경험 등록");

        experience.setScore(changetoNumber(experience));

        experienceMapper.save(experience);

        return experience;

    }

    public List<Experience> findByUserIdx(int user_idx) {

        logger.info("해외경험 조회");

        checkExperienceUserIdx(user_idx);

        return experienceMapper.findByUserIdx(user_idx);

    }



    public Experience modify(Experience experience) {

        logger.info("해외경험 수정");

        checkExperienceUserIdx(experience.getUser_idx());

        checkAll(experience.getIdx(), experience.getUser_idx());

        experience.setScore(changetoNumber(experience));

        experienceMapper.update(experience);

        return experience;

    }

    public void delete (int idx , int user_idx) {

        logger.info("해외경험 삭제");

        checkExperienceUserIdx(user_idx);

        checkAll(idx,user_idx);

        experienceMapper.delete(idx, user_idx);

    }



    //수치화 알고리즘 부분
    public int changetoNumber (Experience experience) {




        return 10;

    }



    public void checkExperienceUserIdx(int user_idx) {
        if ( experienceMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundInfoByUserException("해당 회원의 해외경험 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (experienceMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (experienceMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 해외경험 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundInfoByIdxException("해당 해외경험 번호의 정보가 없습니다.");
        }
    }



}
