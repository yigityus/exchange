package com.example.exchange.controller;

import com.example.exchange.configuration.Constants;
import com.example.exchange.exception.InvalidDateFormatException;
import com.example.exchange.exception.RequestParamMissingException;
import com.example.exchange.model.Conversion;
import com.example.exchange.repository.ConversionRepository;
import com.example.exchange.service.ConversionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@Controller
public class ConvertController {

    private final RateController rateController;
    private final ConversionService conversionService;


    public ConvertController(RateController rateController,
                             ConversionService conversionService) {
        this.rateController = rateController;
        this.conversionService = conversionService;
    }

    @GetMapping("/convert")
    public ResponseEntity<Double> convert(@RequestParam double amount,
                                       @RequestParam String source, @RequestParam String target) {
        ResponseEntity<Double> convertRate = rateController.rate(source, target);
        double rate = convertRate.getBody().doubleValue();

        Runnable runnable = () -> saveConversion(amount, source, target, rate);
        runnable.run();

        return ResponseEntity.of(Optional.of(rate * amount));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Conversion>> list(@RequestParam(required = false) String date,
                                       @RequestParam(required = false) String transactionId) {

        if ((Objects.isNull(date) && Objects.isNull(transactionId))) {;
            throw new RequestParamMissingException(Instant.now());
        }

        if (StringUtils.hasText(transactionId)) {
            return ResponseEntity.of(Optional.of(conversionService.findByTransactionId(transactionId)));
        }

        Instant start;
        try {
            LocalDate from = LocalDate.from(Constants.formatter.parse(date));
            start = from.atStartOfDay().toInstant(ZoneOffset.ofHours(3));
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException(Instant.now());
        }

        Instant end = Instant.now().plus(Duration.of(1, ChronoUnit.DAYS));
        return ResponseEntity.of(Optional.of(conversionService.findByCreatedDateBetween(start, end)));
    }

    private void saveConversion(double amount, String source, String target, double rate) {
        Conversion conversion = new Conversion();
        conversion.setCreatedDate(Instant.now());
        conversion.setTransactionId(UUID.randomUUID().toString());
        conversion.setSource(source);
        conversion.setTarget(target);
        conversion.setAmount(amount);
        conversion.setRate(rate);
        conversionService.save(conversion);
    }

}
