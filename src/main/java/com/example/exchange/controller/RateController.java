package com.example.exchange.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Log4j2
@Controller("/api")
public class RateController {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public RateController(RestTemplate restTemplate, @Value("exchange.api.url") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @GetMapping("/rate")
    public String rate(@RequestParam String base, @RequestParam String symbol) {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(apiUrl, String.class, Map.of("base", "USD", "symbol", "TRY"));
        return "";
    }
}
