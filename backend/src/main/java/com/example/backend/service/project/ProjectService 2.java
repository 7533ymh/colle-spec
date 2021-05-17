package com.example.backend.service.project;

import com.example.backend.advice.exception.CDateException;
import com.example.backend.advice.exception.CNotFoundInfoByIdxException;
import com.example.backend.advice.exception.CNotFoundInfoByUserException;
import com.example.backend.advice.exception.CNotHaveAccessInfoException;
import com.example.backend.domain.Project;
import com.example.backend.domain.Project_img;
import com.example.backend.mapper.ProjectMapper;
import com.example.backend.mapper.Project_imgMapper;
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
public class ProjectService {

    private final ProjectMapper projectMapper;
    private final Project_imgMapper project_imgMapper;
    private final ImgHandler imgHandler;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    public Project save(Project project, List <MultipartFile> files) throws Exception {

        logger.info("프로젝트 등록");

        checkDate(project);

        project.setScore(changetoNumber(project));

        projectMapper.save(project);

        int project_idx = project.getIdx();

        List<Project_img> list = imgHandler.parseFileInfo(project_idx, files);

        if(!list.isEmpty()) {

            for(Project_img project_img : list) {
                project_imgMapper.save(project_img);
            }

        }

        return project;

    }

    public List<Project> findByUserIdx(int user_idx) {

        logger.info("프로젝트 조회");

        checkProjectUserIdx(user_idx);

        return projectMapper.findByUserIdx(user_idx);

    }

    public Project modify(Project project , List <MultipartFile> files ) throws IOException {

        logger.info("프로젝트 수정");

        checkDate(project);

        checkProjectUserIdx(project.getUser_idx());

        checkAll(project.getIdx(), project.getUser_idx());

        project.setScore(changetoNumber(project));

        List<Project_img> deletes = project_imgMapper.findByProject_Idx(project.getIdx());

        if(!deletes.isEmpty()) {

            for(Project_img delete : deletes) {
                Files.delete(Paths.get(delete.getFilepath()));
            }

            project_imgMapper.delete(project.getIdx());

        }


        List<Project_img> list = imgHandler.parseFileInfo(project.getIdx(), files);

        if(!list.isEmpty()) {

            for(Project_img project_img : list) {
                project_imgMapper.save(project_img);
            }

        }

        projectMapper.update(project);

        return project;

    }

    public void delete (int idx , int user_idx) throws IOException {

        logger.info("프로젝트 삭제");

        checkProjectUserIdx(user_idx);

        checkAll(idx,user_idx);

        List<Project_img> deletes = project_imgMapper.findByProject_Idx(idx);

        if(!deletes.isEmpty()) {

            for(Project_img delete : deletes) {
                Files.delete(Paths.get(delete.getFilepath()));
            }

        }

        projectMapper.delete(idx, user_idx);

    }

    public Project_img findProjectImgByidx(int idx)  {

        return project_imgMapper.findByidx(idx)
                .orElseThrow(() -> new CNotFoundInfoByIdxException("해당하는 파일번호에 대한 파일이 없습니다."));

    }


    private void checkDate(Project project) {
        if (project.getStart_date().after(project.getEnd_date())) {
            throw new CDateException();
        }
    }




    //수치화 알고리즘 부분
    public int changetoNumber (Project project) {

        if (project.getSuccess()==1) {
            return 100;
        }
        else {
            return 30;
        }


    }



    public void checkProjectUserIdx(int user_idx) {
        if ( projectMapper.findByUserIdx(user_idx).isEmpty()){
            throw new CNotFoundInfoByUserException("해당 회원의 프로젝트 정보가 없습니다.");
        }

    }


    public void checkAll(int idx , int user_idx) {

        if (projectMapper.finduser_idxByIdx(idx).isPresent() ) {
            if (projectMapper.finduser_idxByIdx(idx).get() != user_idx)
                throw new CNotHaveAccessInfoException("해당 회원의 프로젝트 번호가 아닙니다.");
        }
        else {
            throw new CNotFoundInfoByIdxException("해당 프로젝트 번호의 정보가 없습니다.");
        }
    }

}
