package com.devlog.domain.post;

import jakarta.persistence.*;
import lombok.*;
import com.devlog.domain.user.User;
import com.devlog.domain.common.BaseEntity;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "posts")
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;

//    @Lob
//	•	Large Object (대용량 데이터) 저장을 의미
//	•	content 필드에 붙여서, 긴 본문(게시글) 저장 가능

//    @ManyToOne
//	•	다대일(N:1) 관계 매핑
//	•	예: 여러 Post → 하나의 User
//	•	Post 엔티티에서 User 참조할 때 사용

//    @JoinColumn(name = "user_id")
//	•	외래키(FK) 컬럼 이름 지정
//	•	Post 테이블에 user_id라는 컬럼이 생기고, users.id를 참조
}
