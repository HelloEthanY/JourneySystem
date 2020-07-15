package com.common.org;

import com.common.org.utils.ResultData;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

/**
 * @Author: YU
 * @Date: 13:07 2019/12/18
 * @Description: 全局异常处理类
 */
@RestControllerAdvice
public class TechnicalExceptionHandler {
    // 捕捉到的异常
    @ExceptionHandler(value = TechnicalException.class)
    public ResultData handleServiceException(TechnicalException exception) {
        return exception.getResultData();
    }

    //其他异常
    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultData hadleServerException(Exception exception) {
        System.out.println("其他异常" + exception);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        System.out.println("msg" + httpStatus.name());
        String msg = "server error, please try again later";
        Class exceptionClazz = exception.getClass();
        if (Objects.equals(MissingServletRequestParameterException.class, exceptionClazz)) {
            msg = "incorrect parameter";
            httpStatus = HttpStatus.BAD_REQUEST;
        } else if (Objects.equals(HttpRequestMethodNotSupportedException.class, exceptionClazz)) {
            httpStatus = HttpStatus.BAD_REQUEST;
            msg = exception.getMessage();
        } else if (Objects.equals(DataIntegrityViolationException.class, exceptionClazz)) { // 数据重复
            httpStatus = HttpStatus.BAD_REQUEST;
            msg = "数据重复！";
        }
        return ResultData.newError(httpStatus.value(), msg);
    }
}
