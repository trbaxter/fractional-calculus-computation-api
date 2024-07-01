package com.trbaxter.github.fractionalcomputationapi.exception;

import com.trbaxter.github.fractionalcomputationapi.model.Result;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Result> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errorMessage =
        ex.getBindingResult().getAllErrors().stream()
            .map(error -> Optional.ofNullable(error.getDefaultMessage()).orElse("Validation error"))
            .findFirst()
            .orElse("Validation error");

    logger.warn("Validation error: {}", errorMessage);

    return new ResponseEntity<>(
        new Result("Validation Error: " + errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Result> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    String errorMessage = "Malformed JSON request body";
    logger.warn("Malformed JSON request body: {}", ex.getMessage());
    return new ResponseEntity<>(new Result("Bad Request: " + errorMessage), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Result> handleBadRequestException(BadRequestException ex) {
    logger.warn("Bad request: {}", ex.getMessage());
    return new ResponseEntity<>(
        new Result("Bad Request: " + ex.getMessage()), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result> handleException(Exception e) {
    logger.error("Unhandled exception: ", e);
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new Result("Internal Server Error: " + e.getMessage()));
  }
}
