package com.trbaxter.github.fractionalcomputationapi.validation;

/** ValidationOrder contains nested static classes representing different validation groups. */
public class ValidationOrder {

  private ValidationOrder() {
    // Private constructor to prevent instantiation
  }

  /** First is the first group in the validation sequence. */
  public interface First {}

  /** Second is the second group in the validation sequence. */
  public interface Second {}
}
