package com.ssafy.backend.common.auth;

import com.ssafy.backend.common.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JWTInterceptor implements HandlerInterceptor {
    private static final String HEADER_AUTH = "access-token";


    @Autowired
    private JWTUtil jwtUtil;

    //인터셉터에서 유효성 검사
    //토큰에서 uid 찾기
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }

        final String token = request.getHeader(HEADER_AUTH);

        if (token != null) {
            jwtUtil.checkValid(token);
            return true;
        }
        throw new Exception("유효하지 않은 접근입니다.");

    }
}
