// package com.trbaxter.github.fractionalcomputationapi.service.integration;
//
// import com.trbaxter.github.fractionalcomputationapi.model.Term;
// import
// com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
// import java.math.BigDecimal;
// import java.util.ArrayList;
// import java.util.Comparator;
// import java.util.List;
// import java.util.logging.Logger;
// import org.springframework.stereotype.Service;
//
// @Service
// public class RiemannLiouvilleIntegralService implements
// FractionalCalculusService {
//
// private static final Logger logger =
// Logger.getLogger(RiemannLiouvilleIntegralService.class.getName());
//
// @Override
// public String evaluateExpression(double[] coefficients, double alpha) {
// List<Term> terms = computeTerms(coefficients, BigDecimal.valueOf(alpha));
// return formatTerms(terms);
// }
//
// private List<Term> computeTerms(double[] coefficients, BigDecimal alpha) {
// List<Term> terms = new ArrayList<>();
// int degree = coefficients.length - 1;
//
// if (alpha.stripTrailingZeros().scale() <= 0) {
// computeIntegerOrderTerms(coefficients, alpha.intValue(), terms, degree);
// } else {
// computeFractionalOrderTerms(coefficients, alpha, terms, degree);
// }
//
// terms.sort(Comparator.comparing(Term::power).reversed());
// return terms;
// }
//
// private void computeIntegerOrderTerms(double[] coefficients, int intAlpha,
// List<Term> terms, int
// degree) {
// }
//
// private void computeFractionalOrderTerms(double[] coefficients, BigDecimal
// alpha, List<Term>
// terms, int degree) {
// }
//
// private int factorial(int n) {
// if (n <= 1)
// return 1;
// return n * factorial(n - 1);
// }
//
// private String formatTerms(List<Term> terms) {
// return null;
// }
// }
