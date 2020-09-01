package com.example.exchange.controller;

import com.example.exchange.model.Rate;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@Log4j2
@Controller
public class RateController {
    private final RestTemplate restTemplate;
    private final String apiUrl;

    public RateController(RestTemplate restTemplate, @Value("${exchange.api.url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
    }

    @GetMapping("/rate")
    public ResponseEntity<Double> rate(@RequestParam String base, @RequestParam String symbol) {
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("base", base)
                .queryParam("symbols", symbol);
        ResponseEntity<Rate> rate = restTemplate
                .getForEntity(componentsBuilder.toUriString(), Rate.class);
        double rateValue = rate.getBody()
                .getRates().entrySet()
                .iterator().next().getValue().doubleValue();
        return ResponseEntity.of(Optional.of(rateValue));
    }
}
