package com.xhh.smalldemojpa.config;

import com.xhh.smalldemojpa.filter.AuthenticationTokenFilter;
import com.xhh.smalldemojpa.handler.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig {
    
    
    @Resource
    private AuthenticationTokenFilter authenticationTokenFilter;
    
    @Resource
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        // 不需要csrf,session
        httpSecurity.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests(
                        (authz) -> 
                                authz.antMatchers("/api/1.0/login").permitAll().antMatchers(HttpMethod.OPTIONS).permitAll().antMatchers("/quartzJob/**").permitAll()
                                        .anyRequest().authenticated()
                ).httpBasic(Customizer.withDefaults());

        httpSecurity.cors();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        
        httpSecurity.addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        httpSecurity.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint);
        return httpSecurity.build();
    }
    
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class).build();
    }
    
    
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
