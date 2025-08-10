package com.example.demo.controller;

import com.example.demo.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class HealthController {

    @Autowired
    private TickerRepository tickerRepository;

    @GetMapping
    public String healthCheck() {
        try {
            long count = tickerRepository.count();
            return "OK: 銘柄は %d 件\n".formatted(count);
        } catch (Exception e) {
            return "NG: " + e.getMessage();
        }
    }
}