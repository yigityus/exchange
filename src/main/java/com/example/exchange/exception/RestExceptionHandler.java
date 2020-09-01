package com.example.exchange.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@Log4j2
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

   //other exception handlers

   @ExceptionHandler({InvalidDateFormatException.class, RequestParamMissingException.class})
   protected ResponseEntity<CustomException> handleRequestParamMissing(
           RuntimeException ex) {

       log.error(ex);

       CustomException exception = new CustomException(ex.getMessage());
       return ResponseEntity.of(Optional.of(exception));
   }
}

@Data
@AllArgsConstructor
class CustomException {
    private String error;
}
