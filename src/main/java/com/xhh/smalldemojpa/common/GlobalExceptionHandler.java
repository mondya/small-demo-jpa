package com.xhh.smalldemojpa.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResultVO handlerRuntimeException(Exception e) {
        log.error("error : ",e);
        return error(9001, "系统繁忙");
    }


    public ResultVO error(int code, String message) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(0);
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
