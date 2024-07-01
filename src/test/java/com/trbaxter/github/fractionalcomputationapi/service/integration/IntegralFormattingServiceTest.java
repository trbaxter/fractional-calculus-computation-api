package com.trbaxter.github.fractionalcomputationapi.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class IntegralFormattingServiceTest {

  @Test
  void testGetZeroPolynomialResult() {
    IntegralFormattingService service = new IntegralFormattingService();
    String result = service.getZeroPolynomialResult();
    assertEquals("C", result, "The zero polynomial result should be 'C'");
  }
}
