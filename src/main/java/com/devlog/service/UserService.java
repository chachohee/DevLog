package com.devlog.service;

import com.devlog.domain.user.Role;
import com.devlog.domain.user.User;
import com.devlog.domain.user.UserRepository;
import com.devlog.dto.user.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User registerUser(UserRegisterRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already taken.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email is already in use.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User newUser = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(encodedPassword)
                .nickname(request.getNickname())
                .role(String.valueOf(Role.USER)) // 기본 역할 설정
                .build();

        return userRepository.save(newUser);
    }
}
