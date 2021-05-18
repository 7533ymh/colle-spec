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
    protected CommonResult CUserExistException(HttpServletRequest request, CUserExistException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserExist.code")), getMessage("UserExist.msg"));
    }

    @ExceptionHandler(CUserNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CUserNotFoundException(HttpServletRequest request, CUserNotFoundException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("UserNotFound.code")), getMessage("UserNotFound.msg"));
    }

    @ExceptionHandler(CNotHaveAccessException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotHaveAccessException(HttpServletRequest request, CNotHaveAccessException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotHaveAccess.code")), getMessage("NotHaveAccess.msg"));
    }

    @ExceptionHandler(CNotFoundInfoByUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotFoundInfoByUserException(HttpServletRequest request, CNotFoundInfoByUserException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundInfoByUser.code")), e.getMessage());
    }

    @ExceptionHandler(CNotFoundInfoByIdxException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotFoundInfoByIdxException(HttpServletRequest request, CNotFoundInfoByIdxException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundInfoByIdx.code")), e.getMessage());
    }

    @ExceptionHandler(CNotHaveAccessInfoException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotHaveAccessInfoException(HttpServletRequest request, CNotHaveAccessInfoException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotHaveAccessInfo.code")), e.getMessage());
    }

    @ExceptionHandler(CNotFoundDataTypeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotFoundDataTypeException(HttpServletRequest request, CNotFoundDataTypeException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundDataType.code")), getMessage("NotFoundDataType.msg"));
    }

    @ExceptionHandler(CNotRightFileException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotRightFileException(HttpServletRequest request, CNotRightFileException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotRightFile.code")), e.getMessage());
    }

    @ExceptionHandler(CDateException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CDateException(HttpServletRequest request, CDateException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("Date.code")), getMessage("Date.msg"));
    }

    @ExceptionHandler(CFindSameGradeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CFindSameGradeException(HttpServletRequest request, CFindSameGradeException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("FindSameGrade.code")), getMessage("FindSameGrade.msg"));
    }

    @ExceptionHandler(CFindSameExamException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CFindSameExamException(HttpServletRequest request, CFindSameExamException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("FindSameExam.code")), getMessage("FindSameExam.msg"));
    }

    @ExceptionHandler(CLinkException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CLinkException(HttpServletRequest request, CLinkException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("Link.code")), e.getMessage());
    }

    @ExceptionHandler(CNotFoundInfoByLinkException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotFoundInfoByLinkException(HttpServletRequest request, CNotFoundInfoByLinkException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundInfoByLink.code")), getMessage("NotFoundInfoByLink.msg"));
    }

    @ExceptionHandler(CNotFoundDataException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotFoundDataException(HttpServletRequest request, CNotFoundDataException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundData.code")), getMessage("NotFoundData.msg"));
    }

    @ExceptionHandler(CNotFoundScoreException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected CommonResult CNotFoundDataException(HttpServletRequest request, CNotFoundScoreException e) {
        return responseService.getFailResult(Integer.parseInt(getMessage("NotFoundScore.code")), getMessage("NotFoundScore.msg"));
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
