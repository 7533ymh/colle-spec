package com.example.backend.service.grade;

import com.example.backend.advice.exception.CFindSameGradeException;
import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Grade;
import com.example.backend.domain.GradeCal;
import com.example.backend.mapper.collspec.GradeCalMapper;
import com.example.backend.mapper.collspec.GradeMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GradeService {

    private final GradeMapper gradeMapper;
    private final GradeCalMapper gradeCalMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public Grade save(Grade grade) {

        logger.info("학점 등록");

        changeSemester(grade);

        checkSameGrade(grade);

        gradeMapper.save(grade);

        changetoNumber(grade.getUser_idx());

        return grade;

    }

    public void checkSameGrade(Grade grade) {
        logger.info("학점 중복 체크");

        gradeMapper.findsame(grade)
                .ifPresent(m -> {
            throw new CFindSameGradeException();
        });
    }


    public List<Grade> findByUserIdx(int user_idx) {

        logger.info("학점 조회");

        checkGradeUserIdx(user_idx);

        List<Grade> grades = gradeMapper.findByUserIdx(user_idx);

        for (Grade grade : grades) {
            switch (grade.getSemester()) {
                case "1":
                    grade.setSemester("1");
                    break;

                case "2":
                    grade.setSemester("하계계절");
                    break;

                case "3":
                    grade.setSemester("2");
                    break;

                case "4":
                    grade.setSemester("동계계절");
                    break;

            }
        }

        return grades;

    }

    public Grade modify(Grade grade) {

        logger.info("학점 수정");

        checkGradeUserIdx(grade.getUser_idx());

        checkAll(grade.getIdx(), grade.getUser_idx());

        changeSemester(grade);

        checkSameGrade(grade);

        gradeMapper.update(grade);

        changetoNumber(grade.getUser_idx());

        return grade;

    }

    public void delete(int idx, int user_idx) {

        logger.info("학점 삭제");

        checkGradeUserIdx(user_idx);

        checkAll(idx, user_idx);

        gradeMapper.delete(idx, user_idx);

        changetoNumber(user_idx);

    }

    public void changeSemester(Grade grade) {
        logger.info("학점 학기 변경");

        switch (grade.getSemester()) {
            case "1":
                grade.setSemester("1");
                break;

            case "하계계절":
                grade.setSemester("2");
                break;

            case "2":
                grade.setSemester("3");
                break;

            case "동계계절":
                grade.setSemester("4");
                break;

        }
    }

    public void changetoNumber(int user_idx) {

        logger.info("학점 알고리즘 ");


        GradeCal gradeCal = gradeCalMapper.findByUserIdx(user_idx);

        double avg_score = gradeCal.getAll_avg_score() / gradeCal.getAll_semester();


        if(avg_score >= 4.5)
            gradeCal.setScore(100);
        else if(avg_score >= 4.0)
            gradeCal.setScore(90);
        else if(avg_score >= 3.5)
            gradeCal.setScore(80);
        else if(avg_score >= 3.0)
            gradeCal.setScore(70);
        else if(avg_score >= 2.5)
            gradeCal.setScore(60);
        else if(avg_score >= 2.0)
            gradeCal.setScore(50);
        else if(avg_score >= 1.5)
            gradeCal.setScore(40);
        else if(avg_score > 0.0)
            gradeCal.setScore(30);
        else
            gradeCal.setScore(0);

        gradeCalMapper.update(gradeCal);

    }


    public void checkGradeUserIdx(int user_idx) {
        if (gradeMapper.findByUserIdx(user_idx).isEmpty()) {
            throw new CNotFoundInfoByUserException("해당 회원의 학점 정보가 없습니다.");
        }

    }


    public void checkAll(int idx, int user_idx) {

        if (gradeMapper.findByIdx(idx).isPresent()) {
            if (gradeMapper.findByIdx(idx).get().getUser_idx() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 학점 번호가 아닙니다.");
        } else {
            throw new CNotFoundInfoByIdxException("해당 학점 번호의 정보가 없습니다.");
        }
    }
}
