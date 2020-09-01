package com.example.exchange.service;

import com.example.exchange.model.Conversion;

import java.time.Instant;
import java.util.List;

public interface ConversionService {
    void save(Conversion conversion);
    List<Conversion> findAll();
    List<Conversion> findByTransactionId(String transactionId);
    List<Conversion> findByCreatedDateBetween(Instant start, Instant end);
}
