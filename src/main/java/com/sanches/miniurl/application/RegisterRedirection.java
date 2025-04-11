package com.sanches.miniurl.application;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import com.sanches.miniurl.domain.service.RedirectionService;
import org.springframework.stereotype.Component;

@Component
public class RegisterRedirection {
    private final RedirectionService redirectionService;

    public RegisterRedirection(RedirectionRepository redirectionRepository) {
        this.redirectionService = new RedirectionService(redirectionRepository);
    }

    public Redirection registerRedirection(String target) {
        if (target == null || target.isEmpty())
            throw new IllegalArgumentException("Target URL must not be null or empty");

        return redirectionService.register(target);
    }
}
