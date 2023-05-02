package com.xhh.smalldemojpa.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Data
public class ResultVO<T> implements Serializable {
    int status;
    int code;
    String message;
    Map<String, Object> result = new HashMap<>();
    T data;

    public static <T> ResultVO<T> success(T t) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setStatus(1);
        resultVO.setMessage("success");
        resultVO.setData(t);
        return resultVO;
    }

    public static <T> ResultVO<T> failure() {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setStatus(0);
        resultVO.setMessage("failed");
        return resultVO;
    }
}
