package com.xhh.smalldemojpa;

import com.xhh.smalldemojpa.domain.student.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentTest {
    
    @Test
    void test() {
        StudentEntity student = new StudentEntity();
        student.setNormal(true);

        System.out.println("isNormal" + student.isNormal());
    }
}
