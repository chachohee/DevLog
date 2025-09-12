package com.devlog.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class LogoutService {
    // 인증/인가 토큰 관리 - 로그아웃, 블랙리스트 관리 등

    private final RedisTemplate<String, String> redisTemplate;

    //  로그아웃 시 Access Token을 블랙리스트에 등록
    public void logout(String token, long expirationMillis) {
        // 토큰을 Redis에 저장하여 블랙리스트에 추가
        redisTemplate.opsForValue().set(
                token,
                "logout",
                expirationMillis,
                TimeUnit.MILLISECONDS);
    }
    
    // 블랙리스트에 등록된 토큰인지 확인
    public boolean isLoggedOut(String token) {
        try {
            return Boolean.TRUE.equals(redisTemplate.hasKey(token));
        } catch (Exception e) {
            log.error("Redis 접속 실패, 로그아웃 체크 실패: {}", e.getMessage());
            return false; // 실패 시 차단하지 않고 토큰 허용
        }
    }
}
