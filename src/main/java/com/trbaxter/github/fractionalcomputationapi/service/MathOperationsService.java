package com.trbaxter.github.fractionalcomputationapi.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MathOperationsService {

    public void performCalculations(List<Double> coefficients, List<Double> exponents, double order,
                                    List<Double> newCoefficients, List<Double> newExponents) {
        if (order % 1 == 0) {

            int numOfTerms = coefficients.size();
            int orderIndex = 0;

            while (orderIndex < order) {

                // Running first time?
                if (orderIndex == 0) {
                    for (int i = 0; i < numOfTerms; i++) {
                        double coefficient = coefficients.get(i);
                        double exponent = exponents.get(i);
                        double newExponent = exponent - 1;

                        if (newExponent == 0) {
                            newCoefficients.add(coefficient);
                            newExponents.add(newExponent);
                        }
                        else if (newExponent < 0) {
                            newCoefficients.add(0.0);
                            newExponents.add(0.0);
                        }
                        else {
                            // Calculate new coefficient using recursive gamma function
                            double newCoefficient = coefficient * (gammaFunction((int) (exponent)))
                                    / (gammaFunction((int) (newExponent)));
                            newCoefficients.add(newCoefficient);
                            newExponents.add(newExponent);
                        }
                    }
                }

                // Running again?
                if (orderIndex > 0 & orderIndex < order) {
                    for (int i = 0; i < numOfTerms; i++) {
                        double coefficient = newCoefficients.get(i);
                        double exponent = newExponents.get(i);
                        double newExponent = exponent - 1;

                        if (newExponent == 0) {
                            newCoefficients.set(i, coefficient);
                            newExponents.set(i, newExponent);
                        }
                        else if (newExponent < 0) {
                            newCoefficients.set(i, 0.0);
                            newExponents.set(i, 0.0);
                        }
                        else {
                            // Calculate new coefficient from old coefficient using recursive gamma function
                            double newCoefficient = coefficient * (gammaFunction((int) (exponent)))
                            / (gammaFunction((int) (newExponent)));
                            newCoefficients.set(i, newCoefficient);
                            newExponents.set(i, newExponent);
                        }
                    }
                }

                orderIndex++;
            }
        }

        else {
            throw new IllegalArgumentException("Only integer operations are allowed right now.");
            // TODO: Research on how to expand this to non-integer orders using other techniques.
        }
    }

    private long gammaFunction(int n) {

        if (n == 1) {
            return 1;
        }
        else {
            return n * gammaFunction(n - 1);
        }
    }

    public String reassembleExpandedExpression(List<Double> coefficients,
                                                List<Character> variables,
                                                List<Double> exponents) {
        StringBuilder reassembledExpression = new StringBuilder();
        for (int i = 0; i < coefficients.size(); i++) {
            double coefficient = coefficients.get(i);
            char variable = variables.get(i);
            double exponent = exponents.get(i);

            // Append coefficient if not zero
            if (coefficient != 0.0) {

                if (!reassembledExpression.isEmpty()) {
                    reassembledExpression.append(coefficient >= 0 ? " + " : "");
                }
                if (coefficient % 1 == 0) {
                    int intCoefficientValue = (int) Math.round(coefficient);
                    reassembledExpression.append(intCoefficientValue);
                }
                else {
                    reassembledExpression.append(coefficient);
                }
            }

            // Append variable
            if (variable != ' ' & exponent > 0) {
                reassembledExpression.append(variable);
            }

            // Append exponent if not 0
            if (exponent != 0 && exponent != 1) {
                if (exponent % 1 == 0) {
                    int intExponentValue = (int) Math.round(exponent);
                    reassembledExpression.append("^").append(intExponentValue);
                }
                else {
                    reassembledExpression.append("^").append(exponent);
                }
            }
        }

        if (reassembledExpression.toString().isEmpty()) {
            reassembledExpression = new StringBuilder("0");
        }

        return reassembledExpression.toString();
    }
}
