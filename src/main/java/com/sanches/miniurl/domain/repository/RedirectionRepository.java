package com.sanches.miniurl.domain.repository;

import com.sanches.miniurl.domain.model.Redirection;

import java.time.LocalDateTime;
import java.util.Optional;

public interface RedirectionRepository {
    Redirection save(Redirection redirection);
    Optional<Redirection> findById(Long id);
    Optional<Redirection> findByOrigin(String origin);
    Optional<Redirection> findByTarget(String redirectionId);
    void deleteByOrigin(String origin);
    void deleteByExpirationBefore(LocalDateTime expiration);
}
