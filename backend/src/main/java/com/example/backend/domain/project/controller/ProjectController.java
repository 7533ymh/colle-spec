package com.example.backend.domain.project.controller;

import com.example.backend.domain.project.domain.Project;
import com.example.backend.domain.project.domain.Project_img;
import com.example.backend.domain.project.service.ProjectService;
import com.example.backend.domain.user.service.UserService;
import com.example.backend.global.config.EncodeFileName;
import com.example.backend.global.exception.exception.CNotFoundDataTypeException;
import com.example.backend.global.response.CommonResult;
import com.example.backend.global.response.ListResult;
import com.example.backend.global.response.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.List;

@Api(tags = {"3-9. Project"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProjectController {

    private final ProjectService projectService;
    private final ResponseService responseService;
    private final UserService userService;
    private final EncodeFileName encodeFileName;

    @ApiOperation(value = "프로젝트 입력", notes = "프로젝트정보를 저장한다.")
    @PostMapping(value = "/project")
    public CommonResult insert(@ApiParam(value = "프로젝트 제목 ", required = true) @RequestParam String title,
                               @ApiParam(value = "프로젝트 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "프로젝트 마감년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "프로젝트 사진들") @RequestParam List<MultipartFile> files,
                               @ApiParam(value = "프로젝트 내용", required = true) @RequestParam String content,
                               @ApiParam(value = "프로젝트 성공여부 ( 1: 성공 , 0: 실패 )", required = true) @RequestParam int success) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        projectService.save(Project.builder()
                .user_idx(user_idx)
                .title(title)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .success(success)
                .build(),files);

        return responseService.getSuccessResultMsg("프로젝트 정보가 입력되었습니다.");
    }

    @ApiOperation(value = "프로젝트 조회", notes = "프로젝트정보를 조회한다.")
    @GetMapping(value = "/project")
    public ListResult<Project> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(projectService.findByUserIdx(user_idx),"프로젝트 정보를 조회하였습니다.");
    }

    @ApiOperation(value = "프로젝트 수정", notes = "프로젝트정보를 수정한다")
    @PutMapping(value = "/project")
    public CommonResult modify(@ApiParam(value = "프로젝트 번호 ", required = true) @RequestParam int idx,
                               @ApiParam(value = "프로젝트 제목 ", required = true) @RequestParam String title,
                               @ApiParam(value = "프로젝트 시작년월", required = true) @RequestParam Date start_date,
                               @ApiParam(value = "프로젝트 마감년월", required = true) @RequestParam Date end_date,
                               @ApiParam(value = "프로젝트 사진들", required = false) @RequestParam List<MultipartFile> files,
                               @ApiParam(value = "프로젝트 내용", required = true) @RequestParam String content,
                               @ApiParam(value = "프로젝트 성공여부 ( 1: 성공 , 0: 실패 )", required = true) @RequestParam int success) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        projectService.modify(Project.builder()
                .idx(idx)
                .user_idx(user_idx)
                .title(title)
                .start_date(start_date)
                .end_date(end_date)
                .content(content)
                .success(success)
                .build(),files);

        return responseService.getSuccessResultMsg("프로젝트 정보가 변경되었습니다.");
    }

    @ApiOperation(value = "프로젝트 삭제", notes = "프로젝트정보를 삭제한다")
    @DeleteMapping(value = "/project")
    public CommonResult delete_project(@ApiParam(value = "프로젝트 번호 ", required = true) @RequestParam int idx) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        projectService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("프로젝트 삭제가 완료되었습니다.");
    }

    @ApiOperation(value = "프로젝트 사진 다운로드", notes = "프로젝트 사진을 다운로드한다.")
    @GetMapping(value = "/project_img/download")
    public ResponseEntity<Resource> download(@ApiParam(value = "프로젝트 사진 idx  ", required = true) @RequestParam int idx, HttpServletRequest request) throws MalformedURLException, CNotFoundDataTypeException, UnsupportedEncodingException {

        Project_img project_img = projectService.findProjectImgByidx(idx);
        Path filePath = Paths.get(project_img.getFilepath()).toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());
        String filename =  project_img.getOrigfilename();
        String encodedFilename = encodeFileName.getEncodeFileName(request, filename);

        String contentType;

        try {
            contentType = request.getServletContext().getMimeType(
                    resource.getFile().getAbsolutePath()
            );
        }
        catch (IOException e) {
            throw new CNotFoundDataTypeException();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + encodedFilename + "\""
                )
                .body(resource);
    }





}
