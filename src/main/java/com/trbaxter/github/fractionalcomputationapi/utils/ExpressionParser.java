package com.trbaxter.github.fractionalcomputationapi.utils;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionParser {
  private static final Pattern TERM_PATTERN =
      Pattern.compile("([+-]?[^-+]*x(?:\\^\\(?-?[0-9.]+\\)?)?|[+-]?[^-+]+)");

  /**
   * Parses a polynomial string into a list of terms.
   *
   * @param polynomial the polynomial string to parse.
   * @return a list of terms.
   * @throws IllegalArgumentException if the input is invalid.
   */
  public static List<Term> parse(String polynomial) {
    List<Term> terms = new ArrayList<>();
    Matcher matcher = TERM_PATTERN.matcher(polynomial.replaceAll("\\s+", ""));

    while (matcher.find()) {
      String termString = matcher.group(1);
      terms.add(parseTerm(termString));
    }

    return terms;
  }

  private static Term parseTerm(String term) {
    BigDecimal coefficient;
    BigDecimal power;

    if (term.contains("x")) {
      String[] parts = term.split("x\\^?");
      if (parts.length == 0 || parts[0].isEmpty() || parts[0].equals("+")) {
        coefficient = BigDecimal.ONE;
      } else if (parts[0].equals("-")) {
        coefficient = BigDecimal.ONE.negate();
      } else {
        coefficient = new BigDecimal(parts[0]);
      }
      String powerStr = parts.length > 1 ? parts[1].replace("(", "").replace(")", "") : "1";
      power = new BigDecimal(powerStr);
    } else {
      coefficient = new BigDecimal(term);
      power = BigDecimal.ZERO;
    }

    return new Term(coefficient, power);
  }
}
