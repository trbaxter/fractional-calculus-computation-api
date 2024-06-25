package com.trbaxter.github.fractionalcomputationapi.service.model;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

/** ControllerRequestTest tests the validation and behavior of the ControllerRequest class. */
public class ControllerRequestTest {

  private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private static final Validator validator = factory.getValidator();

  /**
   * Provides valid ControllerRequest instances for parameterized tests.
   *
   * @return a stream of valid ControllerRequest arguments.
   */
  private static Stream<Arguments> provideValidRequests() {
    return Stream.of(
        Arguments.of(new double[] {1.0, 2.0, 3.0}, 1.5),
        Arguments.of(new double[] {}, 2.0),
        Arguments.of(new double[] {1.0, 2.0, 3.0}, -1.0),
        Arguments.of(new double[] {Double.MAX_VALUE, Double.MIN_VALUE}, Double.MAX_VALUE));
  }

  /**
   * Provides invalid ControllerRequest instances for parameterized tests.
   *
   * @return a stream of invalid ControllerRequest arguments.
   */
  private static Stream<Arguments> provideInvalidRequests() {
    return Stream.of(
        Arguments.of(null, 1.5, "Coefficients must not be null"),
        Arguments.of(new double[] {}, 1.5, "At least one coefficient must be provided"),
        Arguments.of(new double[] {1.0, 2.0, 3.0}, -1.0, "Order must be zero or positive"));
  }

  /**
   * Tests the setters and getters of the ControllerRequest class with valid inputs.
   *
   * @param coefficients the coefficients of the polynomial.
   * @param order the order of the operation.
   */
  @ParameterizedTest
  @MethodSource("provideValidRequests")
  public void testControllerRequestSettersAndGetters(double[] coefficients, double order) {
    ControllerRequest request = new ControllerRequest();
    request.setCoefficients(coefficients);
    request.setOrder(order);

    assertArrayEquals(
        coefficients, request.getCoefficients(), "Coefficients should be set correctly");
    assertEquals(order, request.getOrder(), "Order should be set correctly");
  }

  /** Tests the ControllerRequest class with an empty array of coefficients. */
  @Test
  public void testControllerRequestEmptyCoefficients() {
    ControllerRequest request = new ControllerRequest();
    double[] coefficients = {};
    double order = 2.0;

    request.setCoefficients(coefficients);
    request.setOrder(order);

    assertArrayEquals(
        coefficients, request.getCoefficients(), "Coefficients should handle empty array");
    assertEquals(order, request.getOrder(), "Order should handle normal values");
  }

  /** Tests the ControllerRequest class with a negative order value. */
  @Test
  public void testControllerRequestNegativeOrder() {
    ControllerRequest request = new ControllerRequest();
    double[] coefficients = {1.0, 2.0, 3.0};
    double order = -1.0;

    request.setCoefficients(coefficients);
    request.setOrder(order);

    assertArrayEquals(
        coefficients, request.getCoefficients(), "Coefficients should handle normal values");
    assertEquals(order, request.getOrder(), "Order should handle negative values");
  }

  /** Tests the ControllerRequest class with large coefficient and order values. */
  @Test
  public void testControllerRequestLargeValues() {
    ControllerRequest request = new ControllerRequest();
    double[] coefficients = {Double.MAX_VALUE, Double.MIN_VALUE};
    double order = Double.MAX_VALUE;

    request.setCoefficients(coefficients);
    request.setOrder(order);

    assertArrayEquals(
        coefficients, request.getCoefficients(), "Coefficients should handle large values");
    assertEquals(order, request.getOrder(), "Order should handle large values");
  }

  /**
   * Tests the validation of invalid ControllerRequest instances.
   *
   * @param coefficients the coefficients of the polynomial.
   * @param order the order of the operation.
   * @param expectedMessage the expected validation error message.
   */
  @ParameterizedTest
  @MethodSource("provideInvalidRequests")
  public void testInvalidControllerRequests(
      double[] coefficients, double order, String expectedMessage) {
    ControllerRequest request = new ControllerRequest();
    request.setCoefficients(coefficients);
    request.setOrder(order);

    Set<ConstraintViolation<ControllerRequest>> violations = validator.validate(request);
    assertFalse(violations.isEmpty());
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains(expectedMessage)));
  }
}
