package com.xhh.smalldemojpa.controller;

import com.xhh.smalldemojpa.common.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    
    @GetMapping
    ResultVO show(@PathVariable("id") Long id) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(1);
        
        return resultVO;
    }
}
