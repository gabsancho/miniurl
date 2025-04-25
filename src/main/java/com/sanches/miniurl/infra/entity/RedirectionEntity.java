package com.sanches.miniurl.infra.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "redirection")
@Getter
@NoArgsConstructor
public class RedirectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String origin;

    @Column(name = "target_url", nullable = false, updatable = false, unique = true)
    private String target;

    @Column(updatable = false)
    private LocalDateTime expiration;

    public RedirectionEntity(String origin, String target) {
        this.origin = origin;
        this.target = target;
    }

    public RedirectionEntity(String origin, String target, LocalDateTime expiration) {
        this(origin, target);
        this.expiration = expiration;
    }
}