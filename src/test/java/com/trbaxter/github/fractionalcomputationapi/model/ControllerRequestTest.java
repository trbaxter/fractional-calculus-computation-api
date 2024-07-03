package com.trbaxter.github.fractionalcomputationapi.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/** ControllerRequestTest tests the validation and behavior of the ControllerRequest class. */
@ExtendWith(SpringExtension.class)
class ControllerRequestTest {

  private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private static final Validator validator = factory.getValidator();

  /**
   * Provides valid ControllerRequest instances for parameterized tests.
   *
   * @return a stream of valid ControllerRequest arguments.
   */
  private static Stream<Arguments> provideValidRequests() {
    return Stream.of(
        Arguments.of("3x^2 + 2x + 1", 1.5, 1),
        Arguments.of("x + 1", 2.0, 3),
        Arguments.of("5x^2 - 3x + 2", 0.5, 6),
        Arguments.of("2x^3 + 3x^2 + x + 1", 1.0, 15));
  }

  /**
   * Provides invalid ControllerRequest instances for parameterized tests.
   *
   * @return a stream of invalid ControllerRequest arguments.
   */
  private static Stream<Arguments> provideInvalidRequests() {
    return Stream.of(
        Arguments.of(null, 0.5, 1, "Polynomial expression cannot be null"),
        Arguments.of("", 0.5, 1, "Polynomial expression cannot be blank"),
        Arguments.of("x + 1", Double.NaN, 1, "Order cannot be NaN"));
  }

  /**
   * Tests the setters and getters of the ControllerRequest class with valid inputs.
   *
   * @param polynomialExpression the polynomial expression as a string.
   * @param order the order of the operation.
   */
  @ParameterizedTest
  @MethodSource("provideValidRequests")
  void testControllerRequestSettersAndGetters(
      String polynomialExpression, double order, int precision) {
    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(order);
    request.setPrecision(precision);

    assertEquals(
        polynomialExpression,
        request.getPolynomialExpression(),
        "Polynomial expression should be set correctly");
    assertEquals(
        order, request.getOrder(), request.getPrecision(), "Order should be set correctly");
  }

  /** Tests the ControllerRequest class with an empty polynomial expression. */
  @Test
  void testControllerRequestEmptyPolynomialExpression() {
    ControllerRequest request = new ControllerRequest();
    String polynomialExpression = "";
    double order = 2.0;
    int precision = 1;

    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(order);
    request.setPrecision(precision);

    assertEquals(
        polynomialExpression,
        request.getPolynomialExpression(),
        "Polynomial expression should handle empty string");
    assertEquals(
        order, request.getOrder(), request.getPrecision(), "Order should handle normal values");
  }

  /** Tests the ControllerRequest class with a negative order value. */
  @Test
  void testControllerRequestNegativeOrder() {
    ControllerRequest request = new ControllerRequest();
    String polynomialExpression = "1.0x^2 + 2.0x + 3.0";
    double order = -1.0;
    int precision = 1;

    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(order);
    request.setPrecision(precision);

    assertEquals(
        polynomialExpression,
        request.getPolynomialExpression(),
        "Polynomial expression should handle normal values");
    assertEquals(
        order, request.getOrder(), request.getPrecision(), "Order should handle negative values");
  }

  /** Tests the ControllerRequest class with large polynomial expression and order values. */
  @Test
  void testControllerRequestLargeValues() {
    ControllerRequest request = new ControllerRequest();
    String polynomialExpression = "1.0x^10 + 2.0x^5 + 3.0";
    double order = Double.MAX_VALUE;
    int precision = 1;

    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(order);
    request.setPrecision(precision);

    assertEquals(
        polynomialExpression,
        request.getPolynomialExpression(),
        "Polynomial expression should handle large values");
    assertEquals(
        order, request.getOrder(), request.getPrecision(), "Order should handle large values");
  }

  /**
   * Tests the validation of invalid ControllerRequest instances.
   *
   * @param polynomialExpression the polynomial expression as a string.
   * @param order the order of the operation.
   * @param expectedMessage the expected validation error message.
   */
  @ParameterizedTest
  @MethodSource("provideInvalidRequests")
  void testInvalidControllerRequests(
      String polynomialExpression, double order, int precision, String expectedMessage) {
    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(order);
    request.setPrecision(precision);

    Set<ConstraintViolation<ControllerRequest>> violations = validator.validate(request);
    assertFalse(violations.isEmpty());
    assertTrue(violations.stream().anyMatch(v -> v.getMessage().contains(expectedMessage)));
  }
}
