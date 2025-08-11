package com.example.demo.service;

import com.example.demo.dto.SearchResult;
import com.example.demo.entity.Ticker;
import com.example.demo.repository.TickerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TickerService {

    @Autowired
    private TickerRepository tickerRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<SearchResult> searchLike(String keyword) {
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

    public List<SearchResult> searchOr(String keyword) {
        return searchByKeywords(keyword, false)
                .stream()
                .map(ticker -> new SearchResult(ticker.getName()))
                .collect(Collectors.toList());
    }

    public List<SearchResult> searchAnd(String keyword) {
        return searchByKeywords(keyword, true)
                .stream()
                .map(ticker -> new SearchResult(ticker.getName()))
                .collect(Collectors.toList());
    }

    public List<Ticker> searchByKeywords(String keyword, boolean isAnd) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Ticker> query = cb.createQuery(Ticker.class);
        Root<Ticker> root = query.from(Ticker.class);

        if (keyword == null || keyword.isBlank()) {
            query.select(root);
            return entityManager.createQuery(query).getResultList();
        }

        String[] keywords = Arrays.stream(keyword.trim().split("\\s+"))
                .filter(k -> !k.isEmpty())
                .toArray(String[]::new);

        List<Predicate> predicates = new ArrayList<>();
        for (String k : keywords) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + k.toLowerCase() + "%"));
        }

        Predicate finalPredicate = isAnd ? cb.and(predicates.toArray(new Predicate[0]))
               : cb.or(predicates.toArray(new Predicate[0]));

        query.where(finalPredicate);

        return entityManager.createQuery(query).getResultList();
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