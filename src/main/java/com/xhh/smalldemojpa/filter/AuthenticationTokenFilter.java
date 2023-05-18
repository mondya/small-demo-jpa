package com.xhh.smalldemojpa.filter;

import com.alibaba.fastjson2.JSON;
import com.xhh.smalldemojpa.module.JwtUser;
import com.xhh.smalldemojpa.utils.JwtUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Component
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    
    public static final String AUTHORIZATION = "Authorization";
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(AUTHORIZATION);
        
        if (StringUtils.isNotBlank(token)) {
            token = resolveToken(token);
            Authentication authenticationByToken = getAuthenticationByToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationByToken);
        }

        
        doFilter(request, response, filterChain);
    }
    
    public String resolveToken(String token) {
        if (token.startsWith("Bearer ")) {
            return token.substring(7);
        } else {
            return token;
        }
    }
    
    public Authentication getAuthenticationByToken(String token) {
        String claimsFromToken = JwtUtils.getClaimsFromToken(token);
        JwtUser jwtUser = JSON.parseObject(claimsFromToken, JwtUser.class);
        
        if (Objects.nonNull(jwtUser)) {
            return new UsernamePasswordAuthenticationToken(jwtUser, null, null);
        } else {
            return null;
        }
    } 
}
