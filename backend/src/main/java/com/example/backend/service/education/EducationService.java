package com.example.backend.service.education;

import com.example.backend.advice.exception.CDateException;
import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Education;
import com.example.backend.mapper.collspec.EducationMapper;
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

        checkDate(education);

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

        checkDate(education);

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

    private void checkDate(Education education) {
        if (education.getStart_date().after(education.getEnd_date())) {
            throw new CDateException();
        }
    }



    //수치화 알고리즘 부분
    public int changetoNumber (Education education) {




        return 100;

    }



    public void checkEducationUserIdx(int user_idx) {
        if ( educationMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundInfoByUserException("해당 회원의 교육 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (educationMapper.findByIdx(idx).isPresent() ) {
            if (educationMapper.findByIdx(idx).get().getUser_idx() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 교육 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundInfoByIdxException("해당 교육 번호의 정보가 없습니다.");
        }
    }






}
