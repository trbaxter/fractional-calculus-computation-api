package com.trbaxter.github.fractionalcomputationapi.service.parser;

import org.springframework.stereotype.Component;

@Component
public class TermParser {

    public ParsedTerm parseTerm(String term) {
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
            } else if (Character.isLetter(c)) {
                variable = c;
                if (!exponentFound) {
                    exponentBuilder.append(1.0);
                    exponent = Double.parseDouble(exponentBuilder.toString());
                }
                variableFound = true;
            } else if (c == '^') {
                exponentFound = true;
            } else if ((c == '+' || c == '-') && exponentFound) {
                break;
            } else if (exponentFound) {
                exponentBuilder.replace(0,1, String.valueOf(c));
            }
        }

        if (!coefficientBuilder.isEmpty()) {
            coefficient = Double.parseDouble(coefficientBuilder.toString());
        }
        if (variable == ' ') {
            coefficient = 0.0;  // Since d/dx(constant) = 0
        }
        if (!exponentBuilder.isEmpty()) {
            exponent = Double.parseDouble(exponentBuilder.toString());
        }

        return new ParsedTerm(coefficient, variable, exponent);
    }
}
