package com.example.backend.service.education;

import com.example.backend.advice.exception.CNotFoundCareerException;
import com.example.backend.advice.exception.CNotFoundEducationException;
import com.example.backend.domain.Education;
import com.example.backend.mapper.EducationMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EducationService {

    private final EducationMapper educationMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public Education save(Education education) {

        logger.info("교육 등록");

        education.setScore(changetoNumber(education));

        educationMapper.save(education);

        return education;

    }

    public List<Education> findByUserIdx(int user_idx) {

        logger.info("교육 조회");

        checkEducationUserIdx(user_idx);

        return educationMapper.findByUserIdx(user_idx);

    }



    public Education modify(Education education) {

        logger.info("교육 수정");

        checkEducationUserIdx(education.getUser_idx());

        checkAll(education.getIdx(), education.getUser_idx());

        education.setScore(changetoNumber(education));

        educationMapper.update(education);

        return education;

    }

    public void delete (int idx , int user_idx) {

        logger.info("교육 삭제");

        checkEducationUserIdx(user_idx);

        checkAll(idx,user_idx);

        educationMapper.delete(idx, user_idx);

    }


    //수치화 알고리즘 부분
    public int changetoNumber (Education education) {




        return 10;

    }



    public void checkEducationUserIdx(int user_idx) {
        if ( educationMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundEducationException("해당 회원의 교육 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (educationMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (educationMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new CNotFoundCareerException("해당 회원의 교육 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundCareerException("해당 교육 번호의 정보가 없습니다.");
        }
    }






}
