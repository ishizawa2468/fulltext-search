package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "ticker", schema = "stocks")
@Data
public class Ticker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate updatedAt;
    private String code;
    private String name;
    private String market;
    private String sectorCode;
    private String sector;
    private String subsectorCode;
    private String subsector;
    private String scaleCode;
    private String indexName;
}
