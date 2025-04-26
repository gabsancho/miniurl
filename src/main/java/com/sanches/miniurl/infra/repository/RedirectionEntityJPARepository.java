package com.sanches.miniurl.infra.repository;

import com.sanches.miniurl.infra.entity.RedirectionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;

public interface RedirectionEntityJPARepository extends JpaRepository<RedirectionEntity, Long> {
    Optional<RedirectionEntity> findByOrigin(String origin);
    Optional<RedirectionEntity> findByTarget(String target);
    Collection<RedirectionEntity> findAllByExpirationBefore(LocalDateTime expiration);

    @Modifying
    @Query("delete from #{#entityName} re where re.expiration is not null and re.expiration < ?1")
    void deleteByExpirationBefore(LocalDateTime expiration);
}
