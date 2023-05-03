package com.xhh.smalldemojpa.controller;

import com.xhh.smalldemojpa.common.ResultVO;
import com.xhh.smalldemojpa.domain.student.StudentEntity;
import com.xhh.smalldemojpa.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
public class StudentController {
    

    @Resource
    private StudentService studentService;
    
    @GetMapping("/list")
    public ResultVO<Map<String, Object>> findAll() {
        List<StudentEntity> all = studentService.findAll();
        Map<String, Object> map = new HashMap<>();
        map.put("list", all);
        map.put("total", all.size());
        
        return ResultVO.success(map);
    }
}
