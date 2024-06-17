package com.trbaxter.github.fractionalcomputationapi.testdata;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class SharedDerivativeTestData {

  public static Stream<Arguments> coefficientCombinations() {
    return Stream.of(

        // Expression: 0
        Arguments.of("0", 0.0, "0"),
        Arguments.of("0", 0.1, "0"),
        Arguments.of("0", 0.2, "0"),
        Arguments.of("0", 0.3, "0"),
        Arguments.of("0", 0.4, "0"),
        Arguments.of("0", 0.5, "0"),
        Arguments.of("0", 0.6, "0"),
        Arguments.of("0", 0.7, "0"),
        Arguments.of("0", 0.8, "0"),
        Arguments.of("0", 0.9, "0"),
        Arguments.of("0", 1.0, "0"),
        Arguments.of("0", 1.5, "0"),
        Arguments.of("0", 2.0, "0"),
        Arguments.of("0", 3.0, "0"),
        Arguments.of("0", 5.0, "0"),
        Arguments.of("0", 10.0, "0"),
        Arguments.of("0", 100.0, "0"),

        // Expression: 1
        Arguments.of("1", 0.0, "1"),
        Arguments.of("1", 1.0, "0"),
        Arguments.of("1", 2.0, "0"),
        Arguments.of("1", 3.0, "0"),
        Arguments.of("1", 5.0, "0"),
        Arguments.of("1", 10.0, "0"),
        Arguments.of("1", 100.0, "0"),

        // Expression: -1
        Arguments.of("-1", 0.0, "-1"),
        Arguments.of("-1", 1.0, "0"),
        Arguments.of("-1", 2.0, "0"),
        Arguments.of("-1", 3.0, "0"),
        Arguments.of("-1", 5.0, "0"),
        Arguments.of("-1", 10.0, "0"),
        Arguments.of("-1", 100.0, "0"),

        // Expression: 0x + 0
        Arguments.of("0,0", 0.0, "0"),
        Arguments.of("0,0", 0.1, "0"),
        Arguments.of("0,0", 0.2, "0"),
        Arguments.of("0,0", 0.3, "0"),
        Arguments.of("0,0", 0.4, "0"),
        Arguments.of("0,0", 0.5, "0"),
        Arguments.of("0,0", 0.6, "0"),
        Arguments.of("0,0", 0.7, "0"),
        Arguments.of("0,0", 0.8, "0"),
        Arguments.of("0,0", 0.9, "0"),
        Arguments.of("0,0", 1.0, "0"),
        Arguments.of("0,0", 1.5, "0"),
        Arguments.of("0,0", 2.0, "0"),
        Arguments.of("0,0", 3.0, "0"),
        Arguments.of("0,0", 5.0, "0"),
        Arguments.of("0,0", 10.0, "0"),
        Arguments.of("0,0", 100.0, "0"),

        // Expression: x + 1
        Arguments.of("1,1", 0.0, "x + 1"),
        Arguments.of("1,1", 1.0, "1"),
        Arguments.of("1,1", 2.0, "0"),
        Arguments.of("1,1", 3.0, "0"),
        Arguments.of("1,1", 5.0, "0"),
        Arguments.of("1,1", 10.0, "0"),
        Arguments.of("1,1", 100.0, "0"),

        // Expression: -x - 1
        Arguments.of("-1,-1", 0.0, "-x - 1"),
        Arguments.of("-1,-1", 1.0, "-1"),
        Arguments.of("-1,-1", 2.0, "0"),
        Arguments.of("-1,-1", 3.0, "0"),
        Arguments.of("-1,-1", 5.0, "0"),
        Arguments.of("-1,-1", 10.0, "0"),
        Arguments.of("-1,-1", 100.0, "0"),

        // Expression: -x + 1
        Arguments.of("-1,1", 0.0, "-x + 1"),
        Arguments.of("-1,1", 1.0, "-1"),
        Arguments.of("-1,1", 2.0, "0"),
        Arguments.of("-1,1", 3.0, "0"),
        Arguments.of("-1,1", 5.0, "0"),
        Arguments.of("-1,1", 10.0, "0"),
        Arguments.of("-1,1", 100.0, "0"),

        // Expression: x - 1
        Arguments.of("1,-1", 0.0, "x - 1"),
        Arguments.of("1,-1", 1.0, "1"),
        Arguments.of("1,-1", 2.0, "0"),
        Arguments.of("1,-1", 3.0, "0"),
        Arguments.of("1,-1", 5.0, "0"),
        Arguments.of("1,-1", 10.0, "0"),
        Arguments.of("1,-1", 100.0, "0"),

        // Expression: x + 0
        Arguments.of("1,0", 0.0, "x"),
        Arguments.of("1,0", 1.0, "1"),
        Arguments.of("1,0", 2.0, "0"),
        Arguments.of("1,0", 3.0, "0"),
        Arguments.of("1,0", 5.0, "0"),
        Arguments.of("1,0", 10.0, "0"),
        Arguments.of("1,0", 100.0, "0"),

        // Expression: 0x + 1
        Arguments.of("0,1", 0.0, "1"),
        Arguments.of("0,1", 1.0, "0"),
        Arguments.of("0,1", 2.0, "0"),
        Arguments.of("0,1", 3.0, "0"),
        Arguments.of("0,1", 5.0, "0"),
        Arguments.of("0,1", 10.0, "0"),
        Arguments.of("0,1", 100.0, "0"),

        // Expression: 0x^2 + 0x + 0
        Arguments.of("0,0,0", 0.0, "0"),
        Arguments.of("0,0,0", 0.1, "0"),
        Arguments.of("0,0,0", 0.2, "0"),
        Arguments.of("0,0,0", 0.3, "0"),
        Arguments.of("0,0,0", 0.4, "0"),
        Arguments.of("0,0,0", 0.5, "0"),
        Arguments.of("0,0,0", 0.6, "0"),
        Arguments.of("0,0,0", 0.7, "0"),
        Arguments.of("0,0,0", 0.8, "0"),
        Arguments.of("0,0,0", 0.9, "0"),
        Arguments.of("0,0,0", 1.0, "0"),
        Arguments.of("0,0,0", 1.5, "0"),
        Arguments.of("0,0,0", 2.0, "0"),
        Arguments.of("0,0,0", 3.0, "0"),
        Arguments.of("0,0,0", 5.0, "0"),
        Arguments.of("0,0,0", 10.0, "0"),
        Arguments.of("0,0,0", 100.0, "0"),

        // Expression: x^2 + x + 1
        Arguments.of("1,1,1", 0.0, "x^2 + x + 1"),
        Arguments.of("1,1,1", 1.0, "2x + 1"),
        Arguments.of("1,1,1", 2.0, "2"),
        Arguments.of("1,1,1", 3.0, "0"),
        Arguments.of("1,1,1", 5.0, "0"),
        Arguments.of("1,1,1", 10.0, "0"),
        Arguments.of("1,1,1", 100.0, "0"),

        // Expression: -x^2 - x - 1
        Arguments.of("-1,-1,-1", 0.0, "-x^2 - x - 1"),
        Arguments.of("-1,-1,-1", 1.0, "-2x - 1"),
        Arguments.of("-1,-1,-1", 2.0, "-2"),
        Arguments.of("-1,-1,-1", 3.0, "0"),
        Arguments.of("-1,-1,-1", 5.0, "0"),
        Arguments.of("-1,-1,-1", 10.0, "0"),
        Arguments.of("-1,-1,-1", 100.0, "0"),

        // Expression: -x^2 + x + 1
        Arguments.of("-1,1,1", 0.0, "-x^2 + x + 1"),
        Arguments.of("-1,1,1", 1.0, "-2x + 1"),
        Arguments.of("-1,1,1", 2.0, "-2"),
        Arguments.of("-1,1,1", 3.0, "0"),
        Arguments.of("-1,1,1", 5.0, "0"),
        Arguments.of("-1,1,1", 10.0, "0"),
        Arguments.of("-1,1,1", 100.0, "0"),

        // Expression: x^2 - x + 1
        Arguments.of("1,-1,1", 0.0, "x^2 - x + 1"),
        Arguments.of("1,-1,1", 1.0, "2x - 1"),
        Arguments.of("1,-1,1", 2.0, "2"),
        Arguments.of("1,-1,1", 3.0, "0"),
        Arguments.of("1,-1,1", 5.0, "0"),
        Arguments.of("1,-1,1", 10.0, "0"),
        Arguments.of("1,-1,1", 100.0, "0"),

        // Expression: x^2 + x - 1
        Arguments.of("1,1,-1", 0.0, "x^2 + x - 1"),
        Arguments.of("1,1,-1", 1.0, "2x + 1"),
        Arguments.of("1,1,-1", 2.0, "2"),
        Arguments.of("1,1,-1", 3.0, "0"),
        Arguments.of("1,1,-1", 5.0, "0"),
        Arguments.of("1,1,-1", 10.0, "0"),
        Arguments.of("1,1,-1", 100.0, "0"),

        // Expression: x^2 + x + 0
        Arguments.of("1,1,0", 0.0, "x^2 + x"),
        Arguments.of("1,1,0", 1.0, "2x + 1"),
        Arguments.of("1,1,0", 2.0, "2"),
        Arguments.of("1,1,0", 3.0, "0"),
        Arguments.of("1,1,0", 5.0, "0"),
        Arguments.of("1,1,0", 10.0, "0"),
        Arguments.of("1,1,0", 100.0, "0"),

        // Expression: x^2 + 0x + 1
        Arguments.of("1,0,1", 0.0, "x^2 + 1"),
        Arguments.of("1,0,1", 1.0, "2x"),
        Arguments.of("1,0,1", 2.0, "2"),
        Arguments.of("1,0,1", 3.0, "0"),
        Arguments.of("1,0,1", 5.0, "0"),
        Arguments.of("1,0,1", 10.0, "0"),
        Arguments.of("1,0,1", 100.0, "0"),

        // Expression: 0x^2 + x + 1
        Arguments.of("0,1,1", 0.0, "x + 1"),
        Arguments.of("0,1,1", 1.0, "1"),
        Arguments.of("0,1,1", 2.0, "0"),
        Arguments.of("0,1,1", 3.0, "0"),
        Arguments.of("0,1,1", 5.0, "0"),
        Arguments.of("0,1,1", 10.0, "0"),
        Arguments.of("0,1,1", 100.0, "0"));
  }
}