package com.trbaxter.github.fractionalcomputationapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
public class RiemannLiouvilleDerivativeServiceTest {

	@Autowired
	private RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

	@Configuration
	static class Config {
		@Bean
		public RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService() {
			return new RiemannLiouvilleDerivativeService();
		}
	}

	@Test
	public void testDerivative_SimplePolynomial() {
		double[] coefficients = {3.0, 2.0, 1.0};
		double alpha = 0.5;

		try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3))).thenReturn(new BigDecimal("2.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5))).thenReturn(new BigDecimal("1.329"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2))).thenReturn(new BigDecimal("1.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5))).thenReturn(new BigDecimal("0.886"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1))).thenReturn(new BigDecimal("1.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5))).thenReturn(new BigDecimal("1.77245385091"));

			String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
			String expected = "4.515x^1.5 + 2.257x^0.5 + 0.564x^-0.5";

			assertEquals(expected, result);
		}
	}

	@Test
	public void testComputeDerivative_NegativeCoefficients() {
		double[] coefficients = {-3.0, -2.0, -1.0};
		double alpha = 0.5;

		try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3))).thenReturn(new BigDecimal("2.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5))).thenReturn(new BigDecimal("1.329"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2))).thenReturn(new BigDecimal("1.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5))).thenReturn(new BigDecimal("0.886"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1))).thenReturn(new BigDecimal("1.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5))).thenReturn(new BigDecimal("1.77245385091"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(-0.5)))
					.thenReturn(new BigDecimal("-3.54490770181"));

			String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
			String expected = "- 4.515x^1.5 - 2.257x^0.5 - 0.564x^-0.5";

			assertEquals(expected, result);
		}
	}

	@Test
	public void testComputeDerivative_ConstantTerm() {
		double[] coefficients = {3.0};
		double alpha = 0.5;

		try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1))).thenReturn(new BigDecimal("1.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5))).thenReturn(new BigDecimal("1.77245385091"));

			String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
			String expected = "1.693x^-0.5";

			assertEquals(expected, result);
		}
	}

	@Test
	public void testComputeDerivative_ZeroPolynomial() {
		double[] coefficients = {0.0, 0.0, 0.0};
		double alpha = 0.5;

		String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
		String expected = "";

		assertEquals(expected, result);
	}

	@Test
	public void testComputeDerivative_DifferentAlpha() {
		double[] coefficients = {3.0, 0.0, 1.0};
		double alpha = 1.5;

		try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3))).thenReturn(new BigDecimal("2.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5))).thenReturn(new BigDecimal("0.886"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1))).thenReturn(new BigDecimal("1.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5))).thenReturn(new BigDecimal("1.77245385091"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(-0.5)))
					.thenReturn(new BigDecimal("-3.54490770181"));

			String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
			String expected = "6.772x^0.5 - 0.282x^-1.5";

			assertEquals(expected, result);
		}
	}

	@Test
	public void testComputeDerivative_ThrowsArithmeticException() {
		double[] coefficients = {3.0, 2.0, 1.0};
		double alpha = 0.5;

		try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3))).thenReturn(new BigDecimal("2.0"));
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
					.thenThrow(new ArithmeticException("Division by zero")); // This will trigger the
			// ArithmeticException

			String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
			String expected = "";

			assertEquals(expected, result);
		}
	}

	@Test
	public void testComputeDerivative_ThrowsGenericException() {
		double[] coefficients = {1.0};
		double alpha = 0.5;

		try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
			utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
					.thenThrow(new RuntimeException("Unexpected error"));

			String result = riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha);
			String expected = "";

			assertEquals(expected, result);
		}
	}
}
