package com.xhh.smalldemojpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude = {UserDetailsServiceAutoConfiguration.class})
public class SmallDemoJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmallDemoJpaApplication.class, args);
    }

}
