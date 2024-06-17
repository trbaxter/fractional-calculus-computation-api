package com.trbaxter.github.fractionalcomputationapi.testdata;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class CaputoIntegrationTestData {

  public static Stream<Arguments> coefficientCombinations() {
    return Stream.of(

        // Expression: 1
        Arguments.of("1", 0.0, "1"),
        Arguments.of("1", 0.1, "1.051x^0.1 + C"),
        Arguments.of("1", 0.2, "1.089x^0.2 + C"),
        Arguments.of("1", 0.3, "1.114x^0.3 + C"),
        Arguments.of("1", 0.4, "1.127x^0.4 + C"),
        Arguments.of("1", 0.5, "1.128x^0.5 + C"),
        Arguments.of("1", 0.6, "1.119x^0.6 + C"),
        Arguments.of("1", 0.7, "1.101x^0.7 + C"),
        Arguments.of("1", 0.8, "1.074x^0.8 + C"),
        Arguments.of("1", 0.9, "1.040x^0.9 + C"),
        Arguments.of("1", 1.5, "0.752x^1.5 + C")

        // Expression: -1
        //            Arguments.of("-1", 0.1, "0"),
        //            Arguments.of("-1", 0.2, "0"),
        //            Arguments.of("-1", 0.3, "0"),
        //            Arguments.of("-1", 0.4, "0"),
        //            Arguments.of("-1", 0.5, "0"),
        //            Arguments.of("-1", 0.6, "0"),
        //            Arguments.of("-1", 0.7, "0"),
        //            Arguments.of("-1", 0.8, "0"),
        //            Arguments.of("-1", 0.9, "0"),
        //            Arguments.of("-1", 1.5, "0"),

        // Expression: x + 1
        //            Arguments.of("1,1", 0.1, " "),
        //            Arguments.of("1,1", 0.2, " "),
        //            Arguments.of("1,1", 0.3, " "),
        //            Arguments.of("1,1", 0.4, " "),
        //            Arguments.of("1,1", 0.5, " ),
        //            Arguments.of("1,1", 0.6, " "),
        //            Arguments.of("1,1", 0.7, " "),
        //            Arguments.of("1,1", 0.8, " "),
        //            Arguments.of("1,1", 0.9, " "),
        //            Arguments.of("1,1", 1.5, " "),

        // Expression: -x - 1
        //            Arguments.of("-1,-1", 0.1, " "),
        //            Arguments.of("-1,-1", 0.2, " "),
        //            Arguments.of("-1,-1", 0.3, " "),
        //            Arguments.of("-1,-1", 0.4, " "),
        //            Arguments.of("-1,-1", 0.5, " "),
        //            Arguments.of("-1,-1", 0.6, " "),
        //            Arguments.of("-1,-1", 0.7, " "),
        //            Arguments.of("-1,-1", 0.8, " "),
        //            Arguments.of("-1,-1", 0.9, " ),
        //            Arguments.of("-1,-1", 1.5, " "),

        // Expression: -x + 1
        //            Arguments.of("-1,1", 0.1, " "),
        //            Arguments.of("-1,1", 0.2, " ),
        //            Arguments.of("-1,1", 0.3, " "),
        //            Arguments.of("-1,1", 0.4, " "),
        //            Arguments.of("-1,1", 0.5, " "),
        //            Arguments.of("-1,1", 0.6, " "),
        //            Arguments.of("-1,1", 0.7, " "),
        //            Arguments.of("-1,1", 0.8, " "),
        //            Arguments.of("-1,1", 0.9, " "),
        //            Arguments.of("-1,1", 1.5, " "),

        // Expression: x - 1
        //            Arguments.of("1,-1", 0.1, " "),
        //            Arguments.of("1,-1", 0.2, " ),
        //            Arguments.of("1,-1", 0.3, " "),
        //            Arguments.of("1,-1", 0.4, " "),
        //            Arguments.of("1,-1", 0.5, " "),
        //            Arguments.of("1,-1", 0.6, " "),
        //            Arguments.of("1,-1", 0.7, " "),
        //            Arguments.of("1,-1", 0.8, " "),
        //            Arguments.of("1,-1", 0.9, " "),
        //            Arguments.of("1,-1", 1.5, " "),

        // Expression: x + 0
        //            Arguments.of("1,0", 0.1, " "),
        //            Arguments.of("1,0", 0.2, " "),
        //            Arguments.of("1,0", 0.3, " "),
        //            Arguments.of("1,0", 0.4, " "),
        //            Arguments.of("1,0", 0.5, " "),
        //            Arguments.of("1,0", 0.6, " "),
        //            Arguments.of("1,0", 0.7, " "),
        //            Arguments.of("1,0", 0.8, " "),
        //            Arguments.of("1,0", 0.9, " "),
        //            Arguments.of("1,0", 1.5, " "),

        // Expression: 0x + 1
        //            Arguments.of("0,1", 0.1, " "),
        //            Arguments.of("0,1", 0.2, " "),
        //            Arguments.of("0,1", 0.3, " "),
        //            Arguments.of("0,1", 0.4, " "),
        //            Arguments.of("0,1", 0.5, " "),
        //            Arguments.of("0,1", 0.6, " "),
        //            Arguments.of("0,1", 0.7, " "),
        //            Arguments.of("0,1", 0.8, " "),
        //            Arguments.of("0,1", 0.9, " "),
        //            Arguments.of("0,1", 1.5, " "),

        // Expression: x^2 + x + 1
        //            Arguments.of("1,1,1", 0.1, " "),
        //            Arguments.of("1,1,1", 0.2, " "),
        //            Arguments.of("1,1,1", 0.3, " "),
        //            Arguments.of("1,1,1", 0.4, " "),
        //            Arguments.of("1,1,1", 0.5, " "),
        //            Arguments.of("1,1,1", 0.6, " "),
        //            Arguments.of("1,1,1", 0.7, " "),
        //            Arguments.of("1,1,1", 0.8, " "),
        //            Arguments.of("1,1,1", 0.9, " "),
        //            Arguments.of("1,1,1", 1.5, " "),

        // Expression: -x^2 - x - 1
        //            Arguments.of("-1,-1,-1", 0.1, " "),
        //            Arguments.of("-1,-1,-1", 0.2, " "),
        //            Arguments.of("-1,-1,-1", 0.3, " "),
        //            Arguments.of("-1,-1,-1", 0.4, " "),
        //            Arguments.of("-1,-1,-1", 0.5, " "),
        //            Arguments.of("-1,-1,-1", 0.6, " "),
        //            Arguments.of("-1,-1,-1", 0.7, " "),
        //            Arguments.of("-1,-1,-1", 0.8, " "),
        //            Arguments.of("-1,-1,-1", 0.9, " "),
        //            Arguments.of("-1,-1,-1", 1.5, " "),

        // Expression: -x^2 + x + 1
        //            Arguments.of("-1,1,1", 0.1, " "),
        //            Arguments.of("-1,1,1", 0.2, " "),
        //            Arguments.of("-1,1,1", 0.3, " "),
        //            Arguments.of("-1,1,1", 0.4, " "),
        //            Arguments.of("-1,1,1", 0.5, " "),
        //            Arguments.of("-1,1,1", 0.6, " "),
        //            Arguments.of("-1,1,1", 0.7, " "),
        //            Arguments.of("-1,1,1", 0.8, " "),
        //            Arguments.of("-1,1,1", 0.9, " "),
        //            Arguments.of("-1,1,1", 1.5, " "),

        // Expression: x^2 - x + 1
        //            Arguments.of("1,-1,1", 0.1, " "),
        //            Arguments.of("1,-1,1", 0.2, " "),
        //            Arguments.of("1,-1,1", 0.3, " "),
        //            Arguments.of("1,-1,1", 0.4, " "),
        //            Arguments.of("1,-1,1", 0.5, " "),
        //            Arguments.of("1,-1,1", 0.6, " "),
        //            Arguments.of("1,-1,1", 0.7, " "),
        //            Arguments.of("1,-1,1", 0.8, " "),
        //            Arguments.of("1,-1,1", 0.9, " "),
        //            Arguments.of("1,-1,1", 1.5, " "),

        // Expression: x^2 + x - 1
        //            Arguments.of("1,1,-1", 0.1, " "),
        //            Arguments.of("1,1,-1", 0.2, " "),
        //            Arguments.of("1,1,-1", 0.3, " "),
        //            Arguments.of("1,1,-1", 0.4, " "),
        //            Arguments.of("1,1,-1", 0.5, " "),
        //            Arguments.of("1,1,-1", 0.6, " "),
        //            Arguments.of("1,1,-1", 0.7, " "),
        //            Arguments.of("1,1,-1", 0.8, " "),
        //            Arguments.of("1,1,-1", 0.9, " "),
        //            Arguments.of("1,1,-1", 1.5, " "),

        // Expression: x^2 + x + 0
        //            Arguments.of("1,1,0", 0.1, " "),
        //            Arguments.of("1,1,0", 0.2, " "),
        //            Arguments.of("1,1,0", 0.3, " "),
        //            Arguments.of("1,1,0", 0.4, " "),
        //            Arguments.of("1,1,0", 0.5, " "),
        //            Arguments.of("1,1,0", 0.6, " "),
        //            Arguments.of("1,1,0", 0.7, " "),
        //            Arguments.of("1,1,0", 0.8, " "),
        //            Arguments.of("1,1,0", 0.9, " "),
        //            Arguments.of("1,1,0", 1.5, " "),

        // Expression: x^2 + 0x + 1
        //            Arguments.of("1,0,1", 0.1, " "),
        //            Arguments.of("1,0,1", 0.2, " "),
        //            Arguments.of("1,0,1", 0.3, " ),
        //            Arguments.of("1,0,1", 0.4, " "),
        //            Arguments.of("1,0,1", 0.5, " "),
        //            Arguments.of("1,0,1", 0.6, " "),
        //            Arguments.of("1,0,1", 0.7, " "),
        //            Arguments.of("1,0,1", 0.8, " "),
        //            Arguments.of("1,0,1", 0.9, " "),
        //            Arguments.of("1,0,1", 1.5, " "),

        // Expression: 0x^2 + x + 1
        //        Arguments.of("0,1,1", 0.1, " "),
        //        Arguments.of("0,1,1", 0.2, " "),
        //        Arguments.of("0,1,1", 0.3, " "),
        //        Arguments.of("0,1,1", 0.4, " "),
        //        Arguments.of("0,1,1", 0.5, " "),
        //        Arguments.of("0,1,1", 0.6, " "),
        //        Arguments.of("0,1,1", 0.7, " "),
        //        Arguments.of("0,1,1", 0.8, " "),
        //        Arguments.of("0,1,1", 0.9, " "),
        //        Arguments.of("0,1,1", 1.5, " "
        );
  }
}
