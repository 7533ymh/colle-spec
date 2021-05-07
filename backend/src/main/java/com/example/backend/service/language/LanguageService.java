package com.example.backend.service.language;

import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Language;
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

    public void delete(int idx, int user_idx) {

        logger.info("어학 삭제");

        checkLanguageUserIdx(user_idx);

        checkAll(idx, user_idx);

        languageMapper.delete(idx, user_idx);

    }


    public int changetoNumber(Language language) {

        int score;
        String rating;

        switch (language.getExam()) {

            case "TOEIC":
                score = Integer.getInteger(language.getExam_score());
                if (score >= 860)
                    return 100;
                else if (score >= 730)
                    return 80;
                else if (score >= 470)
                    return 60;
                else if (score >= 220)
                    return 40;
                else
                    return 30;

            case "TOEFL Reading":
                score = Integer.getInteger(language.getExam_score());
                if (score >= 24)
                    return 100;
                else if (score >= 18)
                    return 80;
                else if (score >= 4)
                    return 60;
                else
                    return 30;

            case "TOEFL Listening":
                score = Integer.getInteger(language.getExam_score());
                if (score >= 22)
                    return 100;
                else if (score >= 17)
                    return 80;
                else if (score >= 9)
                    return 60;
                else
                    return 30;

            case "TOEFL Speaking":
                score = Integer.getInteger(language.getExam_score());
                if (score >= 25)
                    return 100;
                else if (score >= 20)
                    return 80;
                else if (score >= 16)
                    return 60;
                else if (score >= 10)
                    return 40;
                else
                    return 30;

            case "TOEFL Writing":
                score = Integer.getInteger(language.getExam_score());
                if (score >= 24)
                    return 100;
                else if (score >= 17)
                    return 80;
                else if (score >= 13)
                    return 60;
                else if (score >= 7)
                    return 40;
                else
                    return 30;

            case "TEPS":
                score = Integer.getInteger(language.getExam_score());
                if (score >= 901)
                    return 100;
                else if (score >= 801)
                    return 80;
                else if (score >= 701)
                    return 70;
                else if (score >= 601)
                    return 60;
                else if (score >= 501)
                    return 55;
                else if (score >= 401)
                    return 50;
                else if (score >= 301)
                    return 45;
                else if (score >= 201)
                    return 40;
                else if (score >= 101)
                    return 35;
                else
                    return 30;

            case "OPIC":
                rating = language.getExam_score();
                if (rating.equals("AL"))
                    return 100;
                else if (rating.equals("IH") || rating.equals("IM3"))
                    return 80;
                else if (rating.equals("IM1") || rating.equals("IM2"))
                    return 60;
                else if (rating.equals("IL"))
                    return 40;
                else
                    return 30;

            case "ILPT":
                rating = language.getExam_score();
                if (rating.equals("N1"))
                    return 100;
                else if (rating.equals("N2"))
                    return 80;
                else if (rating.equals("N3"))
                    return 60;
                else if (rating.equals("N4"))
                    return 40;
                else
                    return 30;

            case "HSK":
                rating = language.getExam_score();
                if (rating.equals("6급"))
                    return 100;
                else if (rating.equals("5급"))
                    return 80;
                else if (rating.equals("4급"))
                    return 60;
                else if (rating.equals("3급"))
                    return 50;
                else if (rating.equals("2급"))
                    return 40;
                else
                    return 30;

        }


        return 10;

    }


    public void checkLanguageUserIdx(int user_idx) {
        if (languageMapper.findByUserIdx(user_idx).isEmpty()) {
            throw new CNotFoundInfoByUserException("해당 회원의 어학 정보가 없습니다.");
        }

    }


    public void checkAll(int idx, int user_idx) {

        if (languageMapper.finduser_idxByIdx(idx).isPresent()) {
            if (languageMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 어학 번호가 아닙니다.");
        } else {
            throw new CNotFoundInfoByIdxException("해당 어학 번호의 정보가 없습니다.");
        }
    }


}
