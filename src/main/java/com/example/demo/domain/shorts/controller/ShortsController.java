package com.example.demo.domain.shorts.controller;

import com.example.demo.domain.shorts.service.ShortsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/api/v1/shorts")
public class ShortsController {

    private final ShortsService shortsService;

    public ShortsController(ShortsService shortsService) {
        this.shortsService = shortsService;
    }

    @GetMapping("/")
    public void index() {
        shortsService.testCsvSave();
    }
}
