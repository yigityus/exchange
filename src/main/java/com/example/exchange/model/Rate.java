package com.example.exchange.model;

import lombok.Data;

import java.util.Map;

@Data
public class Rate {
    private String base;
    private Map<String, Double> rates;
    private String date;
}
