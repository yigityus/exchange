package com.example.exchange.service;

import com.example.exchange.model.Conversion;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

public interface ConversionService {
    void save(Conversion conversion);
    List<Conversion> findAll();
    List<Conversion> findByTransactionId(String transactionId);
    List<Conversion> findByCreatedDateBetween(Instant start, Instant end);
}
