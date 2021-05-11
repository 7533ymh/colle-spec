package com.example.backend.service.link;

import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CUserLoginFailException;
import com.example.backend.advice.exception.CUserNotFoundException;
import com.example.backend.domain.Grade;
import com.example.backend.domain.User;
import com.example.backend.mapper.colleasy.LinkMapper;
import com.example.backend.mapper.collspec.UserMapper;
import com.example.backend.service.grade.GradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class Linkservice {

    private final LinkMapper linkMapper;
    private final UserMapper userMapper;
    private final GradeService gradeService;

    public int linkLogin(String id , String pass){

        User user = linkMapper.findById(id)
                .orElseThrow(CUserNotFoundException::new);
        if (!pass.matches(user.getPass())) {
            throw new CUserLoginFailException();
        }

        return user.getIdx();

    }



    public void linkInfo(int appidx , int idx){

        User user = linkMapper.InfoFindByIdx(appidx);
        user.setIdx(idx);
        userMapper.updateLink(user);


    }


    public void linkGrade(int appidx , int idx) {

        List<Grade> gradeList = linkMapper.gradeFindByIdx(appidx);

        if (gradeList.isEmpty()) {
            throw new CNotFoundInfoByUserException("연동 회원의 학점 정보가 없습니다. 어플을 확인해주세요.");
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
