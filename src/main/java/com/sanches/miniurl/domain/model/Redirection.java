package com.sanches.miniurl.domain.model;

public record Redirection(String origin, String target) {
    public Redirection {
        if (origin == null || target == null || origin.isEmpty() || target.isEmpty()) {
            throw new IllegalArgumentException("Origin and target should not be null or empty");
        } else if (origin.equals(target)) {
            throw new IllegalArgumentException("Origin and target should not be the same");
        }
    }
}
