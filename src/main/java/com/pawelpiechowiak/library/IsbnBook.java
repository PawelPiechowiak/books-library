package com.pawelpiechowiak.library;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsbnBook {
    @GetMapping("/test")
    public String test() {
        return "Test";
    }
}
