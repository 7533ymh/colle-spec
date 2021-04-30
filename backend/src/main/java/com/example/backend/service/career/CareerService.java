package com.example.backend.service.career;

import com.example.backend.domain.Career;
import com.example.backend.mapper.CareerMapper;
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

        checkCareerIdx(career.getIdx());

        checkCareerUserIdx(career.getUser_idx());

        checkRealUser(career.getIdx(), career.getUser_idx());

        career.setScore(changetoNumber(career));

        careerMapper.update(career);

        return career;

    }

    public void delete (int idx , int user_idx) {

        logger.info("경력 삭제");

        checkCareerIdx(idx);

        checkCareerUserIdx(user_idx);

        checkRealUser(idx,user_idx);

        careerMapper.delete(idx, user_idx);

    }


    //수치화 알고리즘 부분
    public int changetoNumber (Career career) {




        return 10;

    }



    public void checkCareerUserIdx(int user_idx) {
        if ( careerMapper.findByUserIdx(user_idx).isEmpty()){
            throw new IllegalStateException("해당 회원의 경력 정보가 없습니다.");
        }

    }

    public void checkCareerIdx(int idx) {

        careerMapper.finduser_idxByIdx(idx)
                .orElseThrow(() -> new IllegalStateException("해당 경력 번호의 정보가 없습니다."));

    }

    public void checkRealUser(int idx , int user_idx) {

        if (careerMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (careerMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new IllegalStateException("해당 회원의 경력 번호가 아닙니다.");
        }
    }

}
