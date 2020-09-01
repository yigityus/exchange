package com.example.exchange.service;

import com.example.exchange.model.Conversion;
import com.example.exchange.repository.ConversionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
