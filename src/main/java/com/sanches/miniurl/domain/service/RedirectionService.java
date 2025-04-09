package com.sanches.miniurl.domain.service;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class RedirectionService {
    private final RedirectionRepository redirectionRepository;

    public boolean redirectionExists(String origin) {
        return redirectionRepository.findByOrigin(origin).isPresent();
    }

    public Redirection register(String target) {
        /* TODO:
            1) remove hardcoded host
            2) add strategy to shortened link generation
            3) validate conflicts
        */
        Optional<Redirection> maybeRedirection = redirectionRepository.findByTarget(target);
        if (maybeRedirection.isPresent()) {
            return maybeRedirection.get();
        }

        Redirection redirection = new Redirection("http://localhost:8080/" + redirectionRepository.count(), target);
        return redirectionRepository.save(redirection);
    }

    public Redirection findRedirection(String origin) {
        return redirectionRepository.findByOrigin(origin).orElse(null);
    }
}
