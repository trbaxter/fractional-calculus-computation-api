package com.trbaxter.github.fractionalcomputationapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ComputationServiceTest {

	@Autowired
	private ComputationService computationService;

	@Test
	public void testDerivative() {
		String expression = "3x^2+2x-5";
		double order = 1.0;

		String result = computationService.derivative(expression, order);
		assertEquals("6x + 2", result);
	}

	@Test
	public void testDerivativeOrderZero() {
		String expression = "3x^2+2x-5";
		double order = 0.0;

		String result = computationService.derivative(expression, order);
		assertEquals(expression, result);
	}

	@Test
	public void testDerivativeNullInput() {
		double order = 1.0;

		assertThrows(IllegalArgumentException.class,
		() -> computationService.derivative(null, order));
	}

	@Test
	public void TestDerivativeEmptyInput() {
		String expression = "";
		double order = 1.0;

		assertThrows(IllegalArgumentException.class,
		() -> computationService.derivative(expression, order));
	}

	@Test
	public void testDerivativeNegativeInput() {
		String expression = "3x^2+2x-5";
		double order = -1.0;
		assertThrows(IllegalArgumentException.class,
		() -> computationService.derivative(expression, order));
	}
}

