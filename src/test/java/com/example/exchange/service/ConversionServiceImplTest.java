package com.example.exchange.service;

import com.example.exchange.model.Conversion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.Instant;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConversionServiceImplTest {

    @Autowired
    protected ConversionService conversionService;


    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldSave() {
        Conversion conversion = new Conversion(0L,
                Instant.now(), "TRY", "USD",
                10d, UUID.randomUUID().toString());
        conversionService.save(conversion);
    }

}
