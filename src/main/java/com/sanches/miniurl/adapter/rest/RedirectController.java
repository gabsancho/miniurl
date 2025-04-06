package com.sanches.miniurl.adapter.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class RedirectController {
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
}
