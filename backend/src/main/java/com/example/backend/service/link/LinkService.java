package com.example.backend.service.link;

import com.example.backend.advice.exception.CNotFoundInfoByLinkException;
import com.example.backend.advice.exception.CUserLoginFailException;
import com.example.backend.advice.exception.CUserNotFoundException;
import com.example.backend.domain.Grade;
import com.example.backend.domain.User;
import com.example.backend.mapper.colleasy.LinkMapper;
import com.example.backend.mapper.collspec.UserMapper;
import com.example.backend.service.grade.GradeService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LinkService {

    private final LinkMapper linkMapper;
    private final UserMapper userMapper;
    private final GradeService gradeService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());


    public int linkLogin(String id , String pass){

        logger.info("연동 어플 로그인");

        User user = linkMapper.findById(id)
                .orElseThrow(CUserNotFoundException::new);

        if (!pass.matches(user.getPass())) {
            throw new CUserLoginFailException();
        }

        return user.getIdx();

    }



    public void linkInfo(int appidx , int idx){

        logger.info("연동 어플 정보 저장");

        User user = linkMapper.InfoFindByIdx(appidx)
                .orElseThrow(CNotFoundInfoByLinkException::new);
        user.setLink(appidx);

        if ( user.getGrade() == 0 || user.getCollege().isEmpty() || user.getMajor().isEmpty()){
            throw new CNotFoundInfoByLinkException();
        }

        user.setIdx(idx);
        userMapper.updateLink(user);


    }


    public void linkGrade(int appidx , int idx) {

        logger.info("연동 어플 학점 정보 저장");

        List<Grade> gradeList = linkMapper.gradeFindByIdx(appidx);

        if (gradeList.isEmpty()) {
            throw new CNotFoundInfoByLinkException();
        }

        gradeService.delete_all(idx);

        int year = Integer.parseInt(gradeList.get(0).getGrade());
        for (Grade grade : gradeList) {

            int check = Integer.parseInt(grade.getGrade());
            int realgrade = 1;

            if (check != year) {
                realgrade += 1;
            }

            grade.setGrade(Integer.toString(realgrade));

            grade.setUser_idx(idx);

            gradeService.save(grade);

        }




    }




}
