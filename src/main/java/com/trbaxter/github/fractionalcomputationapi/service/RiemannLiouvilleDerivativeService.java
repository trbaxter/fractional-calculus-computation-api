package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class RiemannLiouvilleDerivativeService implements DerivativeService {

    @Override
    public String computeDerivative(double[] coefficients, double alpha) {
        List<Term> terms = computeTerms(coefficients, alpha);
        return formatTerms(terms);
    }

    private List<Term> computeTerms(double[] coefficients, double alpha) {
        List<Term> terms = new ArrayList<>();
        int degree = coefficients.length - 1;

        for (int i = 0; i <= degree; i++) {
            double coefficient = coefficients[i];
            if (coefficient != 0) {
                double k = degree - i; // k is the current term's degree
                double gammaNumerator = MathUtils.gamma(k + 1); // Gamma(k + 1)
                double gammaDenominator = MathUtils.gamma(k + 1 - alpha); // Gamma(k + 1 - alpha)
                if (gammaDenominator != 0) {
                    double gammaCoefficient = gammaNumerator / gammaDenominator;
                    double newCoefficient = coefficient * gammaCoefficient;
                    double newPower = k - alpha; // k - alpha
                    terms.add(new Term(newCoefficient, newPower));
                }
            }
        }

        terms.sort(Comparator.comparingDouble(Term::power).reversed());
        return terms;
    }

    private String formatTerms(List<Term> terms) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < terms.size(); i++) {
            Term term = terms.get(i);
            if (i > 0) {
                if (term.coefficient() > 0) {
                    result.append(" + ");
                } else {
                    result.append(" - ");
                }
                result.append(String.format("%.3f", Math.abs(term.coefficient())));
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