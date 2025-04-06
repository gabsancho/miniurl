package com.sanches.miniurl.infra.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "redirection")
public class RedirectionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "origin_url", nullable = false, updatable = false, unique = true)
    private String origin;

    @Column(name = "target_url", nullable = false, updatable = false, unique = true)
    private String target;
}
