package com.example.backend.global.exception;


import com.example.backend.global.exception.exception.CNotHaveAccessException;
import com.example.backend.global.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
@ApiIgnore
public class ExceptionController {

    @GetMapping(value = "/entrypoint")
    public CommonResult entrypointException() {
        throw new CNotHaveAccessException();
    }

}
