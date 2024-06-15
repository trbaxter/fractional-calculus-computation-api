package com.trbaxter.github.fractionalcomputationapi.service.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class TermTest {

  private static Stream<Arguments> provideValidTerms() {
    return Stream.of(
        Arguments.of(new BigDecimal("3.5"), new BigDecimal("2.0")),
        Arguments.of(new BigDecimal("0.0"), new BigDecimal("0.0")),
        Arguments.of(new BigDecimal("-1.5"), new BigDecimal("3.0")));
  }

  @ParameterizedTest
  @MethodSource("provideValidTerms")
  public void testTermRecord(BigDecimal coefficient, BigDecimal power) {
    Term term = new Term(coefficient, power);
    assertEquals(coefficient, term.coefficient());
    assertEquals(power, term.power());
  }

  @Test
  public void testTermRecordNullValues() {
    assertThrows(NullPointerException.class, () -> new Term(null, new BigDecimal("2.0")));
    assertThrows(NullPointerException.class, () -> new Term(new BigDecimal("3.5"), null));
  }
}
