package com.example.exchange.service;

import com.example.exchange.model.Conversion;
import com.example.exchange.repository.ConversionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ConversionServiceImpl implements ConversionService {

    private final ConversionRepository conversionRepository;

    public ConversionServiceImpl(ConversionRepository conversionRepository) {
        this.conversionRepository = conversionRepository;
    }

    @Transactional
    @Override
    public void save(Conversion conversion) {
        conversionRepository.save(conversion);
    }

    @Override
    public List<Conversion> findAll() {
        return conversionRepository.findAll();
    }

    @Override
    public List<Conversion> findByTransactionId(String transactionId) {
        return conversionRepository.findByTransactionId(transactionId);
    }

    @Override
    public List<Conversion> findByCreatedDateBetween(Instant start, Instant end) {
        return conversionRepository.findByCreatedDateBetween(start, end);
    }
}
