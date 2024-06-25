package com.trbaxter.github.fractionalcomputationapi.testdata.derivative;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

/**
 * RiemannLiouvilleDerivativeTestData provides a collection of test data for testing the
 * Riemann-Liouville derivative computations. It includes various combinations of polynomial
 * coefficients and fractional orders.
 */
public final class RiemannLiouvilleDerivativeTestData {

  /** Private constructor to prevent instantiation. */
  private RiemannLiouvilleDerivativeTestData() {
    throw new UnsupportedOperationException("Utility class for test data");
  }

  /**
   * Provides a stream of arguments representing different combinations of polynomial coefficients
   * and fractional orders for testing Riemann-Liouville derivative computations.
   *
   * @return a stream of arguments for parameterized tests.
   */
  public static Stream<Arguments> coefficientCombinations() {
    return Stream.of(

        // Expression: 1
        Arguments.of("1", 0.1, "0.936x^-0.1"),
        Arguments.of("1", 0.2, "0.859x^-0.2"),
        Arguments.of("1", 0.3, "0.770x^-0.3"),
        Arguments.of("1", 0.4, "0.672x^-0.4"),
        Arguments.of("1", 0.5, "0.564x^-0.5"),
        Arguments.of("1", 0.6, "0.451x^-0.6"),
        Arguments.of("1", 0.7, "0.334x^-0.7"),
        Arguments.of("1", 0.8, "0.218x^-0.8"),
        Arguments.of("1", 0.9, "0.105x^-0.9"),
        Arguments.of("1", 1.5, "-0.282x^-1.5"),

        // Expression: -1
        Arguments.of("-1", 0.1, "-0.936x^-0.1"),
        Arguments.of("-1", 0.2, "-0.859x^-0.2"),
        Arguments.of("-1", 0.3, "-0.770x^-0.3"),
        Arguments.of("-1", 0.4, "-0.672x^-0.4"),
        Arguments.of("-1", 0.5, "-0.564x^-0.5"),
        Arguments.of("-1", 0.6, "-0.451x^-0.6"),
        Arguments.of("-1", 0.7, "-0.334x^-0.7"),
        Arguments.of("-1", 0.8, "-0.218x^-0.8"),
        Arguments.of("-1", 0.9, "-0.105x^-0.9"),
        Arguments.of("-1", 1.5, "0.282x^-1.5"),

        // Expression: x + 1
        Arguments.of("1,1", 0.1, "1.040x^0.9 + 0.936x^-0.1"),
        Arguments.of("1,1", 0.2, "1.074x^0.8 + 0.859x^-0.2"),
        Arguments.of("1,1", 0.3, "1.101x^0.7 + 0.770x^-0.3"),
        Arguments.of("1,1", 0.4, "1.119x^0.6 + 0.672x^-0.4"),
        Arguments.of("1,1", 0.5, "1.128x^0.5 + 0.564x^-0.5"),
        Arguments.of("1,1", 0.6, "1.127x^0.4 + 0.451x^-0.6"),
        Arguments.of("1,1", 0.7, "1.114x^0.3 + 0.334x^-0.7"),
        Arguments.of("1,1", 0.8, "1.089x^0.2 + 0.218x^-0.8"),
        Arguments.of("1,1", 0.9, "1.051x^0.1 + 0.105x^-0.9"),
        Arguments.of("1,1", 1.5, "0.564x^-0.5 - 0.282x^-1.5"),

        // Expression: -x - 1
        Arguments.of("-1,-1", 0.1, "-1.040x^0.9 - 0.936x^-0.1"),
        Arguments.of("-1,-1", 0.2, "-1.074x^0.8 - 0.859x^-0.2"),
        Arguments.of("-1,-1", 0.3, "-1.101x^0.7 - 0.770x^-0.3"),
        Arguments.of("-1,-1", 0.4, "-1.119x^0.6 - 0.672x^-0.4"),
        Arguments.of("-1,-1", 0.5, "-1.128x^0.5 - 0.564x^-0.5"),
        Arguments.of("-1,-1", 0.6, "-1.127x^0.4 - 0.451x^-0.6"),
        Arguments.of("-1,-1", 0.7, "-1.114x^0.3 - 0.334x^-0.7"),
        Arguments.of("-1,-1", 0.8, "-1.089x^0.2 - 0.218x^-0.8"),
        Arguments.of("-1,-1", 0.9, "-1.051x^0.1 - 0.105x^-0.9"),
        Arguments.of("-1,-1", 1.5, "-0.564x^-0.5 + 0.282x^-1.5"),

        // Expression: -x + 1
        Arguments.of("-1,1", 0.1, "-1.040x^0.9 + 0.936x^-0.1"),
        Arguments.of("-1,1", 0.2, "-1.074x^0.8 + 0.859x^-0.2"),
        Arguments.of("-1,1", 0.3, "-1.101x^0.7 + 0.770x^-0.3"),
        Arguments.of("-1,1", 0.4, "-1.119x^0.6 + 0.672x^-0.4"),
        Arguments.of("-1,1", 0.5, "-1.128x^0.5 + 0.564x^-0.5"),
        Arguments.of("-1,1", 0.6, "-1.127x^0.4 + 0.451x^-0.6"),
        Arguments.of("-1,1", 0.7, "-1.114x^0.3 + 0.334x^-0.7"),
        Arguments.of("-1,1", 0.8, "-1.089x^0.2 + 0.218x^-0.8"),
        Arguments.of("-1,1", 0.9, "-1.051x^0.1 + 0.105x^-0.9"),
        Arguments.of("-1,1", 1.5, "-0.564x^-0.5 - 0.282x^-1.5"),

        // Expression: x - 1
        Arguments.of("1,-1", 0.1, "1.040x^0.9 - 0.936x^-0.1"),
        Arguments.of("1,-1", 0.2, "1.074x^0.8 - 0.859x^-0.2"),
        Arguments.of("1,-1", 0.3, "1.101x^0.7 - 0.770x^-0.3"),
        Arguments.of("1,-1", 0.4, "1.119x^0.6 - 0.672x^-0.4"),
        Arguments.of("1,-1", 0.5, "1.128x^0.5 - 0.564x^-0.5"),
        Arguments.of("1,-1", 0.6, "1.127x^0.4 - 0.451x^-0.6"),
        Arguments.of("1,-1", 0.7, "1.114x^0.3 - 0.334x^-0.7"),
        Arguments.of("1,-1", 0.8, "1.089x^0.2 - 0.218x^-0.8"),
        Arguments.of("1,-1", 0.9, "1.051x^0.1 - 0.105x^-0.9"),
        Arguments.of("1,-1", 1.5, "0.564x^-0.5 + 0.282x^-1.5"),

        // Expression: x + 0
        Arguments.of("1,0", 0.1, "1.040x^0.9"),
        Arguments.of("1,0", 0.2, "1.074x^0.8"),
        Arguments.of("1,0", 0.3, "1.101x^0.7"),
        Arguments.of("1,0", 0.4, "1.119x^0.6"),
        Arguments.of("1,0", 0.5, "1.128x^0.5"),
        Arguments.of("1,0", 0.6, "1.127x^0.4"),
        Arguments.of("1,0", 0.7, "1.114x^0.3"),
        Arguments.of("1,0", 0.8, "1.089x^0.2"),
        Arguments.of("1,0", 0.9, "1.051x^0.1"),
        Arguments.of("1,0", 1.5, "0.564x^-0.5"),

        // Expression: 0x + 1
        Arguments.of("0,1", 0.1, "0.936x^-0.1"),
        Arguments.of("0,1", 0.2, "0.859x^-0.2"),
        Arguments.of("0,1", 0.3, "0.770x^-0.3"),
        Arguments.of("0,1", 0.4, "0.672x^-0.4"),
        Arguments.of("0,1", 0.5, "0.564x^-0.5"),
        Arguments.of("0,1", 0.6, "0.451x^-0.6"),
        Arguments.of("0,1", 0.7, "0.334x^-0.7"),
        Arguments.of("0,1", 0.8, "0.218x^-0.8"),
        Arguments.of("0,1", 0.9, "0.105x^-0.9"),
        Arguments.of("0,1", 1.5, "-0.282x^-1.5"),

        // Expression: x^2 + x + 1
        Arguments.of("1,1,1", 0.1, "1.094x^1.9 + 1.040x^0.9 + 0.936x^-0.1"),
        Arguments.of("1,1,1", 0.2, "1.193x^1.8 + 1.074x^0.8 + 0.859x^-0.2"),
        Arguments.of("1,1,1", 0.3, "1.295x^1.7 + 1.101x^0.7 + 0.770x^-0.3"),
        Arguments.of("1,1,1", 0.4, "1.399x^1.6 + 1.119x^0.6 + 0.672x^-0.4"),
        Arguments.of("1,1,1", 0.5, "1.505x^1.5 + 1.128x^0.5 + 0.564x^-0.5"),
        Arguments.of("1,1,1", 0.6, "1.610x^1.4 + 1.127x^0.4 + 0.451x^-0.6"),
        Arguments.of("1,1,1", 0.7, "1.714x^1.3 + 1.114x^0.3 + 0.334x^-0.7"),
        Arguments.of("1,1,1", 0.8, "1.815x^1.2 + 1.089x^0.2 + 0.218x^-0.8"),
        Arguments.of("1,1,1", 0.9, "1.911x^1.1 + 1.051x^0.1 + 0.105x^-0.9"),
        Arguments.of("1,1,1", 1.5, "2.257x^0.5 + 0.564x^-0.5 - 0.282x^-1.5"),

        // Expression: -x^2 - x - 1
        Arguments.of("-1,-1,-1", 0.1, "-1.094x^1.9 - 1.040x^0.9 - 0.936x^-0.1"),
        Arguments.of("-1,-1,-1", 0.2, "-1.193x^1.8 - 1.074x^0.8 - 0.859x^-0.2"),
        Arguments.of("-1,-1,-1", 0.3, "-1.295x^1.7 - 1.101x^0.7 - 0.770x^-0.3"),
        Arguments.of("-1,-1,-1", 0.4, "-1.399x^1.6 - 1.119x^0.6 - 0.672x^-0.4"),
        Arguments.of("-1,-1,-1", 0.5, "-1.505x^1.5 - 1.128x^0.5 - 0.564x^-0.5"),
        Arguments.of("-1,-1,-1", 0.6, "-1.610x^1.4 - 1.127x^0.4 - 0.451x^-0.6"),
        Arguments.of("-1,-1,-1", 0.7, "-1.714x^1.3 - 1.114x^0.3 - 0.334x^-0.7"),
        Arguments.of("-1,-1,-1", 0.8, "-1.815x^1.2 - 1.089x^0.2 - 0.218x^-0.8"),
        Arguments.of("-1,-1,-1", 0.9, "-1.911x^1.1 - 1.051x^0.1 - 0.105x^-0.9"),
        Arguments.of("-1,-1,-1", 1.5, "-2.257x^0.5 - 0.564x^-0.5 + 0.282x^-1.5"),

        // Expression: -x^2 + x + 1
        Arguments.of("-1,1,1", 0.1, "-1.094x^1.9 + 1.040x^0.9 + 0.936x^-0.1"),
        Arguments.of("-1,1,1", 0.2, "-1.193x^1.8 + 1.074x^0.8 + 0.859x^-0.2"),
        Arguments.of("-1,1,1", 0.3, "-1.295x^1.7 + 1.101x^0.7 + 0.770x^-0.3"),
        Arguments.of("-1,1,1", 0.4, "-1.399x^1.6 + 1.119x^0.6 + 0.672x^-0.4"),
        Arguments.of("-1,1,1", 0.5, "-1.505x^1.5 + 1.128x^0.5 + 0.564x^-0.5"),
        Arguments.of("-1,1,1", 0.6, "-1.610x^1.4 + 1.127x^0.4 + 0.451x^-0.6"),
        Arguments.of("-1,1,1", 0.7, "-1.714x^1.3 + 1.114x^0.3 + 0.334x^-0.7"),
        Arguments.of("-1,1,1", 0.8, "-1.815x^1.2 + 1.089x^0.2 + 0.218x^-0.8"),
        Arguments.of("-1,1,1", 0.9, "-1.911x^1.1 + 1.051x^0.1 + 0.105x^-0.9"),
        Arguments.of("-1,1,1", 1.5, "-2.257x^0.5 + 0.564x^-0.5 - 0.282x^-1.5"),

        // Expression: x^2 - x + 1
        Arguments.of("1,-1,1", 0.1, "1.094x^1.9 - 1.040x^0.9 + 0.936x^-0.1"),
        Arguments.of("1,-1,1", 0.2, "1.193x^1.8 - 1.074x^0.8 + 0.859x^-0.2"),
        Arguments.of("1,-1,1", 0.3, "1.295x^1.7 - 1.101x^0.7 + 0.770x^-0.3"),
        Arguments.of("1,-1,1", 0.4, "1.399x^1.6 - 1.119x^0.6 + 0.672x^-0.4"),
        Arguments.of("1,-1,1", 0.5, "1.505x^1.5 - 1.128x^0.5 + 0.564x^-0.5"),
        Arguments.of("1,-1,1", 0.6, "1.610x^1.4 - 1.127x^0.4 + 0.451x^-0.6"),
        Arguments.of("1,-1,1", 0.7, "1.714x^1.3 - 1.114x^0.3 + 0.334x^-0.7"),
        Arguments.of("1,-1,1", 0.8, "1.815x^1.2 - 1.089x^0.2 + 0.218x^-0.8"),
        Arguments.of("1,-1,1", 0.9, "1.911x^1.1 - 1.051x^0.1 + 0.105x^-0.9"),
        Arguments.of("1,-1,1", 1.5, "2.257x^0.5 - 0.564x^-0.5 - 0.282x^-1.5"),

        // Expression: x^2 + x - x
        Arguments.of("1,1,-1", 0.1, "1.094x^1.9 + 1.040x^0.9 - 0.936x^-0.1"),
        Arguments.of("1,1,-1", 0.2, "1.193x^1.8 + 1.074x^0.8 - 0.859x^-0.2"),
        Arguments.of("1,1,-1", 0.3, "1.295x^1.7 + 1.101x^0.7 - 0.770x^-0.3"),
        Arguments.of("1,1,-1", 0.4, "1.399x^1.6 + 1.119x^0.6 - 0.672x^-0.4"),
        Arguments.of("1,1,-1", 0.5, "1.505x^1.5 + 1.128x^0.5 - 0.564x^-0.5"),
        Arguments.of("1,1,-1", 0.6, "1.610x^1.4 + 1.127x^0.4 - 0.451x^-0.6"),
        Arguments.of("1,1,-1", 0.7, "1.714x^1.3 + 1.114x^0.3 - 0.334x^-0.7"),
        Arguments.of("1,1,-1", 0.8, "1.815x^1.2 + 1.089x^0.2 - 0.218x^-0.8"),
        Arguments.of("1,1,-1", 0.9, "1.911x^1.1 + 1.051x^0.1 - 0.105x^-0.9"),
        Arguments.of("1,1,-1", 1.5, "2.257x^0.5 + 0.564x^-0.5 + 0.282x^-1.5"),

        // Expression: x^2 + x + 0
        Arguments.of("1,1,0", 0.1, "1.094x^1.9 + 1.040x^0.9"),
        Arguments.of("1,1,0", 0.2, "1.193x^1.8 + 1.074x^0.8"),
        Arguments.of("1,1,0", 0.3, "1.295x^1.7 + 1.101x^0.7"),
        Arguments.of("1,1,0", 0.4, "1.399x^1.6 + 1.119x^0.6"),
        Arguments.of("1,1,0", 0.5, "1.505x^1.5 + 1.128x^0.5"),
        Arguments.of("1,1,0", 0.6, "1.610x^1.4 + 1.127x^0.4"),
        Arguments.of("1,1,0", 0.7, "1.714x^1.3 + 1.114x^0.3"),
        Arguments.of("1,1,0", 0.8, "1.815x^1.2 + 1.089x^0.2"),
        Arguments.of("1,1,0", 0.9, "1.911x^1.1 + 1.051x^0.1"),
        Arguments.of("1,1,0", 1.5, "2.257x^0.5 + 0.564x^-0.5"),

        // Expression: x^2 + 0x + 1
        Arguments.of("1,0,1", 0.1, "1.094x^1.9 + 0.936x^-0.1"),
        Arguments.of("1,0,1", 0.2, "1.193x^1.8 + 0.859x^-0.2"),
        Arguments.of("1,0,1", 0.3, "1.295x^1.7 + 0.770x^-0.3"),
        Arguments.of("1,0,1", 0.4, "1.399x^1.6 + 0.672x^-0.4"),
        Arguments.of("1,0,1", 0.5, "1.505x^1.5 + 0.564x^-0.5"),
        Arguments.of("1,0,1", 0.6, "1.610x^1.4 + 0.451x^-0.6"),
        Arguments.of("1,0,1", 0.7, "1.714x^1.3 + 0.334x^-0.7"),
        Arguments.of("1,0,1", 0.8, "1.815x^1.2 + 0.218x^-0.8"),
        Arguments.of("1,0,1", 0.9, "1.911x^1.1 + 0.105x^-0.9"),
        Arguments.of("1,0,1", 1.5, "2.257x^0.5 - 0.282x^-1.5"),

        // Expression: 0x^2 + x + 1
        Arguments.of("0,1,1", 0.1, "1.040x^0.9 + 0.936x^-0.1"),
        Arguments.of("0,1,1", 0.2, "1.074x^0.8 + 0.859x^-0.2"),
        Arguments.of("0,1,1", 0.3, "1.101x^0.7 + 0.770x^-0.3"),
        Arguments.of("0,1,1", 0.4, "1.119x^0.6 + 0.672x^-0.4"),
        Arguments.of("0,1,1", 0.5, "1.128x^0.5 + 0.564x^-0.5"),
        Arguments.of("0,1,1", 0.6, "1.127x^0.4 + 0.451x^-0.6"),
        Arguments.of("0,1,1", 0.7, "1.114x^0.3 + 0.334x^-0.7"),
        Arguments.of("0,1,1", 0.8, "1.089x^0.2 + 0.218x^-0.8"),
        Arguments.of("0,1,1", 0.9, "1.051x^0.1 + 0.105x^-0.9"),
        Arguments.of("0,1,1", 1.5, "0.564x^-0.5 - 0.282x^-1.5"));
  }
}
