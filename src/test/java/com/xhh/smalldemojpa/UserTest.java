package com.xhh.smalldemojpa;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.xhh.smalldemojpa.dao.UserRepository;
import com.xhh.smalldemojpa.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;

@SpringBootTest
public class UserTest {
    
    
    @Resource
    private UserRepository userRepository;
    
    @Test
    void save() {
        User user = new User();
        user.setUserName("simple");
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
    void getByIdList() {
        ArrayList<Long> longs = new ArrayList<>();
//        longs.add(1l);
//        longs.add(2l);
        List<User> byIdList = userRepository.findByIdList(null);
        System.out.println(byIdList);
    }
    
    @Test
    void getByName() {
//        User admin = userRepository.getByUserName("admin");
//        System.out.println(admin);
    }
    
    @Test
    void getAllBySearch() {

        Page<User> userList = userRepository.fetchAllUserBySearch("%\\" + "" + "%", PageRequest.of(0, 2, Sort.Direction.DESC, "id"));
        System.out.println(userList.get().collect(Collectors.toList()).toString());
        System.out.println("size = " + userList.getTotalElements());
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
