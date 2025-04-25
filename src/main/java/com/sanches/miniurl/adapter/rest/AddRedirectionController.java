package com.sanches.miniurl.adapter.rest;

import com.sanches.miniurl.application.RegisterRedirection;
import com.sanches.miniurl.domain.exception.RedirectionGenerationException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AddRedirectionController {
    private static final Log log = LogFactory.getLog(AddRedirectionController.class);
    private final RegisterRedirection registerRedirection;

    public record RegisterInput(String target, LocalDateTime expiration) {
    }

    private String registerBase(String target, LocalDateTime expiration) {
        log.info("Registering redirect to " + target + (expiration != null ? " at " + expiration : ""));
        return registerRedirection.registerRedirection(target, expiration).origin();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody RegisterInput input) {
        return registerBase(input.target(), input.expiration());
    }

    @PostMapping(value = "/register", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String register(@RequestBody String target) {
        return registerBase(target, null);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RedirectionGenerationException.class)
    public void handleRedirectionGenerationException(RedirectionGenerationException e) {
        log.error(e.getMessage(), e);
    }
}
