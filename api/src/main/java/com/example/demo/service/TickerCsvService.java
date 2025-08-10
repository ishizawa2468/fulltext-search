package com.example.demo.service;

import com.example.demo.entity.Ticker;
import com.example.demo.repository.TickerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TickerCsvService {
    private final TickerRepository repository;

    @Value("${ticker.csv.path}")
    private String csvFilePath;

    public String getCsvFilePath() {
        return csvFilePath;
    }

    public void importCsv() throws Exception {
        ClassPathResource resource = new ClassPathResource(getCsvFilePath());
        try (InputStream csvStream = resource.getInputStream();
             BufferedReader reader = new BufferedReader(new InputStreamReader(csvStream))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] cols = line.split(",", -1);
                Ticker ticker = new Ticker();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
                ticker.setUpdatedAt(LocalDate.parse(cols[0], formatter));
                ticker.setCode(cols[1]);
                ticker.setName(cols[2]);
                ticker.setMarket(cols[3]);
                ticker.setSectorCode(cols[4].isEmpty() ? null : cols[4]);
                ticker.setSector(cols[5]);
                ticker.setSubsectorCode(cols[6].isEmpty() ? null : cols[6]);
                ticker.setSubsector(cols[7]);
                ticker.setScaleCode(cols[8].isEmpty() ? null : cols[8]);
                ticker.setIndexName(cols[9]);
                System.out.println("Importing ticker: %s".formatted(ticker.getCode()));
                repository.save(ticker);
            }
        }
    }
}