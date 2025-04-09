package com.sanches.miniurl.infra.repository;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.infra.entity.RedirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RedirectionEntityJPARepository extends JpaRepository<RedirectionEntity, Long> {
    Optional<RedirectionEntity> findByOrigin(String origin);
    Optional<RedirectionEntity> findByTarget(String target);
}
