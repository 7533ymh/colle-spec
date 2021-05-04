package com.example.backend.service.introduction;

import com.example.backend.domain.Introduction;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileHandler {
    public Introduction parseFileInfo(int user_idx, MultipartFile introductionfile) throws IOException {

        // 반환을 할 파일 리스트
        Introduction intro = new Introduction();

        // 파일이 빈 것이 들어오면 빈 것을 반환
        if (introductionfile.isEmpty()) {
            return intro;
        }


        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = "/var/lib/jenkins/workspace/Project/backend/introduction";
        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = absolutePath+ "/" + String.valueOf(user_idx);





        File file = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다

        // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
        if (!introductionfile.isEmpty()) {
            // hwp, pdf 파일들만 받아서 처리할 예정
            String contentType = introductionfile.getContentType();
            String originalFileExtension;
            // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
            if (ObjectUtils.isEmpty(contentType)) {
                throw new IllegalStateException("파일이 잘못되었습니다. 다시 시도해주세요.");
            }
            else {
                if (contentType.contains("application/pdf")) {
                    originalFileExtension = ".pdf";
                }
                else if (contentType.contains("application/vnd.hancom.hwp")) {
                    originalFileExtension = ".hwp";
                }
                // 다른 파일 명이면 아무 일 하지 않는다
                else {
                    throw new IllegalStateException("PDF,HWP파일이 아닙니다. 다시 확인해주세요.");
                }
                // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                String new_file_name = Long.toString(System.nanoTime()) + originalFileExtension;
                // 생성 후 리스트에 추가
                Introduction introduction = Introduction.builder()
                        .user_idx(user_idx)
                        .filepath(path + "/" + new_file_name)
                        .filename(new_file_name)
                        .origfilename(introductionfile.getOriginalFilename())
                        .build();
                file = new File(path + "/" + new_file_name);
                introductionfile.transferTo(file);
                return introduction;
            }

        }

        return intro;
    }
}