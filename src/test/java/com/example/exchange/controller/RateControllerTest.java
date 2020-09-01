package com.example.exchange.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.web.util.UriComponentsBuilder;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RateControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void rateShouldReturnSomething() {
        String url = "http://localhost:" + port + "/rate";
        UriComponentsBuilder componentsBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("base", "USD")
                .queryParam("symbol", "TRY");

        Assertions
                .assertThat(this.restTemplate.getForObject(componentsBuilder.toUriString(), Double.class))
                .isNotNull();

    }
}
