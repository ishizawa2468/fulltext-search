package com.example.demo.controller;

import com.example.demo.service.TickerCsvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticker")
public class TickerCsvController {

    @Autowired
    private TickerCsvService tickerCsvService;

    @PostMapping("/seed")
    public String uploadCsv() {
        try {
            tickerCsvService.importCsv();
            return "CSV登録完了";
        } catch (Exception e) {
            return "登録失敗: " + e.getMessage();
        }
    }
}