package com.example.demo.service;

import com.example.demo.dto.SearchResult;
import com.example.demo.entity.Ticker;
import com.example.demo.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TickerService {

    @Autowired
    private TickerRepository tickerRepository;

    public List<SearchResult> search(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return tickerRepository.findAll()
                    .stream()
                    .map(ticker -> new SearchResult(ticker.getName()))
                    .collect(Collectors.toList());
        }
        return tickerRepository.findByNameContainingIgnoreCase(keyword)
                .stream()
                .map(ticker -> new SearchResult(ticker.getName()))
                .collect(Collectors.toList());
    }

    public List<Ticker> findAll() {
        return tickerRepository.findAll();
    }

    public Optional<Ticker> findById(Long id) {
        return tickerRepository.findById(id);
    }

    public Ticker save(Ticker ticker) {
        return tickerRepository.save(ticker);
    }

    public void deleteById(Long id) {
        tickerRepository.deleteById(id);
    }
}