package com.example.backend.service.language;

import com.example.backend.advice.exception.CNotFoundCareerException;
import com.example.backend.advice.exception.CNotFoundEducationException;
import com.example.backend.domain.Education;
import com.example.backend.domain.Language;
import com.example.backend.mapper.EducationMapper;
import com.example.backend.mapper.LanguageMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LanguageService {

    private final LanguageMapper languageMapper;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Language save(Language language) {

        logger.info("어학 등록");

        language.setScore(changetoNumber(language));

        languageMapper.save(language);

        return language;

    }

    public List<Language> findByUserIdx(int user_idx) {

        logger.info("어학 조회");

        checkLanguageUserIdx(user_idx);

        return languageMapper.findByUserIdx(user_idx);

    }



    public Language modify(Language language) {

        logger.info("어학 수정");

        checkLanguageUserIdx(language.getUser_idx());

        checkAll(language.getIdx(), language.getUser_idx());

        language.setScore(changetoNumber(language));

        languageMapper.update(language);

        return language;

    }

    public void delete (int idx , int user_idx) {

        logger.info("어학 삭제");

        checkLanguageUserIdx(user_idx);

        checkAll(idx,user_idx);

        languageMapper.delete(idx, user_idx);

    }



    public int changetoNumber (Language language) {




        return 10;

    }



    public void checkLanguageUserIdx(int user_idx) {
        if ( languageMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundEducationException("해당 회원의 어학 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (languageMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (languageMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new CNotFoundCareerException("해당 회원의 어학 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundCareerException("해당 어학 번호의 정보가 없습니다.");
        }
    }



}
