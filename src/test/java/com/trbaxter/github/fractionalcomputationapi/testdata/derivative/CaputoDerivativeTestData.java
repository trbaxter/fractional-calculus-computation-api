package com.trbaxter.github.fractionalcomputationapi.testdata.derivative;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public final class CaputoDerivativeTestData {

  public static Stream<Arguments> coefficientCombinations() {
    return Stream.of(

        // Expression: 1
        Arguments.of("1", 0.1, "0"),
        Arguments.of("1", 0.2, "0"),
        Arguments.of("1", 0.3, "0"),
        Arguments.of("1", 0.4, "0"),
        Arguments.of("1", 0.5, "0"),
        Arguments.of("1", 0.6, "0"),
        Arguments.of("1", 0.7, "0"),
        Arguments.of("1", 0.8, "0"),
        Arguments.of("1", 0.9, "0"),
        Arguments.of("1", 1.5, "0"),

        // Expression: -1
        Arguments.of("-1", 0.1, "0"),
        Arguments.of("-1", 0.2, "0"),
        Arguments.of("-1", 0.3, "0"),
        Arguments.of("-1", 0.4, "0"),
        Arguments.of("-1", 0.5, "0"),
        Arguments.of("-1", 0.6, "0"),
        Arguments.of("-1", 0.7, "0"),
        Arguments.of("-1", 0.8, "0"),
        Arguments.of("-1", 0.9, "0"),
        Arguments.of("-1", 1.5, "0"),

        // Expression: x + 1
        Arguments.of("1,1", 0.1, "1.040x^0.9"),
        Arguments.of("1,1", 0.2, "1.074x^0.8"),
        Arguments.of("1,1", 0.3, "1.101x^0.7"),
        Arguments.of("1,1", 0.4, "1.119x^0.6"),
        Arguments.of("1,1", 0.5, "1.128x^0.5"),
        Arguments.of("1,1", 0.6, "1.127x^0.4"),
        Arguments.of("1,1", 0.7, "1.114x^0.3"),
        Arguments.of("1,1", 0.8, "1.089x^0.2"),
        Arguments.of("1,1", 0.9, "1.051x^0.1"),
        Arguments.of("1,1", 1.5, "0"),

        // Expression: -x - 1
        Arguments.of("-1,-1", 0.1, "-1.040x^0.9"),
        Arguments.of("-1,-1", 0.2, "-1.074x^0.8"),
        Arguments.of("-1,-1", 0.3, "-1.101x^0.7"),
        Arguments.of("-1,-1", 0.4, "-1.119x^0.6"),
        Arguments.of("-1,-1", 0.5, "-1.128x^0.5"),
        Arguments.of("-1,-1", 0.6, "-1.127x^0.4"),
        Arguments.of("-1,-1", 0.7, "-1.114x^0.3"),
        Arguments.of("-1,-1", 0.8, "-1.089x^0.2"),
        Arguments.of("-1,-1", 0.9, "-1.051x^0.1"),
        Arguments.of("-1,-1", 1.5, "0"),

        // Expression: -x + 1
        Arguments.of("-1,1", 0.1, "-1.040x^0.9"),
        Arguments.of("-1,1", 0.2, "-1.074x^0.8"),
        Arguments.of("-1,1", 0.3, "-1.101x^0.7"),
        Arguments.of("-1,1", 0.4, "-1.119x^0.6"),
        Arguments.of("-1,1", 0.5, "-1.128x^0.5"),
        Arguments.of("-1,1", 0.6, "-1.127x^0.4"),
        Arguments.of("-1,1", 0.7, "-1.114x^0.3"),
        Arguments.of("-1,1", 0.8, "-1.089x^0.2"),
        Arguments.of("-1,1", 0.9, "-1.051x^0.1"),
        Arguments.of("-1,1", 1.5, "0"),

        // Expression: x - 1
        Arguments.of("1,-1", 0.1, "1.040x^0.9"),
        Arguments.of("1,-1", 0.2, "1.074x^0.8"),
        Arguments.of("1,-1", 0.3, "1.101x^0.7"),
        Arguments.of("1,-1", 0.4, "1.119x^0.6"),
        Arguments.of("1,-1", 0.5, "1.128x^0.5"),
        Arguments.of("1,-1", 0.6, "1.127x^0.4"),
        Arguments.of("1,-1", 0.7, "1.114x^0.3"),
        Arguments.of("1,-1", 0.8, "1.089x^0.2"),
        Arguments.of("1,-1", 0.9, "1.051x^0.1"),
        Arguments.of("1,-1", 1.5, "0"),

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
        Arguments.of("1,0", 1.5, "0"),

        // Expression: 0x + 1
        Arguments.of("0,1", 0.1, "0"),
        Arguments.of("0,1", 0.2, "0"),
        Arguments.of("0,1", 0.3, "0"),
        Arguments.of("0,1", 0.4, "0"),
        Arguments.of("0,1", 0.5, "0"),
        Arguments.of("0,1", 0.6, "0"),
        Arguments.of("0,1", 0.7, "0"),
        Arguments.of("0,1", 0.8, "0"),
        Arguments.of("0,1", 0.9, "0"),
        Arguments.of("0,1", 1.5, "0"),

        // Expression: x^2 + x + 1
        Arguments.of("1,1,1", 0.1, "1.094x^1.9 + 1.040x^0.9"),
        Arguments.of("1,1,1", 0.2, "1.193x^1.8 + 1.074x^0.8"),
        Arguments.of("1,1,1", 0.3, "1.295x^1.7 + 1.101x^0.7"),
        Arguments.of("1,1,1", 0.4, "1.399x^1.6 + 1.119x^0.6"),
        Arguments.of("1,1,1", 0.5, "1.505x^1.5 + 1.128x^0.5"),
        Arguments.of("1,1,1", 0.6, "1.610x^1.4 + 1.127x^0.4"),
        Arguments.of("1,1,1", 0.7, "1.714x^1.3 + 1.114x^0.3"),
        Arguments.of("1,1,1", 0.8, "1.815x^1.2 + 1.089x^0.2"),
        Arguments.of("1,1,1", 0.9, "1.911x^1.1 + 1.051x^0.1"),
        Arguments.of("1,1,1", 1.5, "2.257x^0.5"),

        // Expression: -x^2 - x - 1
        Arguments.of("-1,-1,-1", 0.1, "-1.094x^1.9 - 1.040x^0.9"),
        Arguments.of("-1,-1,-1", 0.2, "-1.193x^1.8 - 1.074x^0.8"),
        Arguments.of("-1,-1,-1", 0.3, "-1.295x^1.7 - 1.101x^0.7"),
        Arguments.of("-1,-1,-1", 0.4, "-1.399x^1.6 - 1.119x^0.6"),
        Arguments.of("-1,-1,-1", 0.5, "-1.505x^1.5 - 1.128x^0.5"),
        Arguments.of("-1,-1,-1", 0.6, "-1.610x^1.4 - 1.127x^0.4"),
        Arguments.of("-1,-1,-1", 0.7, "-1.714x^1.3 - 1.114x^0.3"),
        Arguments.of("-1,-1,-1", 0.8, "-1.815x^1.2 - 1.089x^0.2"),
        Arguments.of("-1,-1,-1", 0.9, "-1.911x^1.1 - 1.051x^0.1"),
        Arguments.of("-1,-1,-1", 1.5, "-2.257x^0.5"),

        // Expression: -x^2 + x + 1
        Arguments.of("-1,1,1", 0.1, "-1.094x^1.9 + 1.040x^0.9"),
        Arguments.of("-1,1,1", 0.2, "-1.193x^1.8 + 1.074x^0.8"),
        Arguments.of("-1,1,1", 0.3, "-1.295x^1.7 + 1.101x^0.7"),
        Arguments.of("-1,1,1", 0.4, "-1.399x^1.6 + 1.119x^0.6"),
        Arguments.of("-1,1,1", 0.5, "-1.505x^1.5 + 1.128x^0.5"),
        Arguments.of("-1,1,1", 0.6, "-1.610x^1.4 + 1.127x^0.4"),
        Arguments.of("-1,1,1", 0.7, "-1.714x^1.3 + 1.114x^0.3"),
        Arguments.of("-1,1,1", 0.8, "-1.815x^1.2 + 1.089x^0.2"),
        Arguments.of("-1,1,1", 0.9, "-1.911x^1.1 + 1.051x^0.1"),
        Arguments.of("-1,1,1", 1.5, "-2.257x^0.5"),

        // Expression: x^2 - x + 1
        Arguments.of("1,-1,1", 0.1, "1.094x^1.9 - 1.040x^0.9"),
        Arguments.of("1,-1,1", 0.2, "1.193x^1.8 - 1.074x^0.8"),
        Arguments.of("1,-1,1", 0.3, "1.295x^1.7 - 1.101x^0.7"),
        Arguments.of("1,-1,1", 0.4, "1.399x^1.6 - 1.119x^0.6"),
        Arguments.of("1,-1,1", 0.5, "1.505x^1.5 - 1.128x^0.5"),
        Arguments.of("1,-1,1", 0.6, "1.610x^1.4 - 1.127x^0.4"),
        Arguments.of("1,-1,1", 0.7, "1.714x^1.3 - 1.114x^0.3"),
        Arguments.of("1,-1,1", 0.8, "1.815x^1.2 - 1.089x^0.2"),
        Arguments.of("1,-1,1", 0.9, "1.911x^1.1 - 1.051x^0.1"),
        Arguments.of("1,-1,1", 1.5, "2.257x^0.5"),

        // Expression: x^2 + x - 1
        Arguments.of("1,1,-1", 0.1, "1.094x^1.9 + 1.040x^0.9"),
        Arguments.of("1,1,-1", 0.2, "1.193x^1.8 + 1.074x^0.8"),
        Arguments.of("1,1,-1", 0.3, "1.295x^1.7 + 1.101x^0.7"),
        Arguments.of("1,1,-1", 0.4, "1.399x^1.6 + 1.119x^0.6"),
        Arguments.of("1,1,-1", 0.5, "1.505x^1.5 + 1.128x^0.5"),
        Arguments.of("1,1,-1", 0.6, "1.610x^1.4 + 1.127x^0.4"),
        Arguments.of("1,1,-1", 0.7, "1.714x^1.3 + 1.114x^0.3"),
        Arguments.of("1,1,-1", 0.8, "1.815x^1.2 + 1.089x^0.2"),
        Arguments.of("1,1,-1", 0.9, "1.911x^1.1 + 1.051x^0.1"),
        Arguments.of("1,1,-1", 1.5, "2.257x^0.5"),

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
        Arguments.of("1,1,0", 1.5, "2.257x^0.5"),

        // Expression: x^2 + 0x + 1
        Arguments.of("1,0,1", 0.1, "1.094x^1.9"),
        Arguments.of("1,0,1", 0.2, "1.193x^1.8"),
        Arguments.of("1,0,1", 0.3, "1.295x^1.7"),
        Arguments.of("1,0,1", 0.4, "1.399x^1.6"),
        Arguments.of("1,0,1", 0.5, "1.505x^1.5"),
        Arguments.of("1,0,1", 0.6, "1.610x^1.4"),
        Arguments.of("1,0,1", 0.7, "1.714x^1.3"),
        Arguments.of("1,0,1", 0.8, "1.815x^1.2"),
        Arguments.of("1,0,1", 0.9, "1.911x^1.1"),
        Arguments.of("1,0,1", 1.5, "2.257x^0.5"),

        // Expression: 0x^2 + x + 1
        Arguments.of("0,1,1", 0.1, "1.040x^0.9"),
        Arguments.of("0,1,1", 0.2, "1.074x^0.8"),
        Arguments.of("0,1,1", 0.3, "1.101x^0.7"),
        Arguments.of("0,1,1", 0.4, "1.119x^0.6"),
        Arguments.of("0,1,1", 0.5, "1.128x^0.5"),
        Arguments.of("0,1,1", 0.6, "1.127x^0.4"),
        Arguments.of("0,1,1", 0.7, "1.114x^0.3"),
        Arguments.of("0,1,1", 0.8, "1.089x^0.2"),
        Arguments.of("0,1,1", 0.9, "1.051x^0.1"),
        Arguments.of("0,1,1", 1.5, "0"));
  }
}
