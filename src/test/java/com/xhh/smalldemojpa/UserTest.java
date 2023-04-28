package com.xhh.smalldemojpa;
import java.time.LocalDateTime;
import java.util.Optional;

import com.xhh.smalldemojpa.dao.UserRepository;
import com.xhh.smalldemojpa.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {
    
    
    @Resource
    private UserRepository userRepository;
    
    @Test
    void save() {
        User user = new User();
        user.setUserName("admin");
        user.setPassword("admin123");
        user.setGender((byte)0);
        user.setIdCard("3601211999");
        user.setEmail("1924292900@qq.com");
        user.setPhone("13767152962");
        user.setBirthday(LocalDateTime.now());
        user.setStatus((byte)0);
        System.out.println(userRepository.save(user));
    }
    
    @Test
    void getById() {
        User byId1 = userRepository.getById(1L);
        System.out.println("by1:" + byId1);
        Optional<User> byId = userRepository.findById(1L);
        byId.ifPresent(System.out::println);
    }
    
    @Test
    void getByName() {
//        User admin = userRepository.getByUserName("admin");
//        System.out.println(admin);
    }
    
    @Test
    void updateById() {
        Optional<User> byId = userRepository.findById(1L);
        User user = byId.get();
        user.setLastUpdated(LocalDateTime.now());
        user.setDataCreated(LocalDateTime.now());
        userRepository.save(user);
    }
    
    @Test
    void update() {
        Optional<User> optionalUser = userRepository.findById(1L);
        User user = optionalUser.get();
        user.setUserName("xhh");
        userRepository.save(user);
    }
    
    @Test
    void deleteById() {
        System.out.println("---");
        userRepository.deleteById(1L);
    }
}
