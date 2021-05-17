package com.example.backend.service.introduction;

import com.example.backend.advice.exception.*;
import com.example.backend.domain.Introduction;
import com.example.backend.mapper.collspec.IntroductionMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@RequiredArgsConstructor
@Service
public class IntroductionService {

    private final IntroductionMapper introductionMapper;
    private final IntroductionHandler introductionHandler;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public void upload(int user_idx, List <MultipartFile> files) throws IOException {

        logger.info("자기소개서 업로드");

        List<Introduction> introductions = introductionHandler.parseFileInfo(user_idx, files);

        if (!introductions.isEmpty()) {
            for(Introduction introduction : introductions) {
                introductionMapper.save(introduction);
            }
        }


    }

    public List<Introduction> checkIntroduction(int user_idx) {

        logger.info("자기소개서 조회");

        checkIntroductionUserIdx(user_idx);

        return introductionMapper.findByuser_idx(user_idx);

    }

    public Introduction findByidx(int idx)  {

       return introductionMapper.findByidx(idx)
               .orElseThrow(() -> new CNotFoundInfoByIdxException("해당하는 파일번호에 대한 파일이 없습니다."));

    }


    public void delete (int idx , int user_idx) throws IOException {

        logger.info("자기소개서 삭제");

        checkIntroductionUserIdx(user_idx);

        checkRealUser(idx,user_idx);

        Introduction delete = introductionMapper.findByidx(idx).get();

        Files.delete(Paths.get(delete.getFilepath()));

        introductionMapper.delete(idx, user_idx);


    }


    public void checkIntroductionUserIdx(int user_idx) {
        if (introductionMapper.findByuser_idx(user_idx).isEmpty()) {
            throw new CNotFoundInfoByUserException("해당 회원의 자기소개서파일이 없습니다.");
        }
    }

    public void checkRealUser(int idx , int user_idx) {

        if (introductionMapper.findByidx(idx).isPresent() ) {
            if (introductionMapper.findByidx(idx).get().getUser_idx() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 자기소개서 번호가 아닙니다.");
        } else {
            throw new CNotFoundInfoByIdxException("해당 자기소개서 번호의 정보가 없습니다.");
        }

    }

}
