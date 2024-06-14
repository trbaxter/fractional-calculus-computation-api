package com.trbaxter.github.fractionalcomputationapi.service.derivation;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

@Service
public class CaputoDerivativeService implements FractionalCalculusService {
	private static final Logger logger = Logger.getLogger(CaputoDerivativeService.class.getName());

	@Override
	public String evaluateExpression(double[] coefficients, double alpha) {
		List<Term> terms = computeTerms(coefficients, BigDecimal.valueOf(alpha));
		return formatTerms(terms);
	}

	private List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
		List<Term> terms = new ArrayList<>();
		int degree = coefficients.length - 1;

		if (alpha.stripTrailingZeros().scale() <= 0) {
			computeIntegerOrderTerms(coefficients, alpha.intValue(), terms, degree);
		} else {
			computeFractionalOrderTerms(coefficients, alpha, terms, degree);
		}

		terms.sort(Comparator.comparing(Term::power).reversed());
		return terms;
	}

	private void computeIntegerOrderTerms(double[] coefficients, int intAlpha, List<Term> terms, int degree) {
		for (int i = 0; i <= degree; i++) {
			BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
			if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
				int newDegree = degree - i + intAlpha;
				BigDecimal newCoefficient = coefficient.divide(
						BigDecimal.valueOf(factorial(newDegree) / factorial(newDegree - intAlpha)),
						MathContext.DECIMAL128);
				terms.add(new Term(newCoefficient, BigDecimal.valueOf(newDegree)));
			}
		}
	}

	private void computeFractionalOrderTerms(double[] coefficients, BigDecimal alpha, List<Term> terms, int degree) {
		for (int i = 0; i <= degree; i++) {
			BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
			if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
				try {
					BigDecimal gammaNumerator = MathUtils.gamma(BigDecimal.valueOf(degree - i + alpha.doubleValue()));
					BigDecimal gammaDenominator = MathUtils.gamma(BigDecimal.valueOf(degree - i + 1));
					logger.info(String.format("Term %d: gammaNumerator = %s, gammaDenominator = %s", i, gammaNumerator,
							gammaDenominator));

					if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
						BigDecimal gammaCoefficient = gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
						BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
						BigDecimal newPower = BigDecimal.valueOf(degree - i).add(alpha);
						logger.info(String.format("Term %d: newCoefficient = %s, newPower = %s", i, newCoefficient,
								newPower));
						terms.add(new Term(newCoefficient, newPower));
					}
				} catch (Exception e) {
					logger.severe(String.format("Error computing term %d: %s", i, e.getMessage()));
				}
			}
		}
	}

	private int factorial(int n) {
		if (n <= 1)
			return 1;
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

			if (term.power().compareTo(BigDecimal.ZERO) != 0) {
				String exponent = term.power().stripTrailingZeros().toPlainString();
				result.append("x^").append(exponent);
			}
		}
		return result.toString();
	}
}
