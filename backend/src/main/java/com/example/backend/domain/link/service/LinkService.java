package com.example.backend.domain.link.service;

import com.example.backend.global.exception.exception.CNotFoundInfoByLinkException;
import com.example.backend.global.exception.exception.CUserLoginFailException;
import com.example.backend.global.exception.exception.CUserNotFoundException;
import com.example.backend.domain.grade.domain.Grade;
import com.example.backend.domain.user.domain.User;
import com.example.backend.domain.link.linkmapper.LinkMapper;
import com.example.backend.domain.user.mapper.UserMapper;
import com.example.backend.domain.grade.service.GradeService;
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
        int realgrade = 1;

        for (Grade grade : gradeList) {

            int check = Integer.parseInt(grade.getGrade());

            if (check != year) {
                year = check;
                realgrade += 1;
            }

            grade.setGrade(Integer.toString(realgrade));

            grade.setUser_idx(idx);

            gradeService.save(grade);

        }


    }




}
