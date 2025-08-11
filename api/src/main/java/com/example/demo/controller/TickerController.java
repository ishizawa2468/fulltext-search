package com.example.demo.controller;

import com.example.demo.dto.SearchResult;
import com.example.demo.entity.Ticker;
import com.example.demo.service.TickerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickers")
public class TickerController {

    @Autowired
    private TickerService tickerService;

    @GetMapping("/search")
    public List<SearchResult> search(@RequestParam String query) {
        return tickerService.search(query);
    }

    @GetMapping
    public List<Ticker> getAll() {
        return tickerService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Ticker> getById(@PathVariable("id") Long id) {
        return tickerService.findById(id);
    }

    @PostMapping
    public Ticker create(@RequestBody Ticker ticker) {
        return tickerService.save(ticker);
    }

    @PutMapping("/{id}")
    public Ticker update(@PathVariable("id") Long id, @RequestBody Ticker ticker) {
        ticker.setId(id);
        return tickerService.save(ticker);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        tickerService.deleteById(id);
    }
}