package com.sanches.miniurl.domain.model;

import java.time.LocalDateTime;

public record Redirection(String origin, String target, LocalDateTime expiration) {
    public Redirection {
        if (origin == null || target == null || origin.isEmpty() || target.isEmpty()) {
            throw new IllegalArgumentException("Origin and target should not be null or empty");
        } else if (origin.equals(target)) {
            throw new IllegalArgumentException("Origin and target should not be the same");
        }

        if (expiration != null && expiration.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Expiration should be after now");
        }
    }

    public Redirection(String origin, String target) {
        this(origin, target, null);
    }
}
