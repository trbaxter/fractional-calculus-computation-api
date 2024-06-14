package com.trbaxter.github.fractionalcomputationapi.service.derivation;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class RiemannLiouvilleDerivativeService implements FractionalCalculusService {
	private static final Logger logger = Logger.getLogger(RiemannLiouvilleDerivativeService.class.getName());

	@Override
	public String evaluateExpression(double[] coefficients, double alpha) {
		logger.info(
				"Computing derivative with coefficients: " + Arrays.toString(coefficients) + " and alpha: " + alpha);
		List<Term> terms = computeTerms(coefficients, BigDecimal.valueOf(alpha));
		return formatTerms(terms);
	}

	private List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
		List<Term> terms = new ArrayList<>();
		int degree = coefficients.length - 1;

		for (int i = 0; i <= degree; i++) {
			BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
			if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
				try {
					BigDecimal k = BigDecimal.valueOf(degree - i);
					BigDecimal gammaNumerator = MathUtils.gamma(k.add(BigDecimal.ONE));
					BigDecimal gammaDenominator = MathUtils.gamma(k.add(BigDecimal.ONE).subtract(alpha));
					logger.info(String.format("Term %d: gammaNumerator = %s, gammaDenominator = %s", i, gammaNumerator,
							gammaDenominator));

					if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
						BigDecimal gammaCoefficient = gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
						BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
						BigDecimal newPower = k.subtract(alpha);
						terms.add(new Term(newCoefficient, newPower));
					}
				} catch (ArithmeticException e) {
					logger.severe(String.format("Arithmetic error computing term %d: %s", i, e.getMessage()));
				} catch (Exception e) {
					logger.severe(String.format("Unexpected error computing term %d: %s", i, e.getMessage()));
				}
			}
		}

		terms.sort(Comparator.comparing(Term::power).reversed());
		return terms;
	}

	private String formatTerms(List<Term> terms) {
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < terms.size(); i++) {
			Term term = terms.get(i);
			if (i > 0) {
				if (term.coefficient().compareTo(BigDecimal.ZERO) > 0) {
					result.append(" + ");
				} else {
					result.append(" - ");
				}
				result.append(term.coefficient().abs().setScale(3, RoundingMode.HALF_UP));
			} else {
				if (term.coefficient().compareTo(BigDecimal.ZERO) < 0) {
					result.append("- ");
					result.append(term.coefficient().abs().setScale(3, RoundingMode.HALF_UP));
				} else {
					result.append(term.coefficient().setScale(3, RoundingMode.HALF_UP));
				}
			}

			// Check if power is zero, and if so, only append the coefficient
			if (term.power().compareTo(BigDecimal.ZERO) != 0) {
				// Remove the trailing zeros from exponents in output
				String exponent = term.power().stripTrailingZeros().toPlainString();
				result.append("x^").append(exponent);
			}
		}
		return result.toString();
	}
}
