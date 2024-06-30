package com.trbaxter.github.fractionalcomputationapi.service.integration;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.BaseFormattingService;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * CaputoIntegralFormattingService is a service that provides formatting for the results of Caputo
 * fractional integrals. It extends the BaseFormattingService to apply specific rules for Caputo
 * integrals.
 */
@Service
public class IntegralFormattingService extends BaseFormattingService {

  @Override
  protected boolean shouldSkipTerm(Term term) {
    return false;
  }

  @Override
  protected String getZeroPolynomialResult() {
    return "C";
  }

  public String formatTerms(List<Term> terms, double alpha, int precision) {
    String result = super.formatTerms(terms, precision);
    boolean integerAlpha = BigDecimal.valueOf(alpha).stripTrailingZeros().scale() <= 0;
    int alphaInt = (int) alpha;

    if (integerAlpha) {
      result = appendConstantsOfIntegration(result, alphaInt, precision);
    } else {
      if (!result.isEmpty()) result += " + ";
      result += "C";
    }

    return result;
  }

  private String appendConstantsOfIntegration(String result, int alphaInt, int precision) {
    StringBuilder sb = new StringBuilder(result);
    for (int i = 0; i < alphaInt; i++) {
      if (!sb.isEmpty()) sb.append(" + ");
      char constant = (char) ('C' + i);
      BigDecimal constantCoefficient = computeConstantCoefficient(i, alphaInt, precision);

      String coefficientStr = formatCoefficientString(constantCoefficient, precision);
      if (!coefficientStr.isEmpty()) {
        sb.append(coefficientStr);
      }
      sb.append(constant);
      appendPower(sb, alphaInt, i);
    }
    return sb.toString();
  }

  private BigDecimal computeConstantCoefficient(int i, int alphaInt, int precision) {
    if (i == 0) {
      return BigDecimal.ONE.divide(
          MathUtils.gamma(BigDecimal.valueOf(alphaInt)), precision, RoundingMode.HALF_UP);
    } else {
      return BigDecimal.ONE.divide(
          MathUtils.gamma(BigDecimal.valueOf(alphaInt - i)), precision, RoundingMode.HALF_UP);
    }
  }

  private String formatCoefficientString(BigDecimal constantCoefficient, int precision) {
    String coefficientStr =
        constantCoefficient.setScale(precision, RoundingMode.HALF_UP).toPlainString();
    if (coefficientStr.contains(".")) {
      String[] parts = coefficientStr.split("\\.");
      if (parts.length == 2 && parts[1].matches("0{" + precision + "}")) {
        coefficientStr = parts[0];
      }
    }
    return constantCoefficient.compareTo(BigDecimal.ONE) == 0 ? "" : coefficientStr;
  }

  private void appendPower(StringBuilder sb, int alphaInt, int i) {
    int power = alphaInt - i - 1;
    if (power > 0) {
      sb.append("x");
      if (power > 1) {
        sb.append("^").append(power);
      }
    }
  }
}
