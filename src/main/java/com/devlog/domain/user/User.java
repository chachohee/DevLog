package com.devlog.domain.user;

import com.devlog.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 요구사항 + 외부 보호
@AllArgsConstructor
@Builder    // @Builder는 객체 생성의 안정성 + 가독성 + 유지보수성을 높여주는 도구
@Table(name = "users") // user는 예약어라서 users 권장
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;    // 로그인 ID

    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 30)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private String role; // e.g. "USER", "ADMIN"
}
