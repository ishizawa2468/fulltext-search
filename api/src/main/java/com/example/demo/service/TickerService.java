package com.example.demo.service;

import com.example.demo.entity.Ticker;
import com.example.demo.repository.TickerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TickerService {

    @Autowired
    private TickerRepository tickerRepository;

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