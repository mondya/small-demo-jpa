package com.xhh.smalldemojpa.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public ResultVO<Map<String, Object>> handlerRuntimeException(Exception e) {
        log.error("error : ",e);
        return error(9001, "系统繁忙");
    }


    public ResultVO<Map<String, Object>> error(int code, String message) {
        ResultVO<Map<String , Object>> resultVO = new ResultVO<>();
        resultVO.setStatus(0);
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
