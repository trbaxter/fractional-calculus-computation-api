package com.tyler.baxter.fractionalcomputation.service;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

public class ComputationServiceTest {

	private final ComputationService computationService = new ComputationService();

	@Test
	public void testDerivative_NullExpression() {
		double order = 1;
		assertThrows(IllegalArgumentException.class, () -> computationService.derivative(null, order));
	}

	@Test
	public void testDerivative_EmptyExpression() {
		double order = 1;
		assertThrows(IllegalArgumentException.class, () -> computationService.derivative("", order));
	}

	@Test
	public void testDerivative_NegativeOrder() {
		String expression = "2x^2+5";
		double order = -1;
		assertThrows(IllegalArgumentException.class, () -> computationService.derivative(expression, order));
	}

	@Test
	public void testDerivative_ZeroOrder() {
		String expression = "2x^2+5";
		double order = 0;
		ResponseEntity<String> response = computationService.derivative(expression, order);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("2x^2+5", response.getBody());
	}

	@Test
	public void testDerivative_parseExpressionQuadratic() {
		String expression = "2x^2+1";
		double order = 1;

		ResponseEntity<String> response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_parseExpressionBinomial() {
		String expression = "2x+1";
		double order = 1;

		ResponseEntity<String> response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_parseExpressionQuadratic_2ndOrder() {
		String expression = "2x^2+1";
		double order = 2;

		ResponseEntity<String> response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_parseExpressionQuadratic_3rdOrder() {
		String expression = "2x^2+1";
		double order = 3;

		ResponseEntity<String> response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_nonIntegerOrderError() {
		String expression = "2x^2+1";
		double order = 1.6;

		assertThrows(IllegalArgumentException.class, () -> computationService.derivative(expression, order));
	}

	@Test
	public void testDerivative_longerExpression() {
		String expression = "4x^2+4x+1";
		double order = 2;
		ResponseEntity<String> response = computationService.derivative(expression, order);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("8", response.getBody());
	}
}
