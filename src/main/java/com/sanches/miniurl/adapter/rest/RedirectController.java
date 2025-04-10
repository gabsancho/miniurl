package com.sanches.miniurl.adapter.rest;

import com.sanches.miniurl.adapter.model.RegisterInput;
import com.sanches.miniurl.application.GetRedirection;
import lombok.RequiredArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
public class RedirectController {
    private static final Log log = LogFactory.getLog(RedirectController.class);
    private final GetRedirection getRedirection;

    @GetMapping("/1")
    public ResponseEntity<String> option1() {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.LOCATION, "https://google.com");
        return new ResponseEntity<>(headers, HttpStatus.TEMPORARY_REDIRECT);
    }

    @GetMapping("/2")
    public String option2() {
        return "redirect:https://google.com";
    }

    @GetMapping("/3")
    public RedirectView option3() {
        RedirectView redirect = new RedirectView("https://google.com");
        redirect.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirect;
    }

    @GetMapping("/re/{code}")
    public RedirectView option4(@PathVariable String code) {
        String target = getRedirection.execute(code);
        RedirectView redirect = new RedirectView(target);
        redirect.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        return redirect;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String register(@RequestBody RegisterInput input) {
        log.info("Registering redirect to " + input.target());
        return "";
    }

    @PostMapping(value = "/register2", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String register(@RequestBody String target) {
        log.info("Registering redirect to " + target);
        return "";
    }
}
