package com.devlog.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {    // 회원 정보 반환용
    private Long id;
    private String username;
    private String email;
    private String nickname;
    private String role;
}
