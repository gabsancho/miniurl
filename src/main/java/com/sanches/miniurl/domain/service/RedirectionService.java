package com.sanches.miniurl.domain.service;

import com.sanches.miniurl.domain.exception.RedirectionGenerationException;
import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class RedirectionService {
    private static final int DEFAULT_SIZE = 6;
    private static final short MAX_RETRIES = 10;
    private final RedirectionRepository redirectionRepository;

    public Redirection register(String target, LocalDateTime expiration) {
        Redirection redirection = redirectionRepository.findByTarget(target).orElse(null);
        if (redirection != null) {
            // to preserve the immutability of objects, remove the old redirection and create a new one
            if (redirection.isExpired())
                redirectionRepository.deleteByOrigin(redirection.origin());
            else
                return redirection;
        }

        short i = 0;
        String origin;
        do {
            origin = OriginGenerator.generate(DEFAULT_SIZE);
            if (i++ > MAX_RETRIES) {
                throw new RedirectionGenerationException("Generation exceeded maximum of " + MAX_RETRIES + " retries");
            }
        }
        while (redirectionRepository.findByOrigin(origin).isPresent());

        return redirectionRepository.save(new Redirection(origin, target, expiration));
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
