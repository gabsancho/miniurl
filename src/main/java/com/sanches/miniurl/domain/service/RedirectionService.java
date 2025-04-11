package com.sanches.miniurl.domain.service;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RedirectionService {
    private static final int DEFAULT_SIZE = 6;
    private final RedirectionRepository redirectionRepository;

    public Redirection register(String target) {
        Optional<Redirection> maybeRedirection = redirectionRepository.findByTarget(target);
        if (maybeRedirection.isPresent()) {
            return maybeRedirection.get();
        }

        String origin;
        do {
            origin = OriginGenerator.generate(DEFAULT_SIZE);
        }
        while (redirectionRepository.findByOrigin(origin).isPresent());

        return redirectionRepository.save(new Redirection(origin, target));
    }

    public Redirection findRedirection(String code) {
        return redirectionRepository.findByOrigin(code).orElse(null);
    }
}
