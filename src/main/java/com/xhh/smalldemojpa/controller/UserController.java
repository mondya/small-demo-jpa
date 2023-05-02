package com.xhh.smalldemojpa.controller;

import com.xhh.smalldemojpa.common.ResultVO;
import com.xhh.smalldemojpa.domain.user.User;
import com.xhh.smalldemojpa.service.user.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserService userService;
    
    @GetMapping("/list")
    public ResultVO<Map<String, Object>> index(@RequestParam(value = "searchValue", required = false) String searchValue,
                          @RequestParam(value = "p", defaultValue = "1") int p,
                          @RequestParam(value = "s", defaultValue = "30") int s) {
        ResultVO<Map<String, Object>> resultVO = new ResultVO<>();
        resultVO.setStatus(1);

        Map<String, Object> stringObjectMap = userService.fetchAllUserByLimit(searchValue, p, s);
        
        resultVO.setData(stringObjectMap);
        return resultVO;
    }
    
    
    @GetMapping("/{id}")
    ResultVO<User> show(@PathVariable("id") Long id) {
        ResultVO<User> resultVO = new ResultVO<>();
        resultVO.setStatus(1);
        User user = userService.fetchById(id);
        resultVO.setData(user);
        return resultVO;
    }
}
