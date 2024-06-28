package com.trbaxter.github.fractionalcomputationapi.exception;

import com.trbaxter.github.fractionalcomputationapi.model.ComputationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ComputationResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    String errorMessage =
        ex.getBindingResult().getAllErrors().stream()
            .map(error -> ((FieldError) error).getField() + ": " + error.getDefaultMessage())
            .findFirst()
            .orElse("Validation error");

    logger.warn("Validation error: {}", errorMessage);

    return new ResponseEntity<>(
        new ComputationResponse("Validation Error: " + errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ComputationResponse> handleException(Exception e) {
    logger.error("Unhandled exception: ", e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ComputationResponse("Internal Server Error: " + e.getMessage()));
  }
}
