package com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville;

import static com.trbaxter.github.fractionalcomputationapi.testdata.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RiemannLiouvilleDerivativeServiceTest {

  @Autowired RiemannLiouvilleDerivativeService derivativeService;

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
    "'1', 0.1, '0.936x^-0.1'",
    "'1', 0.2, '0.859x^-0.2'",
    "'1', 0.3, '0.770x^-0.3'",
    "'1', 0.4, '0.672x^-0.4'",
    "'1', 0.5, '0.564x^-0.5'",
    "'1', 0.6, '0.451x^-0.6'",
    "'1', 0.7, '0.334x^-0.7'",
    "'1', 0.8, '0.218x^-0.8'",
    "'1', 0.9, '0.105x^-0.9'",
    "'1', 1.0, '0'",
    "'1', 1.5, '-0.282x^-1.5'",
    "'1', 2.0, '0'",
    "'1', 3.0, '0'",
    "'1', 5.0, '0'",
    "'1', 10.0, '0'",
    "'1', 100.0, '0'",
    "'-1', 0.0, '-1'",
    "'-1', 0.1, '-0.936x^-0.1'",
    "'-1', 0.2, '-0.859x^-0.2'",
    "'-1', 0.3, '-0.770x^-0.3'",
    "'-1', 0.4, '-0.672x^-0.4'",
    "'-1', 0.5, '-0.564x^-0.5'",
    "'-1', 0.6, '-0.451x^-0.6'",
    "'-1', 0.7, '-0.334x^-0.7'",
    "'-1', 0.8, '-0.218x^-0.8'",
    "'-1', 0.9, '-0.105x^-0.9'",
    "'-1', 1.0, '0'",
    "'-1', 1.5, '0.282x^-1.5'",
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
    "'1,1', 0.1, '1.040x^0.9 + 0.936x^-0.1'",
    "'1,1', 0.2, '1.074x^0.8 + 0.859x^-0.2'",
    "'1,1', 0.3, '1.101x^0.7 + 0.770x^-0.3'",
    "'1,1', 0.4, '1.119x^0.6 + 0.672x^-0.4'",
    "'1,1', 0.5, '1.128x^0.5 + 0.564x^-0.5'",
    "'1,1', 0.6, '1.127x^0.4 + 0.451x^-0.6'",
    "'1,1', 0.7, '1.114x^0.3 + 0.334x^-0.7'",
    "'1,1', 0.8, '1.089x^0.2 + 0.218x^-0.8'",
    "'1,1', 0.9, '1.051x^0.1 + 0.105x^-0.9'",
    "'1,1', 1.0, '1'",
    "'1,1', 1.5, '0.564x^-0.5 - 0.282x^-1.5'",
    "'1,1', 2.0, '0'",
    "'1,1', 3.0, '0'",
    "'1,1', 5.0, '0'",
    "'1,1', 10.0, '0'",
    "'1,1', 100.0, '0'",
    "'-1,-1', 0.0, '-x - 1'",
    "'-1,-1', 0.1, '-1.040x^0.9 - 0.936x^-0.1'",
    "'-1,-1', 0.2, '-1.074x^0.8 - 0.859x^-0.2'",
    "'-1,-1', 0.3, '-1.101x^0.7 - 0.770x^-0.3'",
    "'-1,-1', 0.4, '-1.119x^0.6 - 0.672x^-0.4'",
    "'-1,-1', 0.5, '-1.128x^0.5 - 0.564x^-0.5'",
    "'-1,-1', 0.6, '-1.127x^0.4 - 0.451x^-0.6'",
    "'-1,-1', 0.7, '-1.114x^0.3 - 0.334x^-0.7'",
    "'-1,-1', 0.8, '-1.089x^0.2 - 0.218x^-0.8'",
    "'-1,-1', 0.9, '-1.051x^0.1 - 0.105x^-0.9'",
    "'-1,-1', 1.0, '-1'",
    "'-1,-1', 1.5, '-0.564x^-0.5 + 0.282x^-1.5'",
    "'-1,-1', 2.0, '0'",
    "'-1,-1', 3.0, '0'",
    "'-1,-1', 5.0, '0'",
    "'-1,-1', 10.0, '0'",
    "'-1,-1', 100.0, '0'",
    "'-1,1', 0.0, '-x + 1'",
    "'-1,1', 0.1, '-1.040x^0.9 + 0.936x^-0.1'",
    "'-1,1', 0.2, '-1.074x^0.8 + 0.859x^-0.2'",
    "'-1,1', 0.3, '-1.101x^0.7 + 0.770x^-0.3'",
    "'-1,1', 0.4, '-1.119x^0.6 + 0.672x^-0.4'",
    "'-1,1', 0.5, '-1.128x^0.5 + 0.564x^-0.5'",
    "'-1,1', 0.6, '-1.127x^0.4 + 0.451x^-0.6'",
    "'-1,1', 0.7, '-1.114x^0.3 + 0.334x^-0.7'",
    "'-1,1', 0.8, '-1.089x^0.2 + 0.218x^-0.8'",
    "'-1,1', 0.9, '-1.051x^0.1 + 0.105x^-0.9'",
    "'-1,1', 1.0, '-1'",
    "'-1,1', 1.5, '-0.564x^-0.5 - 0.282x^-1.5'",
    "'-1,1', 2.0, '0'",
    "'-1,1', 3.0, '0'",
    "'-1,1', 5.0, '0'",
    "'-1,1', 10.0, '0'",
    "'-1,1', 100.0, '0'",
    "'1,-1', 0.0, 'x - 1'",
    "'1,-1', 0.1, '1.040x^0.9 - 0.936x^-0.1'",
    "'1,-1', 0.2, '1.074x^0.8 - 0.859x^-0.2'",
    "'1,-1', 0.3, '1.101x^0.7 - 0.770x^-0.3'",
    "'1,-1', 0.4, '1.119x^0.6 - 0.672x^-0.4'",
    "'1,-1', 0.5, '1.128x^0.5 - 0.564x^-0.5'",
    "'1,-1', 0.6, '1.127x^0.4 - 0.451x^-0.6'",
    "'1,-1', 0.7, '1.114x^0.3 - 0.334x^-0.7'",
    "'1,-1', 0.8, '1.089x^0.2 - 0.218x^-0.8'",
    "'1,-1', 0.9, '1.051x^0.1 - 0.105x^-0.9'",
    "'1,-1', 1.0, '1'",
    "'1,-1', 1.5, '0.564x^-0.5 + 0.282x^-1.5'",
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
    "'1,0', 1.5, '0.564x^-0.5'",
    "'1,0', 2.0, '0'",
    "'1,0', 3.0, '0'",
    "'1,0', 5.0, '0'",
    "'1,0', 10.0, '0'",
    "'1,0', 100.0, '0'",
    "'0,1', 0.0, '1'",
    "'0,1', 0.1, '0.936x^-0.1'",
    "'0,1', 0.2, '0.859x^-0.2'",
    "'0,1', 0.3, '0.770x^-0.3'",
    "'0,1', 0.4, '0.672x^-0.4'",
    "'0,1', 0.5, '0.564x^-0.5'",
    "'0,1', 0.6, '0.451x^-0.6'",
    "'0,1', 0.7, '0.334x^-0.7'",
    "'0,1', 0.8, '0.218x^-0.8'",
    "'0,1', 0.9, '0.105x^-0.9'",
    "'0,1', 1.0, '0'",
    "'0,1', 1.5, '-0.282x^-1.5'",
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
    "'1,1,1', 0.1, '1.094x^1.9 + 1.040x^0.9 + 0.936x^-0.1'",
    "'1,1,1', 0.2, '1.193x^1.8 + 1.074x^0.8 + 0.859x^-0.2'",
    "'1,1,1', 0.3, '1.295x^1.7 + 1.101x^0.7 + 0.770x^-0.3'",
    "'1,1,1', 0.4, '1.399x^1.6 + 1.119x^0.6 + 0.672x^-0.4'",
    "'1,1,1', 0.5, '1.505x^1.5 + 1.128x^0.5 + 0.564x^-0.5'",
    "'1,1,1', 0.6, '1.610x^1.4 + 1.127x^0.4 + 0.451x^-0.6'",
    "'1,1,1', 0.7, '1.714x^1.3 + 1.114x^0.3 + 0.334x^-0.7'",
    "'1,1,1', 0.8, '1.815x^1.2 + 1.089x^0.2 + 0.218x^-0.8'",
    "'1,1,1', 0.9, '1.911x^1.1 + 1.051x^0.1 + 0.105x^-0.9'",
    "'1,1,1', 1.0, '2x + 1'",
    "'1,1,1', 1.5, '2.257x^0.5 + 0.564x^-0.5 - 0.282x^-1.5'",
    "'1,1,1', 2.0, '2'",
    "'1,1,1', 3.0, '0'",
    "'1,1,1', 5.0, '0'",
    "'1,1,1', 10.0, '0'",
    "'1,1,1', 100.0, '0'",
    "'-1,-1,-1', 0.0, '-x^2 - x - 1'",
    "'-1,-1,-1', 0.1, '-1.094x^1.9 - 1.040x^0.9 - 0.936x^-0.1'",
    "'-1,-1,-1', 0.2, '-1.193x^1.8 - 1.074x^0.8 - 0.859x^-0.2'",
    "'-1,-1,-1', 0.3, '-1.295x^1.7 - 1.101x^0.7 - 0.770x^-0.3'",
    "'-1,-1,-1', 0.4, '-1.399x^1.6 - 1.119x^0.6 - 0.672x^-0.4'",
    "'-1,-1,-1', 0.5, '-1.505x^1.5 - 1.128x^0.5 - 0.564x^-0.5'",
    "'-1,-1,-1', 0.6, '-1.610x^1.4 - 1.127x^0.4 - 0.451x^-0.6'",
    "'-1,-1,-1', 0.7, '-1.714x^1.3 - 1.114x^0.3 - 0.334x^-0.7'",
    "'-1,-1,-1', 0.8, '-1.815x^1.2 - 1.089x^0.2 - 0.218x^-0.8'",
    "'-1,-1,-1', 0.9, '-1.911x^1.1 - 1.051x^0.1 - 0.105x^-0.9'",
    "'-1,-1,-1', 1.0, '-2x - 1'",
    "'-1,-1,-1', 1.5, '-2.257x^0.5 - 0.564x^-0.5 + 0.282x^-1.5'",
    "'-1,-1,-1', 2.0, '-2'",
    "'-1,-1,-1', 3.0, '0'",
    "'-1,-1,-1', 5.0, '0'",
    "'-1,-1,-1', 10.0, '0'",
    "'-1,-1,-1', 100.0, '0'",
    "'-1,1,1', 0.0, '-x^2 + x + 1'",
    "'-1,1,1', 0.1, '-1.094x^1.9 + 1.040x^0.9 + 0.936x^-0.1'",
    "'-1,1,1', 0.2, '-1.193x^1.8 + 1.074x^0.8 + 0.859x^-0.2'",
    "'-1,1,1', 0.3, '-1.295x^1.7 + 1.101x^0.7 + 0.770x^-0.3'",
    "'-1,1,1', 0.4, '-1.399x^1.6 + 1.119x^0.6 + 0.672x^-0.4'",
    "'-1,1,1', 0.5, '-1.505x^1.5 + 1.128x^0.5 + 0.564x^-0.5'",
    "'-1,1,1', 0.6, '-1.610x^1.4 + 1.127x^0.4 + 0.451x^-0.6'",
    "'-1,1,1', 0.7, '-1.714x^1.3 + 1.114x^0.3 + 0.334x^-0.7'",
    "'-1,1,1', 0.8, '-1.815x^1.2 + 1.089x^0.2 + 0.218x^-0.8'",
    "'-1,1,1', 0.9, '-1.911x^1.1 + 1.051x^0.1 + 0.105x^-0.9'",
    "'-1,1,1', 1.0, '-2x + 1'",
    "'-1,1,1', 1.5, '-2.257x^0.5 + 0.564x^-0.5 - 0.282x^-1.5'",
    "'-1,1,1', 2.0, '-2'",
    "'-1,1,1', 3.0, '0'",
    "'-1,1,1', 5.0, '0'",
    "'-1,1,1', 10.0, '0'",
    "'-1,1,1', 100.0, '0'",
    "'1,-1,1', 0.0, 'x^2 - x + 1'",
    "'1,-1,1', 0.1, '1.094x^1.9 - 1.040x^0.9 + 0.936x^-0.1'",
    "'1,-1,1', 0.2, '1.193x^1.8 - 1.074x^0.8 + 0.859x^-0.2'",
    "'1,-1,1', 0.3, '1.295x^1.7 - 1.101x^0.7 + 0.770x^-0.3'",
    "'1,-1,1', 0.4, '1.399x^1.6 - 1.119x^0.6 + 0.672x^-0.4'",
    "'1,-1,1', 0.5, '1.505x^1.5 - 1.128x^0.5 + 0.564x^-0.5'",
    "'1,-1,1', 0.6, '1.610x^1.4 - 1.127x^0.4 + 0.451x^-0.6'",
    "'1,-1,1', 0.7, '1.714x^1.3 - 1.114x^0.3 + 0.334x^-0.7'",
    "'1,-1,1', 0.8, '1.815x^1.2 - 1.089x^0.2 + 0.218x^-0.8'",
    "'1,-1,1', 0.9, '1.911x^1.1 - 1.051x^0.1 + 0.105x^-0.9'",
    "'1,-1,1', 1.0, '2x - 1'",
    "'1,-1,1', 1.5, '2.257x^0.5 - 0.564x^-0.5 - 0.282x^-1.5'",
    "'1,-1,1', 2.0, '2'",
    "'1,-1,1', 3.0, '0'",
    "'1,-1,1', 5.0, '0'",
    "'1,-1,1', 10.0, '0'",
    "'1,-1,1', 100.0, '0'",
    "'1,1,-1', 0.0, 'x^2 + x - 1'",
    "'1,1,-1', 0.1, '1.094x^1.9 + 1.040x^0.9 - 0.936x^-0.1'",
    "'1,1,-1', 0.2, '1.193x^1.8 + 1.074x^0.8 - 0.859x^-0.2'",
    "'1,1,-1', 0.3, '1.295x^1.7 + 1.101x^0.7 - 0.770x^-0.3'",
    "'1,1,-1', 0.4, '1.399x^1.6 + 1.119x^0.6 - 0.672x^-0.4'",
    "'1,1,-1', 0.5, '1.505x^1.5 + 1.128x^0.5 - 0.564x^-0.5'",
    "'1,1,-1', 0.6, '1.610x^1.4 + 1.127x^0.4 - 0.451x^-0.6'",
    "'1,1,-1', 0.7, '1.714x^1.3 + 1.114x^0.3 - 0.334x^-0.7'",
    "'1,1,-1', 0.8, '1.815x^1.2 + 1.089x^0.2 - 0.218x^-0.8'",
    "'1,1,-1', 0.9, '1.911x^1.1 + 1.051x^0.1 - 0.105x^-0.9'",
    "'1,1,-1', 1.0, '2x + 1'",
    "'1,1,-1', 1.5, '2.257x^0.5 + 0.564x^-0.5 + 0.282x^-1.5'",
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
    "'1,1,0', 1.5, '2.257x^0.5 + 0.564x^-0.5'",
    "'1,1,0', 2.0, '2'",
    "'1,1,0', 3.0, '0'",
    "'1,1,0', 5.0, '0'",
    "'1,1,0', 10.0, '0'",
    "'1,1,0', 100.0, '0'",
    "'1,0,1', 0.0, 'x^2 + 1'",
    "'1,0,1', 0.1, '1.094x^1.9 + 0.936x^-0.1'",
    "'1,0,1', 0.2, '1.193x^1.8 + 0.859x^-0.2'",
    "'1,0,1', 0.3, '1.295x^1.7 + 0.770x^-0.3'",
    "'1,0,1', 0.4, '1.399x^1.6 + 0.672x^-0.4'",
    "'1,0,1', 0.5, '1.505x^1.5 + 0.564x^-0.5'",
    "'1,0,1', 0.6, '1.610x^1.4 + 0.451x^-0.6'",
    "'1,0,1', 0.7, '1.714x^1.3 + 0.334x^-0.7'",
    "'1,0,1', 0.8, '1.815x^1.2 + 0.218x^-0.8'",
    "'1,0,1', 0.9, '1.911x^1.1 + 0.105x^-0.9'",
    "'1,0,1', 1.0, '2x'",
    "'1,0,1', 1.5, '2.257x^0.5 - 0.282x^-1.5'",
    "'1,0,1', 2.0, '2'",
    "'1,0,1', 3.0, '0'",
    "'1,0,1', 5.0, '0'",
    "'1,0,1', 10.0, '0'",
    "'1,0,1', 100.0, '0'",
    "'0,1,1', 0.0, 'x + 1'",
    "'0,1,1', 0.1, '1.040x^0.9 + 0.936x^-0.1'",
    "'0,1,1', 0.2, '1.074x^0.8 + 0.859x^-0.2'",
    "'0,1,1', 0.3, '1.101x^0.7 + 0.770x^-0.3'",
    "'0,1,1', 0.4, '1.119x^0.6 + 0.672x^-0.4'",
    "'0,1,1', 0.5, '1.128x^0.5 + 0.564x^-0.5'",
    "'0,1,1', 0.6, '1.127x^0.4 + 0.451x^-0.6'",
    "'0,1,1', 0.7, '1.114x^0.3 + 0.334x^-0.7'",
    "'0,1,1', 0.8, '1.089x^0.2 + 0.218x^-0.8'",
    "'0,1,1', 0.9, '1.051x^0.1 + 0.105x^-0.9'",
    "'0,1,1', 1.0, '1'",
    "'0,1,1', 1.5, '0.564x^-0.5 - 0.282x^-1.5'",
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
  public void testComputeDerivative_ThrowsArithmeticException() {
    double[] coefficients = {3.0, 2.0, 1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(new BigDecimal(gamma_3));
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenThrow(new ArithmeticException("Division by zero"));

      String result;
      try {
        result = derivativeService.evaluateExpression(coefficients, alpha);
      } catch (ArithmeticException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }

  @Test
  public void testComputeDerivative_ThrowsGenericException() {
    double[] coefficients = {1.0};
    double alpha = 0.5;

    try (MockedStatic<MathUtils> utilities = Mockito.mockStatic(MathUtils.class)) {
      utilities
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
          .thenThrow(new RuntimeException("Unexpected error"));

      String result;
      try {
        result = derivativeService.evaluateExpression(coefficients, alpha);
      } catch (RuntimeException e) {
        result = "";
      }
      String expected = "";

      assertEquals(expected, result);
    }
  }
}
