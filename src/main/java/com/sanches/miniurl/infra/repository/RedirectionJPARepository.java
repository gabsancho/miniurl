package com.sanches.miniurl.infra.repository;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import com.sanches.miniurl.infra.mapper.RedirectionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static com.sanches.miniurl.infra.mapper.RedirectionMapper.toRedirection;
import static com.sanches.miniurl.infra.mapper.RedirectionMapper.toRedirectionEntity;

@Repository
@RequiredArgsConstructor
public class RedirectionJPARepository implements RedirectionRepository {
    private final RedirectionEntityJPARepository repository;

    @Override
    public Redirection save(Redirection redirection) {
        return toRedirection(repository.save(toRedirectionEntity(redirection)));
    }

    @Override
    public Optional<Redirection> findById(Long id) {
        return repository.findById(id)
                .map(RedirectionMapper::toRedirection);
    }

    @Override
    public Optional<Redirection> findByOrigin(String origin) {
        return repository.findByOrigin(origin)
                .map(RedirectionMapper::toRedirection);
    }

    @Override
    public Optional<Redirection> findByTarget(String target) {
        return repository.findByTarget(target)
                .map(RedirectionMapper::toRedirection);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
