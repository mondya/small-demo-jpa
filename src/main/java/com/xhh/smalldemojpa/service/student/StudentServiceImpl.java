package com.xhh.smalldemojpa.service.student;

import com.xhh.smalldemojpa.dao.StudentRepository;
import com.xhh.smalldemojpa.domain.student.StudentEntity;
import com.xhh.smalldemojpa.service.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service("student")
public class StudentServiceImpl extends BaseServiceImpl<StudentRepository, StudentEntity> implements StudentService{
}
