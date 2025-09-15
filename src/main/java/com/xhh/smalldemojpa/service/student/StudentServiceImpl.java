package com.xhh.smalldemojpa.service.student;

import com.xhh.smalldemojpa.dao.StudentRepository;
import com.xhh.smalldemojpa.domain.student.StudentEntity;
import com.xhh.smalldemojpa.service.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service("student")
@RequiredArgsConstructor
public class StudentServiceImpl extends BaseServiceImpl<StudentRepository, StudentEntity> implements StudentService{
    
    
    private final StudentRepository studentRepository;
    
    @Override
    public StudentEntity insertOne(StudentEntity student) {
        return studentRepository.saveAndFlush(student);
    }
}
