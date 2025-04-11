package com.sanches.miniurl.adapter.rest;

import com.sanches.miniurl.adapter.model.RegisterInput;
import com.sanches.miniurl.application.GetRedirection;
import com.sanches.miniurl.application.RegisterRedirection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private static final Log log = LogFactory.getLog(RedirectController.class);
    private final GetRedirection getRedirection;
    private final RegisterRedirection registerRedirection;

    @GetMapping("/re/{code}")
    public ResponseEntity<?> option4(@PathVariable String code) {
        try {
            String target = getRedirection.execute(code);
            return ResponseEntity
                    .status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HttpHeaders.LOCATION, target)
                    .build();
        } catch (RuntimeException ignored) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
        }
    }

    private String registerBase(String target) {
        log.info("Registering redirect to " + target);
        return registerRedirection.registerRedirection(target).target();
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody RegisterInput input) {
        return registerBase(input.target());
    }

    @PostMapping(value = "/register", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String register(@RequestBody String target) {
        return registerBase(target);
    }
}
