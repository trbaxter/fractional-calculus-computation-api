package com.trbaxter.github.fractionalcomputationapi.utils;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpressionParser {

  private static final Logger logger = LoggerFactory.getLogger(ExpressionParser.class);
  private static final Pattern TERM_PATTERN =
      Pattern.compile("([+-]?[^-+]*x(?:\\^\\(?-?[0-9.]+\\)?)?|[+-]?[^-+]+)");
  private static final String WHITESPACE_REGEX = "\\s+";
  private static final String SPECIAL_CHARACTERS_REGEX = "[\\[\\]{}()]";
  private static final String X = "x";
  private static final String POWER = "x\\^?";
  private static final String PLUS = "+";
  private static final String MINUS = "-";
  private static final String EMPTY_STRING = "";

  /**
   * Parses a polynomial string into a list of terms.
   *
   * @param polynomial the polynomial string to parse.
   * @return a list of terms.
   * @throws IllegalArgumentException if the input is invalid.
   */
  public static List<Term> parse(String polynomial) {
    validateInput(polynomial);

    List<Term> terms = new ArrayList<>();
    Matcher matcher =
        TERM_PATTERN.matcher(
            polynomial
                .replaceAll(WHITESPACE_REGEX, EMPTY_STRING)
                .replaceAll(SPECIAL_CHARACTERS_REGEX, EMPTY_STRING));

    while (matcher.find()) {
      String termString = matcher.group(1);
      terms.add(parseTerm(termString));
    }

    return terms;
  }

  private static void validateInput(String polynomial) {
    if (polynomial == null || polynomial.trim().isEmpty()) {
      logger.error("Polynomial expression is null or empty.");
      throw new IllegalArgumentException("Polynomial expression cannot be null or empty.");
    }
  }

  private static Term parseTerm(String term) {
    BigDecimal coefficient;
    BigDecimal power;

    if (term.contains(X)) {
      String[] parts = term.split(POWER);
      coefficient = parseCoefficient(parts);
      power = parsePower(parts);
    } else {
      coefficient = new BigDecimal(term);
      power = BigDecimal.ZERO;
    }

    return new Term(coefficient, power);
  }

  private static BigDecimal parseCoefficient(String[] parts) {
    if (parts.length == 0 || parts[0].isEmpty() || parts[0].equals(PLUS)) {
      return BigDecimal.ONE;
    } else if (parts[0].equals(MINUS)) {
      return BigDecimal.ONE.negate();
    } else {
      return new BigDecimal(parts[0]);
    }
  }

  private static BigDecimal parsePower(String[] parts) {
    String powerStr =
        (parts.length > 1) ? parts[1].replace("(", EMPTY_STRING).replace(")", EMPTY_STRING) : "1";
    return new BigDecimal(powerStr);
  }
}
