package com.trbaxter.github.fractionalcomputationapi.utils;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
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

  // Patterns for different parts of the polynomial terms
  private static final Pattern TERM_WITH_X_PATTERN =
      Pattern.compile("[+-]?[^-+]*x(?:\\^\\(?-?[0-9.]+\\)?)?");
  private static final Pattern CONSTANT_TERM_PATTERN = Pattern.compile("[+-]?[^-+]+");
  private static final Pattern TERM_PATTERN =
      Pattern.compile(String.format("%s|%s", TERM_WITH_X_PATTERN, CONSTANT_TERM_PATTERN));

  // Regex patterns and constants for cleaning and parsing
  private static final String WHITESPACE_REGEX = "\\s+";
  private static final String SPECIAL_CHARACTERS_REGEX = "[\\[\\]{}()]";
  private static final String X = "x";
  private static final String POWER = "x\\^?";
  private static final String PLUS = "+";
  private static final String MINUS = "-";
  private static final String EMPTY_STRING = "";

  // Private constructor to prevent instantiation
  private ExpressionParser() {
    throw new UnsupportedOperationException("Utility class");
  }

  /**
   * Parses a polynomial string into a list of terms.
   *
   * @param polynomial the polynomial string to parse.
   * @return a list of terms.
   * @throws BadRequestException if the input is invalid.
   */
  public static List<Term> parse(String polynomial) {
    validateInput(polynomial);

    String cleanedPolynomial = cleanPolynomial(polynomial);
    List<Term> terms = new ArrayList<>();
    Matcher matcher = TERM_PATTERN.matcher(cleanedPolynomial);

    while (matcher.find()) {
      String termString = matcher.group();
      terms.add(parseTerm(termString));
    }

    return terms;
  }

  /**
   * Validates the input polynomial string.
   *
   * @param polynomial the polynomial string to validate.
   * @throws BadRequestException if the input is invalid.
   */
  private static void validateInput(String polynomial) {
    if (polynomial == null || polynomial.trim().isEmpty()) {
      logger.error("Polynomial expression is null or empty.");
      throw new BadRequestException("Polynomial expression cannot be null or empty.");
    }
    if (!polynomial.matches("[0-9xX^+\\-*.()\\s]+")) {
      logger.error("Polynomial expression contains invalid characters.");
      throw new BadRequestException("Polynomial expression contains invalid characters.");
    }
  }

  /**
   * Cleans the input polynomial string by removing whitespace and special characters.
   *
   * @param polynomial the polynomial string to clean.
   * @return the cleaned polynomial string.
   */
  private static String cleanPolynomial(String polynomial) {
    return polynomial
        .replaceAll(WHITESPACE_REGEX, EMPTY_STRING)
        .replaceAll(SPECIAL_CHARACTERS_REGEX, EMPTY_STRING);
  }

  /**
   * Parses a term string into a Term object.
   *
   * @param term the term string to parse.
   * @return the parsed Term object.
   */
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

  /**
   * Parses the coefficient from the term parts.
   *
   * @param parts the parts of the term split by the power symbol.
   * @return the parsed coefficient as BigDecimal.
   */
  private static BigDecimal parseCoefficient(String[] parts) {
    if (parts.length == 0 || parts[0].isEmpty() || parts[0].equals(PLUS)) {
      return BigDecimal.ONE;
    } else if (parts[0].equals(MINUS)) {
      return BigDecimal.ONE.negate();
    } else {
      return new BigDecimal(parts[0]);
    }
  }

  /**
   * Parses the power from the term parts.
   *
   * @param parts the parts of the term split by the power symbol.
   * @return the parsed power as BigDecimal.
   */
  private static BigDecimal parsePower(String[] parts) {
    String powerStr =
        (parts.length > 1) ? parts[1].replace("(", EMPTY_STRING).replace(")", EMPTY_STRING) : "1";
    return new BigDecimal(powerStr);
  }
}
