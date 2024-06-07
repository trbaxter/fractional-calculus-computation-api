package com.trbaxter.github.fractionalcomputationapi.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermProcessingService {

    /**
     * Method responsible for collecting information required to perform
     * differentiation/integration operations.
     */

    public void fillOutLists(List<String> terms, List<Double> coefficients,
                              List<Character> variables, List<Double> exponents) {

        for (String term : terms) {
            double coefficient = 1.0;
            char variable = ' ';
            double exponent = 0.0;
            StringBuilder coefficientBuilder = new StringBuilder();
            StringBuilder exponentBuilder = new StringBuilder();
            boolean variableFound = false;
            boolean exponentFound = false;

            for (int i = 0; i < term.length(); i++) {
                char c = term.charAt(i);
                if ((Character.isDigit(c) || c == '.') && !variableFound) {
                    coefficientBuilder.append(c);
                }
                else if (Character.isLetter(c)) {
                    variable = c;

                    if (!exponentFound) {
                        exponentBuilder.append(1.0);
                        exponent = Double.parseDouble(exponentBuilder.toString());
                    }
                    variableFound = true;
                }
                else if (c == '^') {
                    exponentFound = true;
                }
                else if ((c == '+' || c == '-') && exponentFound) {
                    break;
                }
                else if (exponentFound) {
                    exponentBuilder.replace(0, 1, String.valueOf(c));
                }
            }

            // Set coefficients, variables, and exponents
            if (!coefficientBuilder.isEmpty()) {
                coefficient = Double.parseDouble(coefficientBuilder.toString());
            }
            if (variable == ' ') {
                coefficient = 0.0; // Reassign coefficient to 0 if variable is empty, since d/dx(constant) = 0
            }
            if (!exponentBuilder.isEmpty()) {
                exponent = Double.parseDouble(exponentBuilder.toString());
            }

            // Add values to respective lists
            coefficients.add(coefficient);
            variables.add(variable);
            exponents.add(exponent);
        }
    }
}
