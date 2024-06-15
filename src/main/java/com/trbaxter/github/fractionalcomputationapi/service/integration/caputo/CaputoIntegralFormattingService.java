package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import static org.apache.commons.math3.special.Gamma.gamma;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CaputoIntegralFormattingService {

  public String formatTerms(List<Term> terms, double alpha) {
    StringBuilder result = new StringBuilder();
    boolean integerAlpha = BigDecimal.valueOf(alpha).stripTrailingZeros().scale() <= 0;
    int alphaInt = (int) alpha;

    for (int i = 0; i < terms.size(); i++) {
      Term term = terms.get(i);
      BigDecimal coefficient = term.coefficient().stripTrailingZeros();
      boolean omitCoefficient =
          coefficient.compareTo(BigDecimal.ONE) == 0
              || coefficient.compareTo(BigDecimal.ONE.negate()) == 0;

      if (i > 0) {
        if (coefficient.compareTo(BigDecimal.ZERO) > 0) {
          result.append(" + ");
        } else {
          result.append(" - ");
        }
        if (!omitCoefficient) {
          result.append(
              coefficient
                  .abs()
                  .setScale(3, RoundingMode.HALF_UP)
                  .stripTrailingZeros()
                  .toPlainString());
        }
      } else {
        String roundThreeSigFigsStripZeros =
            coefficient.setScale(3, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        if (coefficient.compareTo(BigDecimal.ZERO) < 0) {
          result.append(roundThreeSigFigsStripZeros);
        } else {
          if (!omitCoefficient) {
            result.append(roundThreeSigFigsStripZeros);
          }
        }
      }

      if (term.power().compareTo(BigDecimal.ZERO) != 0) {
        result.append("x");
        if (term.power().compareTo(BigDecimal.ONE) != 0) {
          result.append("^").append(term.power().stripTrailingZeros().toPlainString());
        }
      }
    }

    if (integerAlpha && alphaInt > 1) {
      appendConstantsOfIntegration(result, alphaInt);
    } else if (integerAlpha && alphaInt == 1) {
      if (!result.isEmpty()) result.append(" + ");
      result.append("C");
    }

    return result.toString();
  }

  private void appendConstantsOfIntegration(StringBuilder result, int alphaInt) {
    for (int i = 0; i < alphaInt; i++) {
      if (!result.isEmpty()) result.append(" + ");
      char constant = (char) ('C' + i);
      BigDecimal constantCoefficient;

      if (i == 0) {
        constantCoefficient = BigDecimal.valueOf(1.0 / gamma(alphaInt - 1 + 1));
      } else {
        constantCoefficient = BigDecimal.valueOf(1.0 / gamma(alphaInt - 1 - i + 1));
      }

      int power = alphaInt - i - 1;

      if (constantCoefficient.compareTo(BigDecimal.ONE) == 0) {
        result.append(constant);
      } else {
        result
            .append(
                constantCoefficient
                    .setScale(3, RoundingMode.HALF_UP)
                    .stripTrailingZeros()
                    .toPlainString())
            .append(constant);
      }

      if (power > 0) {
        result.append("x");
        if (power > 1) {
          result.append("^").append(power);
        }
      }
    }
  }
}
