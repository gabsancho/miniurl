package com.sanches.miniurl.application;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import com.sanches.miniurl.domain.service.RedirectionService;
import org.springframework.stereotype.Component;

@Component
public class GetRedirection {
    private final RedirectionService redirectionService;

    public GetRedirection(RedirectionRepository redirectionRepository) {
        this.redirectionService = new RedirectionService(redirectionRepository);
    }

    public String execute(String origin) {
        if (origin == null) {
            throw new IllegalArgumentException("Origin cannot be null");
        }

        Redirection redirection = redirectionService.findRedirection(origin);
        if (redirection == null) {
            throw new RuntimeException("Could not find redirection with origin " + origin);
        }

        return redirection.getTarget();
    }
}
