package com.trbaxter.github.fractionalcomputationapi.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegralService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaputoIntegralServiceTest {

	@Autowired
	private CaputoIntegralService caputoIntegralService;

	@Test
	public void testComputeIntegral_IntegerOrder() {
		double[] coefficients = {1.0, 2.0, 3.0};
		double alpha = 1.0;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "0.333x^3 + x^2 + 3x + C";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testComputeIntegral_FractionalOrder() {
		double[] coefficients = {1.0, 2.0, 3.0};
		double alpha = 0.5;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "1.662x^2.5 + 2.659x^1.5 + 2.659x^0.5";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testComputeIntegral_ZeroCoefficients() {
		double[] coefficients = {0.0, 0.0, 0.0};
		double alpha = 1.0;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "C";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testComputeIntegral_NegativeCoefficients() {
		double[] coefficients = {-1.0, -2.0, -3.0};
		double alpha = 1.0;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "-0.333x^3 - x^2 - 3x + C";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testComputeIntegral_HighOrder() {
		double[] coefficients = {1.0, 2.0, 3.0, 4.0, 5.0};
		double alpha = 2.0;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "0.033x^6 + 0.1x^5 + 0.25x^4 + 0.667x^3 + 2.5x^2 + Cx + D";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testComputeIntegral_EvenHigherOrder() {
		double[] coefficients = {1.0, 2.0, 3.0, 4.0, 5.0};
		double alpha = 3.0;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "0.005x^7 + 0.017x^6 + 0.05x^5 + 0.167x^4 + 0.833x^3 + 0.5Cx^2 + Dx + E";
		assertEquals(expectedResult, result);
	}

	@Test
	public void testComputeIntegral_SuperOrder() {
		double[] coefficients = {1.0, 2.0, 3.0, 4.0, 5.0};
		double alpha = 4.0;
		String result = caputoIntegralService.evaluateExpression(coefficients, alpha);
		assertNotNull(result);
		String expectedResult = "0.001x^8 + 0.002x^7 + 0.008x^6 + 0.033x^5 + 0.208x^4 + 0.167Cx^3 + 0.5Dx^2 + Ex + F";
		assertEquals(expectedResult, result);
	}
}
