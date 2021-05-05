package com.example.backend.advice;

import com.example.backend.advice.exception.*;
import com.example.backend.response.CommonResult;
import com.example.backend.service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionAdvice {

    private final ResponseService responseService;
    private final MessageSource messageSource;

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult defaultException(HttpServletRequest request, Exception e) {

        if (e instanceof MissingServletRequestParameterException) {
            return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundParam.code")), getMessage("NotFoundParam.msg"));
        }

        if (e instanceof MethodArgumentTypeMismatchException) {
            return responseService.getFailResult(Integer.parseInt(getMessage("NotMatchParam.code")), getMessage("NotMatchParam.msg"));
        }

        return responseService.getFailResult(e.getMessage());
    }

    @ExceptionHandler(CUserLoginFailException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserLoginFailException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserLoginFail.code")), getMessage("UserLoginFail.msg"));
    }

    @ExceptionHandler(CUserExistException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserExistException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserExist.code")), getMessage("UserExist.msg"));
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserNotFound.code")), getMessage("UserNotFound.msg"));
    }

    @ExceptionHandler(CNotHaveAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CNotHaveAccessException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotHaveAccess.code")), getMessage("NotHaveAccess.msg"));
    }

    @ExceptionHandler(CNotFoundInfoByUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CNotFoundInfoByUserException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundInfoByUser.code")), e.getMessage());
    }

    @ExceptionHandler(CNotFoundInfoByIdxException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CNotFoundInfoByIdxException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundInfoByIdx.code")), e.getMessage());
    }

    @ExceptionHandler(CNotHaveAccessInfoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CNotHaveAccessInfoException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotHaveAccessInfo.code")), e.getMessage());
    }

    @ExceptionHandler(CNotFoundDataTypeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CNotFoundDataTypeException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundDataType.code")), getMessage("NotFoundDataType.msg"));
    }

    @ExceptionHandler(CNotRightFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CNotRightFileException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotRightFile.code")), e.getMessage());
    }

    @ExceptionHandler(CDateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult userNotFoundException(HttpServletRequest request, CDateException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("Date.code")), getMessage("Date.msg"));
    }

















    // code정보에 해당하는 메시지를 조회합니다.
    private String getMessage(String code) {
        return getMessage(code, null);
    }

    // code정보, 추가 argument로 현재 locale에 맞는 메시지를 조회합니다.
    private String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }


}
