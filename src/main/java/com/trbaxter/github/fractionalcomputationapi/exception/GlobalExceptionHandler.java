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
    String errorMessage = extractErrorMessage(ex);
    logWarning("Validation error", errorMessage, ex);
    return buildResponseEntity("Validation Error: " + errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Result> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    String errorMessage = "Malformed JSON request body";
    logWarning("Malformed JSON request body", errorMessage, ex);
    return buildResponseEntity("Bad Request: " + errorMessage, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(UndefinedGammaFunctionException.class)
  public ResponseEntity<Result> handleUndefinedGammaFunctionException(
      UndefinedGammaFunctionException ex) {
    logWarning("Undefined gamma function input", ex.getMessage(), ex);
    return buildResponseEntity("Bad Request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Result> handleBadRequestException(BadRequestException ex) {
    logWarning("Bad request", ex.getMessage(), ex);
    return buildResponseEntity("Bad Request: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result> handleException(Exception ex) {
    logError(ex);
    return buildResponseEntity("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private String extractErrorMessage(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getAllErrors().stream()
        .map(error -> Optional.ofNullable(error.getDefaultMessage()).orElse("Validation error"))
        .findFirst()
        .orElse("Validation error");
  }

  private void logWarning(String context, String message, Exception ex) {
    logger.warn("{}: {}", context, message, ex);
  }

  private void logError(Exception ex) {
    logger.error("{}: ", "Unhandled exception", ex);
  }

  private ResponseEntity<Result> buildResponseEntity(String message, HttpStatus status) {
    return new ResponseEntity<>(new Result(message), status);
  }
}
