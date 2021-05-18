package com.example.backend.service.career;

import com.example.backend.advice.exception.CDateException;
import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Career;
import com.example.backend.mapper.collspec.CareerMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CareerService {

    private final CareerMapper careerMapper;

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Career save(Career career) {

        logger.info("경력 등록");

        checkDate(career);

        career.setScore(changetoNumber(career));

        careerMapper.save(career);

        return career;

    }


    public List<Career> findByUserIdx(int user_idx) {

        logger.info("경력 조회");

        checkCareerUserIdx(user_idx);

        return careerMapper.findByUserIdx(user_idx);

    }

    public Career modify(Career career) {

        logger.info("경력 수정");

        checkDate(career);

        checkCareerUserIdx(career.getUser_idx());

        checkAll(career.getIdx(), career.getUser_idx());

        career.setScore(changetoNumber(career));

        careerMapper.update(career);

        return career;

    }

    public void delete (int idx , int user_idx) {

        logger.info("경력 삭제");

        checkCareerUserIdx(user_idx);

        checkAll(idx,user_idx);

        careerMapper.delete(idx, user_idx);

    }

    public void checkDate(Career career) {
        if (career.getStart_date().after(career.getEnd_date())) {
            throw new CDateException();
        }
    }

    //수치화 알고리즘 부분
    public int changetoNumber (Career career) {


        long baseDay = 24 * 60 * 60 * 1000; 	// 일
        long baseMonth = baseDay * 30;          // 월

        long cal = (career.getEnd_date().getTime() - career.getStart_date().getTime()) / baseMonth;

        if(cal >= 12 ) {
            return 100;
        }
        else if ( cal >= 6) {
            return 80;
        }
        else if ( cal >= 3) {
            return 60;
        }
        else {
            return 30;
        }

    }



    public void checkCareerUserIdx(int user_idx) {
        if ( careerMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundInfoByUserException("해당 회원의 경력 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (careerMapper.findByIdx(idx).isPresent() ) {
            if (careerMapper.findByIdx(idx).get().getUser_idx() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 경력 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundInfoByIdxException("해당 경력 번호의 정보가 없습니다.");
        }
    }

}
