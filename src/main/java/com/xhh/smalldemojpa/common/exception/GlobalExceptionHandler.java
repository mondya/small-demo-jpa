package com.xhh.smalldemojpa.common.exception;

import com.xhh.smalldemojpa.common.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SmallDemoJpaRuntimeException.class)
    public ResultVO<Void> handlerSmallDemoJpaException(SmallDemoJpaRuntimeException smallDemoJpaRuntimeException) {
        log.error("error: ", smallDemoJpaRuntimeException);
        return error(9000, smallDemoJpaRuntimeException.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResultVO<Void> handlerRuntimeException(Exception e) {
        log.error("error : ",e);
        return error(9001, "系统繁忙");
    }


    public ResultVO<Void> error(int code, String message) {
        ResultVO<Void> resultVO = new ResultVO<>();
        resultVO.setStatus(0);
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
