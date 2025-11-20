package com.project.moru.domain.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
public class BaseEntity {

    @CreationTimestamp
    @Column(name = "created_at", columnDefinition = "datetime(6) default current_timestamp(6)")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false, columnDefinition = "datetime(6) default current_timestamp(6)")
    private LocalDateTime modifiedAt;
}
