package com.devlog.domain.common;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass  // 상속받는 엔티티에 매핑 정보 전달
public class BaseEntity {

    @CreationTimestamp
    @Column(updatable = false)  // insert 시에만 값 세팅
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
