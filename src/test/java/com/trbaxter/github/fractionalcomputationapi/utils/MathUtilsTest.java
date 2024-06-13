package com.trbaxter.github.fractionalcomputationapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class MathUtilsTest {

	@Test
	public void testGammaFunctionPositiveValues() {
		BigDecimal input = new BigDecimal("5");
		BigDecimal expected = new BigDecimal("24.000000000000000");
		BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
		assertEquals(expected, result, "Gamma function failed for positive values");
	}

	@Test
	public void testGammaFunctionHalfValues() {
		BigDecimal input = new BigDecimal("0.5");
		BigDecimal expected = BigDecimal.valueOf(Math.sqrt(Math.PI)).setScale(15, RoundingMode.HALF_UP);
		BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
		assertEquals(expected, result, "Gamma function failed for half values");
	}

	@Test
	public void testGammaFunctionNegativeValues() {
		BigDecimal input = new BigDecimal("-0.5");
		BigDecimal expected = new BigDecimal("-3.5449077018110318").setScale(15, RoundingMode.HALF_UP);
		BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
		assertEquals(expected, result, "Gamma function failed for negative values");
	}

	@Test
	public void testGammaFunctionNearZero() {
		BigDecimal input = new BigDecimal("0.01");
		BigDecimal expected = new BigDecimal("99.432585119150600").setScale(15, RoundingMode.HALF_UP);
		BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
		assertEquals(expected, result, "Gamma function failed near zero");
	}

	@Test
	public void testGammaFunctionSpecialCase() {
		BigDecimal input = new BigDecimal("1");
		BigDecimal expected = BigDecimal.ONE.setScale(15, RoundingMode.HALF_UP);
		BigDecimal result = MathUtils.gamma(input).setScale(15, RoundingMode.HALF_UP);
		assertEquals(expected, result, "Gamma function failed for special case");
	}

	@Test
	public void testMathUtilsConstructorThrowsException() {
		Constructor<MathUtils> constructor = null;
		try {
			constructor = MathUtils.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			Executable executable = constructor::newInstance;
			InvocationTargetException thrown = assertThrows(InvocationTargetException.class, executable,
					"Constructor did not throw an exception");
			assertInstanceOf(UnsupportedOperationException.class, thrown.getCause(),
					"Unexpected exception type thrown");
			assertEquals("Utility class for math operations", thrown.getCause().getMessage(),
					"Unexpected exception message");
		} catch (Exception e) {
			fail("Unexpected exception: " + e);
		} finally {
			if (constructor != null) {
				constructor.setAccessible(false);
			}
		}
	}

	@Test
	public void testGammaFunctionWithInvalidInput() {
		BigDecimal invalidInput = mock(BigDecimal.class);

		when(invalidInput.doubleValue()).thenThrow(new NumberFormatException("Invalid input"));

		IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class,
				() -> MathUtils.gamma(invalidInput));
		assertTrue(thrownException.getMessage().contains("Invalid input for gamma function"),
				"Exception message mismatch");
	}
}
