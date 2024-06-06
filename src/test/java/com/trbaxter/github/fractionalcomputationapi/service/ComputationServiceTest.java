package com.trbaxter.github.fractionalcomputationapi.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ComputationServiceTest {

	private final ComputationService computationService = new ComputationService();

	/* -------------------------------------------- */
	/*                 Exceptions                   */
	/* -------------------------------------------- */

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
	public void testDerivative_nonIntegerOrderError() {
		String expression = "2x^2+1";
		double order = 1.6;

		assertThrows(IllegalArgumentException.class, () -> computationService.derivative(expression, order));
	}


	/* -------------------------------------------- */
	/*                 Polynomials                  */
	/* -------------------------------------------- */
	@Test
	public void testDerivative_ZeroOrder() {
		String expression = "2x^2+5";
		double order = 0;
		String response = computationService.derivative(expression, order);
		assertEquals("2x^2+5", response);
	}

	@Test
	public void testDerivative_parseExpressionQuadratic() {
		String expression = "2x^2+1";
		double order = 1;

		String response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_parseExpressionBinomial() {
		String expression = "2x+1";
		double order = 1;

		String response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_parseExpressionQuadratic_2ndOrder() {
		String expression = "2x^2+1";
		double order = 2;

		String response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_parseExpressionQuadratic_3rdOrder() {
		String expression = "2x^2+1";
		double order = 3;

		String response = computationService.derivative(expression, order);
		assertNotNull(response);
	}

	@Test
	public void testDerivative_longerExpression_order2() {
		String expression = "4x^2+4x+1";
		double order = 2;
		String response = computationService.derivative(expression, order);
		assertEquals("8", response);
	}

	@Test
	public void testDerivative_longerExpression_v2() {
		String expression = "4x^3+4x^2+x+1";
		double order = 1;
		String response = computationService.derivative(expression, order);
		assertEquals("12x^2 + 8x + 1", response);
	}

	@Test
	public void testDerivative_longerExpression_v3() {
		String expression = "4x^3+4x^2+x+1";
		double order = 2;
		String response = computationService.derivative(expression, order);
		assertEquals("24x + 8", response);
	}

	@Test
	public void testDerivative_longerExpression_v4() {
		String expression = "4x^3+4x^2+x+1";
		double order = 3;
		String response = computationService.derivative(expression, order);
		assertEquals("24", response);
	}
}

