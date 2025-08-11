package com.example.demo.repository;

import com.example.demo.entity.Ticker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TickerRepository extends JpaRepository<Ticker, Long> {
    List<Ticker> findByNameContainingIgnoreCase(String name);
}