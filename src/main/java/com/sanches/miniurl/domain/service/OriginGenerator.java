package com.sanches.miniurl.domain.service;

import java.security.SecureRandom;

public final class OriginGenerator {
    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final Long MAX_POSSIBILITIES = 56800235584L;

    public static String generate(int size) {
        if (size <= 1) {
            throw new IllegalArgumentException("Size must be greater than 1");
        }
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder(size);

        for (int i = 0; i < size; i++)
            sb.append(CHARS.charAt(random.nextInt(CHARS.length())));

        return sb.toString();
    }
}
