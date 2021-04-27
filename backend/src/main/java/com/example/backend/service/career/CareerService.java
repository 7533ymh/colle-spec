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

        careerMapper.save(career);

        return career;

    }

    public List<Career> findByUserIdx(int user_idx) {

        logger.info("경력 조회");

        if ( careerMapper.findByUserIdx(user_idx).isEmpty()){
            throw new IllegalStateException("해당 회원의 경력 정보가 없습니다.");
        }

        return careerMapper.findByUserIdx(user_idx);

    }


}
