package com.example.backend.domain.introduction.service;

import com.example.backend.global.exception.exception.CNotFoundDataTypeException;
import com.example.backend.global.exception.exception.CNotRightFileException;
import com.example.backend.domain.introduction.domain.Introduction;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class IntroductionHandler {
    public List<Introduction> parseFileInfo(int user_idx, List<MultipartFile> introductions) throws IOException {

        // 반환을 할 파일 리스트
        List<Introduction> fileList = new ArrayList<>();

        // 파일이 빈 것이 들어오면 빈 것을 반환
        if (introductions.isEmpty()) {
           throw new CNotFoundDataTypeException();
        }

        // 기본 저장 경로
        String absolutePath = "/var/lib/jenkins/workspace/Project/backend/introduction";
        // user_idx 별로 저장
        String path = absolutePath+ "/" + user_idx;

        File file = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다
        for (MultipartFile introductionFile  : introductions) {
            // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
            if (!introductionFile.isEmpty()) {
                // hwp, pdf, docx, pptx 파일들만 받아서 처리할 예정
                String contentType = introductionFile.getContentType();
                String originalFileExtension;
                // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
                if (ObjectUtils.isEmpty(contentType)) {
                    throw new CNotFoundDataTypeException();
                } else {
                    if (contentType.contains("application/pdf")) {
                        originalFileExtension = ".pdf";
                    } else if (contentType.contains("application/octet-stream")) {
                        originalFileExtension = ".hwp";
                    }else if (contentType.contains("application/msword")) {
                        originalFileExtension = ".doc";
                    } else if (contentType.contains("application/vnd.openxmlformats-officedocument.wordprocessingml.document")) {
                        originalFileExtension = ".docx";
                    } else if (contentType.contains("application/vnd.ms-powerpoint")) {
                        originalFileExtension = ".ppt";
                    } else if (contentType.contains("application/vnd.openxmlformats-officedocument.presentationml.presentation")) {
                        originalFileExtension = ".pptx";
                    }
                    // 다른 파일 명이면 아무 일 하지 않는다
                    else {
                        throw new CNotRightFileException("PDF,HWP,DOC,PPT 파일이 아닙니다. 다시 확인해주세요.");
                    }
                    // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                    String new_file_name = System.nanoTime() + originalFileExtension;
                    // 생성 후 리스트에 추가
                    Introduction introduction = Introduction.builder()
                            .user_idx(user_idx)
                            .filepath(path + "/" + new_file_name)
                            .filename(new_file_name)
                            .origfilename(introductionFile.getOriginalFilename())
                            .build();
                    fileList.add(introduction);
                    file = new File(path + "/" + new_file_name);
                    introductionFile.transferTo(file);
                }
            }
        }

        return fileList;
    }
}
