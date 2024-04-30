package com.tyler.baxter.fractionalcomputation.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class ServiceTest {

  ComputationService computationService = new ComputationService();

  @Test
  public void testDerivative_200() {
    String expression = "2x^2+5";
    double order = 1;

    ResponseEntity<String> result = computationService.derivative(expression, order);
    assertEquals(HttpStatus.OK, result.getStatusCode());
    assertEquals("4.0x", result.getBody()); // UTF-8 representation of "+" is "%2B"
  }
}
