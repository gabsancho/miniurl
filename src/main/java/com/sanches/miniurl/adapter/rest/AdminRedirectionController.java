package com.sanches.miniurl.adapter.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminRedirectionController {
    @DeleteMapping("/{code}")
    public String delete(@PathVariable String code) {
        return "Deleted!";
    }
}
