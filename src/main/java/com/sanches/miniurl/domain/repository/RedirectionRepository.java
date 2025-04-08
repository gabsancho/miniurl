package com.sanches.miniurl.domain.repository;

import com.sanches.miniurl.domain.model.Redirection;

import java.util.Optional;

public interface RedirectionRepository {
    Redirection save(Redirection redirection);
    Optional<Redirection> findById(Long id);
    Optional<Redirection> findByOrigin(String origin);
}
