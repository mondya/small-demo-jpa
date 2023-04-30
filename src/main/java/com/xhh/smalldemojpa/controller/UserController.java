package com.xhh.smalldemojpa.controller;

import com.xhh.smalldemojpa.common.ResultVO;
import com.xhh.smalldemojpa.service.user.UserService;
import org.apache.el.util.Validation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    
    @GetMapping("/list")
    public ResultVO index(@RequestParam(value = "searchValue", required = false) String searchValue,
                          @RequestParam(value = "p", defaultValue = "1") int p,
                          @RequestParam(value = "s", defaultValue = "30") int s) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(1);

        Map<String, Object> stringObjectMap = userService.fetchAllUserByLimit(searchValue, p, s);
        resultVO.setResult(stringObjectMap);
        
        return resultVO;
    }
    
    
    @GetMapping("/{id}")
    ResultVO show(@PathVariable("id") Long id) {
        ResultVO resultVO = new ResultVO();
        resultVO.setStatus(1);
        
        return resultVO;
    }
}
