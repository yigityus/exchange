package com.example.exchange.repository;

import com.example.exchange.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
    List<Conversion> findByTransactionId(String transactionId);
    List<Conversion> findByCreatedDateBetween(Instant start, Instant end);
}
