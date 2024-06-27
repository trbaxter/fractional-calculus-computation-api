package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import static org.apache.commons.math3.special.Gamma.gamma;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.BaseFormattingService;
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
public class CaputoIntegralFormattingService extends BaseFormattingService {


  @Override
  protected boolean shouldSkipTerm(Term term) {
    return false;
  }

  @Override
  protected String getZeroPolynomialResult() {
    return "C";
  }


  public String formatTerms(List<Term> terms, double alpha) {
    String result = super.formatTerms(terms);

    boolean integerAlpha = BigDecimal.valueOf(alpha).stripTrailingZeros().scale() <= 0;
    int alphaInt = (int) alpha;

    if (integerAlpha) {
      if (alphaInt > 1) {
        result = appendConstantsOfIntegration(result, alphaInt);
      } else if (alphaInt == 1) {
        if (!result.isEmpty()) result += " + ";
        result += "C";
      }
    } else {
      if (!result.isEmpty()) result += " + ";
      result += "C";
    }

    return result;
  }


  private String appendConstantsOfIntegration(String result, int alphaInt) {
    StringBuilder sb = new StringBuilder(result);
    for (int i = 0; i < alphaInt; i++) {
      if (!sb.isEmpty()) sb.append(" + ");
      char constant = (char) ('C' + i);
      BigDecimal constantCoefficient;

      if (i == 0) {
        constantCoefficient = BigDecimal.valueOf(1.0 / gamma(alphaInt - 1 + 1));
      } else {
        constantCoefficient = BigDecimal.valueOf(1.0 / gamma(alphaInt - 1 - i + 1));
      }

      int power = alphaInt - i - 1;

      if (constantCoefficient.compareTo(BigDecimal.ONE) == 0) {
        sb.append(constant);
      } else {
        sb.append(
                constantCoefficient
                    .setScale(3, RoundingMode.HALF_UP)
                    .stripTrailingZeros()
                    .toPlainString())
            .append(constant);
      }

      if (power > 0) {
        sb.append("x");
        if (power > 1) {
          sb.append("^").append(power);
        }
      }
    }
    return sb.toString();
  }
}