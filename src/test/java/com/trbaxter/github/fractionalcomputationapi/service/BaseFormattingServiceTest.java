package com.trbaxter.github.fractionalcomputationapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseFormattingServiceTest {

  private TestFormattingService formattingService;

  @BeforeEach
  public void setUp() {
    formattingService = new TestFormattingService();
  }

  @Test
  void testFormatTerms_AllTermsSkipped() {
    List<Term> terms =
        List.of(
            new Term(BigDecimal.valueOf(1), BigDecimal.ONE),
            new Term(BigDecimal.valueOf(2), BigDecimal.TEN));

    String result = formattingService.formatTerms(terms, 2);
    assertEquals(
        "0", result, "The result should be zero polynomial result when all terms are skipped");
  }

  @Test
  void testFormatTerms_AllZeroCoefficients() {
    List<Term> terms =
        List.of(
            new Term(BigDecimal.ZERO, BigDecimal.ONE), new Term(BigDecimal.ZERO, BigDecimal.TEN));

    String result = formattingService.formatTerms(terms, 2);
    assertEquals(
        "0", result, "The result should be zero polynomial result for all zero coefficients");
  }

  @Test
  void testAppendTerm_CoefficientZero() {
    StringBuilder result = new StringBuilder();
    Term term = new Term(BigDecimal.ZERO, BigDecimal.ONE);
    String coefficientString = "0";

    // Inner class to access the protected method
    class AccessibleFormattingService extends TestFormattingService {
      public void callAppendTerm(StringBuilder result, Term term, String coefficientString) {
        appendTerm(result, term, coefficientString);
      }
    }

    AccessibleFormattingService accessibleService = new AccessibleFormattingService();
    accessibleService.callAppendTerm(result, term, coefficientString);

    assertEquals("", result.toString(), "The result should be empty when coefficient is zero");
  }
}
