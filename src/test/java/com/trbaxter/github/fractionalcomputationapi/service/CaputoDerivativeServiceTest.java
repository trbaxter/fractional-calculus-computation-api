package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CaputoDerivativeServiceTest {

    private CaputoDerivativeService caputoDerivativeService;

    @BeforeEach
    public void setUp() {
        caputoDerivativeService = new CaputoDerivativeService();
    }

    @Test
    public void testComputeDerivative_SimplePolynomial() {
        double[] coefficients = {3.0, 2.0, 1.0};
        double alpha = 0.5;

        try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
                                          .thenReturn(new BigDecimal("2.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
                                          .thenReturn(new BigDecimal("1.329"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
                                          .thenReturn(new BigDecimal("0.886"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
                                          .thenReturn(new BigDecimal("1.77245385091"));

            String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
            String expected = "4.515x^1.5 + 2.257x^0.5";

            assertEquals(expected, result);
        }
    }

    @Test
    public void testComputeDerivative_NegativeCoefficients() {
        double[] coefficients = {-3.0, -2.0, -1.0};
        double alpha = 0.5;

        try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
                                          .thenReturn(new BigDecimal("2.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
                                          .thenReturn(new BigDecimal("1.329"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
                                          .thenReturn(new BigDecimal("0.886"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
                                          .thenReturn(new BigDecimal("1.77245385091"));

            String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
            String expected = "- 4.515x^1.5 - 2.257x^0.5";

            assertEquals(expected, result);
        }
    }

    @Test
    public void testComputeDerivative_ConstantTerm() {
        double[] coefficients = {3.0};
        double alpha = 0.5;

        String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    public void testComputeDerivative_PositiveAndNegativeCoefficients() {
        double[] coefficients = {3.0, -2.0, 1.0};
        double alpha = 0.5;

        try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
                                          .thenReturn(new BigDecimal("2.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
                                          .thenReturn(new BigDecimal("1.329"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
                                          .thenReturn(new BigDecimal("0.886"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
                                          .thenReturn(new BigDecimal("1.77245385091"));

            String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
            String expected = "4.515x^1.5 - 2.257x^0.5";

            assertEquals(expected, result);
        }
    }

    @Test
    public void testComputeDerivative_ZeroCoefficient() {
        double[] coefficients = {0.0, 2.0, -1.0};
        double alpha = 0.5;

        try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
                                          .thenReturn(new BigDecimal("0.886"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
                                          .thenReturn(new BigDecimal("1.77245385091"));

            String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
            String expected = "2.257x^0.5";

            assertEquals(expected, result);
        }
    }

    @Test
    public void testComputeDerivative_ZeroPolynomial() {
        double[] coefficients = {0.0, 0.0, 0.0};
        double alpha = 0.5;

        String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
        String expected = "";

        assertEquals(expected, result);
    }

    @Test
    public void testComputeDerivative_DifferentAlpha() {
        double[] coefficients = {3.0, 0.0, 1.0};
        double alpha = 1.5;

        try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
                                          .thenReturn(new BigDecimal("2.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
                                          .thenReturn(new BigDecimal("0.886"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
                                          .thenReturn(new BigDecimal("1.0"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
                                          .thenReturn(new BigDecimal("1.77245385091"));
            utilities.when(() -> MathUtils.gamma(BigDecimal.valueOf(-0.5)))
                                          .thenReturn(new BigDecimal("-3.54490770181"));

            String result = caputoDerivativeService.computeDerivative(coefficients, alpha);
            String expected = "6.772x^0.5";

            assertEquals(expected, result);
        }
    }
}