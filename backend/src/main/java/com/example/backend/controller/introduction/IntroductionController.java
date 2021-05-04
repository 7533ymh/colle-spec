package com.example.backend.controller.introduction;


import com.example.backend.advice.exception.CNotFoundDataTypeException;
import com.example.backend.domain.Introduction;
import com.example.backend.response.CommonResult;
import com.example.backend.response.ListResult;
import com.example.backend.service.introduction.IntroductionService;
import com.example.backend.service.response.ResponseService;
import com.example.backend.service.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.Builder;
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
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@Api(tags = {"5. Introduction"})
@Builder
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class IntroductionController {

    private final IntroductionService introductionService;
    private final ResponseService responseService;
    private final UserService userService;

    @ApiOperation(value = "자기소개서 업로드", notes = "자기소개서를 저장한다.")
    @PostMapping(value = "/introduction/uplode")
    public CommonResult upload(@ApiParam(value = "자기소개서 파일 ", required = true) MultipartFile introduction) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        introductionService.upload(user_idx, introduction);

        return responseService.getSuccessResultMsg("자기소개서가 등록되었습니다.");
    }


    @ApiOperation(value = "자기소개서 조회", notes = "자기소개서를 조회한다.")
    @GetMapping(value = "/introduction")
    public ListResult<Introduction> show() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);


        return responseService.getListResultMsg(introductionService.checkIntroduction(user_idx), "자기소개서를 조회하였습니다.");
    }

    @ApiOperation(value = "자기소개서 삭제", notes = "자기소개서를 삭제한다")
    @DeleteMapping(value = "/introduction")
    public CommonResult delete(@ApiParam(value = "자기소개서 번호 ", required = true) @RequestParam int idx) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String AuthId = authentication.getName();
        int user_idx = userService.findIdxById(AuthId);

        introductionService.delete(idx , user_idx);

        return responseService.getSuccessResultMsg("자기소개서 삭제가 완료되었습니다.");
    }

    @ApiOperation(value = "자기소개서 파일 다운로드", notes = "자기소개서 파일을 다운로드한다.")
    @GetMapping(value = "/introduction/download")
    public ResponseEntity<Resource> download(@ApiParam(value = "자기소개서 idx  ", required = true) @RequestParam int idx, HttpServletRequest request) throws MalformedURLException, CNotFoundDataTypeException {

        Introduction introduction = introductionService.findByidx(idx);
        Path filePath = Paths.get(introduction.getFilepath()).toAbsolutePath().normalize();
        Resource resource = new UrlResource(filePath.toUri());

        String contentType = null;

        try {
            contentType = request.getServletContext().getMimeType(
                    resource.getFile().getAbsolutePath()
            );
        }
        catch (IOException e) {
            throw new CNotFoundDataTypeException("데이터 타입을 알 수 없습니다. 다시 시도해주세요.");
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + introduction.getOrigfilename() + "\""
                )
                .body(resource);
    }


}
