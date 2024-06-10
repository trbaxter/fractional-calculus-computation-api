package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class ComputationService {

	public String caputoFractionalDerivative(double[] coefficients, double alpha) {
		List<Term> terms = new ArrayList<>();
		int degree = coefficients.length - 1;

		for (int i = 0; i <= degree; i++) {
			double coefficient = coefficients[i];  // Highest degree term comes first
			if (coefficient != 0) {
				double gammaNumerator = MathUtils.gamma(degree - i + 1);
				double gammaDenominator = MathUtils.gamma(degree - i + 1 - alpha);
				if (gammaDenominator != 0) {
					double gammaCoefficient = gammaNumerator / gammaDenominator;
					double newCoefficient = coefficient * gammaCoefficient;
					double newPower = degree - i - alpha;
					if (newPower >= 0) {
						terms.add(new Term(newCoefficient, newPower));
					}
				}
			}
		}

		// Sort terms by the power in descending order
		terms.sort(Comparator.comparingDouble(Term::power).reversed());

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < terms.size(); i++) {
			Term term = terms.get(i);
			if (i > 0) {
				if (term.coefficient() > 0) {
					result.append(" + ");
					result.append(String.format("%.3f", term.coefficient()));
				} else {
					result.append(" - ");
					result.append(String.format("%.3f", Math.abs(term.coefficient())));
				}
			} else {
				if (term.coefficient() < 0) {
					result.append("- ");
					result.append(String.format("%.3f", Math.abs(term.coefficient())));
				} else {
					result.append(String.format("%.3f", term.coefficient()));
				}
			}
			result.append("x^").append(String.format("%.3f", term.power()));
		}

		return result.toString();
	}
}



