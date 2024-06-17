package com.trbaxter.github.fractionalcomputationapi.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegralComputationService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
import com.trbaxter.github.fractionalcomputationapi.testdata.GammaTestData;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaputoIntegrationServiceTest {

  @Autowired private CaputoIntegrationService integrationService;

  @Autowired private CaputoIntegralComputationService computationService;

  @ParameterizedTest
  @MethodSource(
      "com.trbaxter.github.fractionalcomputationapi.testdata"
          + ".CaputoIntegrationTestData#coefficientCombinations")
  public void testCaputoCoefficientCombinations(
      String coefficientString, double alpha, String expected) {
    double[] coefficients =
        Arrays.stream(coefficientString.split(",")).mapToDouble(Double::parseDouble).toArray();

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      GammaTestData.setupMathUtilsMock(utilities);
      String result = integrationService.evaluateExpression(coefficients, alpha);
      assertEquals(expected, result);
    }
  }

  @Test
  public void testGammaFunctionException() {
    double[] coefficients = {1, 2, 3};
    BigDecimal alpha = BigDecimal.valueOf(0.5);

    Logger logger = Logger.getLogger(CaputoIntegralComputationService.class.getName());
    List<String> logMessages = new ArrayList<>();
    Handler testHandler =
        new Handler() {
          @Override
          public void publish(LogRecord record) {
            logMessages.add(record.getMessage());
          }

          @Override
          public void flush() {}

          @Override
          public void close() throws SecurityException {}
        };

    logger.setLevel(Level.SEVERE);
    logger.addHandler(testHandler);

    try (MockedStatic<MathUtils> mockedMathUtils = mockStatic(MathUtils.class)) {
      mockedMathUtils
          .when(() -> MathUtils.gamma(any(BigDecimal.class)))
          .thenThrow(new ArithmeticException("Gamma function error"));

      List<Term> result = computationService.computeTerms(coefficients, alpha);
      assertNotNull(result);
      assertTrue(logMessages.stream().anyMatch(msg -> msg.contains("Gamma function error")));
    } finally {
      logger.removeHandler(testHandler);
    }
  }
}
