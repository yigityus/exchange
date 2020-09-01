package com.example.exchange.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.Optional;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   //other exception handlers

   @ExceptionHandler(RequestParamMissingException.class)
   protected ResponseEntity<CustomException> handleRequestParamMissing(
           RequestParamMissingException ex) {
       CustomException exception = new CustomException(ex.getMessage());
       return ResponseEntity.of(Optional.of(exception));
   }
}

@Data
@AllArgsConstructor
class CustomException {
    private String error;
}
