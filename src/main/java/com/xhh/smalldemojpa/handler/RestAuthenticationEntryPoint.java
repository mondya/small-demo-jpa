package com.xhh.smalldemojpa.handler;

import com.alibaba.fastjson2.JSON;
import com.xhh.smalldemojpa.common.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登入或者token失效访问接口时，自定义返回结果
 */
@Component
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ResultVO<String> resultVO = new ResultVO<String>();
        resultVO.setStatus(0);
        resultVO.setMessage("登入异常，请重新登入");
        response.getWriter().println(JSON.toJSONString(resultVO));
        response.getWriter().flush();
    }
}
