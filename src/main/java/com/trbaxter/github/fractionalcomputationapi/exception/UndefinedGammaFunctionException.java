package com.trbaxter.github.fractionalcomputationapi.exception;

/** Exception thrown when a gamma function value is undefined. */
public class UndefinedGammaFunctionException extends RuntimeException {

  /**
   * Constructs a new UndefinedGammaFunctionException with the specified detail message.
   *
   * @param message the detail message
   */
  public UndefinedGammaFunctionException(String message) {
    super(message);
  }
}
