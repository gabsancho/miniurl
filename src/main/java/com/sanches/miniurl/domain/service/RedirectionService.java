package com.sanches.miniurl.domain.service;

import com.sanches.miniurl.domain.exception.RedirectionGenerationException;
import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
public class RedirectionService {
    private static final int DEFAULT_SIZE = 6;
    private static final short MAX_RETRIES = 10;
    private final RedirectionRepository redirectionRepository;

    public Redirection register(String target, LocalDateTime expiration) {
        Optional<Redirection> maybeRedirection = redirectionRepository.findByTarget(target);
        if (maybeRedirection.isPresent()) {
            return maybeRedirection.get();
        }

        short i = 0;
        String origin;
        do {
            origin = OriginGenerator.generate(DEFAULT_SIZE);
            if (i++ > MAX_RETRIES) {
                if (redirectionRepository.count() >= OriginGenerator.MAX_POSSIBILITIES) {
                    throw new RedirectionGenerationException("Reached maximum number of possible redirections");
                }
                throw new RedirectionGenerationException("Generation exceeded maximum of " + MAX_RETRIES + " retries");
            }
        }
        while (redirectionRepository.findByOrigin(origin).isPresent());

        return redirectionRepository.save(new Redirection(origin, target));
    }

    public Redirection register(String target) {
        return register(target, null);
    }

    public Redirection findRedirection(String code) {
        return redirectionRepository.findByOrigin(code).orElse(null);
    }

    public void removeExpiredRedirections() {
        redirectionRepository.deleteByExpirationBefore(LocalDateTime.now());
    }
}
