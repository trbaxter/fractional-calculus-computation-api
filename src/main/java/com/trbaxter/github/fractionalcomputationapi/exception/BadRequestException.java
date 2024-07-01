package com.trbaxter.github.fractionalcomputationapi.exception;

public class BadRequestException extends RuntimeException {
  public BadRequestException(String message) {
    super(message);
  }
}
