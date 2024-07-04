package com.trbaxter.github.fractionalcomputationapi.exception;

/**
 * Exception thrown when a bad request is made.
 */
public class BadRequestException extends RuntimeException {

  /**
   * Constructs a new BadRequestException with the specified detail message.
   *
   * @param message the detail message
   */
  public BadRequestException(String message) {
    super(message);
  }
}
