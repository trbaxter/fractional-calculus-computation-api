package com.trbaxter.github.fractionalcomputationapi.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

public class FractionalCalculusServiceTest {

  private TestFractionalCalculusService service;

  @BeforeEach
  public void setUp() {
    service = new TestFractionalCalculusService();
  }

  @Test
  public void testParseExpressionThrowsBadRequestException() {
    String invalidPolynomial = "3*x^2 + 2x + 1@"; // Invalid character '@'

    try (MockedStatic<ExpressionParser> mockedParser = Mockito.mockStatic(ExpressionParser.class)) {
      mockedParser
          .when(() -> ExpressionParser.parse(invalidPolynomial))
          .thenThrow(new IllegalArgumentException("Invalid polynomial expression"));

      assertThrows(BadRequestException.class, () -> service.parseExpression(invalidPolynomial));
    }
  }
}
