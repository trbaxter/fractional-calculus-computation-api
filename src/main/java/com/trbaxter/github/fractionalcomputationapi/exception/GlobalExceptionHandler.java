package com.trbaxter.github.fractionalcomputationapi.exception;

import com.trbaxter.github.fractionalcomputationapi.dto.ErrorResponse;
import com.trbaxter.github.fractionalcomputationapi.service.ErrorLoggingService;
import com.trbaxter.github.fractionalcomputationapi.utils.ResponseEntityBuilder;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * GlobalExceptionHandler is a centralized exception handler for the application. It handles various
 * types of exceptions and returns appropriate responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final ErrorLoggingService errorLoggingService;

  @Autowired
  public GlobalExceptionHandler(ErrorLoggingService errorLoggingService) {
    this.errorLoggingService = errorLoggingService;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(
      MethodArgumentNotValidException ex) {
    String errorMessage = extractErrorMessage(ex);
    errorLoggingService.logWarning("Validation Error", errorMessage, ex);
    return ResponseEntityBuilder.buildBadRequestResponse("Validation Error: " + errorMessage);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    errorLoggingService.logWarning("Malformed JSON", ex.getMessage(), ex);
    return ResponseEntityBuilder.buildBadRequestResponse("Malformed JSON request body");
  }

  @ExceptionHandler(UndefinedGammaFunctionException.class)
  public ResponseEntity<ErrorResponse> handleUndefinedGammaFunctionException(
      UndefinedGammaFunctionException ex) {
    errorLoggingService.logWarning("Undefined gamma function input", ex.getMessage(), ex);
    return ResponseEntityBuilder.buildBadRequestResponse(ex.getMessage());
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException ex) {
    errorLoggingService.logWarning("Bad request", ex.getMessage(), ex);
    System.out.println("Debug: Logging bad request"); // Add this line for debugging
    return ResponseEntityBuilder.buildBadRequestResponse(ex.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleException(Exception ex) {
    errorLoggingService.logError("Unhandled exception", ex);
    return ResponseEntityBuilder.buildInternalServerErrorResponse();
  }

  private String extractErrorMessage(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getAllErrors().stream()
        .map(error -> Optional.ofNullable(error.getDefaultMessage()).orElse("Validation error"))
        .findFirst()
        .orElse("Validation error");
  }
}
