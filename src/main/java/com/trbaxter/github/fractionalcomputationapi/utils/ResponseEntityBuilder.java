package com.trbaxter.github.fractionalcomputationapi.utils;

import com.trbaxter.github.fractionalcomputationapi.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityBuilder {

  // Private constructor to prevent instantiation
  private ResponseEntityBuilder() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static ResponseEntity<ErrorResponse> buildBadRequestResponse(String message) {
    return buildResponse(message, "Bad Request", HttpStatus.BAD_REQUEST);
  }

  public static ResponseEntity<ErrorResponse> buildInternalServerErrorResponse() {
    return buildResponse(
        "Internal Server Error", "An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
  }

  private static ResponseEntity<ErrorResponse> buildResponse(
      String message, String details, HttpStatus status) {
    return new ResponseEntity<>(new ErrorResponse(message, details), status);
  }
}
