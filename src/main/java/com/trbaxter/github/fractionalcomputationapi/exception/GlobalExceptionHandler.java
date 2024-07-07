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

/**
 * GlobalExceptionHandler is a centralized exception handler for the application. It handles various
 * types of exceptions and returns appropriate responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

  private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

  private static final String VALIDATION_ERROR = "Validation error";
  private static final String BAD_REQUEST = "Bad Request: ";
  private static final String MALFORMED_JSON = "Malformed JSON request body";
  private static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
  private static final String UNHANDLED_EXCEPTION = "Unhandled exception";

  /**
   * Handles validation exceptions.
   *
   * @param ex the MethodArgumentNotValidException instance.
   * @return a ResponseEntity with the validation error message.
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Result> handleValidationExceptions(MethodArgumentNotValidException ex) {
    String errorMessage = extractErrorMessage(ex);
    logWarning(VALIDATION_ERROR, errorMessage, ex);
    return buildBadRequestResponse("Validation Error: " + errorMessage);
  }

  /**
   * Handles exceptions when the HTTP message is not readable.
   *
   * @param ex the HttpMessageNotReadableException instance.
   * @return a ResponseEntity with the malformed JSON error message.
   */
  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<Result> handleHttpMessageNotReadableException(
      HttpMessageNotReadableException ex) {
    logWarning(MALFORMED_JSON, ex.getMessage(), ex);
    return buildBadRequestResponse(MALFORMED_JSON);
  }

  /**
   * Handles exceptions for undefined gamma function input.
   *
   * @param ex the UndefinedGammaFunctionException instance.
   * @return a ResponseEntity with the bad request message.
   */
  @ExceptionHandler(UndefinedGammaFunctionException.class)
  public ResponseEntity<Result> handleUndefinedGammaFunctionException(
      UndefinedGammaFunctionException ex) {
    logWarning("Undefined gamma function input", ex.getMessage(), ex);
    return buildBadRequestResponse(ex.getMessage());
  }

  /**
   * Handles bad request exceptions.
   *
   * @param ex the BadRequestException instance.
   * @return a ResponseEntity with the bad request message.
   */
  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Result> handleBadRequestException(BadRequestException ex) {
    logWarning("Bad request", ex.getMessage(), ex);
    return buildBadRequestResponse(ex.getMessage());
  }

  /**
   * Handles all other exceptions.
   *
   * @param ex the Exception instance.
   * @return a ResponseEntity with the internal server error message.
   */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<Result> handleException(Exception ex) {
    logError(ex);
    return buildInternalServerErrorResponse();
  }

  /**
   * Extracts error message from the MethodArgumentNotValidException.
   *
   * @param ex the MethodArgumentNotValidException instance.
   * @return the extracted error message.
   */
  private String extractErrorMessage(MethodArgumentNotValidException ex) {
    return ex.getBindingResult().getAllErrors().stream()
        .map(error -> Optional.ofNullable(error.getDefaultMessage()).orElse(VALIDATION_ERROR))
        .findFirst()
        .orElse(VALIDATION_ERROR);
  }

  /**
   * Logs a warning message.
   *
   * @param context the context of the warning.
   * @param message the warning message.
   * @param ex the exception instance.
   */
  private void logWarning(String context, String message, Exception ex) {
    logger.warn("{}: {}", context, message, ex);
  }

  /**
   * Logs an error message.
   *
   * @param ex the exception instance.
   */
  private void logError(Exception ex) {
    logger.error("{}: ", UNHANDLED_EXCEPTION, ex);
  }

  private ResponseEntity<Result> buildBadRequestResponse(String message) {
    return buildResponseEntity(BAD_REQUEST + message, HttpStatus.BAD_REQUEST);
  }

  private ResponseEntity<Result> buildInternalServerErrorResponse() {
    return buildResponseEntity(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private ResponseEntity<Result> buildResponseEntity(String message, HttpStatus status) {
    return new ResponseEntity<>(new Result(message), status);
  }
}
