package com.example.exchange.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

@Log4j2
@Controller("/api")
public class RateController {
    private final RestTemplate restTemplate;

    public RateController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/rate")
    public String rate() {

        return "";
    }
}
