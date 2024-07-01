package com.trbaxter.github.fractionalcomputationapi.utils.expressionparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;

class ExpressionParserTest {

  private String polynomial;
  private List<Term> terms;

  @Test
  void givenValidPolynomial_whenParsed_thenCorrectTermsReturned() {
    polynomial = "3x^2.3543 + 2x^-0.005 + 1";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("3"), new BigDecimal("2.3543")),
        new Term(new BigDecimal("2"), new BigDecimal("-0.005")),
        new Term(new BigDecimal("1"), BigDecimal.ZERO));
  }

  @Test
  void givenValidPolynomialWithParentheses_whenParsed_thenCorrectTermsReturned() {
    polynomial = "3x^(2.3543) + 2x^(-0.005) + 1";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("3"), new BigDecimal("2.3543")),
        new Term(new BigDecimal("2"), new BigDecimal("-0.005")),
        new Term(new BigDecimal("1"), BigDecimal.ZERO));
  }

  @Test
  void givenSingleTermPolynomial_whenParsed_thenCorrectTermReturned() {
    polynomial = "5x^3";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(terms, new Term(new BigDecimal("5"), new BigDecimal("3")));
  }

  @Test
  void givenConstantPolynomial_whenParsed_thenCorrectTermReturned() {
    polynomial = "7";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(terms, new Term(new BigDecimal("7"), BigDecimal.ZERO));
  }

  @Test
  void givenPolynomialWithNegativeCoefficients_whenParsed_thenCorrectTermsReturned() {
    polynomial = "-4x^2 - 3x + 2";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("-4"), new BigDecimal("2")),
        new Term(new BigDecimal("-3"), BigDecimal.ONE),
        new Term(new BigDecimal("2"), BigDecimal.ZERO));
  }

  @Test
  void givenPolynomialWithSpaces_whenParsed_thenCorrectTermsReturned() {
    polynomial = " 2x^2  + 3x - 4 ";
    terms = ExpressionParser.parse(polynomial);

    assertTerms(
        terms,
        new Term(new BigDecimal("2"), new BigDecimal("2")),
        new Term(new BigDecimal("3"), BigDecimal.ONE),
        new Term(new BigDecimal("-4"), BigDecimal.ZERO));
  }

  @Test
  void givenInvalidPolynomial_whenParsed_thenExceptionThrown() {
    polynomial = "2x^2 + 3y - 4";

    assertThrows(BadRequestException.class, () -> ExpressionParser.parse(polynomial));
  }

  @Test
  void testPrivateConstructor() throws Exception {
    Constructor<ExpressionParser> testConstructor = ExpressionParser.class.getDeclaredConstructor();
    testConstructor.setAccessible(true);

    assertThrows(InvocationTargetException.class, testConstructor::newInstance);
  }

  private void assertTerms(List<Term> actualTerms, Term... expectedTerms) {
    assertEquals(expectedTerms.length, actualTerms.size());
    for (int i = 0; i < expectedTerms.length; i++) {
      assertEquals(expectedTerms[i], actualTerms.get(i));
    }
  }
}
