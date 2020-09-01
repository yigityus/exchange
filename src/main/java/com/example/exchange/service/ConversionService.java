package com.example.exchange.service;

import com.example.exchange.model.Conversion;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

public interface ConversionService {
    void save(Conversion conversion);
}
