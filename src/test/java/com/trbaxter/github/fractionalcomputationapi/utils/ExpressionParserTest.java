package com.trbaxter.github.fractionalcomputationapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ExpressionParserTest {

  private String polynomial;
  private List<Term> terms;

  @Test
  public void givenValidPolynomial_whenParsed_thenCorrectTermsReturned() {
    polynomial = "3x^2.3543 + 2x^-0.005 + 1";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("3"), new BigDecimal("2.3543")),
        new Term(new BigDecimal("2"), new BigDecimal("-0.005")),
        new Term(new BigDecimal("1"), BigDecimal.ZERO));
  }

  @Test
  public void givenValidPolynomialWithParentheses_whenParsed_thenCorrectTermsReturned() {
    polynomial = "3x^(2.3543) + 2x^(-0.005) + 1";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("3"), new BigDecimal("2.3543")),
        new Term(new BigDecimal("2"), new BigDecimal("-0.005")),
        new Term(new BigDecimal("1"), BigDecimal.ZERO));
  }

  @Test
  public void givenSingleTermPolynomial_whenParsed_thenCorrectTermReturned() {
    polynomial = "5x^3";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(terms, new Term(new BigDecimal("5"), new BigDecimal("3")));
  }

  @Test
  public void givenConstantPolynomial_whenParsed_thenCorrectTermReturned() {
    polynomial = "7";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(terms, new Term(new BigDecimal("7"), BigDecimal.ZERO));
  }

  @Test
  public void givenPolynomialWithNegativeCoefficients_whenParsed_thenCorrectTermsReturned() {
    polynomial = "-4x^2 - 3x + 2";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("-4"), new BigDecimal("2")),
        new Term(new BigDecimal("-3"), BigDecimal.ONE),
        new Term(new BigDecimal("2"), BigDecimal.ZERO));
  }

  @Test
  public void givenPolynomialWithSpaces_whenParsed_thenCorrectTermsReturned() {
    polynomial = " 2x^2  + 3x - 4 ";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("2"), new BigDecimal("2")),
        new Term(new BigDecimal("3"), BigDecimal.ONE),
        new Term(new BigDecimal("-4"), BigDecimal.ZERO));
  }

  @Test
  public void givenInvalidPolynomial_whenParsed_thenExceptionThrown() {
    polynomial = "2x^2 + 3y - 4";

    assertThrows(IllegalArgumentException.class, () -> ExpressionParser.parse(polynomial));
  }

  private void assertTerms(List<Term> actualTerms, Term... expectedTerms) {
    assertEquals(expectedTerms.length, actualTerms.size());
    for (int i = 0; i < expectedTerms.length; i++) {
      assertEquals(expectedTerms[i], actualTerms.get(i));
    }
  }
}
