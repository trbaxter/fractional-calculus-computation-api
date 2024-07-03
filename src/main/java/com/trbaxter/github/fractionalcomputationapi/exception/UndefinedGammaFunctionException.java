package com.trbaxter.github.fractionalcomputationapi.exception;

/** Custom exception for undefined gamma function values. */
public class UndefinedGammaFunctionException extends RuntimeException {

  public UndefinedGammaFunctionException(String message) {
    super(message);
  }
}
