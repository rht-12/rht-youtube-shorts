package com.example.demo.domain.shorts.controller;

import com.example.demo.domain.shorts.service.ShortsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shorts")
public class ShortsController {

    private final ShortsService shortsService;

    @PostMapping
    public ResponseEntity<String> postBy() {
        shortsService.postBy();
        return ResponseEntity.ok("CSV 저장 완료");
    }
}
