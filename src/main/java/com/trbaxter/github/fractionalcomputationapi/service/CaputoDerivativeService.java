package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CaputoDerivativeService implements DerivativeService {
	private static final Logger logger = Logger.getLogger(CaputoDerivativeService.class.getName());

	@Override
	public String computeDerivative(double[] coefficients, double alpha) {
		List<Term> terms = computeTerms(coefficients, BigDecimal.valueOf(alpha));
		return formatTerms(terms);
	}

	private List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
		List<Term> terms = new ArrayList<>();
		int degree = coefficients.length - 1;

		if (alpha.stripTrailingZeros().scale() <= 0) {

			// If order is an integer, handle as regular derivative
			int intAlpha = alpha.intValue();
			for (int i = 0; i <= degree; i++) {
				BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
				if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
					int newDegree = degree - i - intAlpha;
					if (newDegree >= 0) {
						BigDecimal newCoefficient = coefficient.multiply(
								BigDecimal.valueOf(factorial(degree - i) /
								factorial(degree - i - intAlpha)));
						terms.add(new Term(newCoefficient, BigDecimal.valueOf(newDegree)));
					}
				}
			}
		} else {

			// If order is a non-integer, use the gamma function
			for (int i = 0; i <= degree; i++) {
				BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
				if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
					try {
						BigDecimal gammaNumerator = MathUtils.gamma(BigDecimal.valueOf(degree - i + 1));
						BigDecimal gammaDenominator = MathUtils.gamma(BigDecimal.valueOf(degree - i + 1)
															   .subtract(alpha));
						logger.info(String.format("Term %d: gammaNumerator = %s, gammaDenominator = %s",
															i, gammaNumerator, gammaDenominator));

						if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
							BigDecimal gammaCoefficient = gammaNumerator.divide(gammaDenominator,
																				MathContext.DECIMAL128);
							BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
							BigDecimal newPower = BigDecimal.valueOf(degree - i).subtract(alpha);
							logger.info(String.format("Term %d: newCoefficient = %s, newPower = %s",
													   			i, newCoefficient, newPower));
							if (newPower.compareTo(BigDecimal.ZERO) >= 0) {
								terms.add(new Term(newCoefficient, newPower));
							}
						}
					} catch (Exception e) {
						logger.severe(String.format("Error computing term %d: %s", i, e.getMessage()));
					}
				}
			}
		}

		terms.sort(Comparator.comparing(Term::power).reversed());
		return terms;
	}

	private int factorial(int n) {
		if (n <= 1) return 1;
		return n * factorial(n - 1);
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

			// Remove the trailing zeros from exponents in output
			String exponent = term.power().stripTrailingZeros().toPlainString();
			result.append("x^").append(exponent);
		}
		return result.toString();
	}
}