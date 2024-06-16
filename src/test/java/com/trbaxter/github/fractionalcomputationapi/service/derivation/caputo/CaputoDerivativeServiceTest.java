package com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo;

import static com.trbaxter.github.fractionalcomputationapi.testdata.TestData.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

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
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CaputoDerivativeServiceTest {

  @Autowired private CaputoDerivativeService derivativeService;

  private void setupMathUtilsMock(MockedStatic<MathUtils> utilities) {
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(5)))
        .thenReturn(new BigDecimal(gamma_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(5)))
        .thenReturn(new BigDecimal(gamma_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(4)))
        .thenReturn(new BigDecimal(gamma_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.5)))
        .thenReturn(new BigDecimal(gamma_3_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
        .thenReturn(new BigDecimal(gamma_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.9)))
        .thenReturn(new BigDecimal(gamma_2_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.8)))
        .thenReturn(new BigDecimal(gamma_2_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.7)))
        .thenReturn(new BigDecimal(gamma_2_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.6)))
        .thenReturn(new BigDecimal(gamma_2_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
        .thenReturn(new BigDecimal(gamma_2_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.4)))
        .thenReturn(new BigDecimal(gamma_2_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.3)))
        .thenReturn(new BigDecimal(gamma_2_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.2)))
        .thenReturn(new BigDecimal(gamma_2_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.1)))
        .thenReturn(new BigDecimal(gamma_2_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
        .thenReturn(new BigDecimal(gamma_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.9)))
        .thenReturn(new BigDecimal(gamma_1_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.8)))
        .thenReturn(new BigDecimal(gamma_1_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.7)))
        .thenReturn(new BigDecimal(gamma_1_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.6)))
        .thenReturn(new BigDecimal(gamma_1_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
        .thenReturn(new BigDecimal(gamma_1_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.4)))
        .thenReturn(new BigDecimal(gamma_1_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.3)))
        .thenReturn(new BigDecimal(gamma_1_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.2)))
        .thenReturn(new BigDecimal(gamma_1_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.1)))
        .thenReturn(new BigDecimal(gamma_1_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
        .thenReturn(new BigDecimal(gamma_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.9)))
        .thenReturn(new BigDecimal(gamma_0_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.8)))
        .thenReturn(new BigDecimal(gamma_0_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.7)))
        .thenReturn(new BigDecimal(gamma_0_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.6)))
        .thenReturn(new BigDecimal(gamma_0_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
        .thenReturn(new BigDecimal(gamma_0_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.4)))
        .thenReturn(new BigDecimal(gamma_0_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.3)))
        .thenReturn(new BigDecimal(gamma_0_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.2)))
        .thenReturn(new BigDecimal(gamma_0_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.1)))
        .thenReturn(new BigDecimal(gamma_0_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(-0.5)))
        .thenReturn(new BigDecimal(neg_gamma_0_point_5));
  }

  @ParameterizedTest
  @CsvSource({
    "'0', 0.0, '0'",
    "'0', 0.1, '0'",
    "'0', 0.2, '0'",
    "'0', 0.3, '0'",
    "'0', 0.4, '0'",
    "'0', 0.5, '0'",
    "'0', 0.6, '0'",
    "'0', 0.7, '0'",
    "'0', 0.8, '0'",
    "'0', 0.9, '0'",
    "'0', 1.0, '0'",
    "'0', 1.5, '0'",
    "'0', 2.0, '0'",
    "'0', 3.0, '0'",
    "'0', 5.0, '0'",
    "'0', 10.0, '0'",
    "'0', 100.0, '0'",
    "'1', 0.0, '1'",
    "'1', 0.1, '0'",
    "'1', 0.2, '0'",
    "'1', 0.3, '0'",
    "'1', 0.4, '0'",
    "'1', 0.5, '0'",
    "'1', 0.6, '0'",
    "'1', 0.7, '0'",
    "'1', 0.8, '0'",
    "'1', 0.9, '0'",
    "'1', 1.0, '0'",
    "'1', 1.5, '0'",
    "'1', 2.0, '0'",
    "'1', 3.0, '0'",
    "'1', 5.0, '0'",
    "'1', 10.0, '0'",
    "'1', 100.0, '0'",
    "'-1', 0.0, '-1'",
    "'-1', 0.1, '0'",
    "'-1', 0.2, '0'",
    "'-1', 0.3, '0'",
    "'-1', 0.4, '0'",
    "'-1', 0.5, '0'",
    "'-1', 0.6, '0'",
    "'-1', 0.7, '0'",
    "'-1', 0.8, '0'",
    "'-1', 0.9, '0'",
    "'-1', 1.0, '0'",
    "'-1', 1.5, '0'",
    "'-1', 2.0, '0'",
    "'-1', 3.0, '0'",
    "'-1', 5.0, '0'",
    "'-1', 10.0, '0'",
    "'-1', 100.0, '0'",
    "'0,0', 0.0, '0'",
    "'0,0', 0.1, '0'",
    "'0,0', 0.2, '0'",
    "'0,0', 0.3, '0'",
    "'0,0', 0.4, '0'",
    "'0,0', 0.5, '0'",
    "'0,0', 0.6, '0'",
    "'0,0', 0.7, '0'",
    "'0,0', 0.8, '0'",
    "'0,0', 0.9, '0'",
    "'0,0', 1.0, '0'",
    "'0,0', 1.5, '0'",
    "'0,0', 2.0, '0'",
    "'0,0', 3.0, '0'",
    "'0,0', 5.0, '0'",
    "'0,0', 10.0, '0'",
    "'0,0', 100.0, '0'",
    "'1,1', 0.0, 'x + 1'",
    "'1,1', 0.1, '1.040x^0.9'",
    "'1,1', 0.2, '1.074x^0.8'",
    "'1,1', 0.3, '1.101x^0.7'",
    "'1,1', 0.4, '1.119x^0.6'",
    "'1,1', 0.5, '1.128x^0.5'",
    "'1,1', 0.6, '1.127x^0.4'",
    "'1,1', 0.7, '1.114x^0.3'",
    "'1,1', 0.8, '1.089x^0.2'",
    "'1,1', 0.9, '1.051x^0.1'",
    "'1,1', 1.0, '1'",
    "'1,1', 1.5, '0'",
    "'1,1', 2.0, '0'",
    "'1,1', 3.0, '0'",
    "'1,1', 5.0, '0'",
    "'1,1', 10.0, '0'",
    "'1,1', 100.0, '0'",
    "'-1,-1', 0.0, '-x - 1'",
    "'-1,-1', 0.1, '-1.040x^0.9'",
    "'-1,-1', 0.2, '-1.074x^0.8'",
    "'-1,-1', 0.3, '-1.101x^0.7'",
    "'-1,-1', 0.4, '-1.119x^0.6'",
    "'-1,-1', 0.5, '-1.128x^0.5'",
    "'-1,-1', 0.6, '-1.127x^0.4'",
    "'-1,-1', 0.7, '-1.114x^0.3'",
    "'-1,-1', 0.8, '-1.089x^0.2'",
    "'-1,-1', 0.9, '-1.051x^0.1'",
    "'-1,-1', 1.0, '-1'",
    "'-1,-1', 1.5, '0'",
    "'-1,-1', 2.0, '0'",
    "'-1,-1', 3.0, '0'",
    "'-1,-1', 5.0, '0'",
    "'-1,-1', 10.0, '0'",
    "'-1,-1', 100.0, '0'",
    "'-1,1', 0.0, '-x + 1'",
    "'-1,1', 0.1, '-1.040x^0.9'",
    "'-1,1', 0.2, '-1.074x^0.8'",
    "'-1,1', 0.3, '-1.101x^0.7'",
    "'-1,1', 0.4, '-1.119x^0.6'",
    "'-1,1', 0.5, '-1.128x^0.5'",
    "'-1,1', 0.6, '-1.127x^0.4'",
    "'-1,1', 0.7, '-1.114x^0.3'",
    "'-1,1', 0.8, '-1.089x^0.2'",
    "'-1,1', 0.9, '-1.051x^0.1'",
    "'-1,1', 1.0, '-1'",
    "'-1,1', 1.5, '0'",
    "'-1,1', 2.0, '0'",
    "'-1,1', 3.0, '0'",
    "'-1,1', 5.0, '0'",
    "'-1,1', 10.0, '0'",
    "'-1,1', 100.0, '0'",
    "'1,-1', 0.0, 'x - 1'",
    "'1,-1', 0.1, '1.040x^0.9'",
    "'1,-1', 0.2, '1.074x^0.8'",
    "'1,-1', 0.3, '1.101x^0.7'",
    "'1,-1', 0.4, '1.119x^0.6'",
    "'1,-1', 0.5, '1.128x^0.5'",
    "'1,-1', 0.6, '1.127x^0.4'",
    "'1,-1', 0.7, '1.114x^0.3'",
    "'1,-1', 0.8, '1.089x^0.2'",
    "'1,-1', 0.9, '1.051x^0.1'",
    "'1,-1', 1.0, '1'",
    "'1,-1', 1.5, '0'",
    "'1,-1', 2.0, '0'",
    "'1,-1', 3.0, '0'",
    "'1,-1', 5.0, '0'",
    "'1,-1', 10.0, '0'",
    "'1,-1', 100.0, '0'",
    "'1,0', 0.0, 'x'",
    "'1,0', 0.1, '1.040x^0.9'",
    "'1,0', 0.2, '1.074x^0.8'",
    "'1,0', 0.3, '1.101x^0.7'",
    "'1,0', 0.4, '1.119x^0.6'",
    "'1,0', 0.5, '1.128x^0.5'",
    "'1,0', 0.6, '1.127x^0.4'",
    "'1,0', 0.7, '1.114x^0.3'",
    "'1,0', 0.8, '1.089x^0.2'",
    "'1,0', 0.9, '1.051x^0.1'",
    "'1,0', 1.0, '1'",
    "'1,0', 1.5, '0'",
    "'1,0', 2.0, '0'",
    "'1,0', 3.0, '0'",
    "'1,0', 5.0, '0'",
    "'1,0', 10.0, '0'",
    "'1,0', 100.0, '0'",
    "'0,1', 0.0, '1'",
    "'0,1', 0.1, '0'",
    "'0,1', 0.2, '0'",
    "'0,1', 0.3, '0'",
    "'0,1', 0.4, '0'",
    "'0,1', 0.5, '0'",
    "'0,1', 0.6, '0'",
    "'0,1', 0.7, '0'",
    "'0,1', 0.8, '0'",
    "'0,1', 0.9, '0'",
    "'0,1', 1.0, '0'",
    "'0,1', 1.5, '0'",
    "'0,1', 2.0, '0'",
    "'0,1', 3.0, '0'",
    "'0,1', 5.0, '0'",
    "'0,1', 10.0, '0'",
    "'0,1', 100.0, '0'",
    "'0,0,0', 0.0, '0'",
    "'0,0,0', 0.1, '0'",
    "'0,0,0', 0.2, '0'",
    "'0,0,0', 0.3, '0'",
    "'0,0,0', 0.4, '0'",
    "'0,0,0', 0.5, '0'",
    "'0,0,0', 0.6, '0'",
    "'0,0,0', 0.7, '0'",
    "'0,0,0', 0.8, '0'",
    "'0,0,0', 0.9, '0'",
    "'0,0,0', 1.0, '0'",
    "'0,0,0', 1.5, '0'",
    "'0,0,0', 2.0, '0'",
    "'0,0,0', 3.0, '0'",
    "'0,0,0', 5.0, '0'",
    "'0,0,0', 10.0, '0'",
    "'0,0,0', 100.0, '0'",
    "'1,1,1', 0.0, 'x^2 + x + 1'",
    "'1,1,1', 0.1, '1.094x^1.9 + 1.040x^0.9'",
    "'1,1,1', 0.2, '1.193x^1.8 + 1.074x^0.8'",
    "'1,1,1', 0.3, '1.295x^1.7 + 1.101x^0.7'",
    "'1,1,1', 0.4, '1.399x^1.6 + 1.119x^0.6'",
    "'1,1,1', 0.5, '1.505x^1.5 + 1.128x^0.5'",
    "'1,1,1', 0.6, '1.610x^1.4 + 1.127x^0.4'",
    "'1,1,1', 0.7, '1.714x^1.3 + 1.114x^0.3'",
    "'1,1,1', 0.8, '1.815x^1.2 + 1.089x^0.2'",
    "'1,1,1', 0.9, '1.911x^1.1 + 1.051x^0.1'",
    "'1,1,1', 1.0, '2x + 1'",
    "'1,1,1', 1.5, '2.257x^0.5'",
    "'1,1,1', 2.0, '2'",
    "'1,1,1', 3.0, '0'",
    "'1,1,1', 5.0, '0'",
    "'1,1,1', 10.0, '0'",
    "'1,1,1', 100.0, '0'",
    "'-1,-1,-1', 0.0, '-x^2 - x - 1'",
    "'-1,-1,-1', 0.1, '-1.094x^1.9 - 1.040x^0.9'",
    "'-1,-1,-1', 0.2, '-1.193x^1.8 - 1.074x^0.8'",
    "'-1,-1,-1', 0.3, '-1.295x^1.7 - 1.101x^0.7'",
    "'-1,-1,-1', 0.4, '-1.399x^1.6 - 1.119x^0.6'",
    "'-1,-1,-1', 0.5, '-1.505x^1.5 - 1.128x^0.5'",
    "'-1,-1,-1', 0.6, '-1.610x^1.4 - 1.127x^0.4'",
    "'-1,-1,-1', 0.7, '-1.714x^1.3 - 1.114x^0.3'",
    "'-1,-1,-1', 0.8, '-1.815x^1.2 - 1.089x^0.2'",
    "'-1,-1,-1', 0.9, '-1.911x^1.1 - 1.051x^0.1'",
    "'-1,-1,-1', 1.0, '-2x - 1'",
    "'-1,-1,-1', 1.5, '-2.257x^0.5'",
    "'-1,-1,-1', 2.0, '-2'",
    "'-1,-1,-1', 3.0, '0'",
    "'-1,-1,-1', 5.0, '0'",
    "'-1,-1,-1', 10.0, '0'",
    "'-1,-1,-1', 100.0, '0'",
    "'-1,1,1', 0.0, '-x^2 + x + 1'",
    "'-1,1,1', 0.1, '-1.094x^1.9 + 1.040x^0.9'",
    "'-1,1,1', 0.2, '-1.193x^1.8 + 1.074x^0.8'",
    "'-1,1,1', 0.3, '-1.295x^1.7 + 1.101x^0.7'",
    "'-1,1,1', 0.4, '-1.399x^1.6 + 1.119x^0.6'",
    "'-1,1,1', 0.5, '-1.505x^1.5 + 1.128x^0.5'",
    "'-1,1,1', 0.6, '-1.610x^1.4 + 1.127x^0.4'",
    "'-1,1,1', 0.7, '-1.714x^1.3 + 1.114x^0.3'",
    "'-1,1,1', 0.8, '-1.815x^1.2 + 1.089x^0.2'",
    "'-1,1,1', 0.9, '-1.911x^1.1 + 1.051x^0.1'",
    "'-1,1,1', 1.0, '-2x + 1'",
    "'-1,1,1', 1.5, '-2.257x^0.5'",
    "'-1,1,1', 2.0, '-2'",
    "'-1,1,1', 3.0, '0'",
    "'-1,1,1', 5.0, '0'",
    "'-1,1,1', 10.0, '0'",
    "'-1,1,1', 100.0, '0'",
    "'1,-1,1', 0.0, 'x^2 - x + 1'",
    "'1,-1,1', 0.1, '1.094x^1.9 - 1.040x^0.9'",
    "'1,-1,1', 0.2, '1.193x^1.8 - 1.074x^0.8'",
    "'1,-1,1', 0.3, '1.295x^1.7 - 1.101x^0.7'",
    "'1,-1,1', 0.4, '1.399x^1.6 - 1.119x^0.6'",
    "'1,-1,1', 0.5, '1.505x^1.5 - 1.128x^0.5'",
    "'1,-1,1', 0.6, '1.610x^1.4 - 1.127x^0.4'",
    "'1,-1,1', 0.7, '1.714x^1.3 - 1.114x^0.3'",
    "'1,-1,1', 0.8, '1.815x^1.2 - 1.089x^0.2'",
    "'1,-1,1', 0.9, '1.911x^1.1 - 1.051x^0.1'",
    "'1,-1,1', 1.0, '2x - 1'",
    "'1,-1,1', 1.5, '2.257x^0.5'",
    "'1,-1,1', 2.0, '2'",
    "'1,-1,1', 3.0, '0'",
    "'1,-1,1', 5.0, '0'",
    "'1,-1,1', 10.0, '0'",
    "'1,-1,1', 100.0, '0'",
    "'1,1,-1', 0.0, 'x^2 + x - 1'",
    "'1,1,-1', 0.1, '1.094x^1.9 + 1.040x^0.9'",
    "'1,1,-1', 0.2, '1.193x^1.8 + 1.074x^0.8'",
    "'1,1,-1', 0.3, '1.295x^1.7 + 1.101x^0.7'",
    "'1,1,-1', 0.4, '1.399x^1.6 + 1.119x^0.6'",
    "'1,1,-1', 0.5, '1.505x^1.5 + 1.128x^0.5'",
    "'1,1,-1', 0.6, '1.610x^1.4 + 1.127x^0.4'",
    "'1,1,-1', 0.7, '1.714x^1.3 + 1.114x^0.3'",
    "'1,1,-1', 0.8, '1.815x^1.2 + 1.089x^0.2'",
    "'1,1,-1', 0.9, '1.911x^1.1 + 1.051x^0.1'",
    "'1,1,-1', 1.0, '2x + 1'",
    "'1,1,-1', 1.5, '2.257x^0.5'",
    "'1,1,-1', 2.0, '2'",
    "'1,1,-1', 3.0, '0'",
    "'1,1,-1', 5.0, '0'",
    "'1,1,-1', 10.0, '0'",
    "'1,1,-1', 100.0, '0'",
    "'1,1,0', 0.0, 'x^2 + x'",
    "'1,1,0', 0.1, '1.094x^1.9 + 1.040x^0.9'",
    "'1,1,0', 0.2, '1.193x^1.8 + 1.074x^0.8'",
    "'1,1,0', 0.3, '1.295x^1.7 + 1.101x^0.7'",
    "'1,1,0', 0.4, '1.399x^1.6 + 1.119x^0.6'",
    "'1,1,0', 0.5, '1.505x^1.5 + 1.128x^0.5'",
    "'1,1,0', 0.6, '1.610x^1.4 + 1.127x^0.4'",
    "'1,1,0', 0.7, '1.714x^1.3 + 1.114x^0.3'",
    "'1,1,0', 0.8, '1.815x^1.2 + 1.089x^0.2'",
    "'1,1,0', 0.9, '1.911x^1.1 + 1.051x^0.1'",
    "'1,1,0', 1.0, '2x + 1'",
    "'1,1,0', 1.5, '2.257x^0.5'",
    "'1,1,0', 2.0, '2'",
    "'1,1,0', 3.0, '0'",
    "'1,1,0', 5.0, '0'",
    "'1,1,0', 10.0, '0'",
    "'1,1,0', 100.0, '0'",
    "'1,0,1', 0.0, 'x^2 + 1'",
    "'1,0,1', 0.1, '1.094x^1.9'",
    "'1,0,1', 0.2, '1.193x^1.8'",
    "'1,0,1', 0.3, '1.295x^1.7'",
    "'1,0,1', 0.4, '1.399x^1.6'",
    "'1,0,1', 0.5, '1.505x^1.5'",
    "'1,0,1', 0.6, '1.610x^1.4'",
    "'1,0,1', 0.7, '1.714x^1.3'",
    "'1,0,1', 0.8, '1.815x^1.2'",
    "'1,0,1', 0.9, '1.911x^1.1'",
    "'1,0,1', 1.0, '2x'",
    "'1,0,1', 1.5, '2.257x^0.5'",
    "'1,0,1', 2.0, '2'",
    "'1,0,1', 3.0, '0'",
    "'1,0,1', 5.0, '0'",
    "'1,0,1', 10.0, '0'",
    "'1,0,1', 100.0, '0'",
    "'0,1,1', 0.0, 'x + 1'",
    "'0,1,1', 0.1, '1.040x^0.9'",
    "'0,1,1', 0.2, '1.074x^0.8'",
    "'0,1,1', 0.3, '1.101x^0.7'",
    "'0,1,1', 0.4, '1.119x^0.6'",
    "'0,1,1', 0.5, '1.128x^0.5'",
    "'0,1,1', 0.6, '1.127x^0.4'",
    "'0,1,1', 0.7, '1.114x^0.3'",
    "'0,1,1', 0.8, '1.089x^0.2'",
    "'0,1,1', 0.9, '1.051x^0.1'",
    "'0,1,1', 1.0, '1'",
    "'0,1,1', 1.5, '0'",
    "'0,1,1', 2.0, '0'",
    "'0,1,1', 3.0, '0'",
    "'0,1,1', 5.0, '0'",
    "'0,1,1', 10.0, '0'",
    "'0,1,1', 100.0, '0'",
  })
  public void testCoefficientCombinations(String coefficientString, double alpha, String expected) {
    double[] coefficients =
        Arrays.stream(coefficientString.split(",")).mapToDouble(Double::parseDouble).toArray();

    try (MockedStatic<MathUtils> utilities = mockStatic(MathUtils.class)) {
      setupMathUtilsMock(utilities);
      String result = derivativeService.evaluateExpression(coefficients, alpha);
      assertEquals(expected, result);
    }
  }

  @Test
  public void testGammaFunctionException() {
    double[] coefficients = {1, 2, 3};
    double alpha = 0.5;

    Logger logger = Logger.getLogger(CaputoDerivativeComputationService.class.getName());
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

      String result = derivativeService.evaluateExpression(coefficients, alpha);
      assertNotNull(result);
      assertTrue(logMessages.stream().anyMatch(msg -> msg.contains("Gamma function error")));
    } finally {
      logger.removeHandler(testHandler);
    }
  }
}
