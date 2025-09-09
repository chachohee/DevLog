package com.devlog.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterRequest {    // 회원가입 요청
    private String username;
    private String email;
    private String password;
    private String nickname;
}
