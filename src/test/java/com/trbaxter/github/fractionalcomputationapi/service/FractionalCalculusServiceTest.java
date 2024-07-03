package com.trbaxter.github.fractionalcomputationapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.service.test_services.TestFractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

/** Unit tests for the {@link FractionalCalculusService} interface. */
@ExtendWith(MockitoExtension.class)
class FractionalCalculusServiceTest {

  private TestFractionalCalculusService service;

  /** Sets up the test instance before each test. */
  @BeforeEach
  public void setUp() {
    service = new TestFractionalCalculusService();
  }

  /**
   * Tests that {@link FractionalCalculusService#parseExpression(String)} throws {@link
   * BadRequestException} when given an invalid polynomial expression.
   */
  @Test
  void testParseExpressionThrowsBadRequestException() {
    String invalidPolynomial = "3*x^2 + 2x + 1@";

    try (MockedStatic<ExpressionParser> mockedParser = Mockito.mockStatic(ExpressionParser.class)) {
      mockedParser
          .when(() -> ExpressionParser.parse(invalidPolynomial))
          .thenThrow(new IllegalArgumentException("Invalid polynomial expression"));

      BadRequestException thrown =
          assertThrows(
              BadRequestException.class,
              () -> service.parseExpression(invalidPolynomial),
              "Expected parseExpression to throw BadRequestException, but it didn't");

      assertEquals("Polynomial expression contains invalid characters.", thrown.getMessage());
    }
  }
}
