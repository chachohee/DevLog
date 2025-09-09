package com.devlog.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {   // JWT 인증 필터, SecurityContext에 인증 정보 설정

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. 헤더에서 Authorization 토큰 추출
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String token = null;

        // 1. 헤더에서 Authorization 토큰 추출
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
        try {
            // 2. 토큰에서 사용자 이름 추출
            username = jwtUtil.extractUsername(token);
            // 3. 토큰이 유효한지 검증
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                if (jwtUtil.validateToken(token, username)) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.info("Valid JWT Token, user authenticated: " + username);
                }
            }
        } catch (Exception e) {
            log.warn("JWT Filter Error: {}", e.getMessage());
        }
    }
        // 4. 다음 필터로 요청 전달
        filterChain.doFilter(request, response);
    }
}
