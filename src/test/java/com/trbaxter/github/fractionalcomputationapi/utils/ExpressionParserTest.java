package com.trbaxter.github.fractionalcomputationapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExpressionParserTest {

  @Test
  public void testValidPolynomial() {
    String polynomial = "3x^2.3543 + 2x^-0.005 + 1";
    List<Term> terms = ExpressionParser.parse(polynomial);

    assertEquals(3, terms.size());
    assertEquals(new Term(new BigDecimal("3"), new BigDecimal("2.3543")), terms.get(0));
    assertEquals(new Term(new BigDecimal("2"), new BigDecimal("-0.005")), terms.get(1));
    assertEquals(new Term(new BigDecimal("1"), BigDecimal.ZERO), terms.get(2));
  }

  @Test
  public void testValidPolynomialWithParentheses() {
    String polynomial = "3x^(2.3543) + 2x^(-0.005) + 1";
    List<Term> terms = ExpressionParser.parse(polynomial);

    assertEquals(3, terms.size());
    assertEquals(new Term(new BigDecimal("3"), new BigDecimal("2.3543")), terms.get(0));
    assertEquals(new Term(new BigDecimal("2"), new BigDecimal("-0.005")), terms.get(1));
    assertEquals(new Term(new BigDecimal("1"), BigDecimal.ZERO), terms.get(2));
  }

  @Test
  public void testSingleTermPolynomial() {
    String polynomial = "5x^3";
    List<Term> terms = ExpressionParser.parse(polynomial);

    assertEquals(1, terms.size());
    assertEquals(new Term(new BigDecimal("5"), new BigDecimal("3")), terms.getFirst());
  }

  @Test
  public void testConstantPolynomial() {
    String polynomial = "7";
    List<Term> terms = ExpressionParser.parse(polynomial);

    assertEquals(1, terms.size());
    assertEquals(new Term(new BigDecimal("7"), BigDecimal.ZERO), terms.getFirst());
  }

  @Test
  public void testPolynomialWithNegativeCoefficients() {
    String polynomial = "-4x^2 - 3x + 2";
    List<Term> terms = ExpressionParser.parse(polynomial);

    assertEquals(3, terms.size());
    assertEquals(new Term(new BigDecimal("-4"), new BigDecimal("2")), terms.get(0));
    assertEquals(new Term(new BigDecimal("-3"), BigDecimal.ONE), terms.get(1));
    assertEquals(new Term(new BigDecimal("2"), BigDecimal.ZERO), terms.get(2));
  }

  @Test
  public void testPolynomialWithSpaces() {
    String polynomial = " 2x^2  + 3x - 4 ";
    List<Term> terms = ExpressionParser.parse(polynomial);

    assertEquals(3, terms.size());
    assertEquals(new Term(new BigDecimal("2"), new BigDecimal("2")), terms.get(0));
    assertEquals(new Term(new BigDecimal("3"), BigDecimal.ONE), terms.get(1));
    assertEquals(new Term(new BigDecimal("-4"), BigDecimal.ZERO), terms.get(2));
  }

  @Test
  public void testInvalidPolynomial() {
    String polynomial = "2x^2 + 3y - 4";

    assertThrows(IllegalArgumentException.class, () -> ExpressionParser.parse(polynomial));
  }
}
