package com.sanches.miniurl.domain.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RedirectionTest {
    @Test
    public void canCreateRedirection() {
        try {
            new Redirection("abcde", "https://example.com");
        } catch (Exception e) {
            Assertions.fail("Should be able to create redirection");
        }
    }

    @Test
    public void cannotCreateRedirectionWithNullOrEmpty() {
        final String[] values = {null, ""};
        final String valid = "abcde";

        for (int i = 0; i < 2; ++i) {
            int fi = i;
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Redirection(values[fi], valid));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Redirection(valid, values[fi]));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Redirection(values[fi], values[fi]));
        }
    }

    @Test
    public void cannotCreateRedirectionWithSameOriginAndTarget() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Redirection("something", "something"));
    }
}
