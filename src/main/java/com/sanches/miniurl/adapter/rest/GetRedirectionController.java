package com.sanches.miniurl.adapter.rest;

import com.sanches.miniurl.application.GetRedirection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetRedirectionController {
    private static final Log log = LogFactory.getLog(GetRedirectionController.class);

    private final GetRedirection getRedirection;

    @GetMapping("/{code}")
    public ResponseEntity<?> get(@PathVariable String code) {
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
}
