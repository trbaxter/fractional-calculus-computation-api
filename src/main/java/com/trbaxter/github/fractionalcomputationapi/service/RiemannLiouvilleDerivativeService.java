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

@Service
public class RiemannLiouvilleDerivativeService implements DerivativeService {

    @Override
    public String computeDerivative(double[] coefficients, double alpha) {
        List<Term> terms = computeTerms(coefficients, BigDecimal.valueOf(alpha));
        return formatTerms(terms);
    }

    private List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
        List<Term> terms = new ArrayList<>();
        int degree = coefficients.length - 1;

        for (int i = 0; i <= degree; i++) {
            BigDecimal coefficient = BigDecimal.valueOf(coefficients[i]);
            if (coefficient.compareTo(BigDecimal.ZERO) != 0) {
                BigDecimal k = BigDecimal.valueOf(degree - i);
                BigDecimal gammaNumerator = MathUtils.gamma(k.add(BigDecimal.ONE));
                BigDecimal gammaDenominator = MathUtils.gamma(k.add(BigDecimal.ONE).subtract(alpha));
                if (gammaDenominator.compareTo(BigDecimal.ZERO) != 0) {
                    BigDecimal gammaCoefficient = gammaNumerator.divide(gammaDenominator, MathContext.DECIMAL128);
                    BigDecimal newCoefficient = coefficient.multiply(gammaCoefficient);
                    BigDecimal newPower = k.subtract(alpha);
                    terms.add(new Term(newCoefficient, newPower));
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
            result.append("x^").append(term.power().setScale(3, RoundingMode.HALF_UP));
        }
        return result.toString();
    }
}