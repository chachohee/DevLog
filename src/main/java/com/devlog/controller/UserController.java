package com.devlog.controller;

import com.devlog.dto.user.UserLoginRequest;
import com.devlog.dto.user.UserLoginResponse;
import com.devlog.dto.user.UserRegisterRequest;
import com.devlog.dto.user.UserResponse;
import com.devlog.security.JwtUtil;
import com.devlog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    // 회원가입
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRegisterRequest request) {
        // UserService에서 회원가입
        var savedUser = userService.registerUser(request);

        // UserResponse로 변환
        UserResponse response = new UserResponse(
                savedUser.getId(),
                savedUser.getUsername(),
                savedUser.getEmail(),
                savedUser.getNickname(),
                savedUser.getRole().name() // Role -> String
        );

        return ResponseEntity.ok(response);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(new UserLoginResponse(token));
    }
}