package com.example.backend.domain.project.service;

import com.example.backend.global.exception.exception.CNotFoundDataTypeException;
import com.example.backend.global.exception.exception.CNotRightFileException;
import com.example.backend.domain.project.domain.Project_img;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImgHandler {
    public List<Project_img> parseFileInfo(int project_idx, List<MultipartFile> imgfiles) throws IOException {

        // 반환을 할 파일 리스트
        List<Project_img> fileList = new ArrayList<>();

        // 파일이 빈 것이 들어오면 빈 것을 반환
        if (imgfiles.isEmpty()) {
            return fileList;
        }


        // 프로젝트 폴더에 저장하기 위해 절대경로를 설정 (Window 의 Tomcat 은 Temp 파일을 이용한다)
        String absolutePath = "/var/lib/jenkins/workspace/Project/backend/project";
        // 경로를 지정하고 그곳에다가 저장할 심산이다
        String path = absolutePath+ "/" + project_idx;



        File file = new File(path);
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if (!file.exists()) {
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }

        // 파일들을 이제 만져볼 것이다
        for (MultipartFile imgfile : imgfiles) {
            // 파일이 비어 있지 않을 때 작업을 시작해야 오류가 나지 않는다
            if (!imgfile.isEmpty()) {
                // hwp, pdf 파일들만 받아서 처리할 예정
                String contentType = imgfile.getContentType();
                String originalFileExtension;
                // 확장자 명이 없으면 이 파일은 잘 못 된 것이다
                if (ObjectUtils.isEmpty(contentType)) {
                    throw new CNotFoundDataTypeException();
                } else {
                    if (contentType.contains("image/jpeg")) {
                        originalFileExtension = ".jpg";
                    } else if (contentType.contains("image/png")) {
                        originalFileExtension = ".png";
                    } else if (contentType.contains("image/gif")) {
                        originalFileExtension = ".gif";
                    }
                    // 다른 파일 명이면 아무 일 하지 않는다
                    else {
                        throw new CNotRightFileException("jpg,png,gif 파일이 아닙니다. 다시 확인해주세요.");
                    }
                    // 각 이름은 겹치면 안되므로 나노 초까지 동원하여 지정
                    String new_file_name = System.nanoTime() + originalFileExtension;
                    // 생성 후 리스트에 추가
                    Project_img project_img = Project_img.builder()
                            .project_idx(project_idx)
                            .filepath(path + "/" + new_file_name)
                            .filename(new_file_name)
                            .origfilename(imgfile.getOriginalFilename())
                            .build();
                    fileList.add(project_img);
                    file = new File(path + "/" + new_file_name);
                    imgfile.transferTo(file);
                }

            }
        }
        return fileList;
    }

}
