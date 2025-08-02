package com.sanches.miniurl.domain.service;

import com.sanches.miniurl.domain.model.Redirection;
import com.sanches.miniurl.domain.repository.RedirectionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RedirectionServiceTest {
    private static Redirection EXISTING = new Redirection("123456", "https://hithere.com");


    @Test
    public void findRedirectionByOriginTest() {
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByOrigin(EXISTING.origin())).thenReturn(Optional.of(EXISTING));

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        Redirection returnedRedirection = redirectionService.findRedirection(EXISTING.origin());
        Assertions.assertEquals(EXISTING, returnedRedirection);
    }

    @Test
    public void findRedirectionByOriginNotFoundTest() {
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByOrigin(Mockito.any())).thenReturn(Optional.empty());

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        Redirection returnedRedirection = redirectionService.findRedirection("codeHere");
        Assertions.assertNull(returnedRedirection);
    }

    @Test
    public void registerExistingRedirectionTest() {
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByTarget(EXISTING.target())).thenReturn(Optional.of(EXISTING));

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        Redirection returnedRedirection = redirectionService.register(EXISTING.target());
        Assertions.assertEquals(EXISTING, returnedRedirection);
        Mockito.verify(redirectionRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void registerExistingRedirectionNotExpiredTest() {
        Redirection r = new Redirection(EXISTING.origin(), EXISTING.target(), LocalDateTime.now().plusDays(1));
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByTarget(r.target())).thenReturn(Optional.of(r));

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        Redirection returnedRedirection = redirectionService.register(r.target());
        Assertions.assertEquals(r, returnedRedirection);
        Mockito.verify(redirectionRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    public void registerNonExistingRedirectionTest() {
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByTarget(EXISTING.target())).thenReturn(Optional.of(EXISTING));
        when(redirectionRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        String url = "https://hithere2.com";
        Redirection returnedRedirection = redirectionService.register(url);

        Assertions.assertNotNull(returnedRedirection);
        Assertions.assertEquals(url, returnedRedirection.target());
        Assertions.assertNotNull(returnedRedirection.origin());
        Assertions.assertNull(returnedRedirection.expiration());
        Mockito.verify(redirectionRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void registerNonExistingRedirectionWithExpirationTest() {
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByTarget(EXISTING.target())).thenReturn(Optional.of(EXISTING));
        when(redirectionRepository.save(Mockito.any())).thenAnswer(invocation -> invocation.getArgument(0));

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        String url = "https://hithere2.com";
        LocalDateTime expiration = LocalDateTime.now().plusDays(3);
        Redirection returnedRedirection = redirectionService.register(url, expiration);

        Assertions.assertNotNull(returnedRedirection);
        Assertions.assertEquals(url, returnedRedirection.target());
        Assertions.assertNotNull(returnedRedirection.origin());
        Assertions.assertEquals(expiration, returnedRedirection.expiration());
        Mockito.verify(redirectionRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void registerExistingExpiredRedirectionTest() {
        Redirection r = new Redirection(EXISTING.origin(), EXISTING.target(), LocalDateTime.now().plusSeconds(1));
        RedirectionRepository redirectionRepository = mock(RedirectionRepository.class);
        when(redirectionRepository.findByTarget(r.target())).thenReturn(Optional.of(r));

        RedirectionService redirectionService = new RedirectionService(redirectionRepository);

        String url = "https://hithere2.com";
        LocalDateTime expiration = LocalDateTime.now().plusDays(3);
        Redirection returnedRedirection = redirectionService.register(url, expiration);

        Assertions.assertNotNull(returnedRedirection);
        Assertions.assertEquals(url, returnedRedirection.target());
        Assertions.assertNotNull(returnedRedirection.origin());
        Assertions.assertEquals(expiration, returnedRedirection.expiration());
        Mockito.verify(redirectionRepository, Mockito.times(1)).save(Mockito.any());
    }
}
